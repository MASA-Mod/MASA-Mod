package tk.masa.ironfurnace;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import tk.masa.setup.Registration;

public class BlockIronFurnaceTile extends BlockIronFurnaceTileBase {
    public BlockIronFurnaceTile() {
        super(Registration.IRON_FURNACE_TILE.get());
    }

    @Override
    protected int getCookTimeConfig() {
        return 1;
    }

    @Override
    public String IgetName() {
        return "container.iron_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockIronFurnaceContainer(i, world, pos, playerInventory, playerEntity, this.fields);
    }

}
