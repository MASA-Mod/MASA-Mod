package tk.masa.tileentitys.compressor;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.masa.masa;
import tk.masa.tileentitys.base.BlockMaschineScreenBase;

@OnlyIn(Dist.CLIENT)
public class BlockCompressorScreen extends BlockMaschineScreenBase<BlockCompressorContainer> {

    public BlockCompressorScreen(BlockCompressorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        this.xSize = 176;
        this.ySize = 249;
        GUI = new ResourceLocation(masa.MODID + ":" +"textures/gui/soldering_station.png");
    }
}
