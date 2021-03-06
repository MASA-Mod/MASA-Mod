package tk.masa.items;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import tk.masa.blocks.ModBlocks;
import tk.masa.data.DataHandler;
import tk.masa.dimension.ModDimensions;
import tk.masa.other.ReaseachEnum;
import tk.masa.setup.Registration;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

public class TestItem extends Item{
	
	Minecraft mc = Minecraft.getInstance();
	
	public TestItem() {
		super(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC));
	}
	

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("message.firstitem"));
    }
    
    @Override
	public ActionResultType onItemUse(ItemUseContext context) {
    	System.out.println("Test");
    	BlockPos pos = context.getPos();
    	World world = context.getWorld();
    	
    	//DataHandler.addPlayer("TestTeam", context.getPlayer().getDisplayName().getString());
    	DataHandler.addAspectToPlayer(context.getPlayer().getName().getString(), ReaseachEnum.PREASURE_ORBIT);
    	
    	if(world.isRemote){
    		BlockState bs = world.getBlockState(pos);
    		
    		if(bs == ModBlocks.ASTEROID_ROCK.getDefaultState()) {
    			DataHandler.addAspectToPlayer(context.getPlayer().getName().getString(), ReaseachEnum.PROBE_KOMET);
    			context.getPlayer().sendStatusMessage(new TranslationTextComponent("item.probe.komet"), true);
    		}else if(bs == Blocks.STONE.getDefaultState()) {
    			DataHandler.addAspectToPlayer(context.getPlayer().getName().getString(), ReaseachEnum.PROBE_EARTH);
    			context.getPlayer().sendStatusMessage(new TranslationTextComponent("item.probe.earth"), true);
    		}else if(bs == ModBlocks.MOON_ROCK.getDefaultState()) {
    			DataHandler.addAspectToPlayer(context.getPlayer().getName().getString(), ReaseachEnum.PROBE_MOON);
    			context.getPlayer().sendStatusMessage(new TranslationTextComponent("item.probe.moon"), true);
    		}else {
    			context.getPlayer().sendStatusMessage(new TranslationTextComponent("item.probe.none"), true);
    		}
    		
    	}
	    return ActionResultType.SUCCESS;

    }
	
}
