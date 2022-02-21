package tk.masa.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tk.masa.masa;
import tk.masa.blocks.ironfurnace.IronfurnaceBlock;
import tk.masa.blocks.ironfurnace.IronfurnaceScreen;
import tk.masa.blocks.powergen.PowergenRenderer;
import tk.masa.blocks.powergen.PowergenScreen;

@Mod.EventBusSubscriber(modid = masa.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.POWERGEN_CONTAINER.get(), PowergenScreen::new);
            //MenuScreens.register(Registration.IRONFURNACE_CONTAINER.get(), IronfurnaceScreen::new);
        	
        	//MenuScreens.register(Registration.POWERGEN_CONTAINER, null)
        	
            ItemBlockRenderTypes.setRenderLayer(Registration.POWERGEN.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(Registration.IRONFURNACE.get(), RenderType.translucent());
            PowergenRenderer.register();
        });
        
        //MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
        //KeyBindings.init();
        //OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "name", ManaOverlay.HUD_MANA);
    }

}