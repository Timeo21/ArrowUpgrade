package ch.timeo.arrowUpgrade.components;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;

public interface Component {
    /**
     * Apply the component's effect to the arrow when it's shot.
     * @param arrow The arrow entity.
     */
    void apply(AbstractArrow arrow);

    /**
     * Handle the event when the arrow hits something.
     * @param arrow The arrow entity.
     * @param event The projectile hit event.
     */
    void onArrowHit(AbstractArrow arrow, ProjectileHitEvent event);

    /**
     * Get the unique identifier of the component.
     * @return The component ID.
     */
    String getId();
}
