package me.dubai.lunar.commands;

import org.bukkit.Bukkit;
import me.dubai.lunar.Locale;
import org.bukkit.entity.Player;
import me.dubai.lunar.utils.command.Command;
import me.dubai.lunar.utils.command.BaseCommand;
import me.dubai.lunar.utils.command.CommandArgs;
import com.lunarclient.bukkitapi.LunarClientAPI;

public class LunarStaffCommand extends BaseCommand {
    @Command(name = "lunarstaffmode", permission = "lunarclient.staff", aliases = {"lcstaffmode", "lsm"})

    @Override
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length == 0) {
            LunarClientAPI.getInstance().giveAllStaffModules(player);
            player.sendMessage(Locale.LUNAR_STAFF_COMMAND_PLAYER.format());
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (args.length == 1) {
            if (target != null) {
                LunarClientAPI.getInstance().giveAllStaffModules(target);

                player.sendMessage(Locale.LUNAR_STAFF_COMMAND_TARGET.format()
                        .replace("<target>", target.getDisplayName()));

                target.sendMessage(Locale.LUNAR_STAFF_COMMAND_TO_TARGET.format()
                        .replace("<player>", player.getDisplayName()));
            } else {
                player.sendMessage(Locale.PLAYER_NOT_FOUND.format());
            }
        }
    }
}