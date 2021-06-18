package me.dubai.lunar.commands;

import org.bukkit.Bukkit;
import me.dubai.lunar.utils.CC;
import org.bukkit.entity.Player;
import me.dubai.lunar.utils.command.*;
import com.lunarclient.bukkitapi.LunarClientAPI;

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
            if (target != null) {
                boolean lunarclient = LunarClientAPI.getInstance().isRunningLunarClient(target);
                player.sendMessage(CC.GOLD + target.getName() + CC.YELLOW + " is currently " + (lunarclient ? (CC.GREEN + "ON") : (CC.RED + "NOT ON")) + CC.YELLOW + " Lunar Client.");
            } else {
                player.sendMessage(CC.RED + ("That player doesn't exist."));
            }
        }
    }
}