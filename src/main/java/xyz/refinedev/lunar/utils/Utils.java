package xyz.refinedev.lunar.utils;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.refinedev.lunar.LunarUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> translate(List<String> text) {
        return text.stream().map(string -> ChatColor.translateAlternateColorCodes('&', string)).collect(Collectors.toList());
    }

    public static List<String> getModList() {
        // There is no other way than to list them manually because LunarClientAPI doesn't provide a list of their mod ids
        // There are newer mods, but I'm busy so push pr if you find them

        return new ArrayList<>(Arrays.asList(
                "one_seven_visuals", "fps", "cps", "skyblockAddons", "toggleSneak",
                "hypixel_mod", "armorstatus", "keystrokes", "coords", "crosshair",
                "potioneffects", "directionhud", "waypoints", "scoreboard", "potion_counter",
                "ping", "motionBlur", "chat", "scrollable_tooltips", "uhc_overlay",
                "cooldowns", "worldedit_cui", "clock", "stopwatch", "memory",
                "combo", "range", "time_changer", "serverAddressMod", "saturation",
                "itemPhysic", "itemTrackerChild", "shinyPots", "block_outline", "screenshot",
                "fov_mod", "textHotKey", "mumble-link", "bossbar", "freelook",
                "nickHider", "pack_organizer", "hypixel_bedwars", "particleMod", "glint_colorizer",
                "snaplook", "teamview", "menu_blur", "hitbox", "hitcolor",
                "weather_changer", "lighting", "zoom"
        ));
    }
}