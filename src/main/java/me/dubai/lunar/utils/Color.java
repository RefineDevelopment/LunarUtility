package me.dubai.lunar.utils;

import org.bukkit.ChatColor;

public class Color {

    public static String GREEN = ChatColor.GREEN.toString();
    public static String RED = ChatColor.RED.toString();

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}