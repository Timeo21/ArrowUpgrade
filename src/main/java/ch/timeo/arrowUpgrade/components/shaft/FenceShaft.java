package ch.timeo.arrowUpgrade.components.shaft;

import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class FenceShaft implements Shaft {
    @Override
    public void apply(Arrow arrow) {

    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileHitEvent event) {

    }

    @Override
    public String getId() {
        return "fence";
    }
}
