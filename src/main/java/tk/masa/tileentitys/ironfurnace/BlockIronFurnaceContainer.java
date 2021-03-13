package tk.masa.tileentitys.ironfurnace;

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

public class BlockIronFurnaceContainer extends BlockMaschineContainerBase {

    public BlockIronFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
    	this(windowId, world, pos, playerInventory, player, new IntArray(4));
    }

	public BlockIronFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(Registration.IRON_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
        
        if (tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            	System.out.println("itemhandler");
                addSlot(new SlotItemHandler(h, 0, 64, 24));
                addSlot(new SlotItemHandler(h, 1, 64, 54));
                
            });
            
            layoutPlayerInventorySlots(8, 84);
        }
      
        
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(te.getWorld(), te.getPos()), playerEntity, Registration.IRON_FURNACE.get());
    }
}
