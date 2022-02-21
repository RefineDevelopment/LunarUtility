package xyz.refinedev.lunar.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import xyz.refinedev.lunar.utils.Utils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "lunar";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return "RefineDevelopment";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        // %lunar_status%
        if (identifier.equalsIgnoreCase("status")) {
            return Utils.checkLC(player);
        }

        return null;
    }
}