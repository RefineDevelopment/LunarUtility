package xyz.refinedev.lunar;

import com.lunarclient.bukkitapi.cooldown.LCCooldown;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
import com.lunarclient.bukkitapi.nethandler.client.LCPacketModSettings;
import com.lunarclient.bukkitapi.nethandler.client.obj.ModSettings;
import lombok.Getter;
import me.vaperion.blade.Blade;
import me.vaperion.blade.bindings.impl.BukkitBindings;
import me.vaperion.blade.container.impl.BukkitCommandContainer;
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

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


@Getter
public class LunarUtility extends JavaPlugin {

    @Getter
    private static LunarUtility instance;
    private LCPacketModSettings packetModSettings = null;

    @Override
    public void onEnable() {
        instance = this;

        Blade.of()
                .overrideCommands(true)
                .fallbackPrefix("lunarutility")
                .binding(new BukkitBindings())
                .containerCreator(BukkitCommandContainer.CREATOR)
                .defaultPermissionMessage(ChatColor.RED + "No permission.")
                .build()
                .register(new LunarStaffCommand())
                .register(new LunarCommand(this));

        this.saveDefaultConfig();
        this.registerLunar();
        this.loadDisabledMods();

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
            this.getServer().getScheduler().runTaskTimer(this, new NametagTask(this), 0, Long.parseLong(Locale.LUNAR_TAG_TICKS.messageFormat()));
        }

        if (getConfig().getBoolean("COOLDOWN.ENDERPEARL.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Enderpearl", getConfig().getInt("COOLDOWN.ENDERPEARL.DELAY"), TimeUnit.SECONDS, Material.ENDER_PEARL));
        }


        if (getConfig().getBoolean("COOLDOWN.GAPPLE.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Gapple", getConfig().getInt("COOLDOWN.GAPPLE.DELAY"), TimeUnit.SECONDS, Material.GOLDEN_APPLE));
        }
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