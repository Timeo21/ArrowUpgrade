package ch.timeo.arrowUpgrade.components.point;

import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DefaultPoint implements Point {
    @Override
    public void apply(Arrow arrow) {
        return;
    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileHitEvent event) {
        return;
    }

    @Override
    public String getId() {
        return "default";
    }
}
