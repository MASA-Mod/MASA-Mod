package tk.masa.tileentitys.compressor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import tk.masa.setup.Registration;
import tk.masa.tileentitys.base.BlockMaschineContainerBase;

public class BlockCompressorContainer extends BlockMaschineContainerBase {

    public BlockCompressorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
    	this(windowId, world, pos, playerInventory, player, new IntArray(4));
    }

	public BlockCompressorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(Registration.COMPRESSOR_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
        
        if (tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            	System.out.println("itemhandler");
                addSlot(new SlotItemHandler(h, 0, 80, 14));//input
                addSlot(new SlotItemHandler(h, 1, 132, 50));//input
                addSlot(new SlotItemHandler(h, 2, 119, 105));//input
                addSlot(new SlotItemHandler(h, 3, 38, 105));//input
                addSlot(new SlotItemHandler(h, 4, 26, 50));//solder
                addSlot(new SlotItemHandler(h, 5, 80, 69));//board
                addSlot(new SlotItemHandler(h, 6, 80, 130));//output
                
            });
            
            layoutPlayerInventorySlots(8, 167);
            
        }
      
        
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(te.getWorld(), te.getPos()), playerEntity, Registration.COMPRESSOR.get());
    }
}
