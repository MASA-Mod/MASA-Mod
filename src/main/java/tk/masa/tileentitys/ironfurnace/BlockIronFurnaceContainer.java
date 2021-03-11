package tk.masa.tileentitys.ironfurnace;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.masa.setup.Registration;
import tk.masa.tileentitys.base.BlockMaschineContainerBase;

public class BlockIronFurnaceContainer extends BlockMaschineContainerBase {

    public BlockIronFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(Registration.IRON_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player);
    }

    public BlockIronFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(Registration.IRON_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(te.getWorld(), te.getPos()), playerEntity, Registration.IRON_FURNACE.get());
    }
}
