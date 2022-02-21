package tk.masa.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.commands.ResetChunksCommand;
import tk.masa.masa;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> commands = dispatcher.register(
                Commands.literal(masa.MODID)
                        .then(CommandTeam.register(dispatcher))
                        .then(CommandTpDim.register(dispatcher))
        );

        ResetChunksCommand.register(dispatcher);
    }

}