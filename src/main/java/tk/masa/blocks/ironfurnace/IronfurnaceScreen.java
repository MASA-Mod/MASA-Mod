package tk.masa.blocks.ironfurnace;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tk.masa.masa;
import tk.masa.blocks.base.BaseScreen;

public class IronfurnaceScreen extends BaseScreen<IronfurnaceContainer> {
	
    public IronfurnaceScreen(IronfurnaceContainer container, Inventory inv, Component name) {
        super(container, inv, name);
        GUI = new ResourceLocation(masa.MODID + ":" +"textures/gui/powered_three_in_one_out_gui.png");
    }
    
    /*

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        drawString(matrixStack, Minecraft.getInstance().font, "Energy: " + menu.getEnergy(), 10, 10, 0xffffff);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
    
    */

}
