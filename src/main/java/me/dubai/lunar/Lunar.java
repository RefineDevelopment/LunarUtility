package me.dubai.lunar;

import com.lunarclient.bukkitapi.cooldown.LCCooldown;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
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

        this.saveDefaultConfig();
        this.registerLunar();
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

    private void registerLunar() {
        commandFramework.registerCommands(new LunarCommand());
        commandFramework.registerCommands(new LunarStaffCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LunarListener(), this);

        if (ConfigFile.getConfig().getBoolean("NAMETAG.ENABLE")) {
            pm.registerEvents(new LCNametagsListener(), this);
        }

        if (ConfigFile.getConfig().getBoolean("COOLDOWN.ENDERPEARL.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Enderpearl", ConfigFile.getConfig().getInt("COOLDOWN.ENDERPEARL.DELAY"), TimeUnit.SECONDS, Material.ENDER_PEARL));
        }


        if (ConfigFile.getConfig().getBoolean("COOLDOWN.GAPPLE.ENABLE")) {
            LunarClientAPICooldown.registerCooldown(new LCCooldown("Gapple", ConfigFile.getConfig().getInt("COOLDOWN.GAPPLE.DELAY"), TimeUnit.SECONDS, Material.GOLDEN_APPLE));
        }
    }

    public String parsePapi(Player player, String text) {
        return papi ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }
}