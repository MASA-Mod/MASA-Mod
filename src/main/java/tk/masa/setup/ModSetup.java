package tk.masa.setup;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import tk.masa.masa;
import tk.masa.commands.ModCommands;
import tk.masa.dimension.ModDimensions;

@Mod.EventBusSubscriber(modid = masa.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
	
	public static void init(final FMLCommonSetupEvent event) {
	}
	
	@SubscribeEvent
    public static void onDimensionRegistry(RegisterDimensionsEvent event) {
		System.out.println("DIMS");
        ModDimensions.DIMENSION_TYPE = DimensionManager.registerOrGetDimension(ModDimensions.DIMENSION_ID, Registration.DIMENSION.get(), null, true);
    }
	

    @SubscribeEvent
    public static void serverLoad(FMLServerStartingEvent event) {
        ModCommands.register(event.getCommandDispatcher());
    }

}