package dev.hosea.reduceMobs.events;

import dev.hosea.reduceMobs.handlers.Util;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DetectGUIClick {

    public void execute(InventoryClickEvent e) {
        if (e.getView().getOriginalTitle().equals(Util.confStr("gui-name"))) {
            e.setCancelled(true);
        }
    }

}
