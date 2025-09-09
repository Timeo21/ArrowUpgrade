package ch.timeo.arrowUpgrade.factory;

import ch.timeo.arrowUpgrade.ArrowUpgrade;
import ch.timeo.arrowUpgrade.Utils;
import ch.timeo.arrowUpgrade.registry.ArrowRegistry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

public class ArrowFactory {



    public static ItemStack createArrow(Material fletching, Material shaft, Material point, ItemStack effect) {
        Material arrowMat = Material.ARROW;
        Material effectMat = effect != null ? effect.getType() : null;
        if (effectMat == Material.GLOWSTONE_DUST) arrowMat = Material.SPECTRAL_ARROW;
        if (effectMat == Material.SPLASH_POTION || effectMat == Material.POTION ||effectMat == Material.LINGERING_POTION) arrowMat = Material.TIPPED_ARROW;
        ItemStack arrow = new ItemStack(arrowMat, 8);
        StringBuilder effectDesc = new StringBuilder();
        if (arrowMat == Material.TIPPED_ARROW) {
            PotionMeta arrowMeta = (PotionMeta) arrow.getItemMeta();
            PotionMeta potionMeta = (PotionMeta) effect.getItemMeta();

            if (potionMeta != null && arrowMeta != null) {
                PotionType potionType = potionMeta.getBasePotionType();
                if (potionType == null) potionType = PotionType.AWKWARD;
                StringBuilder finalEffectDesc = effectDesc;
                potionType.getPotionEffects().forEach(potionEffect -> {
                            finalEffectDesc.append(potionEffect.getType().getName()).append(" ").append(potionEffect.getAmplifier() + 1).append(", ").append(potionEffect.getDuration() / 20).append("s; ");
                        });
                arrowMeta.setBasePotionType(potionType);
                arrow.setItemMeta(arrowMeta);
            }
        }

        ItemMeta meta = arrow.getItemMeta();

        // Encode metadata
        meta.getPersistentDataContainer().set(
                new NamespacedKey(ArrowUpgrade.getInstance(), "fletching"),
                PersistentDataType.STRING, ArrowRegistry.getFletchingId(fletching)
        );
        meta.getPersistentDataContainer().set(
                new NamespacedKey(ArrowUpgrade.getInstance(), "shaft"),
                PersistentDataType.STRING, ArrowRegistry.getShaftId(shaft)
        );
        meta.getPersistentDataContainer().set(
                new NamespacedKey(ArrowUpgrade.getInstance(), "point"),
                PersistentDataType.STRING, ArrowRegistry.getPointId(point)
        );
        if (effect != null) {
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(ArrowUpgrade.getInstance(), "effect"),
                    PersistentDataType.STRING, ArrowRegistry.getEffectId(effectMat)
            );
        }
        if (effectMat == Material.GLOWSTONE_DUST) effectDesc = new StringBuilder("Spectral");
        else if (effectMat != Material.TIPPED_ARROW && effect != null) effectDesc = new StringBuilder(Utils.matToString(effectMat));
        // Cosmetic name
        meta.displayName(Component.text("§6Special Arrow"));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Fletching: " + Utils.matToString(fletching), NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Shaft: " + Utils.matToString(shaft), NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Point: " + Utils.matToString(point), NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false));
        if (!effectDesc.isEmpty()) {
            lore.add(Component.text("Effect: " + effectDesc, NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false));
        }
        meta.lore(lore);
        arrow.setItemMeta(meta);

        return arrow;
    }
}
