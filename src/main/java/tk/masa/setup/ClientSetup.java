package tk.masa.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tk.masa.masa;
import tk.masa.ironfurnace.BlockIronFurnaceScreen;

@Mod.EventBusSubscriber(modid = masa.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
    	System.out.println("screens!");
        ScreenManager.registerFactory(Registration.IRON_FURNACE_CONTAINER.get(), BlockIronFurnaceScreen::new);
    }

}