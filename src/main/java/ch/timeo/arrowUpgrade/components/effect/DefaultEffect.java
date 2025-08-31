package ch.timeo.arrowUpgrade.components.effect;

import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DefaultEffect implements Effect{
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
