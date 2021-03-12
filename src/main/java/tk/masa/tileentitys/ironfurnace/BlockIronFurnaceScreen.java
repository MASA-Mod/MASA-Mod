package tk.masa.tileentitys.ironfurnace;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.masa.masa;
import tk.masa.tileentitys.base.BlockMaschineScreenBase;

@OnlyIn(Dist.CLIENT)
public class BlockIronFurnaceScreen extends BlockMaschineScreenBase<BlockIronFurnaceContainer> {

    public BlockIronFurnaceScreen(BlockIronFurnaceContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        GUI = new ResourceLocation(masa.MODID + ":" +"textures/gui/powered_three_in_one_out_gui.png");
    }
}
