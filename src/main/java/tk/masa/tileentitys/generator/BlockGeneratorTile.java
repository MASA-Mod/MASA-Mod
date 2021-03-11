package tk.masa.tileentitys.generator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import tk.masa.setup.Registration;
import tk.masa.tileentitys.base.BlockMaschineTileBase;

public class BlockGeneratorTile extends BlockMaschineTileBase {
    public BlockGeneratorTile() {
        super(Registration.IRON_FURNACE_TILE.get());
    }

    @Override
    protected int getCookTimeConfig() {
        return 1;
    }

    @Override
    public String IgetName() {
        return "container.generator";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockGeneratorContainer(i, world, pos, playerInventory, playerEntity, this.fields);
    }

}
