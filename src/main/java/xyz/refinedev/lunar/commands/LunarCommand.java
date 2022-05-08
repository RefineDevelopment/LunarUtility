package xyz.refinedev.lunar.commands;

import com.lunarclient.bukkitapi.LunarClientAPI;
import lombok.RequiredArgsConstructor;
import me.vaperion.blade.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.lunar.Locale;
import xyz.refinedev.lunar.LunarUtility;
import xyz.refinedev.lunar.utils.Utils;

import java.io.File;

@RequiredArgsConstructor
public class LunarCommand {
    private final LunarUtility plugin;

    @Command(value = {"lunarclient", "lc", "lunar check", "lc check"})
    public void onLunarCommand(@Sender Player player, @Optional @Name("target") Player target) {

        if (target == null) {
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_COMMAND_PLAYER.messageFormat())
                    .replace("<player>", player.getDisplayName())
                    .replace("<status>", Utils.checkLC(player)));
        } else {
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_COMMAND_TARGET.messageFormat())
                    .replace("<target>", target.getName())
                    .replace("<status>", Utils.checkLC(target)));
        }
    }

    @Command(value = {"lunarclient reload", "lc reload", "lunar reload"})
    @Permission(value = "lunar.reload")
    public void onLunarReloadCommand(@Sender CommandSender sender) {
        try {
            plugin.getConfig().load(new File(plugin.getDataFolder(), "config.yml"));
            sender.sendMessage(Locale.LUNAR_COMMAND_RELOAD.messageFormat());
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "An error occurred!");
        }
    }

    @Command(value = {"lunarclient users", "lc users", "lunar users", "lunerclient online", "lc online", "lunar online"})
    public void onLunarUsersCommand(@Sender CommandSender sender) {
        StringBuilder playerList = new StringBuilder();

        Bukkit.getOnlinePlayers().forEach(players -> {
            if (LunarClientAPI.getInstance().getPlayersRunningLunarClient().contains(players)) {
                if (playerList.length() > 0) {
                    playerList.append(Locale.LUNAR_USERS_SEPERATOR.messageFormat());
                }
                playerList.append(players.getName());
            }
        });

        plugin.getConfig().getStringList("MESSAGES.LUNAR-USERS-COMMAND.MESSAGE").stream().map(list -> Utils.translate(list
                        .replace("<totalUsers>", String.valueOf(LunarClientAPI.getInstance().getPlayersRunningLunarClient().size()))
                        .replace("<playerList>", playerList)))
                .forEach(sender::sendMessage);
    }
}