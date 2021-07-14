package me.dubai.lunar;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import me.dubai.lunar.utils.*;
import org.bukkit.entity.Player;
import me.dubai.lunar.commands.*;
import me.dubai.lunar.listeners.*;
import java.util.concurrent.TimeUnit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
import me.dubai.lunar.utils.command.CommandFramework;
import com.lunarclient.bukkitapi.cooldown.LCCooldown;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;


@Getter
public class Lunar extends JavaPlugin {

    @Getter
    public static Lunar instance;
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
    }


    @Override
    public void onDisable() {
        instance = null;
        CC.StopMessage();
    }

    public String parsePapi(Player player, String text) {
        return papi ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }

    private void registerlunar() {
        int gapple = ConfigFile.getConfig().getInt("COOLDOWN.GAPPLE.DELAY");
        int enderpearl = ConfigFile.getConfig().getInt("COOLDOWN.ENDERPEARL.DELAY");

        commandFramework.registerCommands(new LunarCommand());
        commandFramework.registerCommands(new LunarStaffCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LunarListener(), this);
        pm.registerEvents(new LCNametagsListener(), this);

        if (ConfigFile.getConfig().getBoolean("COOLDOWN.ENDERPEARL.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Enderpearl", enderpearl, TimeUnit.SECONDS, Material.ENDER_PEARL));
        }

        if (ConfigFile.getConfig().getBoolean("COOLDOWN.GAPPLE.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Gapple", gapple, TimeUnit.SECONDS, Material.GOLDEN_APPLE));
        }
    }
}