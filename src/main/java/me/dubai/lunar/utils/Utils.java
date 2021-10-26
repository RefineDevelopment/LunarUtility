package me.dubai.lunar.utils;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import me.dubai.lunar.Lunar;
import org.bukkit.entity.Player;

public class Utils {

    public static String parsePapi(Player player, String text) {
        return Lunar.papi ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }

    public static String checkLC(Player player) {

        return LunarClientAPI.getInstance().isRunningLunarClient(player) ? Color.translate(ConfigFile.getConfig().getString("OTHER.ENABLED")) : Color.translate(ConfigFile.getConfig().getString("OTHER.DISABLED"));
    }

    public static boolean isCompatible() {
        return Lunar.getInstance().getServer().getVersion().contains("1.7") || Lunar.getInstance().getServer().getVersion().contains("1.8");
    }
}