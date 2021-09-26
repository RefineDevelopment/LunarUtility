package me.dubai.lunar.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.dubai.lunar.Locale;
import me.dubai.lunar.Lunar;
import me.dubai.lunar.utils.CC;
import me.dubai.lunar.utils.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class LCNametagsListener implements Listener {

    public LCNametagsListener() {
        Bukkit.getScheduler().runTaskTimer(Lunar.getInstance(), () -> Bukkit.getOnlinePlayers().forEach(all -> Bukkit.getOnlinePlayers().forEach(player -> LunarClientAPI.getInstance().overrideNametag(all, nametagSetup(all), player))), 0, 40);
    }

    public List<String> nametagSetup(Player target) {
        List<String> tag = new ArrayList<>();
        final String lunar = (LunarClientAPI.getInstance().isRunningLunarClient(target) ? CC.translate(ConfigFile.getConfig().getString("OTHER.ENABLED")) : CC.translate(ConfigFile.getConfig().getString("OTHER.DISABLED")));

        tag.add(Lunar.getInstance().parsePapi(target, Locale.LUNAR_TAG_1.format())
                .replace("<player_displayname>", target.getDisplayName())
                .replace("<player_name>", target.getName())
                .replace("<status>", lunar));

        if (ConfigFile.getConfig().getBoolean("NAMETAG.SECOND.ENABLE")) {
            tag.add(Lunar.getInstance().parsePapi(target, Locale.LUNAR_TAG_2.format())
                    .replace("<player_displayname>", target.getDisplayName())
                    .replace("<player_name>", target.getName())
                    .replace("<status>", lunar));
        }
        return tag;
    }
}