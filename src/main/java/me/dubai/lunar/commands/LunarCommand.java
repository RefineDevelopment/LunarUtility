package me.dubai.lunar.commands;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.dubai.lunar.Locale;
import me.dubai.lunar.Lunar;
import me.dubai.lunar.utils.CC;
import me.dubai.lunar.utils.ConfigFile;
import me.dubai.lunar.utils.command.BaseCommand;
import me.dubai.lunar.utils.command.Command;
import me.dubai.lunar.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LunarCommand extends BaseCommand {

    @Command(name = "lunarclient", aliases = {"lc", "lunar"})
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length == 0) {
            player.sendMessage(Lunar.getInstance().parsePapi(player, Locale.LUNAR_COMMAND_PLAYER.format())
                    .replace("<player>", player.getDisplayName())
                    .replace("<status>", CC.CheckLC(player)));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (args.length == 1) {
            if (target == null) {
                player.sendMessage(Locale.PLAYER_NOT_FOUND.format());
                return;
            }

            player.sendMessage(Lunar.getInstance().parsePapi(player, Locale.LUNAR_COMMAND_TARGET.format())
                    .replace("<target>", target.getName())
                    .replace("<status>", CC.CheckLC(target)));
        }
    }

    @Command(name = "lunarclient.reload", permission = "lunar.reload", aliases = {"lc.reload", "lunar.reload"})
    public void onReloadCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        final String message = Locale.LUNAR_COMMAND_RELOAD.format();

        ConfigFile.getConfig().reload();
        player.sendMessage(Lunar.getInstance().parsePapi(player, message));
    }

    @Command(name = "lunarclient.users", aliases = {"lc.users", "lunar.users", "lunarclient.online", "lc.online", "lunar.online", "lc.list", "lunar.list", "lunarclient.list"})
    public void onUsersCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();

        new Thread(() -> {
            StringBuilder playerSB = new StringBuilder();
            Bukkit.getServer().getOnlinePlayers().stream().filter(all -> LunarClientAPI.getInstance().isRunningLunarClient(all)).forEach(all -> {
                playerSB.append(CC.WHITE).append(all.getDisplayName()).append(CC.GRAY).append(", ");
                ConfigFile.getConfig().getStringList("MESSAGES.LUNAR-USERS-COMMAND.LIST").forEach(messages -> player.sendMessage(CC.translate(Lunar.getInstance().parsePapi(player, messages).replace("<list>", (playerSB.length() > 1) ? playerSB.substring(0, playerSB.length() - 2) : ""))));
            });
        }).start();
    }
}