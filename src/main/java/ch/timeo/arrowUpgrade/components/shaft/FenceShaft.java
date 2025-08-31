package ch.timeo.arrowUpgrade.components.shaft;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class FenceShaft implements Shaft {
    @Override
    public void apply(AbstractArrow arrow) {

    }

    @Override
    public void onArrowHit(AbstractArrow arrow, ProjectileHitEvent event) {

    }

    @Override
    public String getId() {
        return "fence";
    }
}
