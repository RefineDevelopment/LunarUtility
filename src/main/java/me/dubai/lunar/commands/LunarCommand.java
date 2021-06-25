package me.dubai.lunar.commands;

import org.bukkit.Bukkit;
import me.dubai.lunar.utils.CC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.dubai.lunar.utils.command.*;
import com.lunarclient.bukkitapi.LunarClientAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LunarCommand extends BaseCommand {
    @Command(name = "lunarclient", aliases = {"lc", "lunar"})

    @Override
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length == 0) {
            boolean lunarclient = LunarClientAPI.getInstance().isRunningLunarClient(player);
            player.sendMessage(CC.GOLD + "You are currently " + (lunarclient ? (CC.GREEN + "ON") : (CC.RED + "NOT ON")) + CC.YELLOW + " Lunar Client.");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("users")) {
                Set<Player> users = LunarClientAPI.getInstance().getPlayersRunningLunarClient();
                StringBuilder builder = new StringBuilder();

                if (users.size() == 0) {
                    player.sendMessage(CC.translate("&cThere are no players using Lunar Client!"));
                    return;
                } else if (users.size() == 1) {
                    Player user = (Player) users.iterator();

                    player.sendMessage(CC.translate("&7&o-------------------------------------------"));
                    player.sendMessage(CC.translate("&6Players using Lunar Client:"));
                    player.sendMessage(CC.WHITE + user.getName());
                    player.sendMessage(CC.translate("&7&o-------------------------------------------"));
                    return;
                }

                for (Player player1 : users) {
                    if (builder.length() > 0) {
                        builder.append(CC.D_GRAY).append(", ");
                    }

                    builder.append(CC.WHITE).append(player1.getName());
                }
                player.sendMessage(CC.translate("&7&o-------------------------------------------"));
                player.sendMessage(CC.translate("&6Players using Lunar Client:"));
                player.sendMessage(builder.toString());
                player.sendMessage(CC.translate("&7&o-------------------------------------------"));
                return;
            }
            if (target != null) {
                boolean lunarclient = LunarClientAPI.getInstance().isRunningLunarClient(target);
                player.sendMessage(CC.GOLD + target.getName() + CC.YELLOW + " is currently " + (lunarclient ? (CC.GREEN + "ON") : (CC.RED + "NOT ON")) + CC.YELLOW + " Lunar Client.");
            } else {
                player.sendMessage(CC.RED + ("That player doesn't exist."));
            }
        }
    }
}