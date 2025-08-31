package ch.timeo.arrowUpgrade.components.point;

import ch.timeo.arrowUpgrade.ArrowUpgrade;
import ch.timeo.arrowUpgrade.Utils;
import ch.timeo.arrowUpgrade.listeners.ArrowListener;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class SlimePoint implements Point {
    private static final int MAX_BOUNCES = 3;
    @Override
    public void apply(Arrow arrow) {
        if (!arrow.hasMetadata("bounce_left")) {
            arrow.setMetadata("bounce_left", new FixedMetadataValue(ArrowUpgrade.getInstance(), MAX_BOUNCES));
        }
    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileHitEvent event) {
        arrow.getWorld().spawnParticle(Particle.BLOCK, arrow.getLocation(), 10, 0.2, 0.2, 0.2, 1, Material.SLIME_BLOCK.createBlockData());
        handleBounce(arrow, event);
    }

    @Override
    public String getId() {
        return "slime";
    }

    private void handleBounce(Arrow arrow, ProjectileHitEvent event) {
        int bounces;
        if (event.getHitBlock() == null) return;
        if (arrow.hasMetadata("bounce_left") && !arrow.getMetadata("bounce_left").isEmpty()) {
            bounces = arrow.getMetadata("bounce_left").getFirst().asInt() - 1;
            arrow.setMetadata("bounce_left", new FixedMetadataValue(ArrowUpgrade.getInstance(), bounces));
            if (bounces <= 0) {
                arrow.removeMetadata("bounce_left", ArrowUpgrade.getInstance());
            }
        } else return;

        Vector vel = arrow.getVelocity();
        double speed = vel.length();
        BlockFace face = event.getHitBlockFace();
        if (face == null) return;
        Vector normal = face.getDirection();
        Vector reflected = vel.subtract(normal.multiply(2 * vel.dot(normal))).normalize();
        Vector offset = new Vector(0,0,0);
        offset.copy(reflected);
        Location newLoc = arrow.getLocation().add(offset.normalize().multiply(0.5));
        newLoc.setYaw(getYaw(reflected));
        newLoc.setPitch(getPitch(reflected));
        Arrow newarrow = shootArrow(newLoc, reflected, (float) speed, arrow.getShooter());
        if (bounces > 0) {
            newarrow.setMetadata("bounce_left", new FixedMetadataValue(ArrowUpgrade.getInstance(), bounces));
        }
        newarrow.setItemStack(arrow.getItemStack());
        newarrow.setPickupStatus(arrow.getPickupStatus());
        Utils.transferComponent(arrow.getItemStack(), newarrow);
        ArrowListener.fletchingApply(newarrow);
        ArrowListener.shaftApply(newarrow);
        newarrow.setVelocity(reflected.multiply(speed));
        arrow.getWorld().playSound(arrow.getLocation(), "entity.slime.jump", 1.0f, 1.0f);
        arrow.remove();

    }

    private Arrow shootArrow(Location loc, Vector direction, float speed, ProjectileSource source) {
        Arrow arrow = loc.getWorld().spawnArrow(loc, direction, speed, 0);
        arrow.setShooter(source);
        return arrow;
    }

    private static float getYaw(Vector v) {
        return (float) Math.toDegrees(Math.atan2(-v.getX(), v.getZ()));
    }

    private static float getPitch(Vector v) {
        double xz = Math.sqrt(v.getX() * v.getX() + v.getZ() * v.getZ());
        return (float) Math.toDegrees(Math.atan2(-v.getY(), xz));
    }



}
