package tk.masa.setup;
import static tk.masa.masa.MODID;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tk.masa.masa;
import tk.masa.blocks.PortalBlock;
import tk.masa.blocks.powergen.PowergenBE;
import tk.masa.blocks.powergen.PowergenBlock;
import tk.masa.blocks.powergen.PowergenContainer;
import tk.masa.items.ProbeItem;

public class Registration {
	
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    private static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MODID);
    
    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);
        ENTITIES.register(bus);
        STRUCTURES.register(bus);
    }
    
    
 //   StonecutterScreen
    
//----------------------------------------
    /*
    public static final RegistryObject<Biome> TESTBIOME = BIOMES.register("testbiome", TestBiome::new);
    public static final RegistryObject<VoidModDimension> DIMENSION = DIMENSIONS.register("dimension", VoidModDimension::new);
    
    
    public static final RegistryObject<BlastFurnaceRecipeSerializer> OBSIDIAN_FORGE_RECIPES = RECIPE_SERIALIZERS.register("blast_recipe", BlastFurnaceRecipeSerializer::new);
    public static final RegistryObject<BlockIronFurnace> IRON_FURNACE = BLOCKS.register(BlockIronFurnace.IRON_FURNACE, BlockIronFurnace::new);
    public static final RegistryObject<Item> IRON_FURNACE_ITEM = fromBlock(IRON_FURNACE)
    public static final RegistryObject<TileEntityType<BlockIronFurnaceTile>> IRON_FURNACE_TILE = TILES.register(BlockIronFurnace.IRON_FURNACE, () -> TileEntityType.Builder.create(BlockIronFurnaceTile::new, IRON_FURNACE.get()).build(null));
    public static final RegistryObject<ContainerType<BlockIronFurnaceContainer>> IRON_FURNACE_CONTAINER = CONTAINERS.register(BlockIronFurnace.IRON_FURNACE, () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new BlockIronFurnaceContainer(windowId, Minecraft.getInstance().world, pos, inv, Minecraft.getInstance().player);
    }));
    
    
    //public static final RegistryObject<BlastFurnaceRecipeSerializer> COMPRESSOR_RECIPES = RECIPE_SERIALIZERS.register("compressor_recipe", BlastFurnaceRecipeSerializer::new);
    public static final RegistryObject<BlockCompressor> COMPRESSOR = BLOCKS.register(BlockCompressor.COMPRESSOR, BlockCompressor::new);
    public static final RegistryObject<Item> COMPRESSOR_ITEM = fromBlock(COMPRESSOR)
    public static final RegistryObject<TileEntityType<BlockCompressorTile>> COMPRESSOR_TILE = TILES.register(BlockCompressor.COMPRESSOR, () -> TileEntityType.Builder.create(BlockCompressorTile::new, COMPRESSOR.get()).build(null));
    public static final RegistryObject<ContainerType<BlockCompressorContainer>> COMPRESSOR_CONTAINER = CONTAINERS.register(BlockCompressor.COMPRESSOR, () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new BlockCompressorContainer(windowId, Minecraft.getInstance().world, pos, inv, Minecraft.getInstance().player);
    }));
    

    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket", () -> new BucketItem(() -> ModFluids.oil, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
    public static final RegistryObject<Block> OIL = BLOCKS.register("flowing_oil", ()-> new FlowingFluidBlock(() -> ModFluids.oil, Block.Properties.create(Material.WATER).doesNotBlockMovement().noDrops()));
    
    
    //test
    public static final RegistryObject<Item> TESTITEM = ITEMS.register("testitem", TestItem::new);
    public static final RegistryObject<Item> FIRSTITEM = ITEMS.register("firstitem", () -> new Item(new Item.Properties().group(ItemGroup.MISC).maxStackSize(4)));
    public static final RegistryObject<Item> SECONDITEM = ITEMS.register("seconditem", () -> new Item(ITEM_PROPERTIES));
    
    */
    //---------------------------------------------------------------------------------
    //BLOCKS
    //---------------------------------------------------------------------------------
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);
    
    public static final BlockBehaviour.Properties STONE_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(1f).requiresCorrectToolForDrops();
    public static final BlockBehaviour.Properties ORE_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(1f).requiresCorrectToolForDrops();
    public static final BlockBehaviour.Properties ICE_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.ICE).strength(1f).requiresCorrectToolForDrops();
    public static final BlockBehaviour.Properties SAND_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.SAND).strength(1f);
    public static final BlockBehaviour.Properties DUST_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.CLAY).strength(1f);
    
    //DEBUG Items
    public static final RegistryObject<Block> MYSTERIOUS_ORE_OVERWORLD = BLOCKS.register("mysterious_ore_overworld", () -> new Block(ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MYSTERIOUS_ORE_OVERWORLD_ITEM = fromBlock(MYSTERIOUS_ORE_OVERWORLD);
    public static final RegistryObject<Item> RAW_MYSTERIOUS_CHUNK = ITEMS.register("raw_mysterious_chunk", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> MYSTERIOUS_INGOT = ITEMS.register("mysterious_ingot", () -> new Item(ITEM_PROPERTIES));
    
    public static final RegistryObject<PowergenBlock> POWERGEN = BLOCKS.register("powergen", PowergenBlock::new);
    public static final RegistryObject<Item> POWERGEN_ITEM = fromBlock(POWERGEN);
    public static final RegistryObject<BlockEntityType<PowergenBE>> POWERGEN_BE = BLOCK_ENTITIES.register("powergen", () -> BlockEntityType.Builder.of(PowergenBE::new, POWERGEN.get()).build(null));
    public static final RegistryObject<MenuType<PowergenContainer>> POWERGEN_CONTAINER = CONTAINERS.register("powergen",
            () -> IForgeMenuType.create((windowId, inv, data) -> new PowergenContainer(windowId, data.readBlockPos(), inv, inv.player)));


    public static final Tags.IOptionalNamedTag<Block> MYSTERIOUS_ORE = BlockTags.createOptional(new ResourceLocation(masa.MODID, "mysterious_ore"));
    public static final Tags.IOptionalNamedTag<Item> MYSTERIOUS_ORE_ITEM = ItemTags.createOptional(new ResourceLocation(masa.MODID, "mysterious_ore"));

    
    public static final RegistryObject<Block> PORTAL_BLOCK = BLOCKS.register("portal", PortalBlock::new);
    public static final RegistryObject<Item> PORTAL_ITEM = fromBlock(PORTAL_BLOCK);
    
    public static final RegistryObject<Item> PROBE_ITEM = ITEMS.register("probe", ProbeItem::new);
    
    //Moonitems
    public static final RegistryObject<Block> MOON_ROCK = BLOCKS.register("moon_rock", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MOON_ROCK_ITEM = fromBlock(MOON_ROCK);
    public static final RegistryObject<Block> MOON_DUST = BLOCKS.register("moon_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MOON_DUST_ITEM = fromBlock(MOON_DUST);
    public static final RegistryObject<Block> MOON_COMPRESSED_DUST = BLOCKS.register("moon_compressed_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MOON_COMPRESSED_DUST_ITEM = fromBlock(MOON_COMPRESSED_DUST);
    
    //Mooncheese
    public static final RegistryObject<Block> MOON_CHEESE_ORE = BLOCKS.register("moon_cheese_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MOON_CHEESE_ORE_ITEM = fromBlock(MOON_CHEESE_ORE);
    public static final RegistryObject<Item> CHEESE_ITEM = ITEMS.register("cheese", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(16).food(new FoodProperties.Builder().alwaysEat().fast().nutrition(4).saturationMod(0.6f).build())));
    
    //Marsitems
    public static final RegistryObject<Block> MARS_ROCK = BLOCKS.register("mars_rock", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MARS_ROCK_ITEM = fromBlock(MARS_ROCK);
    public static final RegistryObject<Block> MARS_DUST = BLOCKS.register("mars_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MARS_DUST_ITEM = fromBlock(MARS_DUST);
    public static final RegistryObject<Block> MARS_COMPRESSED_DUST = BLOCKS.register("mars_compressed_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MARS_COMPRESSED_DUST_ITEM = fromBlock(MARS_COMPRESSED_DUST);
    public static final RegistryObject<Block> MARS_ICE_CO2 = BLOCKS.register("mars_ice_co2", () -> new Block(ICE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MARS_ICE_CO2_ITEM = fromBlock(MARS_ICE_CO2);

    //Comet
    public static final RegistryObject<Block> COMET_ICE = BLOCKS.register("comet_ice", () -> new Block(ICE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> COMET_ICE_ITEM = fromBlock(COMET_ICE);
    public static final RegistryObject<Block> COMET_CORE_ROCK = BLOCKS.register("comet_core_rock", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> COMET_CORE_ROCK_ITEM = fromBlock(COMET_CORE_ROCK); 
    public static final RegistryObject<Block> COMET_DUST = BLOCKS.register("comet_dust", () -> new Block(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> COMET_DUST_ITEM = fromBlock(COMET_DUST);
    
    //Asteroid
    public static final RegistryObject<Block> ASTEROID_CLAY = BLOCKS.register("asteroid_clay", () -> new Block(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> ASTEROID_CLAY_ITEM = fromBlock(ASTEROID_CLAY);
    public static final RegistryObject<Block> ASTEROID_ROCK = BLOCKS.register("asteroid_rock", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> ASTEROID_ROCK_ITEM = fromBlock(ASTEROID_ROCK);
    public static final RegistryObject<Block> ASTEROID_GOLD_ORE = BLOCKS.register("asteroid_gold_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> ASTEROID_GOLD_ORE_ITEM = fromBlock(ASTEROID_GOLD_ORE);
    public static final RegistryObject<Block> ASTEROID_SILVER_ORE = BLOCKS.register("asteroid_silver_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> ASTEROID_SILVER_ORE_ITEM = fromBlock(ASTEROID_SILVER_ORE);
    public static final RegistryObject<Block> ASTEROID_PLATINUM_ORE = BLOCKS.register("asteroid_platinum_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> ASTEROID_PLATINUM_ORE_ITEM = fromBlock(ASTEROID_PLATINUM_ORE);
    public static final RegistryObject<Block> ASTEROID_OLIVINE_ORE = BLOCKS.register("asteroid_olivine_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> ASTEROID_OLIVINE_ORE_ITEM = fromBlock(ASTEROID_OLIVINE_ORE);
    public static final RegistryObject<Item> ASTEROID_OLIVINE_GEM_ITEM = ITEMS.register("asteroid_olivine_gem", () -> new Item(ITEM_PROPERTIES));
    
    //titan
    public static final RegistryObject<Block> TITAN_DUST = BLOCKS.register("titan_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> TITAN_DUST_ITEM = fromBlock(TITAN_DUST);
    public static final RegistryObject<Block> TITAN_COMPRESSED_DUST = BLOCKS.register("titan_compressed_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> TITAN_COMPRESSED_DUST_ITEM = fromBlock(TITAN_COMPRESSED_DUST);
    public static final RegistryObject<Block> TITAN_ROCK = BLOCKS.register("titan_rock", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> TITAN_ROCK_ITEM = fromBlock(TITAN_ROCK);

    //mercury
    public static final RegistryObject<Block> MERCURY_DUST = BLOCKS.register("mercury_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MERCURY_DUST_ITEM = fromBlock(MERCURY_DUST);
    public static final RegistryObject<Block> MERCURY_ROCK = BLOCKS.register("mercury_rock", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MERCURY_ROCK_ITEM = fromBlock(MERCURY_ROCK);
    public static final RegistryObject<Block> MERCURY_COMPRESSED_DUST = BLOCKS.register("mercury_compressed_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MERCURY_COMPRESSED_DUST_ITEM = fromBlock(MERCURY_COMPRESSED_DUST);
    public static final RegistryObject<Block> MERCURY_NICKEL_ORE = BLOCKS.register("mercury_nickel_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MERCURY_NICKEL_ORE_ITEM = fromBlock(MERCURY_NICKEL_ORE);
    public static final RegistryObject<Block> MERCURY_IRON_ORE = BLOCKS.register("mercury_iron_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MERCURY_IRON_ORE_ITEM = fromBlock(MERCURY_IRON_ORE);

    //venus
    public static final RegistryObject<Block> VENUS_DUST = BLOCKS.register("venus_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> VENUS_DUST_ITEM = fromBlock(VENUS_DUST);
    public static final RegistryObject<Block> VENUS_COMPRESSED_DUST = BLOCKS.register("venus_compressed_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> VENUS_COMPRESSED_DUST_ITEM = fromBlock(VENUS_COMPRESSED_DUST);
    public static final RegistryObject<Block> VENUS_ROCK = BLOCKS.register("venus_rock", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> VENUS_ROCK_ITEM = fromBlock(VENUS_ROCK);
    public static final RegistryObject<Block> VENUS_IRON_SILICATE = BLOCKS.register("venus_iron_silicate", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> VENUS_IRON_SILICATE_ITEM = fromBlock(VENUS_IRON_SILICATE);
    
    //io
    public static final RegistryObject<Block> IO_SULFUR_DUST = BLOCKS.register("io_sulfur_dust", () -> new Block(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> IO_SULFUR_DUST_ITEM = fromBlock(IO_SULFUR_DUST);
    public static final RegistryObject<Block> IO_SULFUR = BLOCKS.register("io_sulfur", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> IO_SULFUR_ITEM = fromBlock(IO_SULFUR);
    public static final RegistryObject<Block> IO_SULFUR_DIOXID_DUST = BLOCKS.register("io_sulfur_dioxid_dust", () -> new Block(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> IO_SULFUR_DIOXID_DUST_ITEM = fromBlock(IO_SULFUR_DIOXID_DUST);

    //europa
    public static final RegistryObject<Block> EUROPA_ICE = BLOCKS.register("europa_ice", () -> new Block(ICE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> EUROPA_ICE_ITEM = fromBlock(EUROPA_ICE);
    public static final RegistryObject<Block> EUROPA_ICE_SILICATE = BLOCKS.register("europa_ice_silicate", () -> new Block(ICE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> EUROPA_ICE_SILICATE_ITEM = fromBlock(EUROPA_ICE_SILICATE);

    //ganymede
    public static final RegistryObject<Block> GANYMEDE_DUST = BLOCKS.register("ganymede_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> GANYMEDE_DUST_ITEM = fromBlock(GANYMEDE_DUST);
    public static final RegistryObject<Block> GANYMEDE_ROCK = BLOCKS.register("ganymede_rock", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> GANYMEDE_ROCK_ITEM = fromBlock(GANYMEDE_ROCK);
    public static final RegistryObject<Block> GANYMEDE_COMPRESSED_DUST = BLOCKS.register("ganymede_compressed_dust", () -> new FallingBlock(DUST_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> GANYMEDE_COMPRESSED_DUST_ITEM = fromBlock(GANYMEDE_COMPRESSED_DUST);
    public static final RegistryObject<Block> GANYMEDE_IRON_SULFIDE_ORE = BLOCKS.register("ganymede_iron_sulfide_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> GANYMEDE_IRON_SULFIDE_ORE_ITEM = fromBlock(GANYMEDE_IRON_SULFIDE_ORE);
    public static final RegistryObject<Block> GANYMEDE_IRON_OXIDE_ORE = BLOCKS.register("ganymede_iron_oxide_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> GANYMEDE_IRON_OXIDE_ORE_ITEM = fromBlock(GANYMEDE_IRON_OXIDE_ORE);
    
    //Testore
    public static final RegistryObject<Block> MOON_ORE = BLOCKS.register("moon_ore", () -> new Block(ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MOON_ORE_ITEM = fromBlock(MOON_ORE);
    public static final RegistryObject<Item> MOON_DIAMOND_ITEM = ITEMS.register("moon_diamond", () -> new Item(ITEM_PROPERTIES));

    //Tech
    //public static final RegistryObject<Block> GANYMEDE_IRON_OXIDE_ORE = BLOCKS.register("ganymede_iron_oxide_ore", () -> new Block(STONE_BLOCK_PROPERTIES));
    //public static final RegistryObject<Item> GANYMEDE_IRON_OXIDE_ORE_ITEM = fromBlock(GANYMEDE_IRON_OXIDE_ORE)

    //Items---------------------------------
    public static final RegistryObject<Item> LENS_ITEM = ITEMS.register("lens", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> IRON_STICK_ITEM = ITEMS.register("iron_stick", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> STATIV_ITEM = ITEMS.register("stativ", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> TELESCOPE_HEAD_ITEM = ITEMS.register("telescope_head", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> TELESCOPE_ITEM = ITEMS.register("telescope", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SLAG_ITEM = ITEMS.register("slag", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SLAG_COMPRESSED_ITEM = ITEMS.register("slag_compressed", () -> new Item(ITEM_PROPERTIES));
    //new
    public static final RegistryObject<Item> COAL_DUST_ITEM = ITEMS.register("coal_dust", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> IRON_DUST_ITEM = ITEMS.register("iron_dust", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> OBSIDIAN_CRUSHED_ITEM = ITEMS.register("obsidian_crushed", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> STEEL_ITEM = ITEMS.register("steel", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> CASING_ITEM = ITEMS.register("casing", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> COAL_COMPRESSED_DUST_ITEM = ITEMS.register("coal_compressed_dust", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> CARBON_PLATE_ITEM = ITEMS.register("carbon_plate", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> OBSIDIAN_PLATE_ITEM = ITEMS.register("obsidian_plate", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> INSULATED_CASING_ITEM = ITEMS.register("insulated_casting", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> REINFORCED_PLATE_ITEM = ITEMS.register("reinforced_plate", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> ROCKET_CASING_ITEM = ITEMS.register("rocket_casing", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> COPPER_WIRE_ITEM = ITEMS.register("copper_wire", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> SHEET_WOODEN_ITEM = ITEMS.register("wooden_sheet", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> PCB_PLATE_WOODEN_ITEM = ITEMS.register("pcb_plate_wooden", () -> new Item(ITEM_PROPERTIES));
    //new
    public static final RegistryObject<Item> GOLD_DUST_ITEM = ITEMS.register("gold_dust", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> WOODEN_PLATE_ITEM = ITEMS.register("wooden_plate", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> IRON_PLATE_ITEM = ITEMS.register("iron_plate", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> COPPER_PLATE_ITEM = ITEMS.register("copper_plate", () -> new Item(ITEM_PROPERTIES));
    
    
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }

}
