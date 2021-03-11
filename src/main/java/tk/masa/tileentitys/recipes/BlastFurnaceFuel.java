package tk.masa.tileentitys.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import tk.masa.masa;

import java.util.Collections;
import java.util.Map;

public class BlastFurnaceFuel extends MasaSerializableRecipe
{
	public static IRecipeType<BlastFurnaceFuel> TYPE = IRecipeType.register(masa.MODID+":blast_furnace_fuel");
	public static RegistryObject<MasaRecipeSerializer<BlastFurnaceFuel>> SERIALIZER;

	// Initialized by reload listener
	public static Map<ResourceLocation, BlastFurnaceFuel> blastFuels = Collections.emptyMap();

	public final Ingredient input;
	public final int burnTime;

	public BlastFurnaceFuel(ResourceLocation id, Ingredient input, int burnTime)
	{
		super(ItemStack.EMPTY, TYPE, id);
		this.input = input;
		this.burnTime = burnTime;
	}

	public static int getBlastFuelTime(ItemStack stack)
	{
		for(BlastFurnaceFuel e : blastFuels.values())
			if(e.input.test(stack))
				return e.burnTime;
		return 0;
	}

	public static boolean isValidBlastFuel(ItemStack stack)
	{
		return getBlastFuelTime(stack) > 0;
	}

	@Override
	protected MasaRecipeSerializer<BlastFurnaceFuel> getMasaSerializer()
	{
		return SERIALIZER.get();
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return ItemStack.EMPTY;
	}

}

