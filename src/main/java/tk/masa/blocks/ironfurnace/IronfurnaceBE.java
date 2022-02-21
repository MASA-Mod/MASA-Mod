package tk.masa.blocks.ironfurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import tk.masa.blocks.base.BaseBE;
import tk.masa.setup.Registration;

public class IronfurnaceBE extends BaseBE{

	public IronfurnaceBE(BlockPos pos, BlockState state) {
		super(Registration.IRONFURNACE_BE.get(), pos, state);
	}

}
