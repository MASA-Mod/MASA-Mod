package tk.masa.tileentitys.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class MasaSerializableRecipe implements IRecipe<IInventory>
{
	protected final ItemStack outputDummy;
	protected final IRecipeType<?> type;
	protected final ResourceLocation id;

	protected MasaSerializableRecipe(ItemStack outputDummy, IRecipeType<?> type, ResourceLocation id)
	{
		this.outputDummy = outputDummy;
		this.type = type;
		this.id = id;
	}

	@Override
	public boolean isDynamic()
	{
		return true;
	}

	@Override
	public ItemStack getIcon(){
		return getMasaSerializer().getIcon();
	}

	@Override
	public boolean matches(IInventory inv, World worldIn)
	{
		return false;
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv)
	{
		return this.outputDummy;
	}

	@Override
	public boolean canFit(int width, int height)
	{
		return false;
	}

	@Override
	public ResourceLocation getId()
	{
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer()
	{
		return (IRecipeSerializer<?>) getMasaSerializer();
	}

	protected abstract MasaRecipeSerializer getMasaSerializer();

	@Override
	public IRecipeType<?> getType()
	{
		return this.type;
	}
}

