package tk.masa.tileentitys.ironfurnace;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.MathHelper;
import tk.masa.setup.Registration;
import tk.masa.tileentitys.base.BlockMaschineTileBase;
import tk.masa.tileentitys.recipes.BlastFurnaceRecipe;

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
    
    @Override
    public void tick() {
    	for(Object o: this.inventory.toArray()) {
    		//System.out.println(o.toString());
    	}
    	energyStorage.addEnergy(10);
    	boolean flag1 = false;
        if (this.isBurning()) {
            --this.furnaceBurnTime;
        }

        if (!this.world.isRemote) {
            timer++;
            
            if (this.totalCookTime != this.getCookTime()) {
                this.totalCookTime = this.getCookTime();
            }
            
            if (this.recipeType != IRecipeType.SMELTING) {
                this.recipeType = IRecipeType.SMELTING;
            }
            
            //ItemStack itemstack = this.inventory.get(1);
            ItemStack itemstack = itemHandler.getStackInSlot(0);
            if (this.isBurning() || !itemstack.isEmpty()) {
                //IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.world).orElse(null);
            	//IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe((IRecipeType<BlastFurnaceRecipe>) this.recipeType, this, this.world).orElse(null);
            	//IRecipe<?> irecipe = this.getRecipe(this.inventory.get(0));
            	//System.out.println(irecipe.getRecipeOutput().toString());
            	System.out.println(this.inventory.get(0).getDisplayName().toString());
            	IRecipe<?> irecipe = BlastFurnaceRecipe.findRecipe(this.inventory.get(0));
            	if (irecipe != null){
            		System.out.println(irecipe.getId().getPath().toString());
            		System.out.println(irecipe.getRecipeOutput().getDisplayName().toString());
            	}
            	//
                if (!this.isBurning() && this.canSmelt(irecipe)) {
            	//if (true) {
                    this.furnaceBurnTime = getBurnTime(itemstack) * this.getCookTime() / 200;
                    this.currentItemBurnTime = this.furnaceBurnTime;
                    if (this.isBurning()) {
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
                }

                if (this.isBurning() && this.canSmelt(irecipe)) {
                    ++this.cookTime;
                    if (this.cookTime >= this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime();
                        this.smeltItem(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
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
     
}
