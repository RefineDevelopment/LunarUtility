package xyz.refinedev.lunar.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.cooldown.LunarClientAPICooldown;
import com.lunarclient.bukkitapi.serverrule.LunarClientAPIServerRule;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.refinedev.lunar.Locale;
import xyz.refinedev.lunar.LunarUtility;
import xyz.refinedev.lunar.utils.Utils;

@RequiredArgsConstructor
public class LunarListener implements Listener {
    private final LunarUtility plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //Send disabled mod's packet to player
        if (plugin.getPacketModSettings() != null) {
            LunarClientAPI.getInstance().sendPacket(event.getPlayer(), plugin.getPacketModSettings());
        }

        if (plugin.getConfig().getBoolean("WAYPOINT.ENABLED")) {
            plugin.getWaypoints().forEach(waypoint -> LunarClientAPI.getInstance().sendWaypoint(event.getPlayer(), waypoint));
            LunarClientAPIServerRule.sendServerRule(player);
        }

        //After 1 second on join, if the player isn't running lc, kick them
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!LunarClientAPI.getInstance().isRunningLunarClient(player) && plugin.getConfig().getBoolean("REQUIRE-LUNAR.ENABLED")) {
                player.kickPlayer(Locale.LUNAR_KICK_MESSAGE.messageFormat().replace("<space>", "\n"));
            }
        }, 20L);
    }

    @EventHandler
    public void onPlayerDamagePlayer(EntityDamageByEntityEvent event) {
        if (!plugin.getConfig().getBoolean("COOLDOWN.COMBAT.ENABLE")) return;
        if (event.getEntity().getType() != EntityType.PLAYER) return;
        if (event.getDamager().getType() != EntityType.PLAYER) return;

        if (Utils.isCompatible()) {
            LunarClientAPICooldown.sendCooldown((Player) event.getEntity(), "Combat");
            LunarClientAPICooldown.sendCooldown((Player) event.getDamager(), "Combat");
        }
    }

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
}