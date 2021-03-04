package tk.masa.fluids;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.renderer.RenderType.State;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import tk.masa.masa;
import tk.masa.blocks.ModBlocks;
import tk.masa.items.ModItems;
import tk.masa.setup.Registration;

public abstract class FluidOil extends FlowingFluid {

	@Override
	public Fluid getFlowingFluid() {
		return ModFluids.flowing_oil;
	}

	@Override
	public Fluid getStillFluid() {
		return ModFluids.oil;
	}

	@Override
	protected boolean canSourcesMultiply() {
		return false;
	}

	@Override
	protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
	
	}

	@Override
	protected int getSlopeFindDistance(IWorldReader worldIn) {
	
		return 4;
	}

	@Override
	protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
		return 8;
	}

	@Override
	public Item getFilledBucket() {
		return ModItems.oil_bucket;
	}

	@Override
	protected boolean canDisplace(IFluidState state, IBlockReader world, BlockPos pos, Fluid fluid, Direction direction) {
		return false;
	}

	@Override
	public int getTickRate(IWorldReader p_205569_1_) {
		return 30;
	}

	@Override
	protected float getExplosionResistance() {
		return 100.0f;
	}

	@Override
	protected BlockState getBlockState(IFluidState state) {
		return ModBlocks.OIL.getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
	}
	@Override
	public boolean isEquivalentTo(Fluid fluidIn) {
		return fluidIn == ModFluids.oil || fluidIn == ModFluids.flowing_oil;
	}
	@Override
	protected FluidAttributes createAttributes() {
		return FluidAttributes.builder(new ResourceLocation(masa.MODID, "blocks/oil_still"), new ResourceLocation(masa.MODID, "blocks/oil_flow"))
		.translationKey("block.masa.oil")
		.density(100)
		.build(this);
	}
	
	public static class Flowing extends FluidOil{

		@Override
		protected void fillStateContainer(Builder<Fluid, IFluidState> builder) {
			super.fillStateContainer(builder);
			builder.add(LEVEL_1_8);
		}
		
		@Override
		public boolean isSource(IFluidState state) {
			return false;
		}

		@Override
		public int getLevel(IFluidState state) {
			return state.get(FluidOil.LEVEL_1_8);
		}
		
	}
	
	public static class Source extends FluidOil {

		@Override
		public boolean isSource(IFluidState state) {
			return true;
		}

		@Override
		public int getLevel(IFluidState state) {
			return 8;
		}
		
	}
}
