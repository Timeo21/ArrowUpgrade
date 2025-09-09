package ch.timeo.arrowUpgrade.components.shaft;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class BreezeShaft implements Shaft{
    @Override
    public void apply(AbstractArrow arrow) {
        arrow.setVelocity(arrow.getVelocity().multiply(1.5));
    }

    @Override
    public void onArrowHit(AbstractArrow arrow, ProjectileHitEvent event) {

    }

    @Override
    public String getId() {
        return "breeze_shaft";
    }
}
