package me.dubai.lunar.commands;

import me.dubai.lunar.Lunar;
import org.bukkit.Bukkit;
import me.dubai.lunar.Locale;
import org.bukkit.entity.Player;
import me.dubai.lunar.utils.command.Command;
import me.clip.placeholderapi.PlaceholderAPI;
import me.dubai.lunar.utils.command.BaseCommand;
import me.dubai.lunar.utils.command.CommandArgs;
import com.lunarclient.bukkitapi.LunarClientAPI;

public class LunarStaffCommand extends BaseCommand {
    @Command(name = "lunarstaffmode", permission = "lunarclient.staff", aliases = {"lcstaffmode", "lsm"})

    @Override
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();
        String message = Locale.LUNAR_STAFF_COMMAND_PLAYER.format();
        String text = Lunar.getInstance().papi() ? PlaceholderAPI.setPlaceholders(player, message) : message;

        if (args.length == 0) {
            LunarClientAPI.getInstance().giveAllStaffModules(player);
            player.sendMessage(text);
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        String targetmessage = Locale.LUNAR_STAFF_COMMAND_TARGET.format();
        String targettext = Lunar.getInstance().papi() ? PlaceholderAPI.setPlaceholders(player, targetmessage) : targetmessage;

        String totargetmessage = Locale.LUNAR_STAFF_COMMAND_TO_TARGET.format();
        String totargettext = Lunar.getInstance().papi() ? PlaceholderAPI.setPlaceholders(player, totargetmessage) : totargetmessage;

        if (args.length == 1) {
            if (target != null) {
                LunarClientAPI.getInstance().giveAllStaffModules(target);

                player.sendMessage(targettext.replace("<target>", target.getDisplayName()));

                target.sendMessage(totargettext.replace("<player>", player.getDisplayName()));
            } else {
                player.sendMessage(Locale.PLAYER_NOT_FOUND.format());
            }
        }
    }
}