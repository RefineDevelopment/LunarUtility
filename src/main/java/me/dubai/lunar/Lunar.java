package me.dubai.lunar;

import com.lunarclient.bukkitapi.cooldown.LCCooldown;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
import com.lunarclient.bukkitapi.nethandler.client.obj.ServerRule;
import com.lunarclient.bukkitapi.serverrule.LunarClientAPIServerRule;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import me.dubai.lunar.commands.LunarCommand;
import me.dubai.lunar.commands.LunarStaffCommand;
import me.dubai.lunar.hook.PlaceholderAPIHook;
import me.dubai.lunar.listeners.LCNametagsListener;
import me.dubai.lunar.listeners.LunarListener;
import me.dubai.lunar.utils.CC;
import me.dubai.lunar.utils.ConfigFile;
import me.dubai.lunar.utils.command.CommandFramework;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;


@Getter
public class Lunar extends JavaPlugin {

    @Getter
    private static Lunar instance;
    private CommandFramework commandFramework;
    private boolean papi = false;

    @Override
    public void onEnable() {
        instance = this;
        commandFramework = new CommandFramework(this);
        saveDefaultConfig();
        registerlunar();
        CC.StartupMessage();
        papi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

        if (papi) {
            new PlaceholderAPIHook().register();
            Bukkit.getConsoleSender().sendMessage(CC.GREEN + "Placeholder API expansion successfully registered.");
        }
    }


    @Override
    public void onDisable() {
        instance = null;
        CC.StopMessage();
    }

    private void registerlunar() {
        int gapple = ConfigFile.getConfig().getInt("COOLDOWN.GAPPLE.DELAY");
        int enderpearl = ConfigFile.getConfig().getInt("COOLDOWN.ENDERPEARL.DELAY");

        commandFramework.registerCommands(new LunarCommand());
        commandFramework.registerCommands(new LunarStaffCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LunarListener(), this);
        pm.registerEvents(new LCNametagsListener(), this);

        if (ConfigFile.getConfig().contains("SERVER-RULES")) {
            for (ServerRule rule : ServerRule.values()) {
                if (ConfigFile.getConfig().contains("SERVER-RULES." + rule.name()) && ConfigFile.getConfig().isBoolean("SERVER-RULES." + rule.name())) {

                    LunarClientAPIServerRule.setRule(rule, ConfigFile.getConfig().getBoolean("SERVER-RULES." + rule.name()));
                }
            }
        }

        if (ConfigFile.getConfig().getBoolean("COOLDOWN.ENDERPEARL.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Enderpearl", enderpearl, TimeUnit.SECONDS, Material.ENDER_PEARL));
        }

        if (ConfigFile.getConfig().getBoolean("COOLDOWN.GAPPLE.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Gapple", gapple, TimeUnit.SECONDS, Material.GOLDEN_APPLE));
        }
    }

    public String parsePapi(Player player, String text) {
        return papi ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }
}