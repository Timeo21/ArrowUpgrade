package ch.timeo.arrowUpgrade.components.fletching;

import ch.timeo.arrowUpgrade.ArrowUpgrade;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EnderFletch implements Fletching{
    @Override
    public void apply(Arrow arrow) {

    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileHitEvent event) {
        Location playerLoc = arrow.getShooter() instanceof Player player ? player.getLocation() : null;
        if (playerLoc == null) return;
        if (event.getHitEntity() != null) {
            // 1 tick later teleport hit entity to player
            Bukkit.getScheduler().runTaskLater(ArrowUpgrade.getInstance(), () -> {
                event.getHitEntity().teleport(playerLoc);
            }, 1L);
        }
    }

    @Override
    public String getId() {
        return "ender_fletching";
    }
}
