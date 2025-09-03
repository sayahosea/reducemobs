package dev.hosea.reduceMobs.events;

import dev.hosea.reduceMobs.handlers.Util;
import org.bukkit.configuration.MemorySection;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.concurrent.ThreadLocalRandom;

public class DetectEntitySpawn {

    private final Class<CreatureSpawnEvent.SpawnReason> enumSpawnReasonClass = CreatureSpawnEvent.SpawnReason.class;

    public void execute(CreatureSpawnEvent e) {
        MemorySection result = (MemorySection) Util.getEntity(String.valueOf(e.getEntityType()));
        if (result == null) return;

        CreatureSpawnEvent.SpawnReason realReason = e.getSpawnReason();
        if (realReason == CreatureSpawnEvent.SpawnReason.COMMAND || realReason == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) return;

        try {
            double percentage = result.getDouble("percentage");
            if (percentage < 0 || percentage > 100) return;

            double value = ThreadLocalRandom.current().nextDouble(0, 101);
            if (value <= percentage) {

                String spawnReason = result.getString("spawn-reason");
                if (spawnReason != null) {
                    CreatureSpawnEvent.SpawnReason enumReason = Enum.valueOf(enumSpawnReasonClass, spawnReason);
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
