package tk.masa.tileentitys.generator;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.masa.setup.Registration;
import tk.masa.tileentitys.base.BlockMaschineBase;
import tk.masa.tileentitys.base.BlockMaschineTileBase;

import javax.annotation.Nullable;

public class BlockGenerator extends BlockMaschineBase {
	
    public BlockGenerator() {
        super(Properties.from(Blocks.IRON_BLOCK));
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.isCreative()) {
            BlockMaschineTileBase te = (BlockMaschineTileBase) world.getTileEntity(pos);
            if (te.hasCustomName()) {
                ItemStack itemstack = new ItemStack(Registration.GENERATOR.get());
                itemstack.setDisplayName(te.getName());
                world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemstack));
            } else {
                world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Registration.IRON_FURNACE.get())));
            }
        }
        super.onBlockHarvested(world, pos, state, player);
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 1;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BlockGeneratorTile();
    }
}
