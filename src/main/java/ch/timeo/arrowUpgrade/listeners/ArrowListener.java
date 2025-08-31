package ch.timeo.arrowUpgrade.listeners;

import ch.timeo.arrowUpgrade.Utils;
import ch.timeo.arrowUpgrade.components.effect.Effect;
import ch.timeo.arrowUpgrade.components.fletching.Fletching;
import ch.timeo.arrowUpgrade.components.point.Point;
import ch.timeo.arrowUpgrade.components.shaft.Shaft;
import ch.timeo.arrowUpgrade.registry.ArrowRegistry;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ArrowListener implements Listener {
    private final Plugin plugin;

    public ArrowListener(Plugin plugin) {
        this.plugin = plugin;
    }

    // region Apply/Hit
    public static void fletchingHit(AbstractArrow arrow, String fletchingId, ProjectileHitEvent event) {
        Fletching fletching = ArrowRegistry.getFletching(fletchingId);
        if (fletching != null) fletching.onArrowHit(arrow, event);
    }

    public static void fletchingApply(AbstractArrow arrow, String fletchingId) {
        Fletching fletching = ArrowRegistry.getFletching(fletchingId);
        if (fletching != null) fletching.apply(arrow);
    }
    public static void fletchingApply(AbstractArrow arrow){
        if (arrow == null) return;
        Utils.CraftedArrow craftedArrow = new Utils.CraftedArrow(arrow);
        if (craftedArrow.fletchingId != null) fletchingApply(arrow, craftedArrow.fletchingId);
    }

    public static void shaftHit(AbstractArrow arrow, String shaftId, ProjectileHitEvent event) {
        Shaft shaft = ArrowRegistry.getShaft(shaftId);
        if (shaft != null) shaft.onArrowHit(arrow, event);
    }

    public static void shaftApply(AbstractArrow arrow, String shaftId) {
        Shaft shaft = ArrowRegistry.getShaft(shaftId);
        if (shaft != null) shaft.apply(arrow);
    }
    public static void shaftApply(AbstractArrow arrow){
        if (arrow == null) return;
        Utils.CraftedArrow craftedArrow = new Utils.CraftedArrow(arrow);
        if (craftedArrow.shaftId != null) shaftApply(arrow, craftedArrow.shaftId);
    }

    public static void pointHit(AbstractArrow arrow, String pointId, ProjectileHitEvent event) {
        Point point = ArrowRegistry.getPoint(pointId);
        if (point != null) point.onArrowHit(arrow, event);
    }

    public static void pointApply(AbstractArrow arrow, String pointId) {
        Point point = ArrowRegistry.getPoint(pointId);
        if (point != null) point.apply(arrow);
    }
    public static void pointApply(AbstractArrow arrow){
        if (arrow == null) return;
        Utils.CraftedArrow craftedArrow = new Utils.CraftedArrow(arrow);
        if (craftedArrow.pointId != null) pointApply(arrow, craftedArrow.pointId);
    }

    public static void effectHit(AbstractArrow arrow, String effectId, ProjectileHitEvent event) {
        Effect effect = ArrowRegistry.getEffect(effectId);
        if (effect != null) effect.onArrowHit(arrow, event);
    }

    public static void effectApply(AbstractArrow arrow, String effectId) {
        Effect effect = ArrowRegistry.getEffect(effectId);
        if (effect != null) effect.apply(arrow);
    }
    public static void effectApply(AbstractArrow arrow){
        if (arrow == null) return;
        Utils.CraftedArrow craftedArrow = new Utils.CraftedArrow(arrow);
        if (craftedArrow.effectId != null) effectApply(arrow, craftedArrow.effectId);
    }

    // endregion

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow arrow)) return;
        hitComponents(arrow, event);

    }

    @EventHandler
    public void onPlayerShoot(EntityShootBowEvent event) {
        if (!(event.getProjectile() instanceof Arrow arrow)) return;

        // Get persistent data from arrow item
        ItemStack arrowItem = event.getConsumable();
        if (arrowItem == null || !arrowItem.hasItemMeta()) return;

        if (!Utils.transferComponent(arrowItem, arrow)) {
            return;
        }
        // Apply component effects
        applyComponents(arrow);
    }

    public void applyComponents(AbstractArrow arrow){
        if (arrow == null) return;
        Utils.CraftedArrow craftedArrow = new Utils.CraftedArrow(arrow);
        if (craftedArrow.fletchingId != null) fletchingApply(arrow, craftedArrow.fletchingId);
        if (craftedArrow.shaftId != null) shaftApply(arrow, craftedArrow.shaftId);
        if (craftedArrow.pointId != null) pointApply(arrow, craftedArrow.pointId);
        if (craftedArrow.effectId != null) effectApply(arrow, craftedArrow.effectId);
    }

    public void hitComponents(AbstractArrow arrow, ProjectileHitEvent event){
        if (arrow == null) return;
        Utils.CraftedArrow craftedArrow = new Utils.CraftedArrow(arrow);
        if (craftedArrow.fletchingId != null) fletchingHit(arrow, craftedArrow.fletchingId, event);
        if (craftedArrow.shaftId != null) shaftHit(arrow, craftedArrow.shaftId, event);
        if (craftedArrow.pointId != null) pointHit(arrow, craftedArrow.pointId, event);
        if (craftedArrow.effectId != null) effectHit(arrow, craftedArrow.effectId, event);
    }
}
