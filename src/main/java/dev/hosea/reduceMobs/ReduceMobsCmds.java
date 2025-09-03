package dev.hosea.reduceMobs;

import dev.hosea.reduceMobs.cmds.ViewCommand;
import dev.hosea.reduceMobs.handlers.Util;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class ReduceMobsCmds implements BasicCommand {

    private final Collection<String> subCommands = List.of("help", "reload", "view");

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (args.length == 0) {
            sender.sendMessage(Util.message("missing-subcommand"));
            return;
        }

        String subcmd = args[0];
        switch (subcmd) {
            case "help" -> sender.sendMessage(Util.message("help"));
            case "reload" -> {
                ReduceMobs.getPlugin().reloadConfig();
                sender.sendMessage(Util.message("reloaded"));
            }
            case "view" -> ViewCommand.execute(commandSourceStack);
            default -> sender.sendMessage(Util.message("unknown-subcommand"));
        }
    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack source, String[] args) {
        if (args.length < 1) return subCommands;

        return subCommands.stream()
                .filter(arg -> arg.toLowerCase().startsWith(args[0].toLowerCase()))
                .toList();
    }

    @Override
    public @Nullable String permission() {
        return "reducemobs.admin";
    }
}
