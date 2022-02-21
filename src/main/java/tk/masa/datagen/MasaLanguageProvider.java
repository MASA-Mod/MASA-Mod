package tk.masa.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import tk.masa.masa;
import tk.masa.setup.Registration;

public class MasaLanguageProvider extends LanguageProvider {

    public MasaLanguageProvider(DataGenerator gen, String locale) {
        super(gen, masa.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + masa.MODID, "Tutorial");
        add(Registration.MYSTERIOUS_ORE_OVERWORLD.get(), "Mysterious ore");
        add(Registration.MOON_ROCK.get(), "Moon rock");
    }
}

