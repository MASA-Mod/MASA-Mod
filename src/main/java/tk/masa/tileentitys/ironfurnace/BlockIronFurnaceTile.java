package tk.masa.tileentitys.ironfurnace;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.MathHelper;
import tk.masa.setup.Registration;
import tk.masa.tileentitys.base.BlockMaschineTileBase;

public class BlockIronFurnaceTile extends BlockMaschineTileBase {
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
