package ch.timeo.arrowCrafting.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FletchingGUI {
    public static final String GUI_NAME = "Fletching Table";
    public static final int INVENTORY_SIZE = 6*9;
    public static final int SLOT_FLETCHING = 19;
    public static final int SLOT_SHAFT = 21;
    public static final int SLOT_POINT = 23;
    public static final int SLOT_EFFECT = 25;
    public static final int SLOT_RESULT = 40;
    public static final ItemStack fillerItem = createUI(Material.GRAY_STAINED_GLASS_PANE, " ");
    public static final ItemStack invalidItem = createUI(Material.BARRIER, "Invalid Craft", NamedTextColor.RED);

    public static Inventory create(Player player) {
        Inventory inv = Bukkit.createInventory(player, INVENTORY_SIZE, Component.text(GUI_NAME));

        // filler background
        for (int i = 0; i < INVENTORY_SIZE; i++) inv.setItem(i, fillerItem);

        // icons slots
        inv.setItem(SLOT_FLETCHING-9, createUI(Material.FEATHER, "Fletching Material"));
        inv.setItem(SLOT_SHAFT-9, createUI(Material.STICK, "Shaft Material"));
        inv.setItem(SLOT_POINT-9, createUI(Material.FLINT, "Point Material"));
        inv.setItem(SLOT_EFFECT-9, createUI(Material.GLOWSTONE_DUST, "Effect Material"));

        // clear crafting slots
        inv.setItem(SLOT_FLETCHING, null);
        inv.setItem(SLOT_SHAFT, null);
        inv.setItem(SLOT_POINT, null);
        inv.setItem(SLOT_EFFECT, null);
        inv.setItem(SLOT_RESULT, invalidItem);

        return inv;
    }

    private static ItemStack createUI(Material material, String name, NamedTextColor color) {
        ItemStack fi = new ItemStack(material);
        ItemMeta fim = fi.getItemMeta();
        fim.displayName(Component.text(name, color).decoration(TextDecoration.ITALIC, false));
        fi.setItemMeta(fim);
        return fi;
    }
    private static ItemStack createUI(Material material, String name) {
        return createUI(material, name, NamedTextColor.WHITE);
    }
}
