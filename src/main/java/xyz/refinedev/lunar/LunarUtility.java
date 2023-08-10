package xyz.refinedev.lunar;

import co.aikar.commands.PaperCommandManager;
import com.google.gson.JsonParser;
import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.cooldown.LCCooldown;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
import com.lunarclient.bukkitapi.nethandler.client.LCPacketModSettings;
import com.lunarclient.bukkitapi.nethandler.client.obj.ModSettings;
import com.lunarclient.bukkitapi.nethandler.client.obj.ServerRule;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import com.lunarclient.bukkitapi.serverrule.LunarClientAPIServerRule;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.lunar.commands.LunarCommand;
import xyz.refinedev.lunar.commands.LunarStaffCommand;
import xyz.refinedev.lunar.hook.PlaceholderAPIHook;
import xyz.refinedev.lunar.listeners.LunarListener;
import xyz.refinedev.lunar.listeners.NametagTask;
import xyz.refinedev.lunar.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Getter
public class LunarUtility extends JavaPlugin {

    @Getter
    private static LunarUtility instance;

    private PaperCommandManager commandManager;
    private LCPacketModSettings packetModSettings = null;
    private final List<LCWaypoint> waypoints = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        this.commandManager = new PaperCommandManager(this);

        this.commandManager.registerCommand(new LunarCommand(this));
        this.commandManager.registerCommand(new LunarStaffCommand());

        this.saveDefaultConfig();
        this.registerLunar();
        this.loadDisabledMods();
        this.loadWaypoints();

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "LunarUtility 2.0 has been enabled!");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIHook().register();
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Placeholder API expansion successfully registered.");
        }
    }


    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "LunarUtility 2.0 has been disabled!");
    }

    private void registerLunar() {
        Bukkit.getPluginManager().registerEvents(new LunarListener(this), this);

        if (getConfig().getBoolean("NAMETAG.ENABLE")) {
            this.getServer().getScheduler().runTaskTimerAsynchronously(this, new NametagTask(this), 0, Long.parseLong(Locale.LUNAR_TAG_TICKS.messageFormat()));
        }

        if (getConfig().getBoolean("COOLDOWN.COMBAT.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Combat", getConfig().getInt("COOLDOWN.COMBAT.DELAY"), TimeUnit.SECONDS, Material.getMaterial(getConfig().getString("COOLDOWN.COMBAT.ICON"))));
        }

        if (getConfig().getBoolean("COOLDOWN.ENDERPEARL.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Enderpearl", getConfig().getInt("COOLDOWN.ENDERPEARL.DELAY"), TimeUnit.SECONDS, Material.getMaterial(getConfig().getString("COOLDOWN.ENDERPEARL.ICON"))));
        }

        if (getConfig().getBoolean("COOLDOWN.GAPPLE.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Gapple", getConfig().getInt("COOLDOWN.GAPPLE.DELAY"), TimeUnit.SECONDS, Material.getMaterial(getConfig().getString("COOLDOWN.GAPPLE.ICON"))));
        }
    }

    public void loadWaypoints() {
        if (!getConfig().getBoolean("WAYPOINT.ENABLED")) return;

        LunarClientAPIServerRule.setRule(ServerRule.SERVER_HANDLES_WAYPOINTS, true);

        //Original code credit to BukkitImpl
        List<Map<?, ?>> maps = getConfig().getMapList("WAYPOINT.WAYPOINTS");

        maps.stream()
                .flatMap(map -> map.entrySet().stream())
                .map(entry -> new JsonParser().parse(String.valueOf(entry.getValue())).getAsJsonObject())
                .map(object -> new LCWaypoint(object.get("name").getAsString(), object.get("x").getAsInt(), object.get("y").getAsInt(), object.get("z").getAsInt(), LunarClientAPI.getInstance().getWorldIdentifier(Bukkit.getWorld(object.get("world").getAsString())), object.get("color").getAsInt(), object.get("forced").getAsBoolean(), object.get("visible").getAsBoolean()))
                .forEachOrdered(waypoints::add);
    }

    private void loadDisabledMods() {
        ModSettings modSettings = new ModSettings();

        if (getConfig().getBoolean("MODS.DISABLE-ALL-MODS")) {
            Utils.getModList().forEach(modName -> modSettings.addModSetting(modName, new ModSettings.ModSetting(false, new HashMap<>())));

            packetModSettings = new LCPacketModSettings(modSettings);
            return;
        }

        if (getConfig().getBoolean("MODS.DISABLE-MODS.ENABLED")) {
            getConfig().getStringList("MODS.DISABLE-MODS.MODS")
                    .forEach(modName -> modSettings.addModSetting(modName, new ModSettings.ModSetting(false, new HashMap<>())));

            packetModSettings = new LCPacketModSettings(modSettings);
        }
    }
}