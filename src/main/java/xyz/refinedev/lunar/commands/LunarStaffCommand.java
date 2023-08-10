package xyz.refinedev.lunar.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.StaffModule;
import org.bukkit.entity.Player;
import xyz.refinedev.lunar.Locale;
import xyz.refinedev.lunar.utils.Utils;

@CommandAlias(value = "lunarstaffmode|lcstaffmode|lunarstaffmod|lunarstaffmods|lsm")
@CommandPermission(value = "lunar.staff")
public class LunarStaffCommand extends BaseCommand {

    @Default
    @Subcommand(value = "enable|add")
    @Syntax(value = "[Player]")
    @CommandCompletion(value = "@players")
    @Description(value = "Enable a player's lunar mods.")
    public void enableStaffMods(Player player, @Optional OnlinePlayer onlinePlayer) {
        Player target = (onlinePlayer == null ? player : onlinePlayer.getPlayer());

        LunarClientAPI.getInstance().setStaffModuleState(target, StaffModule.XRAY, true);

        if (onlinePlayer == null) {
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_ENABLE_PLAYER.messageFormat()));
        } else {
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_ENABLE_TARGET.messageFormat()).replace("<target>", target.getDisplayName()));
            target.sendMessage(Utils.parsePapi(target, Locale.LUNAR_STAFF_COMMAND_ENABLE_TO_TARGET.messageFormat()).replace("<player>", player.getDisplayName()));
        }
    }

    @Subcommand(value = "disable|remove")
    @Syntax(value = "[Player]")
    @CommandCompletion(value = "@players")
    @Description(value = "Disable a player's lunar mods.")
    public void disableStaffMods(Player player, @Optional OnlinePlayer onlinePlayer) {
        Player target = (onlinePlayer == null ? player : onlinePlayer.getPlayer());

        LunarClientAPI.getInstance().setStaffModuleState(target, StaffModule.XRAY, false);

        if (onlinePlayer == null) {
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_DISABLE_PLAYER.messageFormat()));
        } else {
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_DISABLE_TARGET.messageFormat()).replace("<target>", target.getDisplayName()));
            target.sendMessage(Utils.parsePapi(target, Locale.LUNAR_STAFF_COMMAND_DISABLE_TO_TARGET.messageFormat()).replace("<player>", player.getDisplayName()));
        }
    }
}