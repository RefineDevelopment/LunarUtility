package me.dubai.lunar.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import lombok.RequiredArgsConstructor;
import me.dubai.lunar.Locale;
import me.dubai.lunar.LunarUtility;
import me.dubai.lunar.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class NametagTask implements Runnable {
    private final LunarUtility plugin;

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(all -> Bukkit.getOnlinePlayers()
                .forEach(player -> LunarClientAPI.getInstance().overrideNametag(all, nametagSetup(all), player)));
    }

    private List<String> nametagSetup(Player target) {
        List<String> tag = new ArrayList<>();
        String lunar = (LunarClientAPI.getInstance().isRunningLunarClient(target) ? Utils.translate(plugin.getConfig().getString("OTHER.ENABLED")) : Utils.translate(plugin.getConfig().getString("OTHER.DISABLED")));

        tag.add(Utils.parsePapi(target, Locale.LUNAR_TAG_1.messageFormat())
                .replace("<player_displayname>", target.getDisplayName())
                .replace("<player_name>", target.getName())
                .replace("<status>", lunar));

        if (plugin.getConfig().getBoolean("NAMETAG.SECOND.ENABLE")) {
            tag.add(Utils.parsePapi(target, Locale.LUNAR_TAG_2.messageFormat())
                    .replace("<player_displayname>", target.getDisplayName())
                    .replace("<player_name>", target.getName())
                    .replace("<status>", lunar));
        }
        return tag;
    }
}