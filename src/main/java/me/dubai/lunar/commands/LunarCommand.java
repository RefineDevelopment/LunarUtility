package me.dubai.lunar.commands;

import me.dubai.lunar.Lunar;
import org.bukkit.Bukkit;
import me.dubai.lunar.Locale;
import me.dubai.lunar.utils.*;
import org.bukkit.entity.Player;
import me.dubai.lunar.utils.command.Command;
import me.clip.placeholderapi.PlaceholderAPI;
import com.lunarclient.bukkitapi.LunarClientAPI;
import me.dubai.lunar.utils.command.CommandArgs;
import me.dubai.lunar.utils.command.BaseCommand;

public class LunarCommand extends BaseCommand {

    @Command(name = "lunarclient", aliases = {"lc", "lunar"})
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length == 0) {
            boolean lunarclient = LunarClientAPI.getInstance().isRunningLunarClient(player);
            String lunar = (lunarclient ? (CC.GREEN + "ON") : (CC.RED + "NOT ON"));
            String message = Locale.LUNAR_COMMAND_PLAYER.format();
            String text = Lunar.getInstance().papi() ? PlaceholderAPI.setPlaceholders(player, message) : message;

            player.sendMessage(text.replace("<player>", player.getDisplayName()).replace("<status>", lunar));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (args.length == 1) {
            if (target == null) {
                player.sendMessage(Locale.PLAYER_NOT_FOUND.format());
                return;
            }

            boolean lunarclient = LunarClientAPI.getInstance().isRunningLunarClient(target);
            String lunar = lunarclient ? (CC.GREEN + "ON") : (CC.RED + "NOT ON");
            String message = Locale.LUNAR_COMMAND_TARGET.format();
            String text = Lunar.getInstance().papi() ? PlaceholderAPI.setPlaceholders(player, message) : message;

            player.sendMessage(text.replace("<target>", target.getName()).replace("<status>", lunar));
        }
    }

    @Command(name = "lunarclient.reload", permission = "lunar.reload", aliases = {"lc.reload", "lunar.reload"})
    public void reload(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String message = Locale.LUNAR_COMMAND_RELOAD.format();
        String text = Lunar.getInstance().papi() ? PlaceholderAPI.setPlaceholders(player, message) : message;

        ConfigFile.getConfig().reload();
        player.sendMessage(text);
    }

    @Command(name = "lunarclient.users", aliases = {"lc.users", "lunar.users", "lunarclient.online", "lc.online", "lunar.online", "lc.list", "lunar.list", "lunarclient.list"})
    public void users(CommandArgs cmd) {
        Player player = cmd.getPlayer();

        (new Thread(() -> {
            StringBuilder playerSB = new StringBuilder();
            for (Player all : Bukkit.getServer().getOnlinePlayers())
                if (LunarClientAPI.getInstance().isRunningLunarClient(all))
                    playerSB.append(CC.WHITE).append(all.getDisplayName()).append(CC.GRAY).append(", ");
            for (String messages : ConfigFile.getConfig().getStringList("MESSAGES.LUNAR-USERS-COMMAND.LIST"))
                player.sendMessage(CC.translate(messages.replace("<list>", (playerSB.toString().length() > 1) ? playerSB.substring(0, playerSB.toString().length() - 2) : "")));
        })).start();
    }
}