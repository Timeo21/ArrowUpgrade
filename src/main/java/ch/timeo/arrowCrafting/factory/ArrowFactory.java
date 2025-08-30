package ch.timeo.arrowCrafting.factory;

import ch.timeo.arrowCrafting.ArrowCrafting;
import ch.timeo.arrowCrafting.Utils;
import ch.timeo.arrowCrafting.registry.ArrowRegistry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ArrowFactory {



    public static ItemStack createArrow(Material fletching, Material shaft, Material point, Material effect) {
        ItemStack arrow = new ItemStack(Material.ARROW, 8);
        ItemMeta meta = arrow.getItemMeta();

        // Encode metadata
        meta.getPersistentDataContainer().set(
                new NamespacedKey(ArrowCrafting.getInstance(), "fletching"),
                PersistentDataType.STRING, ArrowRegistry.getFletchingId(fletching)
        );
        meta.getPersistentDataContainer().set(
                new NamespacedKey(ArrowCrafting.getInstance(), "shaft"),
                PersistentDataType.STRING, ArrowRegistry.getShaftId(shaft)
        );
        meta.getPersistentDataContainer().set(
                new NamespacedKey(ArrowCrafting.getInstance(), "point"),
                PersistentDataType.STRING, ArrowRegistry.getPointId(point)
        );
        if (effect != null) {
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(ArrowCrafting.getInstance(), "effect"),
                    PersistentDataType.STRING, ArrowRegistry.getEffectId(effect)
            );
        }

        // Cosmetic name
        meta.displayName(Component.text("§6Special Arrow"));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Fletching: " + Utils.matToString(fletching), NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Shaft: " + Utils.matToString(shaft), NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Point: " + Utils.matToString(point), NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false));
        if (effect != null) {
            lore.add(Component.text("Effect: " + effect.name()));
        }
        meta.lore(lore);
        arrow.setItemMeta(meta);

        return arrow;
    }
}
