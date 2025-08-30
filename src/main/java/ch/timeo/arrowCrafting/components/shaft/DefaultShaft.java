package ch.timeo.arrowCrafting.components.shaft;

import ch.timeo.arrowCrafting.Utils;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DefaultShaft implements Shaft{
    @Override
    public void apply(Arrow arrow) {
    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileHitEvent event) {
    }

    @Override
    public String getId() {
        return Utils.DEFAULT_ID;
    }
}
