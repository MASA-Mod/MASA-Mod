package tk.masa.items;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.ITeleporter;
import tk.masa.data.ReaseachEnum;
import tk.masa.data.TeamManager;
import tk.masa.setup.ModSetup;
import tk.masa.setup.Registration;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

public class ProbeItem extends Item{
	
	Minecraft mc = Minecraft.getInstance();
	
	public ProbeItem() {
		super(new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(1));
	}
	

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent("message.firstitem"));
    }
    
    @Override
	public InteractionResult useOn(UseOnContext context) {
    	System.out.println("Test");
    	BlockPos pos = context.getClickedPos();
    	Level world = context.getLevel();
    	
    	//DataHandler.addPlayer("TestTeam", context.getPlayer().getDisplayName().getString());
    	TeamManager.addAspectToPlayer(context.getPlayer().getName().getString(), ReaseachEnum.PREASURE_ORBIT);
    	
    	if(world.isClientSide){
    		BlockState bs = world.getBlockState(pos);
    		
    		if(bs == Registration.ASTEROID_ROCK.get().defaultBlockState()) {
    			TeamManager.addAspectToPlayer(context.getPlayer().getName().getString(), ReaseachEnum.PROBE_KOMET);
    			context.getPlayer().displayClientMessage(new TranslatableComponent("item.probe.komet"), true);
    		}else if(bs == Blocks.STONE.defaultBlockState()) {
    			TeamManager.addAspectToPlayer(context.getPlayer().getName().getString(), ReaseachEnum.PROBE_EARTH);
    			context.getPlayer().displayClientMessage(new TranslatableComponent("item.probe.earth"), true);
    		}else if(bs == Registration.MOON_ROCK.get().defaultBlockState()) {
    			TeamManager.addAspectToPlayer(context.getPlayer().getName().getString(), ReaseachEnum.PROBE_MOON);
    			context.getPlayer().displayClientMessage(new TranslatableComponent("item.probe.moon"), true);
    		}else {
    			context.getPlayer().displayClientMessage(new TranslatableComponent("item.probe.none"), true);
    		}
    		
    	}
    	
	    return InteractionResult.SUCCESS;

    }
	
}
