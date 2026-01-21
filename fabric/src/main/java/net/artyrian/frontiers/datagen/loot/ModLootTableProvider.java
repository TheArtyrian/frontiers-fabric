package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.SlimeBulbBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.data.components.ModDataComponents;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.util.LootTableHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.loot.function.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    // Set a few predicates.
    public static final LootItemCondition.Builder NO_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)).invert();
    public static final LootItemCondition.Builder WITH_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    public LootTable.Builder createMushroomBlockDrop(Block withSilkTouch, ItemLike withoutSilkTouch) {
        return this.createSilkTouchDispatchTable(
                withSilkTouch,
                this.applyExplosionDecay(
                        withSilkTouch,
                        LootItem.lootTableItem(withoutSilkTouch)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(-6.0F, 2.0F)))
                                .apply(LimitCount.limitCount(IntRange.lowerBound(0)))
                )
        );
    }

    // Generate tables.
    @Override
    public void generate()
    {
        // Registry lookup!
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        // Ancient Rose + pot
        dropSelf(ModBlocks.ANCIENT_ROSE);
        dropPottedContents(ModBlocks.POTTED_ANCIENT_ROSE);
        // Ancient Rose Bush (Bush)
        add(ModBlocks.ANCIENT_ROSE_BUSH, block -> LootTableHelper.newRoseBushDrops(block, ModBlocks.ANCIENT_ROSE));
        // Ancient Rose Seed
        add(
                ModBlocks.ANCIENT_ROSE_CROP,
                applyExplosionDecay(ModBlocks.ANCIENT_ROSE_CROP, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItem.ANCIENT_ROSE_SEED))))
        );
        // Rose + Pot
        dropSelf(ModBlocks.ROSE);
        dropPottedContents(ModBlocks.POTTED_ROSE);
        // (Vanilla) Rose Bush (Bush) - lazy workaround but it explicitly says I dont focus on connectivity :T
        add(Blocks.ROSE_BUSH,block -> LootTableHelper.newRoseBushDrops(block, ModBlocks.ROSE));
        // Violet Rose + Pot
        dropSelf(ModBlocks.VIOLET_ROSE);
        dropPottedContents(ModBlocks.POTTED_VIOLET_ROSE);
        // Violet Rose Bush (Bush)
        add(ModBlocks.VIOLET_ROSE_BUSH, block -> LootTableHelper.newRoseBushDrops(block, ModBlocks.VIOLET_ROSE));
        // Frostite Ore
        add(ModBlocks.FROSTITE_ORE, createSilkTouchOnlyTable(ModBlocks.FROSTITE_ORE));
        // Fungal Daffodil Block
        add(ModBlocks.FUNGAL_DAFFODIL_BLOCK, block -> createMushroomBlockDrop(block, ModBlocks.FUNGAL_DAFFODIL));
        // Snow Dahlia + Pot
        dropSelf(ModBlocks.SNOW_DAHLIA);
        dropPottedContents(ModBlocks.POTTED_SNOW_DAHLIA);
        // Fungal Daffodil + Pot
        dropSelf(ModBlocks.FUNGAL_DAFFODIL);
        dropPottedContents(ModBlocks.POTTED_FUNGAL_DAFFODIL);
        // Crimcone + Pot
        dropSelf(ModBlocks.CRIMCONE);
        dropPottedContents(ModBlocks.POTTED_CRIMCONE);
        // Experiwinkle + Pot
        add(
                ModBlocks.EXPERIWINKLE,
                block -> this.createSilkTouchOrShearsDispatchTable(
                        block,
                        this.applyExplosionDecay(
                                block,
                                LootItem.lootTableItem(ModItem.EXPERIWINKLE_BULB).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))
                                )
                        )
                )
        );
        dropPottedContents(ModBlocks.POTTED_EXPERIWINKLE);
        // Blighted Birch Sapling + Pot
        dropSelf(ModBlocks.BLIGHTED_BIRCH_SAPLING);
        dropPottedContents(ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING);
        // Phantom Bed
        this.add(ModBlocks.PHANTOM_STITCH_BED, block -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        // All Corrupted Amethyst Buds
        this.add(
                ModBlocks.CORRUPTED_AMETHYST_CLUSTER,
                block -> this.createSilkTouchDispatchTable(
                        block,
                        LootItem.lootTableItem(ModItem.END_CRYSTAL_SHARD)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .otherwise(
                                        (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                                                block, LootItem.lootTableItem(ModItem.END_CRYSTAL_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                        )
                                )
                )
        );
        this.dropWhenSilkTouch(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD);
        this.dropWhenSilkTouch(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD);
        this.dropWhenSilkTouch(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD);
        // Experiwinkle Bulb
        add(
                ModBlocks.EXPERIWINKLE_CROP,
                applyExplosionDecay(ModBlocks.EXPERIWINKLE_CROP, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItem.EXPERIWINKLE_BULB))))
        );
        // Warped Wart
        this.add(
                ModBlocks.WARPED_WART,
                block -> LootTable.lootTable()
                        .withPool(
                                this.applyExplosionDecay(
                                        block,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ModItem.WARPED_WART)
                                                                .apply(
                                                                        SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))
                                                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(NetherWartBlock.AGE, 3)))
                                                                )
                                                                .apply(
                                                                        ApplyBonusCount.addUniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))
                                                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(NetherWartBlock.AGE, 3)))
                                                                )
                                                )
                                )
                        )
        );
        // Slime Trail
        this.add(
                ModBlocks.SLIME_TRAIL,
                block -> LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .add(
                                                this.applyExplosionDecay(
                                                        block,
                                                        LootItem.lootTableItem(block)
                                                                .apply(
                                                                        Direction.values(),
                                                                        direction -> SetItemCountFunction.setCount(ConstantValue.exactly(1.0F), true)
                                                                                .when(
                                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MultifaceBlock.getFaceProperty(direction), true))
                                                                                )
                                                                )
                                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true))
                                                )
                                                .when(this.hasSilkTouch())
                                                .otherwise(this.applyExplosionDecay(
                                                        block,
                                                        LootItem.lootTableItem(Items.SLIME_BALL)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(-10.0F, 1.0F))))
                                                )
                                        )
                        )
        );
        // Slime Bulb
        this.add(
                ModBlocks.SLIME_BULB,
                block -> LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                .add(
                                        this.applyExplosionDecay(
                                                block,
                                                LootItem.lootTableItem(ModItem.HARDENED_SLIME)
                                                        .apply(
                                                                SetItemCountFunction.setCount(ConstantValue.exactly(1.0F), true)
                                                                        .when(
                                                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlimeBulbBlock.AGE, 3))
                                                                        )
                                                        )
                                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true))
                                        )
                                )
                        )
        );
        // Enchanting Magnet
        add(ModBlocks.ENCHANTING_MAGNET, block -> LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(
                                        LootItem.lootTableItem(block)
                                                .when(this.hasSilkTouch())
                                                .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY).include(ModDataComponents.EXP_AMOUNT))
                                                .otherwise(LootItem.lootTableItem(block))
                                )
                )
        );

        // Blighted Birch Leaves
        add(ModBlocks.BLIGHTED_BIRCH_LEAVES, block -> createLeavesDrops(block, ModBlocks.BLIGHTED_BIRCH_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .when(this.doesNotHaveShearsOrSilkTouch())
                                        .add(
                                                (this.applyExplosionCondition(block, LootItem.lootTableItem(ModItem.POMEGRANATE)))
                                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                                        )
        ));

        // All ores
        add(ModBlocks.COBALT_ORE, block -> createOreDrop(block, ModItem.RAW_COBALT));
        add(ModBlocks.DEEPSLATE_COBALT_ORE, block -> createOreDrop(block, ModItem.RAW_COBALT));
        add(ModBlocks.VERDINITE_ORE, block -> createOreDrop(block, ModItem.RAW_VERDINITE)
                .apply(LimitCount.limitCount(IntRange.upperBound(3))));
        add(ModBlocks.DEEPSLATE_VERDINITE_ORE, block -> createOreDrop(block, ModItem.RAW_VERDINITE)
                .apply(LimitCount.limitCount(IntRange.upperBound(3))));
        add(ModBlocks.VIVULITE_ORE, block -> createOreDrop(block, ModItem.RAW_VIVULITE)
                .apply(LimitCount.limitCount(IntRange.upperBound(2))));
        add(ModBlocks.DEEPSLATE_VIVULITE_ORE, block -> createOreDrop(block, ModItem.RAW_VIVULITE)
                .apply(LimitCount.limitCount(IntRange.upperBound(2))));
        add(ModBlocks.BLACK_EMERALD_ORE, block -> createOreDrop(block, ModItem.BLACK_EMERALD));
        add(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE, block -> createOreDrop(block, ModItem.BLACK_EMERALD));
        add(ModBlocks.BRIMTAN_ORE, block -> createOreDrop(block, ModItem.BRIMTAN_CLUSTER)
                .apply(LimitCount.limitCount(IntRange.upperBound(2))));

        // Stone-likes
        add(ModBlocks.HIELOSTONE, block -> this.createSingleItemTableWithSilkTouch(block, ModBlocks.COBBLEFROST));
        dropSelf(ModBlocks.HIELOSTONE_STAIRS);
        add(ModBlocks.HIELOSTONE_SLAB, createSlabItemTable(ModBlocks.HIELOSTONE_SLAB));
        dropSelf(ModBlocks.HIELOSTONE_WALL);
        dropSelf(ModBlocks.COBBLEFROST);
        dropSelf(ModBlocks.COBBLEFROST_STAIRS);
        add(ModBlocks.COBBLEFROST_SLAB, createSlabItemTable(ModBlocks.COBBLEFROST_SLAB));
        dropSelf(ModBlocks.COBBLEFROST_WALL);

        add(ModBlocks.PERSONAL_CHEST, this::createNameableBlockEntityTable);
        add(ModBlocks.CURSE_ALTAR, this::createNameableBlockEntityTable);

        // Nothing drops
        add(ModBlocks.BEEF_WELLINGTON, noDrop());
        add(ModBlocks.FRUITCAKE, noDrop());

        // All blocks that drop self
        dropSelf(ModBlocks.BLACK_EMERALD_BLOCK);
        dropSelf(ModBlocks.COBALT_BLOCK);
        dropSelf(ModBlocks.RAW_COBALT_BLOCK);
        dropSelf(ModBlocks.FROSTITE_BLOCK);
        dropSelf(ModBlocks.RAW_FROSTITE_BLOCK);
        dropSelf(ModBlocks.MOURNING_GOLD_BLOCK);
        dropSelf(ModBlocks.VERDINITE_BLOCK);
        dropSelf(ModBlocks.RAW_VERDINITE_BLOCK);
        dropSelf(ModBlocks.VIVULITE_BLOCK);
        dropSelf(ModBlocks.RAW_VIVULITE_BLOCK);
        dropSelf(ModBlocks.BRIMTAN_BLOCK);
        dropSelf(ModBlocks.NECRO_WEAVE_BLOCK);
        dropSelf(ModBlocks.NECRO_RUG);
        dropSelf(ModBlocks.SUGAR_CANE_BLOCK);
        dropSelf(ModBlocks.COCOA_BEAN_BLOCK);
        dropSelf(ModBlocks.COBALT_GRILLES);

        dropSelf(ModBlocks.HIELOSTONE_TILES);
        dropSelf(ModBlocks.HIELOSTONE_TILE_STAIRS);
        add(ModBlocks.HIELOSTONE_TILE_SLAB, createSlabItemTable(ModBlocks.HIELOSTONE_TILE_SLAB));
        dropSelf(ModBlocks.HIELOSTONE_TILE_WALL);
        dropSelf(ModBlocks.HIELOSTONE_BRICKS);
        dropSelf(ModBlocks.HIELOSTONE_BRICK_STAIRS);
        add(ModBlocks.HIELOSTONE_BRICK_SLAB, createSlabItemTable(ModBlocks.HIELOSTONE_BRICK_SLAB));
        dropSelf(ModBlocks.HIELOSTONE_BRICK_WALL);
        dropSelf(ModBlocks.HIELOSTONE_PLATES);
        dropSelf(ModBlocks.HIELOSTONE_PLATE_STAIRS);
        add(ModBlocks.HIELOSTONE_PLATE_SLAB, createSlabItemTable(ModBlocks.HIELOSTONE_PLATE_SLAB));
        dropSelf(ModBlocks.HIELOSTONE_PLATE_WALL);

        dropSelf(ModBlocks.TOWER_BRICKS);
        dropSelf(ModBlocks.TOWER_BRICK_STAIRS);
        add(ModBlocks.TOWER_BRICK_SLAB, createSlabItemTable(ModBlocks.TOWER_BRICK_SLAB));
        dropSelf(ModBlocks.TOWER_BRICK_WALL);

        dropSelf(ModBlocks.MOSSY_TOWER_BRICKS);
        dropSelf(ModBlocks.MOSSY_TOWER_BRICK_STAIRS);
        add(ModBlocks.MOSSY_TOWER_BRICK_SLAB, createSlabItemTable(ModBlocks.MOSSY_TOWER_BRICK_SLAB));
        dropSelf(ModBlocks.MOSSY_TOWER_BRICK_WALL);

        dropSelf(ModBlocks.STRANGE_CORE);
        dropSelf(ModBlocks.GLOWING_OBSIDIAN);
        dropSelf(ModBlocks.ONYX_BONE_BLOCK);
        dropSelf(ModBlocks.QUICKSAND);
        dropSelf(ModBlocks.RED_QUICKSAND);
        dropSelf(ModBlocks.GLISTERING_MELON);
        dropSelf(ModBlocks.CARVED_GLISTERING_MELON);
        dropSelf(ModBlocks.CARVED_MELON);
        dropSelf(ModBlocks.JUNE_O_LANTERN);
        dropSelf(ModBlocks.GLISTERING_JUNE_O_LANTERN);
        dropSelf(ModBlocks.WHITE_PUMPKIN);
        dropSelf(ModBlocks.WHITE_JACK_O_LANTERN);

        dropSelf(ModBlocks.NACRE_BRICKS);
        dropSelf(ModBlocks.NACRE_BRICK_STAIRS);
        add(ModBlocks.NACRE_BRICK_SLAB, createSlabItemTable(ModBlocks.NACRE_BRICK_SLAB));
        dropSelf(ModBlocks.NACRE_BRICK_WALL);

        dropSelf(ModBlocks.TURTLE_SCUTE_BRICKS);
        dropSelf(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS);
        add(ModBlocks.TURTLE_SCUTE_BRICK_SLAB, createSlabItemTable(ModBlocks.TURTLE_SCUTE_BRICK_SLAB));
        dropSelf(ModBlocks.TURTLE_SCUTE_BRICK_WALL);

        dropSelf(ModBlocks.CRAGULSTANE);
        dropSelf(ModBlocks.CRAGULSTANE_BRICKS);
        dropSelf(ModBlocks.CRAGULSTANE_BRICK_STAIRS);
        add(ModBlocks.CRAGULSTANE_BRICK_SLAB, createSlabItemTable(ModBlocks.CRAGULSTANE_BRICK_SLAB));
        dropSelf(ModBlocks.CRAGULSTANE_BRICK_WALL);
        dropSelf(ModBlocks.CHISELED_CRAGULSTANE_BRICKS);
        dropSelf(ModBlocks.CRACKED_CRAGULSTANE_BRICKS);

        dropSelf(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS);
        dropSelf(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS);
        add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB, createSlabItemTable(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB));
        dropSelf(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL);
        dropSelf(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS);
        dropSelf(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS);

        dropSelf(ModBlocks.ORANGE_CRAGULSTANE_BRICKS);
        dropSelf(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS);
        add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB, createSlabItemTable(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB));
        dropSelf(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL);
        dropSelf(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS);
        dropSelf(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS);

        dropSelf(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS);
        dropSelf(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS);
        add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB, createSlabItemTable(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB));
        dropSelf(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL);
        dropSelf(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS);
        dropSelf(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS);

        dropSelf(ModBlocks.BLUE_NETHER_BRICKS);
        dropSelf(ModBlocks.CRACKED_BLUE_NETHER_BRICKS);
        dropSelf(ModBlocks.CHISELED_BLUE_NETHER_BRICKS);
        add(ModBlocks.BLUE_NETHER_BRICK_SLAB, createSlabItemTable(ModBlocks.BLUE_NETHER_BRICK_SLAB));
        dropSelf(ModBlocks.BLUE_NETHER_BRICK_STAIRS);
        dropSelf(ModBlocks.BLUE_NETHER_BRICK_WALL);
        dropSelf(ModBlocks.BLUE_NETHER_BRICK_FENCE);
        dropSelf(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE);

        dropSelf(ModBlocks.PURPLE_NETHER_BRICKS);
        dropSelf(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS);
        dropSelf(ModBlocks.CHISELED_PURPLE_NETHER_BRICKS);
        add(ModBlocks.PURPLE_NETHER_BRICK_SLAB, createSlabItemTable(ModBlocks.PURPLE_NETHER_BRICK_SLAB));
        dropSelf(ModBlocks.PURPLE_NETHER_BRICK_STAIRS);
        dropSelf(ModBlocks.PURPLE_NETHER_BRICK_WALL);
        dropSelf(ModBlocks.PURPLE_NETHER_BRICK_FENCE);
        dropSelf(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE);

        dropSelf(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        dropSelf(ModBlocks.CHISELED_RED_NETHER_BRICKS);
        dropSelf(ModBlocks.RED_NETHER_BRICK_FENCE);
        dropSelf(ModBlocks.RED_NETHER_BRICK_FENCE_GATE);

        dropSelf(ModBlocks.NETHER_BRICK_FENCE_GATE);

        dropSelf(ModBlocks.DIAMOND_LUMEN);
        dropSelf(ModBlocks.QUARTZ_LUMEN);
        dropSelf(ModBlocks.REDSTONE_LUMEN);
        dropSelf(ModBlocks.EMERALD_LUMEN);
        dropSelf(ModBlocks.AMETHYST_LUMEN);
        dropSelf(ModBlocks.COBALT_LUMEN);
        dropSelf(ModBlocks.FROSTITE_LUMEN);
        dropSelf(ModBlocks.VERDINITE_LUMEN);
        dropSelf(ModBlocks.VIVULITE_LUMEN);
        dropSelf(ModBlocks.BRIMTAN_LUMEN);
        dropSelf(ModBlocks.ECHO_LUMEN);
        dropSelf(BFBlock.FELDSPAR_LUMEN);

        dropSelf(ModBlocks.PALE_PRISMARINE);
        dropSelf(ModBlocks.PALE_PRISMARINE_STAIRS);
        add(ModBlocks.PALE_PRISMARINE_SLAB, createSlabItemTable(ModBlocks.PALE_PRISMARINE_SLAB));
        dropSelf(ModBlocks.PALE_PRISMARINE_WALL);
        dropSelf(ModBlocks.PALE_PRISMARINE_BRICKS);
        dropSelf(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS);
        add(ModBlocks.PALE_PRISMARINE_BRICK_SLAB, createSlabItemTable(ModBlocks.PALE_PRISMARINE_BRICK_SLAB));
        dropSelf(ModBlocks.DEEP_PALE_PRISMARINE);
        dropSelf(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS);
        add(ModBlocks.DEEP_PALE_PRISMARINE_SLAB, createSlabItemTable(ModBlocks.DEEP_PALE_PRISMARINE_SLAB));

        dropSelf(ModBlocks.SEA_GLASS);
        dropSelf(ModBlocks.SEA_GLASS_PANE);
        dropSelf(ModBlocks.PALE_SEA_GLASS);
        dropSelf(ModBlocks.PALE_SEA_GLASS_PANE);

        dropSelf(ModBlocks.VIVULITE_ANVIL);
        dropSelf(ModBlocks.ITEM_VACUUM);

        dropSelf(ModBlocks.OAK_WREATH);
        dropSelf(ModBlocks.DARK_OAK_WREATH);
        dropSelf(ModBlocks.BIRCH_WREATH);
        dropSelf(ModBlocks.SPRUCE_WREATH);
        dropSelf(ModBlocks.JUNGLE_WREATH);
        dropSelf(ModBlocks.ACACIA_WREATH);
        dropSelf(ModBlocks.MANGROVE_WREATH);
        dropSelf(ModBlocks.AZALEA_WREATH);
        dropSelf(ModBlocks.CHERRY_WREATH);
        dropSelf(ModBlocks.BLIGHTED_BIRCH_WREATH);

        dropSelf(ModBlocks.EBONCORK);
        dropSelf(ModBlocks.EBONCORK_PLANKS);
        dropSelf(ModBlocks.EBONCORK_STAIRS);
        add(ModBlocks.EBONCORK_SLAB, createSlabItemTable(ModBlocks.EBONCORK_SLAB));
        dropSelf(ModBlocks.EBONCORK_FENCE);
        dropSelf(ModBlocks.EBONCORK_FENCE_GATE);
        dropSelf(ModBlocks.EBONCORK_PRESSURE_PLATE);
        dropSelf(ModBlocks.EBONCORK_BUTTON);
        add(ModBlocks.EBONCORK_DOOR, createDoorTable(ModBlocks.EBONCORK_DOOR));
        dropSelf(ModBlocks.EBONCORK_TRAPDOOR);

        dropSelf(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG);
        dropSelf(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG);
        dropSelf(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD);
        dropSelf(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD);
        dropSelf(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG);
        dropSelf(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD);
        dropSelf(ModBlocks.BLIGHTED_BIRCH_PLANKS);
        dropSelf(ModBlocks.BLIGHTED_BIRCH_STAIRS);
        add(ModBlocks.BLIGHTED_BIRCH_SLAB, createSlabItemTable(ModBlocks.BLIGHTED_BIRCH_SLAB));
        dropSelf(ModBlocks.BLIGHTED_BIRCH_FENCE);
        dropSelf(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE);
        dropSelf(ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE);
        dropSelf(ModBlocks.BLIGHTED_BIRCH_BUTTON);
        add(ModBlocks.BLIGHTED_BIRCH_DOOR, createDoorTable(ModBlocks.BLIGHTED_BIRCH_DOOR));
        dropSelf(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR);

        dropSelf(ModBlocks.SPIRIT_CANDLE);
        dropSelf(ModBlocks.MONSTER_BAKERY);

        dropSelf(ModBlocks.CREEPER_MODEL);
        dropSelf(ModBlocks.SKELETON_MODEL);
        dropSelf(ModBlocks.STRAY_MODEL);
        dropSelf(ModBlocks.BOGGED_MODEL);
        dropSelf(ModBlocks.BLAZE_MODEL);
        dropSelf(ModBlocks.WITHER_SKELETON_MODEL);
        dropSelf(ModBlocks.ENDERMAN_MODEL);
        dropSelf(ModBlocks.SLIME_MODEL);
        dropSelf(ModBlocks.MAGMA_CUBE_MODEL);

        // VANILLA BLOCKS
        dropOther(Blocks.SPAWNER, ModItem.SPAWNER_CHUNK);
    }
}
