package tk.masa.tileentitys.generator;

import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import tk.masa.tileentitys.base.BlockMaschineTileBase;

public class SlotGenerator extends Slot {
    BlockMaschineTileBase te;

    public SlotGenerator(BlockMaschineTileBase te, int index, int x, int y) {
        super(te, index, x, y);
        this.te = te;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack) {
        return this.te.isItemFuel(stack) || isBucket(stack);
    }

    public int getItemStackLimit(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.getItem() == Items.BUCKET;
    }

}
