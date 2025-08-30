package ch.timeo.arrowCrafting.listeners;

import ch.timeo.arrowCrafting.ArrowCrafting;
import ch.timeo.arrowCrafting.factory.ArrowFactory;
import ch.timeo.arrowCrafting.gui.FletchingGUI;
import ch.timeo.arrowCrafting.registry.ArrowRegistry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FletchingListener implements Listener {
    @EventHandler
    public void onOpenFletching(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() != Material.FLETCHING_TABLE) return;

        event.setCancelled(true); // prevent vanilla interaction
        Player player = event.getPlayer();
        player.openInventory(FletchingGUI.create(player));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(FletchingGUI.GUI_NAME)) return;
        Inventory inv = event.getInventory();

        // Allow placing in crafting slots, cancel others
        int slot = event.getRawSlot();
        if (slot < FletchingGUI.INVENTORY_SIZE && slot != FletchingGUI.SLOT_FLETCHING &&
                slot != FletchingGUI.SLOT_SHAFT &&
                slot != FletchingGUI.SLOT_POINT &&
                slot != FletchingGUI.SLOT_EFFECT &&
                slot != FletchingGUI.SLOT_RESULT) {
            event.setCancelled(true);
            return;
        }

        // Delay update by 1 tick (because item isn't placed yet in inventory)
        Bukkit.getScheduler().runTaskLater(ArrowCrafting.getInstance(), () -> {
            updateResultPreview(inv);
        }, 1L);

        // Handle actual craft when clicking result
        if (slot == FletchingGUI.SLOT_RESULT) {
            event.setCancelled(true); // prevent taking ghost item directly
            craftArrow(event.getWhoClicked(), inv);
        }
    }

    public static boolean isValidMaterial(Material fletching, Material shaft, Material point, Material effect) {
        return ArrowRegistry.isValidFletching(fletching) &&
                ArrowRegistry.isValidShaft(shaft) &&
                ArrowRegistry.isValidPoint(point) &&
                (effect == null || ArrowRegistry.isValidEffect(effect));
    }

    private void updateResultPreview(Inventory inv) {
        ItemStack fletching = inv.getItem(FletchingGUI.SLOT_FLETCHING);
        ItemStack shaft = inv.getItem(FletchingGUI.SLOT_SHAFT);
        ItemStack point = inv.getItem(FletchingGUI.SLOT_POINT);
        ItemStack effect = inv.getItem(FletchingGUI.SLOT_EFFECT);

        if (fletching == null || shaft == null || point == null) {
            inv.setItem(FletchingGUI.SLOT_RESULT, FletchingGUI.invalidItem);
            return;
        }

        if (!isValidMaterial(
                fletching.getType(),
                shaft.getType(),
                point.getType(),
                effect != null ? effect.getType() : null
        )) {
            inv.setItem(FletchingGUI.SLOT_RESULT, FletchingGUI.invalidItem);
            return;
        }


        // Build a preview arrow (just one)
        ItemStack preview = ArrowFactory.createArrow(
                fletching.getType(),
                shaft.getType(),
                point.getType(),
                effect != null ? effect.getType() : null
        );

        // Mark it as "Preview Only"
        ItemMeta meta = preview.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        lore.add("§8Click to craft this arrow");
        meta.setLore(lore);
        preview.setItemMeta(meta);

        inv.setItem(FletchingGUI.SLOT_RESULT, preview);
    }

    private void craftArrow(HumanEntity player, Inventory inv) {
        ItemStack fletching = inv.getItem(FletchingGUI.SLOT_FLETCHING);
        ItemStack shaft = inv.getItem(FletchingGUI.SLOT_SHAFT);
        ItemStack point = inv.getItem(FletchingGUI.SLOT_POINT);
        ItemStack effect = inv.getItem(FletchingGUI.SLOT_EFFECT);

        if (fletching == null || shaft == null || point == null) {
            player.sendMessage("§cYou must fill all required slots!");
            return;
        }

        // Create arrow
        ItemStack result = ArrowFactory.createArrow(
                fletching.getType(),
                shaft.getType(),
                point.getType(),
                effect != null ? effect.getType() : null
        );

        if (result.getType() == Material.BARRIER) {
            player.sendMessage("§cInvalid arrow combination!");
            return;
        }
        // Give to player
        player.getInventory().addItem(result);
        player.sendMessage("§aYou crafted a custom arrow!");

        // Reduce crafting slot material number by 1
        inv.setItem(FletchingGUI.SLOT_FLETCHING, fletching.subtract());
        inv.setItem(FletchingGUI.SLOT_SHAFT, shaft.subtract());
        inv.setItem(FletchingGUI.SLOT_POINT, point.subtract());
        if (effect != null) inv.setItem(FletchingGUI.SLOT_EFFECT, effect.subtract());
        inv.setItem(FletchingGUI.SLOT_RESULT, result.subtract());
    }


}
