package me.dubai.lunar.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
import com.lunarclient.bukkitapi.nethandler.client.obj.ServerRule;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import com.lunarclient.bukkitapi.serverrule.LunarClientAPIServerRule;
import me.dubai.lunar.utils.ConfigFile;
import me.dubai.lunar.utils.Utils;
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

public class LunarListener implements Listener {

    @EventHandler
    private void onPearlLaunch(ProjectileLaunchEvent event) {
        if (Utils.isCompatible()) {
            if (event.getEntity() instanceof EnderPearl && ConfigFile.getConfig().getBoolean("COOLDOWN.ENDERPEARL.ENABLE")) {
                if (event.getEntity().getShooter() instanceof Player) {
                    LunarClientAPICooldown.sendCooldown((Player) event.getEntity().getShooter(), "Enderpearl");
                }
            }
        }
    }

    @EventHandler
    private void onGappleConsume(PlayerItemConsumeEvent event) {
        if (Utils.isCompatible()) {
            if (event.getItem().getType().equals(Material.GOLDEN_APPLE) && ConfigFile.getConfig().getBoolean("COOLDOWN.GAPPLE.ENABLE")) {
                LunarClientAPICooldown.sendCooldown(event.getPlayer(), "Gapple");
            }
        }
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        final String name = ConfigFile.getConfig().getString("WAYPOINTS.NAME");
        final String world = ConfigFile.getConfig().getString("WAYPOINTS.WORLD");

        if (ConfigFile.getConfig().getBoolean("WAYPOINTS.ENABLE")) {
            LunarClientAPIServerRule.setRule(ServerRule.SERVER_HANDLES_WAYPOINTS, true);
            LunarClientAPI.getInstance().sendWaypoint(player, new LCWaypoint(name, Bukkit.getWorld(world).getSpawnLocation(), Color.GREEN.asRGB(), true, true));
        }
    }
}