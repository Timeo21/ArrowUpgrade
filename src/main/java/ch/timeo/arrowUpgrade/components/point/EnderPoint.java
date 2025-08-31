package ch.timeo.arrowUpgrade.components.point;

import ch.timeo.arrowUpgrade.ArrowUpgrade;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.UUID;

public class EnderPoint implements Point {
    @Override
    public void apply(AbstractArrow arrow) {
    }

    @Override
    public void onArrowHit(AbstractArrow arrow, ProjectileHitEvent event) {
        UUID uuid = arrow.getOwnerUniqueId();
        if (uuid == null) return;
        Player player = Bukkit.getPlayer(uuid);
        Location goTo = event.getHitEntity() != null ? event.getHitEntity().getLocation() : arrow.getLocation();
        if (player == null) return;
        Bukkit.getScheduler().runTaskLater(ArrowUpgrade.getInstance(), () -> {
            player.teleport(goTo);
        }, 1L);
    }

    @Override
    public String getId() {
        return "ender";
    }
}
