package ch.timeo.arrowUpgrade.components.effect;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public class GlowEffect implements Effect {
    @Override
    public void apply(AbstractArrow arrow) {
        arrow.setGlowing(true);
    }

    @Override
    public void onArrowHit(AbstractArrow arrow, ProjectileHitEvent event) {
    }

    @Override
    public String getId() {
        return "glow_effect";
    }
}
