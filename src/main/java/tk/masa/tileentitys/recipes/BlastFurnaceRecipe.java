package tk.masa.tileentitys.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import tk.masa.masa;
import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class BlastFurnaceRecipe extends MasaSerializableRecipe
{
	public static IRecipeType<BlastFurnaceRecipe> TYPE = IRecipeType.register(masa.MODID+":blast_recipe");
	public static RegistryObject<MasaRecipeSerializer<BlastFurnaceRecipe>> SERIALIZER;

	public final Ingredient input;
	public final ItemStack output;
	@Nonnull
	public final ItemStack slag;
	public final int time;

	public BlastFurnaceRecipe(ResourceLocation id, ItemStack output, Ingredient input, int time, @Nonnull ItemStack slag)
	{
		super(output, TYPE, id);
		this.output = output;
		this.input = input;
		this.time = time;
		this.slag = slag;
		System.out.println(this.id.toString());
		recipeList.put(id, this);
		
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return output;
	}

	// Initialized by reload listener
	//public static Map<ResourceLocation, BlastFurnaceRecipe> recipeList = Collections.emptyMap();
	public static HashMap<ResourceLocation, BlastFurnaceRecipe> recipeList = new HashMap<>();

	public static BlastFurnaceRecipe findRecipe(ItemStack input)
	{
		System.out.println(recipeList.values().toString());
		for(BlastFurnaceRecipe recipe : recipeList.values()) {
			System.out.println(recipe.output);
			if(recipe.input.test(input)) {
				
				return recipe;
			}
		}
		return null;
	}

	@Override
	protected MasaRecipeSerializer getMasaSerializer() {
		return SERIALIZER.get();
	}
}
