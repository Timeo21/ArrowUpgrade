package ch.timeo.arrowUpgrade.components.shaft;

import ch.timeo.arrowUpgrade.Utils;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class FireShaft implements Shaft{
    @Override
    public void apply(Arrow arrow) {
        arrow.setFireTicks(Integer.MAX_VALUE);
    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileHitEvent event) {
        if (event.getHitEntity() != null) {
            event.getHitEntity().setFireTicks(Integer.MAX_VALUE);
        }
    }

    @Override
    public String getId() {
        return Utils.FIRE_SHAFT_ID;
    }
}
