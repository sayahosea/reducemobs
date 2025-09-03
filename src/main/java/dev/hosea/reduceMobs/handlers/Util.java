package dev.hosea.reduceMobs.handlers;

import dev.hosea.reduceMobs.ReduceMobs;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

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
        return config.getString(key);
    }

    public static String messageStr(String key) {
        return confStr("message.prefix") + confStr("message." + key);
    }

    public static Component message(String key) {
        return format(messageStr(key));
    }

    // definitely not stolen from here https://www.spigotmc.org/wiki/creating-a-gui-inventory/
    public static ItemStack createGuiItem(
            @NotNull final Material material, final int amount, final String name, @Nullable final Component... lore
    ) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();

        meta.displayName(Util.format(name));
        meta.lore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }
}
