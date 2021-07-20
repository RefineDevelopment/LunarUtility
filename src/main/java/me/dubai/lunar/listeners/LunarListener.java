package me.dubai.lunar.listeners;

import org.bukkit.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.entity.EnderPearl;
import org.bukkit.event.EventHandler;
import me.dubai.lunar.utils.ConfigFile;
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
        if (event.getEntity() instanceof EnderPearl && ConfigFile.getConfig().getBoolean("COOLDOWN.ENDERPEARL.ENABLE")) {
                Player player = (Player) event.getEntity().getShooter();

                LunarClientAPICooldown.sendCooldown(player, "Enderpearl");
            }
    }

    @EventHandler
    private void gapplecooldown(PlayerItemConsumeEvent event) {
        Material item = event.getItem().getType();
        if (item.equals(Material.GOLDEN_APPLE) && ConfigFile.getConfig().getBoolean("COOLDOWN.GAPPLE.ENABLE")) {
                Player player = event.getPlayer();

                LunarClientAPICooldown.sendCooldown(player, "Gapple");
            }
    }

    @EventHandler
    private void waypoint(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String name = ConfigFile.getConfig().getString("WAYPOINTS.NAME");
        String world = ConfigFile.getConfig().getString("WAYPOINTS.WORLD");

        if (ConfigFile.getConfig().getBoolean("WAYPOINTS.ENABLED")) {
            LunarClientAPIServerRule.setRule(ServerRule.SERVER_HANDLES_WAYPOINTS, true);
            LunarClientAPI.getInstance().sendWaypoint(player, new LCWaypoint(name, Bukkit.getWorld(world).getSpawnLocation(), Color.GREEN.asRGB(), true, true));
        }
    }
}