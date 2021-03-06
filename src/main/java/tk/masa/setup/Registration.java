package tk.masa.setup;

import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import static tk.masa.masa.MODID;

import com.mojang.brigadier.CommandDispatcher;

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
import tk.masa.fluids.ModFluids;
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
    private static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, MODID);
    
    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }
    
//----------------------------------------

    public static final RegistryObject<Biome> TESTBIOME = BIOMES.register("testbiome", TestBiome::new);
    public static final RegistryObject<VoidModDimension> DIMENSION = DIMENSIONS.register("dimension", VoidModDimension::new);
    
    
    public static final RegistryObject<BlockIronFurnace> IRON_FURNACE = BLOCKS.register(BlockIronFurnace.IRON_FURNACE, BlockIronFurnace::new);
    public static final RegistryObject<Item> IRON_FURNACE_ITEM = ITEMS.register(BlockIronFurnace.IRON_FURNACE, () -> new BlockItem(IRON_FURNACE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<TileEntityType<BlockIronFurnaceTile>> IRON_FURNACE_TILE = TILES.register(BlockIronFurnace.IRON_FURNACE, () -> TileEntityType.Builder.create(BlockIronFurnaceTile::new, IRON_FURNACE.get()).build(null));

    public static final RegistryObject<ContainerType<BlockIronFurnaceContainer>> IRON_FURNACE_CONTAINER = CONTAINERS.register(BlockIronFurnace.IRON_FURNACE, () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new BlockIronFurnaceContainer(windowId, Minecraft.getInstance().world, pos, inv, Minecraft.getInstance().player);
    }));
    
    
    
    
