package dev.hosea.reduceMobs;

import dev.hosea.reduceMobs.events.DetectEntitySpawn;
import dev.hosea.reduceMobs.events.DetectGUIClick;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ReduceMobsEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void entitySpawn(CreatureSpawnEvent e) {
        new DetectEntitySpawn().execute(e);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent e) {
        new DetectGUIClick().execute(e);
    }
}
