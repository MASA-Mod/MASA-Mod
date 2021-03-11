package tk.masa.tileentitys.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import tk.masa.blocks.ModBlocks;
import tk.masa.items.TestItem;

import javax.annotation.Nullable;

public class BlastFurnaceRecipeSerializer extends MasaRecipeSerializer<BlastFurnaceRecipe>
{
	@Override
	public ItemStack getIcon()
	{
		//return new ItemStack(Multiblocks.blastFurnace);
		return new ItemStack(ModBlocks.ASTEROID_CLAY);
	}

	@Override
	public BlastFurnaceRecipe readFromJson(ResourceLocation recipeId, JsonObject json)
	{
		ItemStack output = readOutput(json.get("result"));
		Ingredient input = Ingredient.deserialize(json.get("input"));
		int time = JSONUtils.getInt(json, "time", 200);
		ItemStack slag = ItemStack.EMPTY;
		if(json.has("slag"))
			slag = readOutput(JSONUtils.getJsonObject(json, "slag"));
		System.out.println("recipemod");
		System.out.println(output.getDisplayName().getString());
		return new BlastFurnaceRecipe(recipeId, output, input, time, slag);
	}

	@Nullable
	@Override
	public BlastFurnaceRecipe read(ResourceLocation recipeId, PacketBuffer buffer)
	{
		ItemStack output = buffer.readItemStack();
		Ingredient input = Ingredient.read(buffer);
		int time = buffer.readInt();
		ItemStack slag = ItemStack.EMPTY;
		if(buffer.readBoolean())
			slag = buffer.readItemStack();
		System.out.println("recipemod");
		System.out.println(output.getDisplayName().getString());
		return new BlastFurnaceRecipe(recipeId, output, input, time, slag);
	}

	@Override
	public void write(PacketBuffer buffer, BlastFurnaceRecipe recipe)
	{
		buffer.writeItemStack(recipe.output);
		recipe.input.write(buffer);
		buffer.writeInt(recipe.time);
		buffer.writeBoolean(!recipe.slag.isEmpty());
		if(!recipe.slag.isEmpty())
			buffer.writeItemStack(recipe.slag);
	}
}
