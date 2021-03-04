package tk.masa.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.DummyWorldSaveData;
import tk.masa.masa;

public class TeamWorldSavedData extends WorldSavedData{
	
	public CompoundNBT data = new CompoundNBT();
	
	private static final String DATA_NAME = masa.MODID + "_TeamData";

	public TeamWorldSavedData() {
		super(DATA_NAME);
	}
	public TeamWorldSavedData(String s) {
		super(s);
		markDirty();
	}
	
	@Override
	public void read(CompoundNBT nbt)
	{
		System.out.println("readData");
		System.out.println(nbt.toString());
		data = nbt.getCompound("Team");
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt)
	{
		nbt.put("Team", data);
		return nbt;
	}
	
	public static TeamWorldSavedData get(World world) {

		ServerWorld sworld = world.getServer().getWorld(DimensionType.OVERWORLD);
		DimensionSavedDataManager storage = sworld.getSavedData();
		return storage.getOrCreate(TeamWorldSavedData::new, DATA_NAME);
	}
	
	
}
