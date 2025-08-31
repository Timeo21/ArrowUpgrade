package ch.timeo.arrowUpgrade.components.fletching;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DefaultFletching implements Fletching {
    @Override
    public void apply(AbstractArrow arrow) {

    }

    @Override
    public void onArrowHit(AbstractArrow arrow, ProjectileHitEvent event) {

    }

    @Override
    public String getId() {
        return "";
    }
}
