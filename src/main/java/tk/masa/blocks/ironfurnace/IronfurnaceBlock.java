package tk.masa.blocks.ironfurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import tk.masa.blocks.base.BaseBE;
import tk.masa.blocks.base.BaseBlock;

public class IronfurnaceBlock extends BaseBlock{
    public IronfurnaceBlock() {
        super();
    }

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new IronfurnaceBE(blockPos, blockState);
	}
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace){
	    if (!level.isClientSide) {
	        BlockEntity be = level.getBlockEntity(pos);
	        if (be instanceof BaseBE) {
	            MenuProvider containerProvider = new MenuProvider() {
	                @Override
	                public Component getDisplayName() {
	                    return new TranslatableComponent(SCREEN_TUTORIAL_POWERGEN);
	                }
	
	                @Override
	                public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
	                    return new IronfurnaceContainer(windowId, pos, playerInventory, playerEntity);
	                }
	            };
	            NetworkHooks.openGui((ServerPlayer) player, containerProvider, be.getBlockPos());
	        } else {
	            throw new IllegalStateException("Our named container provider is missing!");
	        }
	    }
	    return InteractionResult.SUCCESS;
	}
}
