package tk.masa.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class FirstBlock extends Block{

	public FirstBlock() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(10f, 10f).harvestTool(ToolType.PICKAXE).lightValue(15));
	}

}
