package me.dubai.lunar.utils;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import me.dubai.lunar.LunarUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
    public static String parsePapi(Player player, String text) {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }

    public static String checkLC(Player player) {
        return LunarClientAPI.getInstance().isRunningLunarClient(player) ? translate(LunarUtility.getInstance().getConfig().getString("OTHER.ENABLED")) : translate(LunarUtility.getInstance().getConfig().getString("OTHER.DISABLED"));
    }

    public static boolean isCompatible() {
        return Bukkit.getServer().getVersion().contains("1.7") || Bukkit.getServer().getVersion().contains("1.8");
    }

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}