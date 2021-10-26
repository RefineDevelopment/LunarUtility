package me.dubai.lunar.commands;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.dubai.lunar.Locale;
import me.dubai.lunar.utils.command.BaseCommand;
import me.dubai.lunar.utils.command.Command;
import me.dubai.lunar.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.dubai.lunar.utils.Utils.parsePapi;

public class LunarStaffCommand extends BaseCommand {

    @Command(name = "lunarstaffmode", permission = "lunarclient.staff", aliases = {"lcstaffmode", "lsm"})
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length == 0) {
            LunarClientAPI.getInstance().giveAllStaffModules(player);
            player.sendMessage(parsePapi(player, Locale.LUNAR_STAFF_COMMAND_PLAYER.messageFormat()));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (args.length == 1) {
            if (target != null) {
                LunarClientAPI.getInstance().giveAllStaffModules(target);
                player.sendMessage(parsePapi(player, Locale.LUNAR_STAFF_COMMAND_TARGET.messageFormat()).replace("<target>", target.getDisplayName()));
                target.sendMessage(parsePapi(target, Locale.LUNAR_STAFF_COMMAND_TO_TARGET.messageFormat()).replace("<player>", player.getDisplayName()));
            } else {
                player.sendMessage(Locale.PLAYER_NOT_FOUND.messageFormat());
            }
        }
    }
}