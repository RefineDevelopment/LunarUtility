package me.dubai.lunar.utils;

import com.lunarclient.bukkitapi.LunarClientAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CC {

    public static String GREEN = ChatColor.GREEN.toString();
    public static String RED = ChatColor.RED.toString();
    public static String GRAY = ChatColor.GRAY.toString();
    public static String WHITE = ChatColor.WHITE.toString();

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void out(String out) {
        Bukkit.getConsoleSender().sendMessage(translate(out));
    }

    public static String CheckLC(Player player) {

        return LunarClientAPI.getInstance().isRunningLunarClient(player) ? CC.translate(ConfigFile.getConfig().getString("OTHER.ENABLED")) : CC.translate(ConfigFile.getConfig().getString("OTHER.DISABLED"));
    }

    public static void StartupMessage() {
        out("&7&m------------------");
        out("&6LunarUtility &ev1.4");
        out("&6Developer: &eDubaiGamer");
        out("&6Status: &aEnabled");
        out("&7&m------------------");
    }

    public static void StopMessage() {
        out("&7&m------------------");
        out("&6LunarUtility &ev1.4");
        out("&6Developer: &eDubaiGamer");
        out("&6Status: &cDisabled");
        out("&7&m------------------");
    }
}