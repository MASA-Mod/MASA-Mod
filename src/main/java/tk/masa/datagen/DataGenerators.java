package tk.masa.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import tk.masa.masa;

@Mod.EventBusSubscriber(modid = masa.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
    	System.out.println("Run datagen");
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new MasaRecipes(generator));
            generator.addProvider(new MasaLootTables(generator));
            MasaBlockTags blockTags = new MasaBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new MasaItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new MasaBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new MasaItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new MasaLanguageProvider(generator, "en_us"));
        }
    }
}
