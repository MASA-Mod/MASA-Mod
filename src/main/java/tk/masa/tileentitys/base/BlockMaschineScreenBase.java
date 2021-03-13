package tk.masa.tileentitys.base;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.BeaconScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.BeaconContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.masa.masa;

@OnlyIn(Dist.CLIENT)
public abstract class BlockMaschineScreenBase<T extends BlockMaschineContainerBase> extends ContainerScreen<T> {

    public ResourceLocation GUI;
    PlayerInventory playerInv;
    ITextComponent name;

    public BlockMaschineScreenBase(T t, PlayerInventory inv, ITextComponent name) {
        super(t, inv, name);
        playerInv = inv;
        this.name = name;

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
  
        this.minecraft.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedComponentText(), 7, this.ySize - 93, 4210752);
        
        this.minecraft.fontRenderer.drawString(name.getUnformattedComponentText(), 7 + this.xSize / 2 - this.minecraft.fontRenderer.getStringWidth(name.getUnformattedComponentText()) / 2, 6, 4210752);
        
        if(162 <= (mouseX - guiLeft) && 169 >= (mouseX - guiLeft)) {
        	if(12 <= (mouseY - guiTop) && 73 >= (mouseY - guiTop)) {
	            String s = String.valueOf(container.te.energyStorage.getEnergyStored()) + "/" + String.valueOf(container.te.energyStorage.getMaxEnergyStored());
	            renderTooltip(s, mouseX - guiLeft, mouseY - guiTop);
        	}
        }
        	
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
        int i;
       

        //i = ((BlockMaschineContainerBase)this.container).getCookScaled(24);
        //this.blit(guiLeft + 79, guiTop + 34, 176, 14, i + 1, 16);
        
        i = ((BlockMaschineContainerBase)this.container).getEnergyScaled(60);
        this.blit(guiLeft + 162, guiTop + 73, 248, 61, 8, -i);
       // BeaconScreen
       // BeaconContainer
    }	

    

}
