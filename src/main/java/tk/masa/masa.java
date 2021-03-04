package tk.masa;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tk.masa.setup.ClientSetup;
import tk.masa.setup.ModSetup;
import tk.masa.setup.Registration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("masa")
public class masa
{

    public static final String MODID = "masa";
    
    private static final Logger LOGGER = LogManager.getLogger();
    
    public masa() {
    	LOGGER.log(Level.INFO, "Hi from MASA");
    	Registration.init();
    	
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);

        /*
		BlockInventoryAdvanced - Der eigentliche Block - Licht,Comperator...
		ContainerFurnace - Client Slots - Slots
		ContainerScreenFurnace - Client Draw - Background, icons
		FurnaceStateData - Save Data
		FurnaceZoneContents - Links
		TileEntityFurnace - Logic
         */        
    }

}
