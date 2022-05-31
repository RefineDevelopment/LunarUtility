package xyz.refinedev.lunar.commands;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.vaperion.blade.annotation.*;
import org.bukkit.entity.Player;
import xyz.refinedev.lunar.Locale;
import xyz.refinedev.lunar.utils.Utils;

public class LunarStaffCommand {

    @Command(value = {"lunarstaffmode", "lcstaffmode", "lunarstaffmod", "lunarstaffmods", "lsm"})
    @Permission(value = "lunar.staff")
    public void onLunarStaffModeCommand(@Sender Player player, @Optional @Name("target") Player target) {
        if (target == null) {
            LunarClientAPI.getInstance().giveAllStaffModules(player);
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_ENABLE_PLAYER.messageFormat()));
        } else {
            LunarClientAPI.getInstance().giveAllStaffModules(target);
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_ENABLE_TARGET.messageFormat()).replace("<target>", target.getDisplayName()));
            target.sendMessage(Utils.parsePapi(target, Locale.LUNAR_STAFF_COMMAND_ENABLE_TO_TARGET.messageFormat()).replace("<player>", player.getDisplayName()));
        }
    }

    @Command(value = {"lunarstaffmode disable", "lcstaffmode disable", "lunarstaffmod disable", "lunarstaffmods disable", "lsm disable", "lsm remove"})
    @Permission(value = "lunar.staff")
    public void onLunarStaffModeRemoveCommand(@Sender Player player, @Optional @Name("target") Player target) {

        if (target == null) {
            LunarClientAPI.getInstance().disableAllStaffModules(player);
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_DISABLE_PLAYER.messageFormat()));
        } else {
            LunarClientAPI.getInstance().disableAllStaffModules(target);
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_DISABLE_TARGET.messageFormat()).replace("<target>", target.getDisplayName()));
            target.sendMessage(Utils.parsePapi(target, Locale.LUNAR_STAFF_COMMAND_DISABLE_TO_TARGET.messageFormat()).replace("<player>", player.getDisplayName()));
        }
    }
}