package tk.masa.commands;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import tk.masa.data.DataHandler;

public class CommandTeam{
	
    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("team")
        		.then(
        				Commands.literal("join")
        				.then(
        						Commands.argument("teamName", StringArgumentType.word())
        							.executes(
	        									c -> {
	        					                    DataHandler.addPlayer(StringArgumentType.getString(c, "teamName"), c.getSource().asPlayer().getName().getString());
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
	        					                    DataHandler.removePlayer(StringArgumentType.getString(c, "teamName"), c.getSource().asPlayer().getName().getString());
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

}