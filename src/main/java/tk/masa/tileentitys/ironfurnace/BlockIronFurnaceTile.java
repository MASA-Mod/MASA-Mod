package tk.masa.tileentitys.ironfurnace;
import javax.annotation.Nonnull;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.ItemStackHandler;
import tk.masa.setup.Registration;
import tk.masa.tileentitys.base.BlockMaschineTileBase;
import tk.masa.tileentitys.recipes.BlastFurnaceRecipe;

public class BlockIronFurnaceTile extends BlockMaschineTileBase {
    public BlockIronFurnaceTile() {
        super(Registration.IRON_FURNACE_TILE.get());
    }

    @Override
    protected int getCookTimeConfig() {
        return 50;
    }

    @Override
    public String IgetName() {
        return "container.iron_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockIronFurnaceContainer(i, world, pos, playerInventory, playerEntity, this.fields);
    }
    
    @Override
    public void tick() {
    	energyStorage.addEnergy(200);
    	boolean flag1 = false;
        if (this.isRunning()) {
            --this.furnaceBurnTime;
        }

        if (!this.world.isRemote) {
            timer++;
            
            if (this.totalCookTime != this.getCookTime()) {
                this.totalCookTime = this.getCookTime();
            }
            
            //ItemStack itemstack = this.inventory.get(1);
            ItemStack itemstack = itemHandler.getStackInSlot(0);
            if (this.isRunning() || !itemstack.isEmpty()) {
            	IRecipe<?> irecipe = BlastFurnaceRecipe.findRecipe(this.inventory.get(0));
            	if (irecipe != null){
            		System.out.println(irecipe.getId().getPath().toString());
            		System.out.println(irecipe.getRecipeOutput().getDisplayName().toString());
            	}
            	//
                if (this.canProcess(irecipe)) {
            	//if (true) {
                        flag1 = true;
                        if (true) {
                            if (itemstack.hasContainerItem()) {
                                this.inventory.set(1, itemstack.getContainerItem());
                            } else if (!itemstack.isEmpty()) {
                                Item item = itemstack.getItem();
                                itemstack.shrink(1);
                                if (itemstack.isEmpty()) {
                                    Item item1 = item.getContainerItem();
                                    this.inventory.set(1, item1 == null ? ItemStack.EMPTY : new ItemStack(item1));
                                }
                            }
                        }
                }

                if (this.canProcess(irecipe)) {
                    ++this.cookTime;
                    if (this.cookTime >= this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime();
                        this.processItem(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isRunning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }
            if (timer % 24 == 0) {
                BlockState state = world.getBlockState(pos);
                if (state.get(BlockStateProperties.LIT) != this.furnaceBurnTime > 0) {
                    world.setBlockState(pos, state.with(BlockStateProperties.LIT, this.furnaceBurnTime > 0), 3);
                }
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    public ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
               // return stack.getItem() == Items.DIAMOND;
            	return true;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (stack.getItem() != Items.DIAMOND) {
                  //  return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }
     
}
