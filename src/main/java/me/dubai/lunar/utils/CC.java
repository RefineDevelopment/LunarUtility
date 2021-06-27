package me.dubai.lunar.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CC {

    public static String GREEN = ChatColor.GREEN.toString();
    public static String RED = ChatColor.RED.toString();

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String out(String out) {
        Bukkit.getConsoleSender().sendMessage(translate(out));
        return out;
    }

    public static void StartupMessage() {
        out("&7&m------------------");
        out("&6LunarUtility &ev1.1.2");
        out("&6Developer: &eDubaiGamer");
        out("&6Status: &aEnabled");
        out("&7&m------------------");
    }

    public static void StopMessage() {
        out("&7&m------------------");
        out("&6LunarUtility &ev1.1.2");
        out("&6Developer: &eDubaiGamer");
        out("&6Status: &cDisabled");
        out("&7&m------------------");
    }
}