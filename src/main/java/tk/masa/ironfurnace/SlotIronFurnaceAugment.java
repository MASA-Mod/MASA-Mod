package tk.masa.ironfurnace;

import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotIronFurnaceAugment extends Slot {

    private BlockIronFurnaceTileBase te;

    public SlotIronFurnaceAugment(BlockIronFurnaceTileBase te, int slotIndex, int xPosition, int yPosition) {
        super(te, slotIndex, xPosition, yPosition);
        this.te = te;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */


    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
