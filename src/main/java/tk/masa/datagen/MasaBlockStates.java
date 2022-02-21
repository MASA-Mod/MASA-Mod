package tk.masa.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tk.masa.masa;
import tk.masa.setup.Registration;

public class MasaBlockStates extends BlockStateProvider{
	public MasaBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, masa.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.MOON_ROCK.get());
    }
}
