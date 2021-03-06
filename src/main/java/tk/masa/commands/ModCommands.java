package tk.masa.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import tk.masa.masa;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> cmdDim = dispatcher.register(
                Commands.literal(masa.MODID)
                        .then(CommandTpDim.register(dispatcher))
        );
        
        LiteralCommandNode<CommandSource> cmdJoin = dispatcher.register(
                Commands.literal(masa.MODID)
                        .then(CommandTeam.register(dispatcher))
        );

    }

}