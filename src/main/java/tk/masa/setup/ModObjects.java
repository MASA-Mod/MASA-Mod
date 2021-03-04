package tk.masa.setup;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import tk.masa.dimension.VoidModDimension;

public class ModObjects {
	public static Item firstItem = Registration.FIRSTITEM.get();
	public static Item testItem = Registration.TESTITEM.get();
	public static Item firstblockItem = Registration.FIRSTBLOCK_ITEM.get();
	
	
	
	public static VoidModDimension testdimension = Registration.DIMENSION.get();
	public static Biome testBiome = Registration.TESTBIOME.get();
}
