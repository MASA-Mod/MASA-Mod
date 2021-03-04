package tk.masa.items;

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
import tk.masa.dimension.ModDimensions;
import tk.masa.other.LearningSystem;
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
    	
    	if(world.isRemote){
    		world.getBlockState(pos);
	    	LearningSystem.research(ReaseachEnum.PROBE_KOMET);
    	}
	    return ActionResultType.SUCCESS;
	    //test
    }
	
}
