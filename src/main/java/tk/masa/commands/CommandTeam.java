package tk.masa.commands;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import tk.masa.data.DataHandler;

public class CommandTeam implements Command<CommandSourceStack> {
	
	private static final CommandTeam CMD = new CommandTeam();
	
    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("team")
        		.requires(cs -> cs.hasPermission(0))
        		.then(
        				Commands.literal("join")
        				.then(
        						Commands.argument("teamName", StringArgumentType.word())
        							.executes(
	        									c -> {
	        					                    DataHandler.addPlayer(StringArgumentType.getString(c, "teamName"), c.getSource().getPlayerOrException().getName().getString());
	        					                    return 1;
	        					                })
        									)
        						)
        		.then(
        				Commands.literal("leave")
        				.then(
        						Commands.argument("teamName", StringArgumentType.word())
        							.executes(
	        									c -> {
	        					                    DataHandler.removePlayer(StringArgumentType.getString(c, "teamName"), c.getSource().getPlayerOrException().getName().getString());
	        					                    return 1;
	        					                })
        									)
        						)
        		.then(
        				Commands.literal("add")
        				.then(
        						Commands.argument("teamName", StringArgumentType.word())
        							.executes(
	        									c -> {
	        					                    DataHandler.addTeam(StringArgumentType.getString(c, "teamName"));
	        					                    return 1;
	        					                })
        									)
        						)
        		.then(
        				Commands.literal("delete")
        				.then(
        						Commands.argument("teamName", StringArgumentType.word())
        							.executes(
	        									c -> {
	        										DataHandler.deleteTeam(StringArgumentType.getString(c, "teamName"));
	        					                    return 1;
	        					                })
        									)
        						)
        		.then(
        				Commands.literal("debug")
        				.executes(
								c -> {
				                    DataHandler.debug();
				                    return 1;
				                })						
        		)
        		.then(
        				Commands.literal("list")
        				.executes(
								c -> {
				                    Object[] temp = DataHandler.listTeams();
				                    System.out.println(temp.toString());
				                    return 1;
				                })					
        		);

    }

	@Override
	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		return 0;
	}

}