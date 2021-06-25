package me.dubai.lunar.commands;

import org.bukkit.Bukkit;
import me.dubai.lunar.utils.*;
import org.bukkit.entity.Player;
import me.dubai.lunar.utils.command.Command;
import com.lunarclient.bukkitapi.LunarClientAPI;
import me.dubai.lunar.utils.command.CommandArgs;
import me.dubai.lunar.utils.command.BaseCommand;

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
            if (target != null) {
                boolean lunarclient = LunarClientAPI.getInstance().isRunningLunarClient(target);
                String lunar = (lunarclient ? (CC.GREEN + "ON") : (CC.RED + "NOT ON"));
                String message = CC.translate(ConfigFile.getConfig().getString("MESSAGES.LUNAR-COMMAND.TARGET"))
                        .replace("<target>", target.getDisplayName())
                        .replace("<status>", lunar);

                player.sendMessage(message);
            } else {
                player.sendMessage(CC.translate(ConfigFile.getConfig().getString("MESSAGES.MAIN.OFFLINE")));
            }
        }
    }

    @Command(name = "lunarclient.reload", aliases = {"lc.reload", "lunar.reload"})
    private void ReloadCommand(CommandArgs command) {
        Player player = command.getPlayer();
        if (!player.hasPermission("lunar.reload") || !player.isOp()) {
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("MESSAGES.MAIN.PERMISSION")));
        } else {
            ConfigFile.getConfig().reload();
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("MESSAGES.MAIN.RELOAD")));
        }
    }
}
//TODO: Make this code cleaner