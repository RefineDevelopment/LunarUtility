package me.dubai.lunar.commands;

import org.bukkit.Bukkit;
import me.dubai.lunar.utils.CC;
import org.bukkit.ChatColor;
import me.dubai.lunar.utils.*;
import org.bukkit.entity.Player;
import me.dubai.lunar.utils.command.Command;
import com.lunarclient.bukkitapi.LunarClientAPI;
import me.dubai.lunar.utils.command.CommandArgs;
import me.dubai.lunar.utils.command.BaseCommand;

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
            String lunar = (lunarclient ? (CC.GREEN + "ON") : (CC.RED + "NOT ON"));
            String message = CC.translate(ConfigFile.getConfig().getString("MESSAGES.LUNAR-COMMAND.PLAYER"))
                    .replace("<player>", player.getDisplayName())
                    .replace("<status>", lunar);

            player.sendMessage(message);
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
                player.sendMessage(CC.translate(ConfigFile.getConfig().getString("MESSAGES.MAIN.OFFLINE")));
            }

            boolean lunarclient = LunarClientAPI.getInstance().isRunningLunarClient(target);
            String lunar = lunarclient ? (CC.GREEN + "ON") : (CC.RED + "NOT ON");
            String message = CC.translate(ConfigFile.getConfig().getString("MESSAGES.LUNAR-COMMAND.TARGET"))
                    .replace("<target>", target.getName())
                    .replace("<status>", lunar);

            player.sendMessage(message);
        }
    }

    @Command(name = "lunarclient.reload", aliases = {"lc.reload", "lunar.reload"})
    public void reload(CommandArgs command) {
        Player player = command.getPlayer();
        if (!player.hasPermission("lunar.reload") || !player.isOp()) {
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("MESSAGES.MAIN.PERMISSION")));
        } else {
            ConfigFile.getConfig().reload();
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("MESSAGES.MAIN.RELOAD")));
        }
    }
}
//TODO: Make this code cleaner because its looking so damn bad