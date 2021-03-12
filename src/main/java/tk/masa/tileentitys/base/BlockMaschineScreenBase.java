package tk.masa.tileentitys.base;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
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
        drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), 10, 10, 0xffffff);
        this.minecraft.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedComponentText(), 7, this.ySize - 93, 4210752);
        this.minecraft.fontRenderer.drawString(name.getUnformattedComponentText(), 7 + this.xSize / 2 - this.minecraft.fontRenderer.getStringWidth(name.getUnformattedComponentText()) / 2, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
        int i;
        if (((BlockMaschineContainerBase)this.container).isBurning()) {
            i = ((BlockMaschineContainerBase)this.container).getBurnScaled(13);
            this.blit(guiLeft + 56, guiTop + 36 + 12 - i, 176, 12 - i, 14, i + 1);
        }

        i = ((BlockMaschineContainerBase)this.container).getCookScaled(24);
        this.blit(guiLeft + 79, guiTop + 34, 176, 14, i + 1, 16);
    }



}
