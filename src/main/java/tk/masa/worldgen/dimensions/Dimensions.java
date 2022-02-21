package tk.masa.worldgen.dimensions;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import tk.masa.masa;

public class Dimensions {

    public static final ResourceKey<Level> MARS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(masa.MODID, "mars"));

    public static void register() {
    	System.out.println("Registered mars_chunkgen");
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(masa.MODID, "mars_chunkgen"),
                MarsChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(masa.MODID, "biomes"),
                MarsBiomeProvider.CODEC);
    }
}