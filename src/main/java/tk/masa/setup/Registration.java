package tk.masa.setup;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import static tk.masa.masa.MODID;

import net.minecraftforge.common.extensions.IForgeContainerType;


import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tk.masa.biomes.TestBiome;
import tk.masa.blocks.BlockList;
import tk.masa.blocks.FirstBlock;
import tk.masa.dimension.VoidModDimension;
import tk.masa.ironfurnace.BlockIronFurnace;
import tk.masa.ironfurnace.BlockIronFurnaceContainer;
import tk.masa.ironfurnace.BlockIronFurnaceTile;
import tk.masa.items.TestItem;

public class Registration {
	
	private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MODID);
    private static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, MODID);
    private static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, MODID);
    


    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
//----------------------------------------

    public static final RegistryObject<Item> TESTITEM = ITEMS.register("testitem", TestItem::new);
    public static final RegistryObject<Item> FIRSTITEM = ITEMS.register("firstitem", () -> new Item(new Item.Properties().group(ItemGroup.MISC).maxStackSize(4)));
    
    //public static final RegistryObject<Block> FIRSTBLOCK = BLOCKS.register("firstblock", FirstBlock::new);
    public static final RegistryObject<Block> FIRSTBLOCK = BLOCKS.register("firstblock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0)));
    public static final RegistryObject<Item> FIRSTBLOCK_ITEM = ITEMS.register("firstblock", () -> new BlockItem(FIRSTBLOCK.get(), new Item.Properties().group(ItemGroup.MISC)));
    
    
    public static final RegistryObject<Biome> TESTBIOME = BIOMES.register("testbiome", TestBiome::new);
    public static final RegistryObject<VoidModDimension> DIMENSION = DIMENSIONS.register("dimension", VoidModDimension::new);
    
    
    public static final RegistryObject<BlockIronFurnace> IRON_FURNACE = BLOCKS.register(BlockIronFurnace.IRON_FURNACE, BlockIronFurnace::new);
    public static final RegistryObject<Item> IRON_FURNACE_ITEM = ITEMS.register(BlockIronFurnace.IRON_FURNACE, () -> new BlockItem(IRON_FURNACE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<TileEntityType<BlockIronFurnaceTile>> IRON_FURNACE_TILE = TILES.register(BlockIronFurnace.IRON_FURNACE, () -> TileEntityType.Builder.create(BlockIronFurnaceTile::new, IRON_FURNACE.get()).build(null));

    public static final RegistryObject<ContainerType<BlockIronFurnaceContainer>> IRON_FURNACE_CONTAINER = CONTAINERS.register(BlockIronFurnace.IRON_FURNACE, () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new BlockIronFurnaceContainer(windowId, Minecraft.getInstance().world, pos, inv, Minecraft.getInstance().player);
    }));
    
    
    
}
