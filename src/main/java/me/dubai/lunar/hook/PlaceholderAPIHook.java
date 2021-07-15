package me.dubai.lunar.hook;

import me.dubai.lunar.utils.CC;
import org.bukkit.entity.Player;
import com.lunarclient.bukkitapi.LunarClientAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "lunar";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "Dubai";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        boolean lunarclient = LunarClientAPI.getInstance().isRunningLunarClient(player);

        // %lunar_status%
        if (identifier.equalsIgnoreCase("status")) {
            if (lunarclient) {
                return CC.GREEN + "ON";
            }

            if (!lunarclient) {
                return CC.RED + "OFF";
            }
        }

        return null;
    }
}