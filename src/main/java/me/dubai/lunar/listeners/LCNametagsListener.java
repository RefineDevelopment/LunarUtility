package me.dubai.lunar.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
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
        Bukkit.getScheduler().runTaskTimer(Lunar.getInstance(), () -> {
            for (Player all : Bukkit.getOnlinePlayers()) {
                Bukkit.getOnlinePlayers().forEach(player -> LunarClientAPI.getInstance().overrideNametag(all, nametag(all, player), player));
            }
        }, 0, 20);
    }

    public List<String> nametag(Player target, Player viewer) {
        List<String> tag = new ArrayList<>();
        String tag1 = ConfigFile.getConfig().getString("NAMETAG.FIRST");
        String tag2 = ConfigFile.getConfig().getString("NAMETAG.SECOND");
        boolean lunarclient = LunarClientAPI.getInstance().isRunningLunarClient(target);
        String lunar = lunarclient ? (CC.GREEN + "ON") : (CC.RED + "NOT ON");

        if (ConfigFile.getConfig().getString("NAMETAG.ENABLE").equals("true")) {
            tag.add(Lunar.getInstance().parsePapi(target, CC.translate(tag1))
                    .replace("<status>", lunar));

            tag.add(Lunar.getInstance().parsePapi(target, CC.translate(tag2))
                    .replace("<player_displayname>", target.getDisplayName())
                    .replace("<player_name>", target.getName()));
        } else {
            tag.add(target.getDisplayName());
        }
        return tag;
    }
}