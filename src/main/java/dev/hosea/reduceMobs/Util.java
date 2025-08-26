package dev.hosea.reduceMobs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Util {

    private Util() {}

    private final static Plugin plugin = ReduceMobs.getPlugin();
    private final static FileConfiguration config = plugin.getConfig();

    public static void logError(String message) {
        plugin.getLogger().severe(message);
    }

    public static FileConfiguration config() {
        return config;
    }

    public static Object getEntity(String entity) {
        return plugin.getConfig().get("entities." + entity);
    }

    public static Component format(String message) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(message)
        .decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }

    public static String confStr(String key) {
        return config.getString("message." + key);
    }

    public static String messageStr(String key) {
        return confStr("prefix") + confStr(key);
    }

    public static Component message(String key) {
        return format(messageStr(key));
    }
}
