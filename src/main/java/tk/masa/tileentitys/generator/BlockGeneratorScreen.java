package tk.masa.tileentitys.generator;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.masa.tileentitys.base.BlockMaschineScreenBase;

@OnlyIn(Dist.CLIENT)
public class BlockGeneratorScreen extends BlockMaschineScreenBase<BlockGeneratorContainer> {

    public BlockGeneratorScreen(BlockGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }
}
