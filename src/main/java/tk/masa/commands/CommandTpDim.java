package tk.masa.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.SharedConstants;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import tk.masa.varia.TeleportationTools;
import tk.masa.worldgen.dimensions.Dimensions;


public class CommandTpDim implements Command<CommandSourceStack> {

    private static final CommandTpDim CMD = new CommandTpDim();

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("tp")
                .requires(cs -> cs.hasPermission(1))
                .then(Commands.argument("name", StringArgumentType.string())
                        .executes(CMD));
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        SharedConstants.IS_RUNNING_IN_IDE = true;
        ServerPlayer player = context.getSource().getPlayerOrException();
        int x = player.blockPosition().getX();
        int z = player.blockPosition().getZ();
        
        if (player.level.dimension().equals(Dimensions.MARS)) {
        	ServerLevel world = player.getServer().getLevel(Level.OVERWORLD);
            TeleportationTools.teleport(player, world, new BlockPos(x, 200, z), true);
        } else {
        	ServerLevel world = player.getServer().getLevel(Dimensions.MARS);
            TeleportationTools.teleport(player, world, new BlockPos(x, 200, z), true);
        }
        return 0;
    }
}