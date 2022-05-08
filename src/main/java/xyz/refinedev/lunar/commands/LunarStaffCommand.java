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
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_PLAYER.messageFormat()));
        } else {
            LunarClientAPI.getInstance().giveAllStaffModules(target);
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_TARGET.messageFormat()).replace("<target>", target.getDisplayName()));
            target.sendMessage(Utils.parsePapi(target, Locale.LUNAR_STAFF_COMMAND_TO_TARGET.messageFormat()).replace("<player>", player.getDisplayName()));
        }
    }
}