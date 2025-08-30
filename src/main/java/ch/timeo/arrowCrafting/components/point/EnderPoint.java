package ch.timeo.arrowCrafting.components.point;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.UUID;

public class EnderPoint implements Point {
    @Override
    public void apply(Arrow arrow) {
    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileHitEvent event) {
            UUID uuid = arrow.getOwnerUniqueId();
        if (uuid == null) return;
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) return;
        player.teleport(arrow.getLocation());
    }

    @Override
    public String getId() {
        return "ender";
    }
}
