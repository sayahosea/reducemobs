package dev.hosea.reduceMobs.cmds;

import dev.hosea.reduceMobs.handlers.GUI;
import dev.hosea.reduceMobs.handlers.Util;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class ViewCommand {

    public static void execute(@NotNull CommandSourceStack commandSourceStack) {
        assert commandSourceStack.getExecutor() != null;

        if (!(commandSourceStack.getExecutor() instanceof Player player)) {
            commandSourceStack.getExecutor().sendMessage(Util.message("player-only-cmd"));
            return;
        }

        player.openInventory(new GUI().view());
    }

}
