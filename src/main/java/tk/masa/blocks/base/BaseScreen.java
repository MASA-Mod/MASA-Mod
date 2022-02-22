package tk.masa.blocks.base;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tk.masa.blocks.ironfurnace.IronfurnaceContainer;

public abstract class BaseScreen<T extends BaseContainer> extends AbstractContainerScreen<T> {
	
	public ResourceLocation GUI;
	private Component name;
	private Inventory playerInv;

    public BaseScreen(T t, Inventory inv, Component name) {
        super(t, inv, name);
        playerInv = inv;
        this.name = name;
    }

	@Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        drawString(matrixStack, Minecraft.getInstance().font, "Energy: " + menu.getEnergy(), 10, 10, 0xffffff);
        drawString(matrixStack, Minecraft.getInstance().font, this.playerInv.getDisplayName().getString(), 7, this.getYSize() - 93, 4210752);
        //drawString(matrixStack, Minecraft.getInstance().font, name.getString(), 7 + this.getXSize() / 2 - this.minecraft.fontRenderer.getStringWidth(name.getString()) / 2, 6, 4210752);
        
        drawString(matrixStack, Minecraft.getInstance().font, name.getString(), 7 + this.getXSize() / 2, 6, 4210752);

        if(162 <= (mouseX - getGuiLeft()) && 169 >= (mouseX - getGuiLeft())) {
        	if(12 <= (mouseY - getGuiTop()) && 73 >= (mouseY - getGuiTop())) {
	            String s = String.valueOf(menu.getEnergy()) + "/" + String.valueOf(menu.getEnergy());
	            renderTooltip(matrixStack, mouseX - getGuiLeft(), mouseY - getGuiTop());
        	}
        }
    }
    
    
    
    /*
    @Override
    protected void renderFg(int mouseX, int mouseY) {
  
        this.minecraft.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedComponentText(), 7, this.ySize - 93, 4210752);
        
        this.minecraft.fontRenderer.drawString(name.getUnformattedComponentText(), 7 + this.xSize / 2 - this.minecraft.fontRenderer.getStringWidth(name.getUnformattedComponentText()) / 2, 6, 4210752);
        
        if(162 <= (mouseX - guiLeft) && 169 >= (mouseX - guiLeft)) {
        	if(12 <= (mouseY - guiTop) && 73 >= (mouseY - guiTop)) {
	            String s = String.valueOf(container.te.energyStorage.getEnergyStored()) + "/" + String.valueOf(container.te.energyStorage.getMaxEnergyStored());
	            renderTooltip(s, mouseX - guiLeft, mouseY - guiTop);
        	}
        }
        	
    }
	*/
    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        int i;
        
        i = ((BaseContainer)this.menu).getEnergyScaled(60);
        this.blit(matrixStack, getGuiLeft() + 162, getGuiTop() + 73, 248, 61, 8, -i);
    }
}