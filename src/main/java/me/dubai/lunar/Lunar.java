package me.dubai.lunar;

import lombok.Getter;
import org.bukkit.*;
import org.bukkit.plugin.*;
import me.dubai.lunar.utils.*;
import me.dubai.lunar.commands.*;
import me.dubai.lunar.listeners.*;
import java.util.concurrent.TimeUnit;
import org.bukkit.plugin.java.JavaPlugin;
import me.dubai.lunar.utils.command.CommandFramework;
import com.lunarclient.bukkitapi.cooldown.LCCooldown;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;

@Getter
public class Lunar extends JavaPlugin {

    @Getter
    public static Lunar instance;
    private CommandFramework commandFramework;

    @Override
    public void onEnable() {
        instance = this;
        commandFramework = new CommandFramework(this);
        saveDefaultConfig();
        saveConfig();
        reloadConfig();
        registerlunar();
        long start = System.currentTimeMillis();
        CC.out("LunarUtilities enabled in " + (System.currentTimeMillis() - start) + "ms");
        CC.out("Coded by DubaiGamer");
    }

    @Override
    public void onDisable() {
        instance = null;
        CC.out("LunarUtility has been disabled.");
        CC.out("Coded by DubaiGamer");
    }

    public void registerlunar() {
        int gapple = ConfigFile.getConfig().getInt("COOLDOWN.ENDERPEARL.DELAY");
        int enderpearl = ConfigFile.getConfig().getInt("COOLDOWN.GAPPLE.DELAY");

        commandFramework.registerCommands(new LunarCommand());
        LunarClientAPICooldown.registerCooldown(new LCCooldown("Enderpearl", gapple, TimeUnit.SECONDS, Material.ENDER_PEARL));
        LunarClientAPICooldown.registerCooldown(new LCCooldown("Gapple", enderpearl, TimeUnit.SECONDS, Material.GOLDEN_APPLE));

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LunarListener(), this);
    }
}