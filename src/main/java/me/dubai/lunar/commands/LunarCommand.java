package me.dubai.lunar.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.OptArg;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import com.lunarclient.bukkitapi.LunarClientAPI;
import lombok.RequiredArgsConstructor;
import me.dubai.lunar.Locale;
import me.dubai.lunar.LunarUtility;
import me.dubai.lunar.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

@RequiredArgsConstructor
public class LunarCommand {
    private final LunarUtility plugin;

    @Command(name = "", aliases = {"check"}, desc = "Check if a player is using LunarClient", usage = "<player>")
    public void onLunarCommand(@Sender Player player, @OptArg Player target) {

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

    @Command(name = "reload", desc = "Reload the config file")
    @Require(value = "lunar.reload")
    public void onLunarReloadCommand(@Sender CommandSender sender) {
        try {
            plugin.getConfig().load(new File(plugin.getDataFolder(), "config.yml"));
            sender.sendMessage(Locale.LUNAR_COMMAND_RELOAD.messageFormat());
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "An error occurred!");
        }
    }

    @Command(name = "users", aliases = {"online", "lc"}, desc = "View a list of players using LunarClient")
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