package tk.masa.dimension;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.OverworldChunkGenerator;

public class VoidDimension extends Dimension{
	
	    public VoidDimension(World world, DimensionType type) {
	        super(world, type, 0.0f);
	    }

	    @Override
	    public ChunkGenerator<?> createChunkGenerator() {
	        return new VoidChunkGenerator(world, new VoidBiomeProvider());
	    }
	    
	    @Nullable
	    @Override
	    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
	        return null;
	    }

	    @Nullable
	    @Override
	    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
	        return null;
	    }

	    @Override
	    public int getActualHeight() {
	        return 256;
	    }

	    @Override
	    public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
	        return SleepResult.ALLOW;
	    }

	    @Override
	    public float calculateCelestialAngle(long worldTime, float partialTicks) {
	        double d0 = MathHelper.frac((double)worldTime / 2400.0D - 0.25D);
	        double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
	        float result = (float)(d0 * 2.0D + d1) / 3.0F;;
	        return result;
	     }

	    @Override
	    public boolean isSurfaceWorld() {
	        return true;
	    }

	    @Override
	    public boolean hasSkyLight() {
	        return true;
	    }

	    @Override
	    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
	        return new Vec3d(0, 0, 0);
	    }

	    @Override
	    public boolean canRespawnHere() {
	        return false;
	    }

	    @Override
	    public boolean doesXZShowFog(int x, int z) {
	        return false;
	    }

}
