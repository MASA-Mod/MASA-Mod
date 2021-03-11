package tk.masa.tileentitys.ironfurnace;

import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import tk.masa.tileentitys.base.BlockMaschineTileBase;

public class SlotIronFurnaceAugment extends Slot {

    private BlockMaschineTileBase te;

    public SlotIronFurnaceAugment(BlockMaschineTileBase te, int slotIndex, int xPosition, int yPosition) {
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
