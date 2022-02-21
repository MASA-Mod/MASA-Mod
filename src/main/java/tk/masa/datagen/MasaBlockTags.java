package tk.masa.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import tk.masa.masa;
import tk.masa.setup.Registration;

public class MasaBlockTags extends BlockTagsProvider {

    public MasaBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, masa.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.MYSTERIOUS_ORE_OVERWORLD.get())
                .add(Registration.MOON_ROCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Registration.MYSTERIOUS_ORE_OVERWORLD.get())
                .add(Registration.MOON_ROCK.get());
        tag(Tags.Blocks.ORES)
                .add(Registration.MYSTERIOUS_ORE_OVERWORLD.get());
    }

    @Override
    public String getName() {
        return "Tutorial Tags";
    }
}