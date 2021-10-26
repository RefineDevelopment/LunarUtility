package me.dubai.lunar;

import com.lunarclient.bukkitapi.cooldown.LCCooldown;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
import lombok.Getter;
import me.dubai.lunar.commands.LunarCommand;
import me.dubai.lunar.commands.LunarStaffCommand;
import me.dubai.lunar.hook.PlaceholderAPIHook;
import me.dubai.lunar.listeners.LunarListener;
import me.dubai.lunar.listeners.NametagTask;
import me.dubai.lunar.utils.Color;
import me.dubai.lunar.utils.ConfigFile;
import me.dubai.lunar.utils.command.CommandFramework;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;


@Getter
public class Lunar extends JavaPlugin {

    @Getter
    private static Lunar instance;
    public static boolean papi = false;
    private CommandFramework commandFramework;

    @Override
    public void onEnable() {
        instance = this;
        commandFramework = new CommandFramework(this);

        this.saveDefaultConfig();
        this.registerLunar();
        Color.onStartMessage();
        papi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

        if (papi) {
            new PlaceholderAPIHook().register();
            Bukkit.getConsoleSender().sendMessage(Color.GREEN + "Placeholder API expansion successfully registered.");
        }
    }


    @Override
    public void onDisable() {
        instance = null;
        Color.onStopMessage();
    }

    private void registerLunar() {
        commandFramework.registerCommands(new LunarCommand());
        commandFramework.registerCommands(new LunarStaffCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LunarListener(), this);

        //This is normal because it's the nametag updating. If there is any issue, I'll update it
        this.getServer().getScheduler().runTaskTimer(this, new NametagTask(), 0, 20);

        if (ConfigFile.getConfig().getBoolean("NAMETAG.ENABLE")) {
            new NametagTask();
        }

        if (ConfigFile.getConfig().getBoolean("COOLDOWN.ENDERPEARL.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Enderpearl", ConfigFile.getConfig().getInt("COOLDOWN.ENDERPEARL.DELAY"), TimeUnit.SECONDS, Material.ENDER_PEARL));
        }


        if (ConfigFile.getConfig().getBoolean("COOLDOWN.GAPPLE.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Gapple", ConfigFile.getConfig().getInt("COOLDOWN.GAPPLE.DELAY"), TimeUnit.SECONDS, Material.GOLDEN_APPLE));
        }
    }
}