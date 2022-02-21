package xyz.refinedev.lunar.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
import com.lunarclient.bukkitapi.nethandler.client.obj.ServerRule;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import com.lunarclient.bukkitapi.serverrule.LunarClientAPIServerRule;
import lombok.RequiredArgsConstructor;
import xyz.refinedev.lunar.Locale;
import xyz.refinedev.lunar.LunarUtility;
import xyz.refinedev.lunar.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class LunarListener implements Listener {
    private final LunarUtility plugin;

    @EventHandler
    public void onPearlLaunch(ProjectileLaunchEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;

        if (Utils.isCompatible() && event.getEntity() instanceof EnderPearl && plugin.getConfig().getBoolean("COOLDOWN.ENDERPEARL.ENABLE")) {
            LunarClientAPICooldown.sendCooldown((Player) event.getEntity().getShooter(), "Enderpearl");
        }
    }

    @EventHandler
    public void onGappleConsume(PlayerItemConsumeEvent event) {

        if (Utils.isCompatible() && event.getItem().getType().equals(Material.GOLDEN_APPLE) && plugin.getConfig().getBoolean("COOLDOWN.GAPPLE.ENABLE")) {
            LunarClientAPICooldown.sendCooldown(event.getPlayer(), "Gapple");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!LunarClientAPI.getInstance().isRunningLunarClient(player) && plugin.getConfig().getBoolean("REQUIRE-LUNAR.ENABLED")) {
                player.kickPlayer(Locale.LUNAR_KICK_MESSAGE.messageFormat().replace("\n", "<space>"));
            }
        }, 2 * 20L);

        if (plugin.getConfig().getBoolean("WAYPOINTS.ENABLE")) {
            LunarClientAPIServerRule.setRule(ServerRule.SERVER_HANDLES_WAYPOINTS, true);
            LunarClientAPI.getInstance().sendWaypoint(player, new LCWaypoint(plugin.getConfig().getString("WAYPOINTS.NAME"), Bukkit.getWorld(plugin.getConfig().getString("WAYPOINTS.WORLD")).getSpawnLocation(), Color.GREEN.asRGB(), true, true));
        }
    }
}