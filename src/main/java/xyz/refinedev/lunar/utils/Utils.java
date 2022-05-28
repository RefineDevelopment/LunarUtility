package xyz.refinedev.lunar.utils;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.refinedev.lunar.LunarUtility;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getModList() {
        List<String> modList = new ArrayList<>();

        modList.add("one_seven_visuals");
        modList.add("fps");
        modList.add("cps");
        modList.add("skyblockAddons");
        modList.add("toggleSneak");
        modList.add("hypixel_mod");
        modList.add("armorstatus");
        modList.add("keystrokes");
        modList.add("coords");
        modList.add("crosshair");
        modList.add("potioneffects");
        modList.add("directionhud");
        modList.add("waypoints");
        modList.add("scoreboard");
        modList.add("potion_counter");
        modList.add("ping");
        modList.add("motionBlur");
        modList.add("chat");
        modList.add("scrollable_tooltips");
        modList.add("uhc_overlay");
        modList.add("cooldowns");
        modList.add("worldedit_cui");
        modList.add("clock");
        modList.add("stopwatch");
        modList.add("memory");
        modList.add("combo");
        modList.add("range");
        modList.add("time_changer");
        modList.add("serverAddressMod");
        modList.add("saturation");
        modList.add("itemPhysic");
        modList.add("itemTrackerChild");
        modList.add("shinyPots");
        modList.add("block_outline");
        modList.add("screenshot");
        modList.add("fov_mod");
        modList.add("textHotKey");
        modList.add("mumble-link");
        modList.add("bossbar");
        modList.add("freelook");
        modList.add("nickHider");
        modList.add("pack_organizer");
        modList.add("hypixel_bedwars");
        modList.add("particleMod");
        modList.add("glint_colorizer");
        modList.add("snaplook");
        modList.add("teamview");
        modList.add("menu_blur");
        modList.add("hitbox");
        modList.add("hitcolor");
        modList.add("weather_changer");
        modList.add("lighting");
        modList.add("zoom");

        return modList;
    }
}