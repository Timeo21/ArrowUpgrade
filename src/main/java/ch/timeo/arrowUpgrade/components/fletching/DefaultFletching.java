package ch.timeo.arrowUpgrade.components.fletching;

import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DefaultFletching implements Fletching {
    @Override
    public void apply(Arrow arrow) {

    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileHitEvent event) {

    }

    @Override
    public String getId() {
        return "";
    }
}
