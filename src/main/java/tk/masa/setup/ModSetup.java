package tk.masa.setup;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import tk.masa.masa;
import tk.masa.commands.ModCommands;
import tk.masa.data.TeamWorldSavedData;
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
    
    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event)
	{
    	System.out.println("LOADW");
    	if (event.getWorld() instanceof ServerWorld) {
    		
    		ServerWorld s = (ServerWorld) event.getWorld();

            ServerWorld overworld = s.getServer().getWorld(DimensionType.OVERWORLD);
            
            TeamWorldSavedData twsd = TeamWorldSavedData.get(overworld);
            //CompoundNBT nbt = twsd.data;
            //System.out.println("-----");
            //System.out.println(nbt.getString());
        }

	}
    
    @SubscribeEvent
    public static void onWorldSave(WorldEvent.Save event) {
    	System.out.println("SAVEW");
    	if (event.getWorld() instanceof ServerWorld) {
    		
            //ServerWorld server = (ServerWorld) event.getWorld();
            //ServerWorld overworld = server.getServer().getWorld(DimensionType.OVERWORLD);
            //TeamWorldSavedData twsd = TeamWorldSavedData.get(overworld);
            //CompoundNBT nbt = new CompoundNBT();
        	//nbt.putString("Team", "test");
            //twsd.write(nbt);

        }

    }
    

}