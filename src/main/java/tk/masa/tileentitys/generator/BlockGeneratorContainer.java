package tk.masa.tileentitys.generator;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.masa.setup.Registration;
import tk.masa.tileentitys.base.BlockMaschineContainerBase;

public class BlockGeneratorContainer extends BlockMaschineContainerBase {

    public BlockGeneratorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(Registration.GENERATOR_CONTAINER.get(), windowId, world, pos, playerInventory, player);
    }

    public BlockGeneratorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(Registration.GENERATOR_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(te.getWorld(), te.getPos()), playerEntity, Registration.GENERATOR.get());
    }
}
