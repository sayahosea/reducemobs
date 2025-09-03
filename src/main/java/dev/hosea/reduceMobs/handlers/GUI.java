package dev.hosea.reduceMobs.handlers;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Set;

public class GUI {

    // definitely not stolen from here https://www.spigotmc.org/wiki/creating-a-gui-inventory/
    public ItemStack createItem(
            @NotNull final Material material, final int amount, final String name, @Nullable final Component... lore
    ) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();

        meta.displayName(Util.format(name));
        meta.lore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }

    public Inventory view() {
        Inventory inv = Bukkit.createInventory(null, 45, Util.format(Util.confStr("gui-name")));

        ConfigurationSection entitiesSection = Util.config().getConfigurationSection("entities");
        if (entitiesSection == null) return inv;

        Set<String> entities = entitiesSection.getKeys(false);
        if (entities.isEmpty()) return inv;

        for (String entity : entities) {
            int change = entitiesSection.getInt(entity + ".percentage");
            String spawnReason = entitiesSection.getString(entity + ".spawn-reason");

            inv.addItem(createItem(
                    Material.EGG, 1,
                    "&f" + entity,
                    Util.format("&7• &fDespawn Change: &b" + change + "%"),
                    Util.format("&7• &fReason: &b" + ((spawnReason == null) ? "Any" : spawnReason))
            ));
        }

        return inv;
    }

}
