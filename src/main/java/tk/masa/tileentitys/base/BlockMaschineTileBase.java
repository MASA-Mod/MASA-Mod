package tk.masa.tileentitys.base;

import com.google.common.collect.Maps;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.FurnaceFuelSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.*;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import tk.masa.tileentitys.recipes.BlastFurnaceRecipe;
import tk.masa.tools.CustomEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.Map;

public abstract class BlockMaschineTileBase extends TileEntityInventory implements ITickableTileEntity, IRecipeHolder, IRecipeHelperPopulator {
    private static final int[] SLOTS_UP = new int[]{0};
    private static final int[] SLOTS_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_HORIZONTAL = new int[]{1};

    //protected CustomEnergyStorage energyStorage = createEnergy();
    //protected ItemStackHandler itemHandler = createHandler();
    
    protected CustomEnergyStorage energyStorage = createEnergy();
    protected ItemStackHandler itemHandler = createHandler();
    
    protected int timer;
    protected int furnaceBurnTime;
    
    protected LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    protected LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);
    
    //protected LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    //protected LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    protected int cookTime;
    protected int totalCookTime = this.getCookTime();
    protected final Map<ResourceLocation, Integer> recipeUseCounts = Maps.newHashMap();

    public Map<ResourceLocation, Integer> getRecipeUseCounts() {
        return this.recipeUseCounts;
    }

    //protected IRecipeType<? extends AbstractCookingRecipe> recipeType;
    
    protected IRecipeType<? extends IRecipe> recipeType;

    public BlockMaschineTileBase(TileEntityType<?> tileentitytypeIn) {
        super(tileentitytypeIn, 4);
    }

    protected int getCookTime() {
        return getCookTimeConfig();
    }

    protected int getCookTimeConfig() {
        return 200;
    }

    public final IIntArray fields = new IIntArray() {
        public int get(int index) {
            switch (index) {
                case 0:
                    return BlockMaschineTileBase.this.furnaceBurnTime;
                case 1:
                    return BlockMaschineTileBase.this.furnaceBurnTime;
                case 2:
                    return BlockMaschineTileBase.this.cookTime;
                case 3:
                    return BlockMaschineTileBase.this.totalCookTime;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch (index) {
                case 0:
                    BlockMaschineTileBase.this.furnaceBurnTime = value;
                    break;
                case 1:
                    BlockMaschineTileBase.this.furnaceBurnTime = value;
                    break;
                case 2:
                    BlockMaschineTileBase.this.cookTime = value;
                    break;
                case 3:
                    BlockMaschineTileBase.this.totalCookTime = value;
            }

        }

        public int size() {
            return 4;
        }
    };
    
   

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }
    @Override
    public void remove() {
    	super.remove();
    	handler.invalidate();
        energy.invalidate();
    }
    
    
    protected boolean canSmelt(@Nullable IRecipe recipe) {
    	ArrayList<ItemStack> outputslots = new ArrayList<>();
    	outputslots.add(this.inventory.get(2));
        if (!this.inventory.get(0).isEmpty() && recipe != null) {
            ItemStack itemstack = recipe.getRecipeOutput();
            if (itemstack.isEmpty()) {
                return false;
            } else {
            	for(ItemStack itemstack1 : outputslots) {
	                if (itemstack1.isEmpty()) {
	                    return true;
	                } else if (!itemstack1.isItemEqual(itemstack)) {
	                    return false;
	                } else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
	                    continue;
	                } else {
	                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
	                }
	            }
            }
        } else {
            return false;
        }
        
        return true;
        
    }

    protected void smeltItem(@Nullable IRecipe recipe) {
        timer = 0;
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack itemstack = this.inventory.get(0);
            ItemStack itemstack1 = recipe.getRecipeOutput();
            ItemStack itemstack2 = this.inventory.get(2);
            if (itemstack2.isEmpty()) {
                this.inventory.set(2, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (!this.world.isRemote) {
                this.canUseRecipe(this.world, (ServerPlayerEntity) null, recipe);
            }

            itemstack.shrink(1);
        }
    }

    @Override
    public void read(CompoundNBT tag) {
    	
    	itemHandler.deserializeNBT(tag.getCompound("inv"));
    	
        ItemStackHelper.loadAllItems(tag, this.inventory);
        this.cookTime = tag.getInt("CookTime");
        this.totalCookTime = tag.getInt("CookTimeTotal");
        this.timer = 0;
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        
        int i = tag.getShort("RecipesUsedSize");
        for (int j = 0; j < i; ++j) {
            ResourceLocation resourcelocation = new ResourceLocation(tag.getString("RecipeLocation" + j));
            int k = tag.getInt("RecipeAmount" + j);
            this.recipeUseCounts.put(resourcelocation, k);
        }

        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
    	
    	tag.put("inv", itemHandler.serializeNBT());
    	
        ItemStackHelper.saveAllItems(tag, this.inventory);
        tag.putInt("CookTime", this.cookTime);
        tag.putInt("CookTimeTotal", this.totalCookTime);
        tag.putShort("RecipesUsedSize", (short) this.recipeUseCounts.size());
        tag.put("energy", energyStorage.serializeNBT());
        int i = 0;

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeUseCounts.entrySet()) {
            tag.putString("RecipeLocation" + i, entry.getKey().toString());
            tag.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }

        return super.write(tag);
    }

    LazyOptional<? extends IItemHandler>[] handlers =
            SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.removed && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }

        if (capability == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void onCrafting(PlayerEntity player) {
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for (ItemStack itemstack : this.inventory) {
            helper.accountStack(itemstack);
        }

    }

    @Override
    public void setRecipeUsed(IRecipe recipe) {
        if (this.recipeUseCounts.containsKey(recipe.getId())) {
            this.recipeUseCounts.put(recipe.getId(), this.recipeUseCounts.get(recipe.getId()) + 1);
        } else {
            this.recipeUseCounts.put(recipe.getId(), 1);
        }

    }

    @Nullable
    public IRecipe getRecipeUsed() {
        return null;
    }

    @Override
    public int[] IgetSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_DOWN;
        } else {
            return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
        }
    }

    @Override
    public boolean IcanExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            Item item = stack.getItem();
            if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
                return false;
            }
        }
        return true;
    }
    
    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(100000, 0) {
            @Override
            protected void onEnergyChanged() {
                markDirty();
            }
        };
    }
    
    //private ItemStackHandler createHandler() {
    public abstract ItemStackHandler createHandler();
    
	@Override
	public boolean IisItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

}
