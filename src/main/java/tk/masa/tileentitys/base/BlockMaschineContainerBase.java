package tk.masa.tileentitys.base;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import tk.masa.tileentitys.ironfurnace.SlotIronFurnace;
import tk.masa.tileentitys.ironfurnace.SlotIronFurnaceAugment;
import tk.masa.tileentitys.ironfurnace.SlotIronFurnaceFuel;


public abstract class BlockMaschineContainerBase extends Container {

    protected BlockMaschineTileBase te;
    protected IIntArray fields;
    protected PlayerEntity playerEntity;
    protected IItemHandler playerInventory;
    protected final World world;
    private TileEntity tileEntity;

    public BlockMaschineContainerBase(ContainerType<?> containerType, int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        this(containerType, windowId, world, pos, playerInventory, player, new IntArray(4));
    }

    public BlockMaschineContainerBase(ContainerType<?> containerType, int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(containerType, windowId);
        this.te = (BlockMaschineTileBase) world.getTileEntity(pos);
        tileEntity = world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.world = playerInventory.player.world;
        this.fields = fields;
        assertIntArraySize(this.fields, 4);
        this.trackIntArray(this.fields);
        if (tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            	System.out.println("itemhandler");
            	addSlot(new Slot(te, 0, 56, 17));
                addSlot(new SlotIronFurnaceFuel(this.te, 1, 56, 53));
                addSlot(new SlotIronFurnace(playerEntity, te, 2, 116, 35));
                addSlot(new SlotIronFurnaceAugment(te, 3, 26, 35));
                addSlot(new SlotItemHandler(h, 0, 64, 24));
            });
        }
      
        
        
        layoutPlayerInventorySlots(8, 84);


    }
    
    

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.te.isBurning();
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookScaled(int pixels) {
        int lvt_1_1_ = this.fields.get(2);
        int lvt_2_1_ = this.fields.get(3);
        return lvt_2_1_ != 0 && lvt_1_1_ != 0 ? lvt_1_1_ * pixels / lvt_2_1_ : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnScaled(int pixels) {
        int lvt_1_1_ = this.fields.get(1);
        if (lvt_1_1_ == 0) {
            lvt_1_1_ = 200;
        }

        return this.fields.get(0) * pixels / lvt_1_1_;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
        super.updateProgressBar(id, data);
        this.te.fields.set(id, data);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0 && index != 3) {
                if (this.func_217057_a(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (BlockMaschineTileBase.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 4 && index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 4, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 4, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
    
    protected boolean func_217057_a(ItemStack p_217057_1_) {
        return this.world.getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(new ItemStack[]{p_217057_1_}), this.world).isPresent();
    }

}
