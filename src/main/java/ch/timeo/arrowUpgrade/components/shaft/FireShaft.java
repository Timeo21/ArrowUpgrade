package ch.timeo.arrowUpgrade.components.shaft;

import ch.timeo.arrowUpgrade.Utils;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class FireShaft implements Shaft{
    @Override
    public void apply(AbstractArrow arrow) {
        arrow.setFireTicks(Integer.MAX_VALUE);
    }

    @Override
    public void onArrowHit(AbstractArrow arrow, ProjectileHitEvent event) {
        if (event.getHitEntity() != null) {
            event.getHitEntity().setFireTicks(Integer.MAX_VALUE);
        }
    }

    @Override
    public String getId() {
        return Utils.FIRE_SHAFT_ID;
    }
}
