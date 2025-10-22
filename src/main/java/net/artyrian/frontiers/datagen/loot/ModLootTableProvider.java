package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.data.components.ModDataComponents;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.util.LootTableHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    // Set a few predicates.
    public static final LootCondition.Builder NO_SHEARS = MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS)).invert();
    public static final LootCondition.Builder WITH_SHEARS = MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS));

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    // Generate tables.
    @Override
    public void generate()
    {
        // Registry lookup!
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        // Ancient Rose + pot
        addDrop(ModBlocks.ANCIENT_ROSE);
        addPottedPlantDrops(ModBlocks.POTTED_ANCIENT_ROSE);
        // Ancient Rose Bush (Bush)
        addDrop(ModBlocks.ANCIENT_ROSE_BUSH, block -> LootTableHelper.newRoseBushDrops(block, ModBlocks.ANCIENT_ROSE));
        // Ancient Rose Seed
        addDrop(
                ModBlocks.ANCIENT_ROSE_CROP,
                applyExplosionDecay(ModBlocks.ANCIENT_ROSE_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ModItem.ANCIENT_ROSE_SEED))))
        );
        // Rose + Pot
        addDrop(ModBlocks.ROSE);
        addPottedPlantDrops(ModBlocks.POTTED_ROSE);
        // (Vanilla) Rose Bush (Bush) - lazy workaround but it explicitly says I dont focus on connectivity :T
        addDrop(Blocks.ROSE_BUSH,block -> LootTableHelper.newRoseBushDrops(block, ModBlocks.ROSE));
        // Violet Rose + Pot
        addDrop(ModBlocks.VIOLET_ROSE);
        addPottedPlantDrops(ModBlocks.POTTED_VIOLET_ROSE);
        // Violet Rose Bush (Bush)
        addDrop(ModBlocks.VIOLET_ROSE_BUSH, block -> LootTableHelper.newRoseBushDrops(block, ModBlocks.VIOLET_ROSE));
        // Frostite Ore
        addDrop(ModBlocks.FROSTITE_ORE, dropsWithSilkTouch(ModBlocks.FROSTITE_ORE));
        // Snow Dahlia + Pot
        addDrop(ModBlocks.SNOW_DAHLIA);
        addPottedPlantDrops(ModBlocks.POTTED_SNOW_DAHLIA);
        // Fungal Daffodil + Pot
        addDrop(ModBlocks.FUNGAL_DAFFODIL);
        addPottedPlantDrops(ModBlocks.POTTED_FUNGAL_DAFFODIL);
        // Crimcone + Pot
        addDrop(ModBlocks.CRIMCONE);
        addPottedPlantDrops(ModBlocks.POTTED_CRIMCONE);
        // Experiwinkle + Pot
        addDrop(
                ModBlocks.EXPERIWINKLE,
                block -> this.dropsWithSilkTouchOrShears(
                        block,
                        this.applyExplosionDecay(
                                block,
                                ItemEntry.builder(ModItem.EXPERIWINKLE_BULB).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))
                                )
                        )
                )
        );
        addPottedPlantDrops(ModBlocks.POTTED_EXPERIWINKLE);
        // Blighted Birch Sapling + Pot
        addDrop(ModBlocks.BLIGHTED_BIRCH_SAPLING);
        addPottedPlantDrops(ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING);
        // Phantom Bed
        this.addDrop(ModBlocks.PHANTOM_STITCH_BED, block -> this.dropsWithProperty(block, BedBlock.PART, BedPart.HEAD));
        // All Corrupted Amethyst Buds
        this.addDrop(
                ModBlocks.CORRUPTED_AMETHYST_CLUSTER,
                block -> this.dropsWithSilkTouch(
                        block,
                        ItemEntry.builder(ModItem.END_CRYSTAL_SHARD)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .alternatively(
                                        (LootPoolEntry.Builder<?>)this.applyExplosionDecay(
                                                block, ItemEntry.builder(ModItem.END_CRYSTAL_SHARD).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                        )
                                )
                )
        );
        this.addDropWithSilkTouch(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD);
        this.addDropWithSilkTouch(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD);
        this.addDropWithSilkTouch(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD);
        // Experiwinkle Bulb
        addDrop(
                ModBlocks.EXPERIWINKLE_CROP,
                applyExplosionDecay(ModBlocks.EXPERIWINKLE_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ModItem.EXPERIWINKLE_BULB))))
        );
        // Warped Wart
        this.addDrop(
                ModBlocks.WARPED_WART,
                block -> LootTable.builder()
                        .pool(
                                this.applyExplosionDecay(
                                        block,
                                        LootPool.builder()
                                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                                .with(
                                                        ItemEntry.builder(ModItem.WARPED_WART)
                                                                .apply(
                                                                        SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))
                                                                                .conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(NetherWartBlock.AGE, 3)))
                                                                )
                                                                .apply(
                                                                        ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))
                                                                                .conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(NetherWartBlock.AGE, 3)))
                                                                )
                                                )
                                )
                        )
        );
        // Enchanting Magnet
        addDrop(ModBlocks.ENCHANTING_MAGNET, block -> LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        ItemEntry.builder(block)
                                                .conditionally(this.createSilkTouchCondition())
                                                .apply(CopyComponentsLootFunction.builder(CopyComponentsLootFunction.Source.BLOCK_ENTITY).include(ModDataComponents.EXP_AMOUNT))
                                                .alternatively(ItemEntry.builder(block))
                                )
                )
        );

        // Blighted Birch Leaves
        addDrop(ModBlocks.BLIGHTED_BIRCH_LEAVES, block -> leavesDrops(block, ModBlocks.BLIGHTED_BIRCH_SAPLING, SAPLING_DROP_CHANCE)
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .conditionally(this.createWithoutShearsOrSilkTouchCondition())
                                        .with(
                                                (this.addSurvivesExplosionCondition(block, ItemEntry.builder(ModItem.POMEGRANATE)))
                                                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                                        )
        ));

        // All ores
        addDrop(ModBlocks.COBALT_ORE, block -> oreDrops(block, ModItem.RAW_COBALT));
        addDrop(ModBlocks.DEEPSLATE_COBALT_ORE, block -> oreDrops(block, ModItem.RAW_COBALT));
        addDrop(ModBlocks.VERDINITE_ORE, block -> oreDrops(block, ModItem.RAW_VERDINITE)
                .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMax(3))));
        addDrop(ModBlocks.DEEPSLATE_VERDINITE_ORE, block -> oreDrops(block, ModItem.RAW_VERDINITE)
                .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMax(3))));
        addDrop(ModBlocks.VIVULITE_ORE, block -> oreDrops(block, ModItem.RAW_VIVULITE)
                .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMax(2))));
        addDrop(ModBlocks.DEEPSLATE_VIVULITE_ORE, block -> oreDrops(block, ModItem.RAW_VIVULITE)
                .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMax(2))));
        addDrop(ModBlocks.BRIMTAN_ORE, block -> oreDrops(block, ModItem.BRIMTAN_CLUSTER)
                .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMax(2))));

        // Stone-likes
        addDrop(ModBlocks.HIELOSTONE, block -> this.drops(block, ModBlocks.COBBLEFROST));
        addDrop(ModBlocks.HIELOSTONE_STAIRS);
        addDrop(ModBlocks.HIELOSTONE_SLAB, slabDrops(ModBlocks.HIELOSTONE_SLAB));
        addDrop(ModBlocks.HIELOSTONE_WALL);
        addDrop(ModBlocks.COBBLEFROST);
        addDrop(ModBlocks.COBBLEFROST_STAIRS);
        addDrop(ModBlocks.COBBLEFROST_SLAB, slabDrops(ModBlocks.COBBLEFROST_SLAB));
        addDrop(ModBlocks.COBBLEFROST_WALL);

        addDrop(ModBlocks.PERSONAL_CHEST, this::nameableContainerDrops);
        addDrop(ModBlocks.CURSE_ALTAR, this::nameableContainerDrops);

        // Nothing drops
        addDrop(ModBlocks.BEEF_WELLINGTON, dropsNothing());
        addDrop(ModBlocks.FRUITCAKE, dropsNothing());

        // All blocks that drop self
        addDrop(ModBlocks.COBALT_BLOCK);
        addDrop(ModBlocks.RAW_COBALT_BLOCK);
        addDrop(ModBlocks.FROSTITE_BLOCK);
        addDrop(ModBlocks.RAW_FROSTITE_BLOCK);
        addDrop(ModBlocks.MOURNING_GOLD_BLOCK);
        addDrop(ModBlocks.VERDINITE_BLOCK);
        addDrop(ModBlocks.RAW_VERDINITE_BLOCK);
        addDrop(ModBlocks.VIVULITE_BLOCK);
        addDrop(ModBlocks.RAW_VIVULITE_BLOCK);
        addDrop(ModBlocks.BRIMTAN_BLOCK);
        addDrop(ModBlocks.SUGAR_CANE_BLOCK);
        addDrop(ModBlocks.COCOA_BEAN_BLOCK);

        addDrop(ModBlocks.HIELOSTONE_TILES);
        addDrop(ModBlocks.HIELOSTONE_TILE_STAIRS);
        addDrop(ModBlocks.HIELOSTONE_TILE_SLAB, slabDrops(ModBlocks.HIELOSTONE_TILE_SLAB));
        addDrop(ModBlocks.HIELOSTONE_TILE_WALL);
        addDrop(ModBlocks.HIELOSTONE_BRICKS);
        addDrop(ModBlocks.HIELOSTONE_BRICK_STAIRS);
        addDrop(ModBlocks.HIELOSTONE_BRICK_SLAB, slabDrops(ModBlocks.HIELOSTONE_BRICK_SLAB));
        addDrop(ModBlocks.HIELOSTONE_BRICK_WALL);
        addDrop(ModBlocks.HIELOSTONE_PLATES);
        addDrop(ModBlocks.HIELOSTONE_PLATE_STAIRS);
        addDrop(ModBlocks.HIELOSTONE_PLATE_SLAB, slabDrops(ModBlocks.HIELOSTONE_PLATE_SLAB));
        addDrop(ModBlocks.HIELOSTONE_PLATE_WALL);

        addDrop(ModBlocks.TOWER_BRICKS);
        addDrop(ModBlocks.MOSSY_TOWER_BRICKS);

        addDrop(ModBlocks.STRANGE_CORE);
        addDrop(ModBlocks.GLOWING_OBSIDIAN);
        addDrop(ModBlocks.ONYX_BONE_BLOCK);
        addDrop(ModBlocks.QUICKSAND);
        addDrop(ModBlocks.RED_QUICKSAND);
        addDrop(ModBlocks.GLISTERING_MELON);
        addDrop(ModBlocks.CARVED_GLISTERING_MELON);
        addDrop(ModBlocks.CARVED_MELON);
        addDrop(ModBlocks.JUNE_O_LANTERN);
        addDrop(ModBlocks.GLISTERING_JUNE_O_LANTERN);
        addDrop(ModBlocks.WHITE_PUMPKIN);
        addDrop(ModBlocks.WHITE_JACK_O_LANTERN);

        addDrop(ModBlocks.NACRE_BRICKS);
        addDrop(ModBlocks.NACRE_BRICK_STAIRS);
        addDrop(ModBlocks.NACRE_BRICK_SLAB, slabDrops(ModBlocks.NACRE_BRICK_SLAB));
        addDrop(ModBlocks.NACRE_BRICK_WALL);

        addDrop(ModBlocks.TURTLE_SCUTE_BRICKS);
        addDrop(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS);
        addDrop(ModBlocks.TURTLE_SCUTE_BRICK_SLAB, slabDrops(ModBlocks.TURTLE_SCUTE_BRICK_SLAB));
        addDrop(ModBlocks.TURTLE_SCUTE_BRICK_WALL);

        addDrop(ModBlocks.CRAGULSTANE);
        addDrop(ModBlocks.CRAGULSTANE_BRICKS);
        addDrop(ModBlocks.CRAGULSTANE_BRICK_STAIRS);
        addDrop(ModBlocks.CRAGULSTANE_BRICK_SLAB, slabDrops(ModBlocks.CRAGULSTANE_BRICK_SLAB));
        addDrop(ModBlocks.CRAGULSTANE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_CRAGULSTANE_BRICKS);
        addDrop(ModBlocks.CRACKED_CRAGULSTANE_BRICKS);

        addDrop(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS);
        addDrop(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS);
        addDrop(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB, slabDrops(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB));
        addDrop(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS);
        addDrop(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS);

        addDrop(ModBlocks.ORANGE_CRAGULSTANE_BRICKS);
        addDrop(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS);
        addDrop(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB, slabDrops(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB));
        addDrop(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS);
        addDrop(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS);

        addDrop(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS);
        addDrop(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS);
        addDrop(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB, slabDrops(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB));
        addDrop(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS);
        addDrop(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS);

        addDrop(ModBlocks.BLUE_NETHER_BRICKS);
        addDrop(ModBlocks.CRACKED_BLUE_NETHER_BRICKS);
        addDrop(ModBlocks.CHISELED_BLUE_NETHER_BRICKS);
        addDrop(ModBlocks.BLUE_NETHER_BRICK_SLAB, slabDrops(ModBlocks.BLUE_NETHER_BRICK_SLAB));
        addDrop(ModBlocks.BLUE_NETHER_BRICK_STAIRS);
        addDrop(ModBlocks.BLUE_NETHER_BRICK_WALL);
        addDrop(ModBlocks.BLUE_NETHER_BRICK_FENCE);
        addDrop(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE);

        addDrop(ModBlocks.PURPLE_NETHER_BRICKS);
        addDrop(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS);
        addDrop(ModBlocks.CHISELED_PURPLE_NETHER_BRICKS);
        addDrop(ModBlocks.PURPLE_NETHER_BRICK_SLAB, slabDrops(ModBlocks.PURPLE_NETHER_BRICK_SLAB));
        addDrop(ModBlocks.PURPLE_NETHER_BRICK_STAIRS);
        addDrop(ModBlocks.PURPLE_NETHER_BRICK_WALL);
        addDrop(ModBlocks.PURPLE_NETHER_BRICK_FENCE);
        addDrop(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE);

        addDrop(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        addDrop(ModBlocks.CHISELED_RED_NETHER_BRICKS);
        addDrop(ModBlocks.RED_NETHER_BRICK_FENCE);
        addDrop(ModBlocks.RED_NETHER_BRICK_FENCE_GATE);

        addDrop(ModBlocks.NETHER_BRICK_FENCE_GATE);

        addDrop(ModBlocks.DIAMOND_LUMEN);
        addDrop(ModBlocks.QUARTZ_LUMEN);
        addDrop(ModBlocks.REDSTONE_LUMEN);
        addDrop(ModBlocks.EMERALD_LUMEN);
        addDrop(ModBlocks.AMETHYST_LUMEN);
        addDrop(ModBlocks.COBALT_LUMEN);
        addDrop(ModBlocks.FROSTITE_LUMEN);
        addDrop(ModBlocks.VERDINITE_LUMEN);
        addDrop(ModBlocks.VIVULITE_LUMEN);
        addDrop(ModBlocks.BRIMTAN_LUMEN);
        addDrop(ModBlocks.ECHO_LUMEN);
        addDrop(BFBlock.FELDSPAR_LUMEN);

        addDrop(ModBlocks.PALE_PRISMARINE);
        addDrop(ModBlocks.PALE_PRISMARINE_STAIRS);
        addDrop(ModBlocks.PALE_PRISMARINE_SLAB, slabDrops(ModBlocks.PALE_PRISMARINE_SLAB));
        addDrop(ModBlocks.PALE_PRISMARINE_WALL);
        addDrop(ModBlocks.PALE_PRISMARINE_BRICKS);
        addDrop(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS);
        addDrop(ModBlocks.PALE_PRISMARINE_BRICK_SLAB, slabDrops(ModBlocks.PALE_PRISMARINE_BRICK_SLAB));
        addDrop(ModBlocks.DEEP_PALE_PRISMARINE);
        addDrop(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS);
        addDrop(ModBlocks.DEEP_PALE_PRISMARINE_SLAB, slabDrops(ModBlocks.DEEP_PALE_PRISMARINE_SLAB));

        addDrop(ModBlocks.SEA_GLASS);
        addDrop(ModBlocks.SEA_GLASS_PANE);
        addDrop(ModBlocks.PALE_SEA_GLASS);
        addDrop(ModBlocks.PALE_SEA_GLASS_PANE);

        addDrop(ModBlocks.VIVULITE_ANVIL);
        addDrop(ModBlocks.ITEM_VACUUM);

        addDrop(ModBlocks.OAK_WREATH);
        addDrop(ModBlocks.DARK_OAK_WREATH);
        addDrop(ModBlocks.BIRCH_WREATH);
        addDrop(ModBlocks.SPRUCE_WREATH);
        addDrop(ModBlocks.JUNGLE_WREATH);
        addDrop(ModBlocks.ACACIA_WREATH);
        addDrop(ModBlocks.MANGROVE_WREATH);
        addDrop(ModBlocks.AZALEA_WREATH);
        addDrop(ModBlocks.CHERRY_WREATH);
        addDrop(ModBlocks.BLIGHTED_BIRCH_WREATH);

        addDrop(ModBlocks.EBONCORK);
        addDrop(ModBlocks.EBONCORK_PLANKS);
        addDrop(ModBlocks.EBONCORK_STAIRS);
        addDrop(ModBlocks.EBONCORK_SLAB, slabDrops(ModBlocks.EBONCORK_SLAB));
        addDrop(ModBlocks.EBONCORK_FENCE);
        addDrop(ModBlocks.EBONCORK_FENCE_GATE);
        addDrop(ModBlocks.EBONCORK_PRESSURE_PLATE);
        addDrop(ModBlocks.EBONCORK_BUTTON);
        addDrop(ModBlocks.EBONCORK_DOOR, doorDrops(ModBlocks.EBONCORK_DOOR));
        addDrop(ModBlocks.EBONCORK_TRAPDOOR);

        addDrop(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG);
        addDrop(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG);
        addDrop(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD);
        addDrop(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD);
        addDrop(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG);
        addDrop(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD);
        addDrop(ModBlocks.BLIGHTED_BIRCH_PLANKS);
        addDrop(ModBlocks.BLIGHTED_BIRCH_STAIRS);
        addDrop(ModBlocks.BLIGHTED_BIRCH_SLAB, slabDrops(ModBlocks.BLIGHTED_BIRCH_SLAB));
        addDrop(ModBlocks.BLIGHTED_BIRCH_FENCE);
        addDrop(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE);
        addDrop(ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE);
        addDrop(ModBlocks.BLIGHTED_BIRCH_BUTTON);
        addDrop(ModBlocks.BLIGHTED_BIRCH_DOOR, doorDrops(ModBlocks.BLIGHTED_BIRCH_DOOR));
        addDrop(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR);

        addDrop(ModBlocks.SPIRIT_CANDLE);
        addDrop(ModBlocks.MONSTER_BAKERY);

        addDrop(ModBlocks.CREEPER_MODEL);
        addDrop(ModBlocks.SKELETON_MODEL);
        addDrop(ModBlocks.STRAY_MODEL);
        addDrop(ModBlocks.BOGGED_MODEL);
        addDrop(ModBlocks.BLAZE_MODEL);
        addDrop(ModBlocks.WITHER_SKELETON_MODEL);
        addDrop(ModBlocks.ENDERMAN_MODEL);
        addDrop(ModBlocks.SLIME_MODEL);
        addDrop(ModBlocks.MAGMA_CUBE_MODEL);

        // VANILLA BLOCKS
        addDrop(Blocks.SPAWNER, ModItem.SPAWNER_CHUNK);
    }
}
