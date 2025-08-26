package dev.hosea.reduceMobs;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("UnstableApiUsage")
public final class ReduceMobs extends JavaPlugin {

    private static Plugin plugin;
    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;

        getServer().getPluginManager().registerEvents(new DetectEntitySpawn(), this);

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS,
                event -> event.registrar().register("reducemobs", new ReduceMobsCmd())
        );
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
