package me.dubai.lunar.commands;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.dubai.lunar.Locale;
import me.dubai.lunar.Lunar;
import me.dubai.lunar.utils.command.BaseCommand;
import me.dubai.lunar.utils.command.Command;
import me.dubai.lunar.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LunarStaffCommand extends BaseCommand {

    @Command(name = "lunarstaffmode", permission = "lunarclient.staff", aliases = {"lcstaffmode", "lsm"})
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length == 0) {
            LunarClientAPI.getInstance().giveAllStaffModules(player);
            player.sendMessage(Lunar.getInstance().parsePapi(player, Locale.LUNAR_STAFF_COMMAND_PLAYER.format()));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (args.length == 1) {
            if (target != null) {
                LunarClientAPI.getInstance().giveAllStaffModules(target);
                player.sendMessage(Lunar.getInstance().parsePapi(player, Locale.LUNAR_STAFF_COMMAND_TARGET.format()).replace("<target>", target.getDisplayName()));
                target.sendMessage(Lunar.getInstance().parsePapi(target, Locale.LUNAR_STAFF_COMMAND_TO_TARGET.format()).replace("<player>", player.getDisplayName()));
            } else {
                player.sendMessage(Locale.PLAYER_NOT_FOUND.format());
            }
        }
    }
}