//--------------------------------------------------------------------
    
    
  //fluidtest
    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket", () -> new BucketItem(() -> ModFluids.oil, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
    public static final RegistryObject<Block> OIL = BLOCKS.register("flowing_oil", ()-> new FlowingFluidBlock(() -> ModFluids.oil, Block.Properties.create(Material.WATER).doesNotBlockMovement().noDrops()));
    
    
    //test
    public static final RegistryObject<Item> TESTITEM = ITEMS.register("testitem", TestItem::new);
    public static final RegistryObject<Item> FIRSTITEM = ITEMS.register("firstitem", () -> new Item(new Item.Properties().group(ItemGroup.MISC).maxStackSize(4)));
    public static final RegistryObject<Item> SECONDITEM = ITEMS.register("seconditem", () -> new Item(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64)));

    //public static final RegistryObject<Block> FIRSTBLOCK = BLOCKS.register("firstblock", FirstBlock::new);
    public static final RegistryObject<Block> FIRSTBLOCK = BLOCKS.register("firstblock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0)));
    public static final RegistryObject<Item> FIRSTBLOCK_ITEM = ITEMS.register("firstblock", () -> new BlockItem(FIRSTBLOCK.get(), new Item.Properties().group(ItemGroup.MISC)));

    //Moonitems
    public static final RegistryObject<Block> MOON_ROCK = BLOCKS.register("moon_rock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0)));
    public static final RegistryObject<Item> MOON_ROCK_ITEM = ITEMS.register("moon_rock", () -> new BlockItem(MOON_ROCK.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> MOON_DUST = BLOCKS.register("moon_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)))));
    public static final RegistryObject<Item> MOON_DUST_ITEM = ITEMS.register("moon_dust", () -> new BlockItem(MOON_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> MOON_COMPRESSED_DUST = BLOCKS.register("moon_compressed_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.SAND)))));
    public static final RegistryObject<Item> MOON_COMPRESSED_DUST_ITEM = ITEMS.register("moon_compressed_dust", () -> new BlockItem(MOON_COMPRESSED_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    
    //Mooncheese
    public static final RegistryObject<Block> MOON_CHEESE_ORE = BLOCKS.register("moon_cheese_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)))));
    public static final RegistryObject<Item> MOON_CHEESE_ORE_ITEM = ITEMS.register("moon_cheese_ore", () -> new BlockItem(MOON_CHEESE_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> CHEESE_ITEM = ITEMS.register("cheese", () -> new Item(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).food(new Food.Builder().fastToEat().hunger(4).saturation(0.6f).build())));
    
    //Marsitems
    public static final RegistryObject<Block> MARS_ROCK = BLOCKS.register("mars_rock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0)));
    public static final RegistryObject<Item> MARS_ROCK_ITEM = ITEMS.register("mars_rock", () -> new BlockItem(MARS_ROCK.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> MARS_DUST = BLOCKS.register("mars_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)))));
    public static final RegistryObject<Item> MARS_DUST_ITEM = ITEMS.register("mars_dust", () -> new BlockItem(MARS_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> MARS_COMPRESSED_DUST = BLOCKS.register("mars_compressed_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.SAND)))));
    public static final RegistryObject<Item> MARS_COMPRESSED_DUST_ITEM = ITEMS.register("mars_compressed_dust", () -> new BlockItem(MARS_COMPRESSED_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> MARS_ICE_CO2 = BLOCKS.register("mars_ice_co2", () -> new Block(Block.Properties.create(Material.ICE).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(-1).sound(SoundType.GLASS)))));
    public static final RegistryObject<Item> MARS_ICE_CO2_ITEM = ITEMS.register("mars_ice_co2", () -> new BlockItem(MARS_ICE_CO2.get(), new Item.Properties().group(ItemGroup.MISC)));

    //Comet
    public static final RegistryObject<Block> COMET_ICE = BLOCKS.register("comet_ice", () -> new Block(Block.Properties.create(Material.ICE).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0)));
    public static final RegistryObject<Item> COMET_ICE_ITEM = ITEMS.register("comet_ice", () -> new BlockItem(COMET_ICE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> COMET_CORE_ROCK = BLOCKS.register("comet_core_rock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> COMET_CORE_ROCK_ITEM = ITEMS.register("comet_core_rock", () -> new BlockItem(COMET_CORE_ROCK.get(), new Item.Properties().group(ItemGroup.MISC))); 
    public static final RegistryObject<Block> COMET_DUST = BLOCKS.register("comet_dust", () -> new Block(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)));
    public static final RegistryObject<Item> COMET_DUST_ITEM = ITEMS.register("comet_dust", () -> new BlockItem(COMET_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    
    //Asteroid
    public static final RegistryObject<Block> ASTEROID_CLAY = BLOCKS.register("asteroid_clay", () -> new Block(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)));
    public static final RegistryObject<Item> ASTEROID_CLAY_ITEM = ITEMS.register("asteroid_clay", () -> new BlockItem(ASTEROID_CLAY.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> ASTEROID_ROCK = BLOCKS.register("asteroid_rock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> ASTEROID_ROCK_ITEM = ITEMS.register("asteroid_rock", () -> new BlockItem(ASTEROID_ROCK.get(), new Item.Properties().group(ItemGroup.MISC))); 
    public static final RegistryObject<Block> ASTEROID_GOLD_ORE = BLOCKS.register("asteroid_gold_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> ASTEROID_GOLD_ORE_ITEM = ITEMS.register("asteroid_gold_ore", () -> new BlockItem(ASTEROID_GOLD_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> ASTEROID_SILVER_ORE = BLOCKS.register("asteroid_silver_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> ASTEROID_SILVER_ORE_ITEM = ITEMS.register("asteroid_silver_ore", () -> new BlockItem(ASTEROID_SILVER_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> ASTEROID_PLATINUM_ORE = BLOCKS.register("asteroid_platinum_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> ASTEROID_PLATINUM_ORE_ITEM = ITEMS.register("asteroid_platinum_ore", () -> new BlockItem(ASTEROID_PLATINUM_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> ASTEROID_OLIVINE_ORE = BLOCKS.register("asteroid_olivine_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> ASTEROID_OLIVINE_ORE_ITEM = ITEMS.register("asteroid_olivine_ore", () -> new BlockItem(ASTEROID_OLIVINE_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> ASTEROID_OLIVINE_GEM_ITEM = ITEMS.register("asteroid_olivine_gem", () -> new Item(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64)));
    
    //titan
    public static final RegistryObject<Block> TITAN_DUST = BLOCKS.register("titan_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)));
    public static final RegistryObject<Item> TITAN_DUST_ITEM = ITEMS.register("titan_dust", () -> new BlockItem(TITAN_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> TITAN_COMPRESSED_DUST = BLOCKS.register("titan_compressed_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.SAND)));
    public static final RegistryObject<Item> TITAN_COMPRESSED_DUST_ITEM = ITEMS.register("titan_compressed_dust", () -> new BlockItem(TITAN_COMPRESSED_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> TITAN_ROCK = BLOCKS.register("titan_rock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> TITAN_ROCK_ITEM = ITEMS.register("titan_rock", () -> new BlockItem(TITAN_ROCK.get(), new Item.Properties().group(ItemGroup.MISC)));

    //mercury
    public static final RegistryObject<Block> MERCURY_DUST = BLOCKS.register("mercury_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)));
    public static final RegistryObject<Item> MERCURY_DUST_ITEM = ITEMS.register("mercury_dust", () -> new BlockItem(MERCURY_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> MERCURY_ROCK = BLOCKS.register("mercury_rock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> MERCURY_ROCK_ITEM = ITEMS.register("mercury_rock", () -> new BlockItem(MERCURY_ROCK.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> MERCURY_COMPRESSED_DUST = BLOCKS.register("mercury_compressed_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.SAND)));
    public static final RegistryObject<Item> MERCURY_COMPRESSED_DUST_ITEM = ITEMS.register("mercury_compressed_dust", () -> new BlockItem(MERCURY_COMPRESSED_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> MERCURY_NICKEL_ORE = BLOCKS.register("mercury_nickel_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> MERCURY_NICKEL_ORE_ITEM = ITEMS.register("mercury_nickel_ore", () -> new BlockItem(MERCURY_NICKEL_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> MERCURY_IRON_ORE = BLOCKS.register("mercury_iron_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> MERCURY_IRON_ORE_ITEM = ITEMS.register("mercury_iron_ore", () -> new BlockItem(MERCURY_IRON_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));

    //venus
    public static final RegistryObject<Block> VENUS_DUST = BLOCKS.register("venus_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)));
    public static final RegistryObject<Item> VENUS_DUST_ITEM = ITEMS.register("venus_dust", () -> new BlockItem(VENUS_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> VENUS_COMPRESSED_DUST = BLOCKS.register("venus_compressed_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.SAND)));
    public static final RegistryObject<Item> VENUS_COMPRESSED_DUST_ITEM = ITEMS.register("venus_compressed_dust", () -> new BlockItem(VENUS_COMPRESSED_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> VENUS_ROCK = BLOCKS.register("venus_rock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> VENUS_ROCK_ITEM = ITEMS.register("venus_rock", () -> new BlockItem(VENUS_ROCK.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> VENUS_IRON_SILICATE = BLOCKS.register("venus_iron_silicate", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> VENUS_IRON_SILICATE_ITEM = ITEMS.register("venus_iron_silicate", () -> new BlockItem(VENUS_IRON_SILICATE.get(), new Item.Properties().group(ItemGroup.MISC)));
    
    //io
    public static final RegistryObject<Block> IO_SULFUR_DUST = BLOCKS.register("io_sulfur_dust", () -> new Block(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)));
    public static final RegistryObject<Item> IO_SULFUR_DUST_ITEM = ITEMS.register("io_sulfur_dust", () -> new BlockItem(IO_SULFUR_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> IO_SULFUR = BLOCKS.register("io_sulfur", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> IO_SULFUR_ITEM = ITEMS.register("io_sulfur", () -> new BlockItem(IO_SULFUR.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> IO_SULFUR_DIOXID_DUST = BLOCKS.register("io_sulfur_dioxid_dust", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)));
    public static final RegistryObject<Item> IO_SULFUR_DIOXID_DUST_ITEM = ITEMS.register("io_sulfur_dioxid_dust", () -> new BlockItem(IO_SULFUR_DIOXID_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));

    //europa
    public static final RegistryObject<Block> EUROPA_ICE = BLOCKS.register("europa_ice", () -> new Block(Block.Properties.create(Material.ICE).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(-1).sound(SoundType.GLASS)));
    public static final RegistryObject<Item> EUROPA_ICE_ITEM = ITEMS.register("europa_ice", () -> new BlockItem(EUROPA_ICE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> EUROPA_ICE_SILICATE = BLOCKS.register("europa_ice_silicate", () -> new Block(Block.Properties.create(Material.ICE).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(-1).sound(SoundType.GLASS)));
    public static final RegistryObject<Item> EUROPA_ICE_SILICATE_ITEM = ITEMS.register("europa_ice_silicate", () -> new BlockItem(EUROPA_ICE_SILICATE.get(), new Item.Properties().group(ItemGroup.MISC)));

    //ganymede
    public static final RegistryObject<Block> GANYMEDE_DUST = BLOCKS.register("ganymede_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.CLOTH)));
    public static final RegistryObject<Item> GANYMEDE_DUST_ITEM = ITEMS.register("ganymede_dust", () -> new BlockItem(GANYMEDE_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> GANYMEDE_ROCK = BLOCKS.register("ganymede_rock", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> GANYMEDE_ROCK_ITEM = ITEMS.register("ganymede_rock", () -> new BlockItem(GANYMEDE_ROCK.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> GANYMEDE_COMPRESSED_DUST = BLOCKS.register("ganymede_compressed_dust", () -> new FallingBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(1f, 1f).harvestTool(ToolType.SHOVEL).harvestLevel(-1).sound(SoundType.SAND)));
    public static final RegistryObject<Item> GANYMEDE_COMPRESSED_DUST_ITEM = ITEMS.register("ganymede_compressed_dust", () -> new BlockItem(GANYMEDE_COMPRESSED_DUST.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> GANYMEDE_IRON_SULFIDE_ORE = BLOCKS.register("ganymede_iron_sulfide_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> GANYMEDE_IRON_SULFIDE_ORE_ITEM = ITEMS.register("ganymede_iron_sulfide_ore", () -> new BlockItem(GANYMEDE_IRON_SULFIDE_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Block> GANYMEDE_IRON_OXIDE_ORE = BLOCKS.register("ganymede_iron_oxide_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    public static final RegistryObject<Item> GANYMEDE_IRON_OXIDE_ORE_ITEM = ITEMS.register("ganymede_iron_oxide_ore", () -> new BlockItem(GANYMEDE_IRON_OXIDE_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));
    
    //Testore
    public static final RegistryObject<Block> MOON_ORE = BLOCKS.register("moon_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE)))));
    public static final RegistryObject<Item> MOON_ORE_ITEM = ITEMS.register("moon_ore", () -> new BlockItem(MOON_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> MOON_DIAMONDITEM = ITEMS.register("moon_diamond", () -> new Item(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64)));

    //Tech
    //public static final RegistryObject<Block> GANYMEDE_IRON_OXIDE_ORE = BLOCKS.register("ganymede_iron_oxide_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 1f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.STONE)));
    //public static final RegistryObject<Item> GANYMEDE_IRON_OXIDE_ORE_ITEM = ITEMS.register("ganymede_iron_oxide_ore", () -> new BlockItem(GANYMEDE_IRON_OXIDE_ORE.get(), new Item.Properties().group(ItemGroup.MISC)));

    
}
