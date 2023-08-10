package xyz.refinedev.lunar.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.lunarclient.bukkitapi.LunarClientAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.lunar.Locale;
import xyz.refinedev.lunar.LunarUtility;
import xyz.refinedev.lunar.utils.Utils;

import java.io.File;
import java.util.stream.Collectors;

@CommandAlias(value = "lunarclient|lc")
public class LunarCommand extends BaseCommand {

    @Dependency
    private final LunarUtility plugin;

    public LunarCommand(LunarUtility plugin) {
        this.plugin = plugin;
    }

    @Default
    @Syntax(value = "[Player]")
    @CommandCompletion(value = "@players")
    @Description(value = "View a player's LunarClient status.")
    public void lunarCommand(Player player, @Optional OnlinePlayer target) {

        if (target == null) {
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_COMMAND_PLAYER.messageFormat())
                    .replace("<player>", player.getDisplayName())
                    .replace("<status>", Utils.checkLC(player)));
        } else {
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_COMMAND_TARGET.messageFormat())
                    .replace("<target>", target.getPlayer().getName())
                    .replace("<status>", Utils.checkLC(target.getPlayer())));
        }
    }

    @Subcommand(value = "users|players|online")
    @Description(value = "View a list of players using LunarClient.")
    public void userCommand(CommandSender sender) {

        if (LunarClientAPI.getInstance().getPlayersRunningLunarClient().size() == 0) {
            sender.sendMessage(ChatColor.RED + "There are no players online using LunarClient!");
            return;
        }

        String playerLists = Bukkit.getOnlinePlayers().stream()
                .filter(player -> LunarClientAPI.getInstance().isRunningLunarClient(player))
                .map(Player::getName)
                .collect(Collectors.joining(Locale.LUNAR_USERS_SEPERATOR.messageFormat()));

        Locale.LUNAR_USERS_MESSAGE.messageFormatList().stream()
                .map(message -> message
                        .replace("<totalUsers>", String.valueOf(LunarClientAPI.getInstance().getPlayersRunningLunarClient().size()))
                        .replace("<playerList>", playerLists))
                .forEach(sender::sendMessage);
    }

    @Subcommand(value = "reload")
    @CommandPermission(value = "lunar.reload")
    @Description(value = "Reload the LunarUtility plugin configuration.")
    public void reloadCommand(CommandSender sender) {
        try {
            plugin.getConfig().load(new File(plugin.getDataFolder(), "config.yml"));
            sender.sendMessage(Locale.LUNAR_COMMAND_RELOAD.messageFormat());
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "An error occurred!");
        }
    }
}