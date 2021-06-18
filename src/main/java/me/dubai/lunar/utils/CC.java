package me.dubai.lunar.utils;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CC {
    private static final Map<String, ChatColor> MAP = new HashMap<>();

    public static String AQUA = ChatColor.AQUA.toString();
    public static String BLACK = ChatColor.BLACK.toString();
    public static String BLUE = ChatColor.BLUE.toString();
    public static String GOLD = ChatColor.GOLD.toString();
    public static String GRAY = ChatColor.GRAY.toString();
    public static String GREEN = ChatColor.GREEN.toString();
    public static String PINK = ChatColor.LIGHT_PURPLE.toString();
    public static String RED = ChatColor.RED.toString();
    public static String WHITE = ChatColor.WHITE.toString();
    public static String YELLOW = ChatColor.YELLOW.toString();

    public static String D_AQUA = ChatColor.DARK_AQUA.toString();
    public static String D_BLUE = ChatColor.DARK_BLUE.toString();
    public static String D_GRAY = ChatColor.DARK_GRAY.toString();
    public static String D_GREEN = ChatColor.DARK_GREEN.toString();
    public static String D_PURPLE = ChatColor.DARK_PURPLE.toString();
    public static String D_RED = ChatColor.DARK_RED.toString();

    public static String BOLD = ChatColor.BOLD.toString();
    public static String STRIKETHGOUGH = ChatColor.STRIKETHROUGH.toString();
    public static String RESET = ChatColor.RESET.toString();
    public static String RANDOM = ChatColor.MAGIC.toString();
    public static String UNDERLINE = ChatColor.UNDERLINE.toString();
    public static String ITALIC = ChatColor.ITALIC.toString();

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String out(String out) {
        Bukkit.getConsoleSender().sendMessage(translate(out));
        return out;
    }
}
