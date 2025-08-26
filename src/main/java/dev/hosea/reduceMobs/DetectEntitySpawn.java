package dev.hosea.reduceMobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.configuration.MemorySection;

import java.util.concurrent.ThreadLocalRandom;

public class DetectEntitySpawn implements Listener {

    private final Class<SpawnReason> enumSpawnReasonClass = SpawnReason.class;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void entitySpawn(CreatureSpawnEvent e) {

        MemorySection result = (MemorySection) Util.getEntity(String.valueOf(e.getEntityType()));
        if (result == null) return;

        SpawnReason realReason = e.getSpawnReason();
        if (realReason == SpawnReason.COMMAND || realReason == SpawnReason.SPAWNER_EGG) return;

        try {
            double percentage = result.getDouble("percentage");
            if (percentage < 0 || percentage > 100) return;

            double value = ThreadLocalRandom.current().nextDouble(0, 101);
            if (value <= percentage) {

                String spawnReason = result.getString("spawn-reason");
                if (spawnReason != null) {
                    SpawnReason enumReason = Enum.valueOf(enumSpawnReasonClass, spawnReason);
                    if (realReason == enumReason) e.setCancelled(true);
                } else {
                    e.setCancelled(true);
                }
            }
        } catch (ClassCastException error) {
            Util.logError("Error: " + error.getMessage());
        }
    }

}
