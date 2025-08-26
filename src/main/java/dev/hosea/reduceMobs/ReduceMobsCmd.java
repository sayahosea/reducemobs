package dev.hosea.reduceMobs;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("UnstableApiUsage")
public class ReduceMobsCmd implements BasicCommand {

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
        }
    }

    @Override
    public @Nullable String permission() {
        return "reducemobs.admin";
    }
}
