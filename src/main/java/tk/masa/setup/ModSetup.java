package tk.masa.setup;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import tk.masa.masa;
import tk.masa.worldgen.dimensions.Dimensions;

@Mod.EventBusSubscriber(modid = masa.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
	
    public static final String TAB_NAME = "masa";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND);
        }
    };
    
    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        //bus.addListener(Ores::onBiomeLoadingEvent);
        //bus.addListener(EventPriority.NORMAL, Structures::addDimensionalSpacing);
        //bus.addListener(EventPriority.NORMAL, Structures::setupStructureSpawns);
        //bus.addGenericListener(Entity.class, ManaEvents::onAttachCapabilitiesPlayer);
        //bus.addListener(ManaEvents::onPlayerCloned);
        //bus.addListener(ManaEvents::onRegisterCapabilities);
        //bus.addListener(ManaEvents::onWorldTick);
    }
	
    public static void init(FMLCommonSetupEvent event) {
    	System.out.println("Called init");
        event.enqueueWork(() -> {
        	
        	Dimensions.register();
        	
            /*Ores.registerConfiguredFeatures();
            Structures.setupStructures();
            Structures.registerConfiguredStructures();*/
            
        });
        //Messages.register();
    }
	

    /*@SubscribeEvent
    public static void serverLoad(FMLServerStartingEvent event) {
        ModCommands.register(event.getCommandDispatcher());
    }
    
    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event)
	{
    	if (event.getWorld() instanceof ServerWorld) {
    		DataHandler.init(event.getWorld().getWorld());
        }

	}*/
    

}