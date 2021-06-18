package me.dubai.lunar.listeners;

import com.lunarclient.bukkitapi.nethandler.client.obj.ServerRule;
import com.lunarclient.bukkitapi.serverrule.LunarClientAPIServerRule;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerJoinEvent;
import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;

public class LunarListener implements Listener {

    @EventHandler
    private void PearlCooldown(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof EnderPearl) {
            Player player = (Player) event.getEntity().getShooter();

            LunarClientAPICooldown.sendCooldown(player, "Enderpearl");
        }
    }

    @EventHandler
    private void GappleCooldown(PlayerItemConsumeEvent event) {
        if (event.getItem().getType().equals(Material.GOLDEN_APPLE)) {
            Player player = event.getPlayer();

            LunarClientAPICooldown.sendCooldown(player, "Gapple");
        }
    }

    @EventHandler
    private void Waypoint(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        LunarClientAPIServerRule.setRule(ServerRule.SERVER_HANDLES_WAYPOINTS, true);
        LunarClientAPI.getInstance().sendWaypoint(player, new LCWaypoint("Spawn", Bukkit.getWorld("world").getSpawnLocation(), Color.GREEN.asRGB(), true, true));
    }
}