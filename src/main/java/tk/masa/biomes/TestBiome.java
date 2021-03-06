package tk.masa.biomes;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import tk.masa.blocks.BlockList;
import tk.masa.blocks.ModBlocks;

public class TestBiome extends Biome{

	public TestBiome() {
		super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(ModBlocks.MARS_DUST.getDefaultState(), ModBlocks.MARS_COMPRESSED_DUST.getDefaultState(), ModBlocks.MARS_ROCK.getDefaultState()))
				.precipitation(RainType.NONE)
				.category(Category.NONE)
				.downfall(0f)
				.depth(1f)
				.temperature(0.5f)
				.scale(0.1f)
				.waterColor(0x047d53)
				.waterFogColor(0x047d53)
				.parent(null)
				);
		
		
		//DefaultBiomeFeatures.addLakes(this);
	}
	
}
