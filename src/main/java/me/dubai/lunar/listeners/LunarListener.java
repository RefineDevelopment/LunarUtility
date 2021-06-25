package me.dubai.lunar.listeners;

import org.bukkit.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.entity.EnderPearl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
import com.lunarclient.bukkitapi.nethandler.client.obj.ServerRule;
import com.lunarclient.bukkitapi.serverrule.LunarClientAPIServerRule;

public class LunarListener implements Listener {

    @EventHandler
    private void pearlcooldown(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof EnderPearl) {
            Player player = (Player) event.getEntity().getShooter();

            LunarClientAPICooldown.sendCooldown(player, "Enderpearl");
        }
    }

    @EventHandler
    private void gapplecooldown(PlayerItemConsumeEvent event) {
        if (event.getItem().getType().equals(Material.GOLDEN_APPLE)) {
            Player player = event.getPlayer();

            LunarClientAPICooldown.sendCooldown(player, "Gapple");
        }
    }

    @EventHandler
    private void waypoint(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        LunarClientAPIServerRule.setRule(ServerRule.SERVER_HANDLES_WAYPOINTS, true);
        LunarClientAPI.getInstance().sendWaypoint(player, new LCWaypoint("Spawn", Bukkit.getWorld("world").getSpawnLocation(), Color.GREEN.asRGB(), true, true));
    }
}