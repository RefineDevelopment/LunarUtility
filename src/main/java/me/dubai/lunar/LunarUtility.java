package me.dubai.lunar;

import com.jonahseguin.drink.CommandService;
import com.jonahseguin.drink.Drink;
import com.lunarclient.bukkitapi.cooldown.LCCooldown;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
import lombok.Getter;
import me.dubai.lunar.commands.LunarCommand;
import me.dubai.lunar.commands.LunarStaffCommand;
import me.dubai.lunar.hook.PlaceholderAPIHook;
import me.dubai.lunar.listeners.LunarListener;
import me.dubai.lunar.listeners.NametagTask;
import me.dubai.lunar.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


@Getter
public class LunarUtility extends JavaPlugin {

    @Getter
    private static LunarUtility instance;
    private CommandService drink;

    @Override
    public void onEnable() {
        instance = this;
        drink = Drink.get(this);

        this.saveDefaultConfig();
        this.registerLunar();
        this.drink.registerCommands();

        Arrays.asList(
                "&7&m------------------",
                "&6LunarUtility &ev2.0",
                "&6Developer: &eDubaiGamer",
                "&6Status: &aEnabled",
                "&7&m------------------").forEach(s -> getServer().getConsoleSender().sendMessage(Color.translate(s)));

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIHook().register();
            getServer().getConsoleSender().sendMessage(Color.GREEN + "Placeholder API expansion successfully registered.");
        }
    }


    @Override
    public void onDisable() {
        instance = null;
        getServer().getConsoleSender().sendMessage(Color.RED + "LunarUtility 2.0 has been disabled!");
    }

    private void registerLunar() {
        Bukkit.getPluginManager().registerEvents(new LunarListener(this), this);
        drink.register(new LunarCommand(this), "lunarclient", "lunar", "lc");
        drink.register(new LunarStaffCommand(), "lunarstaffmode", "lcstaffmode", "lunarstaffmod", "lunarstaffmods", "lsm");

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
}