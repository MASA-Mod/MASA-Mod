package tk.masa.datagen;

import net.minecraft.data.DataGenerator;
import tk.masa.setup.Registration;

public class MasaLootTables extends BaseLootTableProvider{
	 public MasaLootTables(DataGenerator dataGeneratorIn) {
	        super(dataGeneratorIn);
	 }

	 @Override
	 protected void addTables() {
	    lootTables.put(Registration.MYSTERIOUS_ORE_OVERWORLD.get(), createSilkTouchTable("mysterious_ore_overworld", Registration.MYSTERIOUS_ORE_OVERWORLD.get(), Registration.RAW_MYSTERIOUS_CHUNK.get(), 1, 3));
	 }
}
