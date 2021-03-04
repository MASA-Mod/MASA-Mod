package tk.masa.dimension;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;
import tk.masa.blocks.BlockList;
import tk.masa.setup.Registration;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoidBiomeProvider extends BiomeProvider {

    private final Biome biome;
    private static final List<Biome> SPAWN = Collections.singletonList(Registration.TESTBIOME.get());
   // private static final List<Biome> SPAWN = Collections.singletonList(TestBiome.BIOMES.);

    public VoidBiomeProvider() {
        super(new HashSet<>(SPAWN));
       
        biome = Registration.TESTBIOME.get();
        //biome = Biomes.THE_VOID;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return biome;
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return SPAWN;
    }

    @Override
    public boolean hasStructure(Structure<?> structure) {
        return false;
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (topBlocksCache.isEmpty()) {
            topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
        }

        return topBlocksCache;
    }
}