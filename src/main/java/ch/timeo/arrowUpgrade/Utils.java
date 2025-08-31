package ch.timeo.arrowUpgrade;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.List;

public class Utils {
    public static final String DEFAULT_ID = "default";
    public static final String FIRE_SHAFT_ID = "blaze_rod";
    public static final String ENDER_POINT_ID = "ender_point";
    public static final String ENDER_FLETCHING_ID = "ender_fletching";
    public static boolean transferComponent(ItemStack source, AbstractArrow target) {
        if (source == null || target == null) return false;
        if (source.getType() != Material.ARROW && source.getType() != Material.SPECTRAL_ARROW && source.getType() != Material.TIPPED_ARROW) return false;
        if (!source.hasItemMeta()) return false;
        // Extract component IDs from source arrow
        String fletchingId = source.getItemMeta().getPersistentDataContainer().get(
                new NamespacedKey(ArrowUpgrade.getInstance(), "fletching"),
                PersistentDataType.STRING
        );
        String shaftId = source.getItemMeta().getPersistentDataContainer().get(
                new NamespacedKey(ArrowUpgrade.getInstance(), "shaft"),
                PersistentDataType.STRING
        );
        String pointId = source.getItemMeta().getPersistentDataContainer().get(
                new NamespacedKey(ArrowUpgrade.getInstance(), "point"),
                PersistentDataType.STRING
        );
        String effectId = source.getItemMeta().getPersistentDataContainer().get(
                new NamespacedKey(ArrowUpgrade.getInstance(), "effect"),
                PersistentDataType.STRING
        );
        // Apply components to arrow
        target.getPersistentDataContainer().set(
                new NamespacedKey(ArrowUpgrade.getInstance(), "fletching"),
                PersistentDataType.STRING,
                fletchingId != null ? fletchingId : Utils.DEFAULT_ID
        );
        target.getPersistentDataContainer().set(
                new NamespacedKey(ArrowUpgrade.getInstance(), "shaft"),
                PersistentDataType.STRING,
                shaftId != null ? shaftId : Utils.DEFAULT_ID
        );
        target.getPersistentDataContainer().set(
                new NamespacedKey(ArrowUpgrade.getInstance(), "point"),
                PersistentDataType.STRING,
                pointId != null ? pointId : Utils.DEFAULT_ID
        );
        target.getPersistentDataContainer().set(
                new NamespacedKey(ArrowUpgrade.getInstance(), "effect"),
                PersistentDataType.STRING,
                effectId != null ? effectId : Utils.DEFAULT_ID
        );
        return true;
    }
    public static class CraftedArrow {
        public final ItemStack item;
        public final String fletchingId;
        public final String shaftId;
        public final String pointId;
        public final String effectId;

        public CraftedArrow(AbstractArrow arrow) {
            item = arrow.getItemStack();
            fletchingId = arrow.getPersistentDataContainer().get(
                    new NamespacedKey(ArrowUpgrade.getInstance(), "fletching"),
                    PersistentDataType.STRING
            );
            shaftId = arrow.getPersistentDataContainer().get(
                    new NamespacedKey(ArrowUpgrade.getInstance(), "shaft"),
                    PersistentDataType.STRING
            );
            pointId = arrow.getPersistentDataContainer().get(
                    new NamespacedKey(ArrowUpgrade.getInstance(), "point"),
                    PersistentDataType.STRING
            );
            effectId = arrow.getPersistentDataContainer().get(
                    new NamespacedKey(ArrowUpgrade.getInstance(), "effect"),
                    PersistentDataType.STRING
            );
        }
    }

    /**
     * Convert a Material enum to a more user-friendly string (removes underscores and capitalizes words).
     * @param material the material to convert
     * @return a user-friendly string representation of the material
     */
    public static String matToString(Material material) {
        String name = material.name().toLowerCase().replace('_', ' ');
        String[] words = name.split(" ");
        StringBuilder friendlyName = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                friendlyName.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
            }
        }
        return friendlyName.toString().trim();
    }

    public static void givePlayer(HumanEntity player, ItemStack item) {
        HashMap<Integer,ItemStack> toDrop = player.getInventory().addItem(item);
        for (ItemStack drop : toDrop.values()) {
            player.getWorld().dropItem(player.getLocation(), drop);
        }
    }

    public static void givePlayerAll(HumanEntity player, List<ItemStack> items) {
        for (ItemStack item : items) {
            givePlayer(player, item);
        }
    }
}
