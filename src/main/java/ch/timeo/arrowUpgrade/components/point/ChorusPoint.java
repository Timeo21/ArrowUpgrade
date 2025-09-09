package ch.timeo.arrowUpgrade.components.point;

import ch.timeo.arrowUpgrade.ArrowUpgrade;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Random;

public class ChorusPoint implements Point{
    private static final double TELEPORT_RADIUS = 10.0;
    @Override
    public void apply(AbstractArrow arrow) {
    }

    @Override
    public void onArrowHit(AbstractArrow arrow, ProjectileHitEvent event) {
        if (event.getHitEntity() != null) {
            Entity target = event.getHitEntity();
            Bukkit.getScheduler().runTaskLater(ArrowUpgrade.getInstance(), () -> {
                simulateChorusEat(target);
            }, 1L);
        }
    }

    @Override
    public String getId() {
        return "chorus_point";
    }

    public static void simulateChorusEat(Entity entity) {
        if (entity == null) return;

        Random random = new Random();

        // Current location
        Location loc = entity.getLocation();
        World world = loc.getWorld();
        if (world == null) return;

        for (int attempt = 0; attempt < 16; attempt++) { // try 16 times like vanilla
            double offsetX = (random.nextDouble() - 0.5) * 2 * TELEPORT_RADIUS;
            double offsetY = random.nextDouble() * 8 - 4; // can go slightly up or down
            double offsetZ = (random.nextDouble() - 0.5) * 2 * TELEPORT_RADIUS;

            Location target = loc.clone().add(offsetX, offsetY, offsetZ);
            target.setY(Math.max(0, Math.min(target.getY(), world.getMaxHeight() - 1)));

            // Find the first solid block under the target
            Location safe = world.getHighestBlockAt(target).getLocation().add(0, 1, 0);

            if (safe.getBlock().getType().isAir()) {
                entity.teleport(safe);
                entity.getWorld().playSound(entity.getLocation(), "entity.enderman.teleport", 1.0f, 1.0f);
                break;
            }
        }

        // Optional: play particle effect
        entity.getWorld().spawnParticle(org.bukkit.Particle.PORTAL, entity.getLocation(), 32, 0.5, 1, 0.5, 0.1);
    }

}
