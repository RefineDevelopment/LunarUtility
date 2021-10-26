package me.dubai.lunar.commands;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.dubai.lunar.Locale;
import me.dubai.lunar.utils.Color;
import me.dubai.lunar.utils.ConfigFile;
import me.dubai.lunar.utils.command.BaseCommand;
import me.dubai.lunar.utils.command.Command;
import me.dubai.lunar.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.dubai.lunar.utils.Utils.checkLC;
import static me.dubai.lunar.utils.Utils.parsePapi;

public class LunarCommand extends BaseCommand {

    @Command(name = "lunarclient", aliases = {"lc", "lunar"})
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length == 0) {
            player.sendMessage(parsePapi(player, Locale.LUNAR_COMMAND_PLAYER.messageFormat())
                    .replace("<player>", player.getDisplayName())
                    .replace("<status>", checkLC(player)));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (args.length == 1) {
            if (target == null) {
                player.sendMessage(Locale.PLAYER_NOT_FOUND.messageFormat());
                return;
            }

            player.sendMessage(parsePapi(player, Locale.LUNAR_COMMAND_TARGET.messageFormat())
                    .replace("<target>", target.getName())
                    .replace("<status>", checkLC(target)));
        }
    }

    @Command(name = "lunarclient.reload", permission = "lunar.reload", aliases = {"lc.reload", "lunar.reload"})
    public void onReloadCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        final String message = Locale.LUNAR_COMMAND_RELOAD.messageFormat();

        ConfigFile.getConfig().reload();
        player.sendMessage(parsePapi(player, message));
    }

    @Command(name = "lunarclient.users", aliases = {"lc.users", "lunar.users", "lunarclient.online", "lc.online", "lunar.online", "lc.list", "lunar.list", "lunarclient.list"})
    public void onUsersCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();

        StringBuilder playerSB = new StringBuilder();
        Bukkit.getServer().getOnlinePlayers().stream().filter(all -> LunarClientAPI.getInstance().isRunningLunarClient(all)).forEach(all -> {
            playerSB.append(Color.WHITE).append(all.getDisplayName()).append(Color.GRAY).append(", ");
            ConfigFile.getConfig().getStringList("MESSAGES.LUNAR-USERS-COMMAND.LIST")
                    .forEach(messages -> player.sendMessage(Color.translate(parsePapi(player, messages)
                            .replace("<list>", (playerSB.length() > 1) ? playerSB.substring(0, playerSB.length() - 2) : ""))));
        });
    }
}