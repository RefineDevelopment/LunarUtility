package me.dubai.lunar.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Color {

    public static String GREEN = ChatColor.GREEN.toString();
    public static String GRAY = ChatColor.GRAY.toString();
    public static String WHITE = ChatColor.WHITE.toString();

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void onStartMessage() {
        Bukkit.getConsoleSender().sendMessage(translate("&7&m------------------"));
        Bukkit.getConsoleSender().sendMessage(translate("&6LunarUtility &ev1.4"));
        Bukkit.getConsoleSender().sendMessage(translate("&6Developer: &eDubaiGamer"));
        Bukkit.getConsoleSender().sendMessage(translate("&6Status: &aEnabled"));
        Bukkit.getConsoleSender().sendMessage(translate("&7&m------------------"));
    }

    public static void onStopMessage() {
        Bukkit.getConsoleSender().sendMessage(translate("&7&m------------------"));
        Bukkit.getConsoleSender().sendMessage(translate("&6LunarUtility &ev1.4"));
        Bukkit.getConsoleSender().sendMessage(translate("&6Developer: &eDubaiGamer"));
        Bukkit.getConsoleSender().sendMessage(translate("&6Status: &cDisabled"));
        Bukkit.getConsoleSender().sendMessage(translate("&7&m------------------"));
    }
}