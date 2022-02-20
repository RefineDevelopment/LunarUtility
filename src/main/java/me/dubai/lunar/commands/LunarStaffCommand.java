package me.dubai.lunar.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.OptArg;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import com.lunarclient.bukkitapi.LunarClientAPI;
import me.dubai.lunar.Locale;
import me.dubai.lunar.utils.Utils;
import org.bukkit.entity.Player;

public class LunarStaffCommand {

    @Command(name = "", desc = "Give a person Lunar staff mods", usage = "<player>")
    @Require(value = "lunar.staff")
    public void onLunarStaffModeCommand(@Sender Player player, @OptArg Player target) {
        if (target == null) {
            LunarClientAPI.getInstance().giveAllStaffModules(player);
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_PLAYER.messageFormat()));
        } else {
            LunarClientAPI.getInstance().giveAllStaffModules(target);
            player.sendMessage(Utils.parsePapi(player, Locale.LUNAR_STAFF_COMMAND_TARGET.messageFormat()).replace("<target>", target.getDisplayName()));
            target.sendMessage(Utils.parsePapi(target, Locale.LUNAR_STAFF_COMMAND_TO_TARGET.messageFormat()).replace("<player>", player.getDisplayName()));
        }
    }
}