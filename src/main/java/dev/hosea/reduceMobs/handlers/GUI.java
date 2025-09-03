package dev.hosea.reduceMobs.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;

import java.util.Set;

public class GUI {

    public Inventory view() {
        Inventory inv = Bukkit.createInventory(null, 45, Util.format(Util.confStr("gui-name")));

        ConfigurationSection entitiesSection = Util.config().getConfigurationSection("entities");
        if (entitiesSection == null) return inv;

        Set<String> entities = entitiesSection.getKeys(false);
        if (entities.isEmpty()) return inv;

        for (String entity : entities) {
            int change = entitiesSection.getInt(entity + ".percentage");
            String spawnReason = entitiesSection.getString(entity + ".spawn-reason");

            inv.addItem(Util.createGuiItem(
                    Material.EGG, 1,
                    "&f" + entity,
                    Util.format("&7• &fDespawn Change: &b" + change + "%"),
                    Util.format("&7• &fReason: &b" + ((spawnReason == null) ? "Any" : spawnReason))
            ));
        }

        return inv;
    }

}
