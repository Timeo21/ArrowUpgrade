package ch.timeo.arrowUpgrade.components.point;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DefaultPoint implements Point {
    @Override
    public void apply(AbstractArrow arrow) {
        return;
    }

    @Override
    public void onArrowHit(AbstractArrow arrow, ProjectileHitEvent event) {
        return;
    }

    @Override
    public String getId() {
        return "default";
    }
}
