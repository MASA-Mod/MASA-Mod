package tk.masa.tileentitys.compressor;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tk.masa.masa;
import tk.masa.tileentitys.base.BlockMaschineScreenBase;

public class BlockCompressorConfigScreen extends Screen {
	
	public HashMap<Integer, Integer> config = new HashMap<>();
	
	public BlockCompressorConfigScreen(ITextComponent name) {
		super(name);
	   // GUI = new ResourceLocation(masa.MODID + ":" +"textures/gui/config_menu.png");
	}
	
	// @Override
	// protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	      //  RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	      //  this.minecraft.getTextureManager().bindTexture(GUI);
	      //  int relX = (this.width - this.xSize) / 2;
	      //  int relY = (this.height - this.ySize) / 2;
	      //  this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
	      //  int i;
	       
	        //if (i == this.container.getSelectedRecipe()) {
	         //  j1 += 18;
	        //} else if (mouseX >= k && mouseY >= i1 && mouseX < k + 16 && mouseY < i1 + 18) {
	          // j1 += 36;
	       // }

	        //this.blit(k, i1 - 1, 0, j1, 16, 18);
	  //  }
	
}
