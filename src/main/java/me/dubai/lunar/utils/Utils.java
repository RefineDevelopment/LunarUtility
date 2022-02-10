package me.dubai.lunar.utils;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import me.dubai.lunar.LunarUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {
    public static String parsePapi(Player player, String text) {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }

    public static String checkLC(Player player) {
        return LunarClientAPI.getInstance().isRunningLunarClient(player) ? Color.translate(LunarUtility.getInstance().getConfig().getString("OTHER.ENABLED")) : Color.translate(LunarUtility.getInstance().getConfig().getString("OTHER.DISABLED"));
    }

    public static boolean isCompatible() {
        return LunarUtility.getInstance().getServer().getVersion().contains("1.7") || LunarUtility.getInstance().getServer().getVersion().contains("1.8");
    }
}