package net.artyrian.frontiers.datagen.frontiers.loot;

import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.definition.block.custom.SlimeBulbBlock;
import net.artyrian.frontiers.definition.loot.helpers.LootTableHelper;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.content.FRTags;
import net.artyrian.frontiers.reg.misc.FRDataComponents;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
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
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;

public class FRLootTableProvider extends FabricBlockLootTableProvider
{
    // Set a few predicates.
    public static final LootItemCondition.Builder NO_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)).invert();
    public static final LootItemCondition.Builder WITH_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));

    public FRLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
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
        dropSelf(FRBlocks.ANCIENT_ROSE.get());
        dropPottedContents(FRBlocks.POTTED_ANCIENT_ROSE.get());
        // Ancient Rose Bush (Bush)
        add(FRBlocks.ANCIENT_ROSE_BUSH.get(), block -> LootTableHelper.newRoseBushDrops(block, FRBlocks.ANCIENT_ROSE.get()));
        // Ancient Rose Seed
        add(
                FRBlocks.ANCIENT_ROSE_CROP.get(),
                applyExplosionDecay(FRBlocks.ANCIENT_ROSE_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(FRItems.ANCIENT_ROSE_SEED.get()))))
        );
        // Rose + Pot
        dropSelf(FRBlocks.ROSE.get());
        dropPottedContents(FRBlocks.POTTED_ROSE.get());
        // (Vanilla) Rose Bush (Bush) - lazy workaround but it explicitly says I dont focus on connectivity :T
        add(Blocks.ROSE_BUSH,block -> LootTableHelper.newRoseBushDrops(block, FRBlocks.ROSE.get()));
        // Violet Rose + Pot
        dropSelf(FRBlocks.VIOLET_ROSE.get());
        dropPottedContents(FRBlocks.POTTED_VIOLET_ROSE.get());
        // Violet Rose Bush (Bush)
        add(FRBlocks.VIOLET_ROSE_BUSH.get(), block -> LootTableHelper.newRoseBushDrops(block, FRBlocks.VIOLET_ROSE.get()));
        // Frostite Ore
        add(FRBlocks.FROSTITE_ORE.get(), createSilkTouchOnlyTable(FRBlocks.FROSTITE_ORE.get()));
        // Fungal Daffodil Block
        add(FRBlocks.FUNGAL_DAFFODIL_BLOCK.get(), block -> createMushroomBlockDrop(block, FRBlocks.FUNGAL_DAFFODIL.get()));
        // Snow Dahlia + Pot
        dropSelf(FRBlocks.SNOW_DAHLIA.get());
        dropPottedContents(FRBlocks.POTTED_SNOW_DAHLIA.get());
        // Fungal Daffodil + Pot
        dropSelf(FRBlocks.FUNGAL_DAFFODIL.get());
        dropPottedContents(FRBlocks.POTTED_FUNGAL_DAFFODIL.get());
        // Crimcone + Pot
        dropSelf(FRBlocks.CRIMCONE.get());
        dropPottedContents(FRBlocks.POTTED_CRIMCONE.get());
        // Experiwinkle + Pot
        add(
                FRBlocks.EXPERIWINKLE.get(),
                block -> this.createSilkTouchOrShearsDispatchTable(
                        block,
                        this.applyExplosionDecay(
                                block,
                                LootItem.lootTableItem(FRItems.EXPERIWINKLE_BULB.get())
                                        .when(InvertedLootItemCondition.invert(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(FRTags.EntityTypes.CANNOT_DROP_EXPERIWINKLE))
                                        ))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))
                                )
                        )
                )
        );
        dropPottedContents(FRBlocks.POTTED_EXPERIWINKLE.get());
        // Blighted Birch Sapling + Pot
        dropSelf(FRBlocks.BLIGHTED_BIRCH_SAPLING.get());
        dropPottedContents(FRBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.get());
        // Phantom Bed
        this.add(FRBlocks.PHANTOM_STITCH_BED.get(), block -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        // All Corrupted Amethyst Buds
        this.add(
                FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get(),
                block -> this.createSilkTouchDispatchTable(
                        block,
                        LootItem.lootTableItem(FRItems.END_CRYSTAL_SHARD.get())
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .otherwise(this.applyExplosionDecay(block, LootItem.lootTableItem(FRItems.END_CRYSTAL_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))
                )
        );
        this.dropWhenSilkTouch(FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get());
        this.dropWhenSilkTouch(FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get());
        this.dropWhenSilkTouch(FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get());
        // Experiwinkle Bulb
        add(
                FRBlocks.EXPERIWINKLE_CROP.get(),
                applyExplosionDecay(FRBlocks.EXPERIWINKLE_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(FRItems.EXPERIWINKLE_BULB.get()))))
        );
        // Warped Wart
        this.add(
                FRBlocks.WARPED_WART.get(),
                block -> LootTable.lootTable()
                        .withPool(
                                this.applyExplosionDecay(
                                        block,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(FRItems.WARPED_WART.get())
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
                FRBlocks.SLIME_TRAIL.get(),
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
                FRBlocks.SLIME_BULB.get(),
                block -> LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                .add(
                                        this.applyExplosionDecay(
                                                block,
                                                LootItem.lootTableItem(FRItems.HARDENED_SLIME.get())
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
        add(FRBlocks.ENCHANTING_MAGNET.get(), block -> LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(
                                        LootItem.lootTableItem(block)
                                                .when(this.hasSilkTouch())
                                                .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY).include(FRDataComponents.EXP_AMOUNT.get()))
                                                .otherwise(LootItem.lootTableItem(block))
                                )
                )
        );

        // Blighted Birch Leaves
        add(FRBlocks.BLIGHTED_BIRCH_LEAVES.get(), block -> createLeavesDrops(block, FRBlocks.BLIGHTED_BIRCH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .when(this.doesNotHaveShearsOrSilkTouch())
                                        .add(
                                                (this.applyExplosionCondition(block, LootItem.lootTableItem(FRItems.POMEGRANATE.get())))
                                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), 0.01F, 0.00111111114F, 0.0125F, 0.016666668F, 0.05F))
                                        )
        ));

        // All ores
        add(FRBlocks.COBALT_ORE.get(), block -> createOreDrop(block, FRItems.RAW_COBALT.get()));
        add(FRBlocks.DEEPSLATE_COBALT_ORE.get(), block -> createOreDrop(block, FRItems.RAW_COBALT.get()));
        add(FRBlocks.VERDINITE_ORE.get(), block -> createOreDrop(block, FRItems.RAW_VERDINITE.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(3))));
        add(FRBlocks.DEEPSLATE_VERDINITE_ORE.get(), block -> createOreDrop(block, FRItems.RAW_VERDINITE.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(3))));
        add(FRBlocks.VIVULITE_ORE.get(), block -> createOreDrop(block, FRItems.RAW_VIVULITE.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(2))));
        add(FRBlocks.DEEPSLATE_VIVULITE_ORE.get(), block -> createOreDrop(block, FRItems.RAW_VIVULITE.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(2))));
        add(FRBlocks.BLACK_EMERALD_ORE.get(), block -> createOreDrop(block, FRItems.BLACK_EMERALD.get()));
        add(FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get(), block -> createOreDrop(block, FRItems.BLACK_EMERALD.get()));
        add(FRBlocks.BRIMTAN_ORE.get(), block -> createOreDrop(block, FRItems.BRIMTAN_CLUSTER.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(2))));

        // Stone-likes
        add(FRBlocks.HIELOSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, FRBlocks.COBBLEFROST.get()));
        dropSelf(FRBlocks.HIELOSTONE_STAIRS.get());
        add(FRBlocks.HIELOSTONE_SLAB.get(), createSlabItemTable(FRBlocks.HIELOSTONE_SLAB.get()));
        dropSelf(FRBlocks.HIELOSTONE_WALL.get());
        dropSelf(FRBlocks.COBBLEFROST.get());
        dropSelf(FRBlocks.COBBLEFROST_STAIRS.get());
        add(FRBlocks.COBBLEFROST_SLAB.get(), createSlabItemTable(FRBlocks.COBBLEFROST_SLAB.get()));
        dropSelf(FRBlocks.COBBLEFROST_WALL.get());

        // Unique Slabs
        add(FRBlocks.EGG_PALLET.get(), createSlabItemTable(FRBlocks.EGG_PALLET.get()));
        add(FRBlocks.GOLDEN_EGG_PALLET.get(), createSlabItemTable(FRBlocks.GOLDEN_EGG_PALLET.get()));

        add(FRBlocks.PERSONAL_CHEST.get(), this::createNameableBlockEntityTable);
        add(FRBlocks.CURSE_ALTAR.get(), this::createNameableBlockEntityTable);

        // Nothing drops
        add(FRBlocks.BEEF_WELLINGTON.get(), noDrop());
        add(FRBlocks.FRUITCAKE.get(), noDrop());
        add(FRBlocks.TOWER_WATCHER.get(), noDrop());
        add(FRBlocks.TOWER_SPAWNER.get(), noDrop());
        add(FRBlocks.TOWER_HEART.get(), noDrop());

        // All blocks that drop self
        dropSelf(FRBlocks.BLACK_EMERALD_BLOCK.get());
        dropSelf(FRBlocks.COBALT_BLOCK.get());
        dropSelf(FRBlocks.RAW_COBALT_BLOCK.get());
        dropSelf(FRBlocks.FROSTITE_BLOCK.get());
        dropSelf(FRBlocks.RAW_FROSTITE_BLOCK.get());
        dropSelf(FRBlocks.MOURNING_GOLD_BLOCK.get());
        dropSelf(FRBlocks.VERDINITE_BLOCK.get());
        dropSelf(FRBlocks.RAW_VERDINITE_BLOCK.get());
        dropSelf(FRBlocks.VIVULITE_BLOCK.get());
        dropSelf(FRBlocks.RAW_VIVULITE_BLOCK.get());
        dropSelf(FRBlocks.BRIMTAN_BLOCK.get());
        dropSelf(FRBlocks.NECRO_WEAVE_BLOCK.get());
        dropSelf(FRBlocks.NECRO_RUG.get());
        dropSelf(FRBlocks.SUGAR_CANE_BLOCK.get());
        dropSelf(FRBlocks.COCOA_BEAN_BLOCK.get());
        dropSelf(FRBlocks.ROTTEN_FLESH_BLOCK.get());
        dropSelf(FRBlocks.COBALT_GRILLES.get());

        dropSelf(FRBlocks.HIELOSTONE_TILES.get());
        dropSelf(FRBlocks.HIELOSTONE_TILE_STAIRS.get());
        add(FRBlocks.HIELOSTONE_TILE_SLAB.get(), createSlabItemTable(FRBlocks.HIELOSTONE_TILE_SLAB.get()));
        dropSelf(FRBlocks.HIELOSTONE_TILE_WALL.get());
        dropSelf(FRBlocks.HIELOSTONE_BRICKS.get());
        dropSelf(FRBlocks.HIELOSTONE_BRICK_STAIRS.get());
        add(FRBlocks.HIELOSTONE_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.HIELOSTONE_BRICK_SLAB.get()));
        dropSelf(FRBlocks.HIELOSTONE_BRICK_WALL.get());
        dropSelf(FRBlocks.HIELOSTONE_PLATES.get());
        dropSelf(FRBlocks.HIELOSTONE_PLATE_STAIRS.get());
        add(FRBlocks.HIELOSTONE_PLATE_SLAB.get(), createSlabItemTable(FRBlocks.HIELOSTONE_PLATE_SLAB.get()));
        dropSelf(FRBlocks.HIELOSTONE_PLATE_WALL.get());

        dropSelf(FRBlocks.TOWER_BRICKS.get());
        dropSelf(FRBlocks.TOWER_BRICK_STAIRS.get());
        add(FRBlocks.TOWER_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.TOWER_BRICK_SLAB.get()));
        dropSelf(FRBlocks.TOWER_BRICK_WALL.get());

        dropSelf(FRBlocks.MOSSY_TOWER_BRICKS.get());
        dropSelf(FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get());
        add(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get()));
        dropSelf(FRBlocks.MOSSY_TOWER_BRICK_WALL.get());

        dropSelf(FRBlocks.QUICKSAND.get());
        dropSelf(FRBlocks.CRUSTED_QUICKSAND.get());
        dropSelf(FRBlocks.CRUSTY_SAND_BRICKS.get());
        dropSelf(FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get());
        add(FRBlocks.CRUSTY_SAND_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.CRUSTY_SAND_BRICK_SLAB.get()));
        dropSelf(FRBlocks.CRUSTY_SAND_BRICK_WALL.get());

        dropSelf(FRBlocks.RED_QUICKSAND.get());
        dropSelf(FRBlocks.CRUSTED_RED_QUICKSAND.get());
        dropSelf(FRBlocks.CRUSTY_RED_SAND_BRICKS.get());
        dropSelf(FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get());
        add(FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get()));
        dropSelf(FRBlocks.CRUSTY_RED_SAND_BRICK_WALL.get());

        dropSelf(FRBlocks.STRANGE_CORE.get());
        dropSelf(FRBlocks.GLOWING_OBSIDIAN.get());
        dropSelf(FRBlocks.ONYX_BONE_BLOCK.get());
        dropSelf(FRBlocks.GLISTERING_MELON.get());
        dropSelf(FRBlocks.CARVED_GLISTERING_MELON.get());
        dropSelf(FRBlocks.CARVED_MELON.get());
        dropSelf(FRBlocks.JUNE_O_LANTERN.get());
        dropSelf(FRBlocks.GLISTERING_JUNE_O_LANTERN.get());
        dropSelf(FRBlocks.WHITE_PUMPKIN.get());
        dropSelf(FRBlocks.WHITE_JACK_O_LANTERN.get());

        dropSelf(FRBlocks.NACRE_BRICKS.get());
        dropSelf(FRBlocks.NACRE_BRICK_STAIRS.get());
        add(FRBlocks.NACRE_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.NACRE_BRICK_SLAB.get()));
        dropSelf(FRBlocks.NACRE_BRICK_WALL.get());

        dropSelf(FRBlocks.TURTLE_SCUTE_BRICKS.get());
        dropSelf(FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get());
        add(FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get()));
        dropSelf(FRBlocks.TURTLE_SCUTE_BRICK_WALL.get());

        dropSelf(FRBlocks.CRAGULSTANE.get());
        dropSelf(FRBlocks.CRAGULSTANE_BRICKS.get());
        dropSelf(FRBlocks.CRAGULSTANE_BRICK_STAIRS.get());
        add(FRBlocks.CRAGULSTANE_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.CRAGULSTANE_BRICK_SLAB.get()));
        dropSelf(FRBlocks.CRAGULSTANE_BRICK_WALL.get());
        dropSelf(FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get());
        dropSelf(FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get());

        dropSelf(FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get());
        dropSelf(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get());
        add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get()));
        dropSelf(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get());
        dropSelf(FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get());
        dropSelf(FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get());

        dropSelf(FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get());
        dropSelf(FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get());
        add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get()));
        dropSelf(FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get());
        dropSelf(FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get());
        dropSelf(FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get());

        dropSelf(FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get());
        dropSelf(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get());
        add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get()));
        dropSelf(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get());
        dropSelf(FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get());
        dropSelf(FRBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get());

        dropSelf(FRBlocks.BLUE_NETHER_BRICKS.get());
        dropSelf(FRBlocks.CRACKED_BLUE_NETHER_BRICKS.get());
        dropSelf(FRBlocks.CHISELED_BLUE_NETHER_BRICKS.get());
        add(FRBlocks.BLUE_NETHER_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.BLUE_NETHER_BRICK_SLAB.get()));
        dropSelf(FRBlocks.BLUE_NETHER_BRICK_STAIRS.get());
        dropSelf(FRBlocks.BLUE_NETHER_BRICK_WALL.get());
        dropSelf(FRBlocks.BLUE_NETHER_BRICK_FENCE.get());
        dropSelf(FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());

        dropSelf(FRBlocks.PURPLE_NETHER_BRICKS.get());
        dropSelf(FRBlocks.CRACKED_PURPLE_NETHER_BRICKS.get());
        dropSelf(FRBlocks.CHISELED_PURPLE_NETHER_BRICKS.get());
        add(FRBlocks.PURPLE_NETHER_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.PURPLE_NETHER_BRICK_SLAB.get()));
        dropSelf(FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get());
        dropSelf(FRBlocks.PURPLE_NETHER_BRICK_WALL.get());
        dropSelf(FRBlocks.PURPLE_NETHER_BRICK_FENCE.get());
        dropSelf(FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get());

        dropSelf(FRBlocks.CRACKED_RED_NETHER_BRICKS.get());
        dropSelf(FRBlocks.CHISELED_RED_NETHER_BRICKS.get());
        dropSelf(FRBlocks.RED_NETHER_BRICK_FENCE.get());
        dropSelf(FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get());

        dropSelf(FRBlocks.NETHER_BRICK_FENCE_GATE.get());

        dropSelf(FRBlocks.DIAMOND_LUMEN.get());
        dropSelf(FRBlocks.QUARTZ_LUMEN.get());
        dropSelf(FRBlocks.REDSTONE_LUMEN.get());
        dropSelf(FRBlocks.EMERALD_LUMEN.get());
        dropSelf(FRBlocks.AMETHYST_LUMEN.get());
        dropSelf(FRBlocks.COBALT_LUMEN.get());
        dropSelf(FRBlocks.FROSTITE_LUMEN.get());
        dropSelf(FRBlocks.VERDINITE_LUMEN.get());
        dropSelf(FRBlocks.VIVULITE_LUMEN.get());
        dropSelf(FRBlocks.BRIMTAN_LUMEN.get());
        dropSelf(FRBlocks.ECHO_LUMEN.get());
        dropSelf(BFBlock.FELDSPAR_LUMEN.get());

        dropSelf(FRBlocks.PALE_PRISMARINE.get());
        dropSelf(FRBlocks.PALE_PRISMARINE_STAIRS.get());
        add(FRBlocks.PALE_PRISMARINE_SLAB.get(), createSlabItemTable(FRBlocks.PALE_PRISMARINE_SLAB.get()));
        dropSelf(FRBlocks.PALE_PRISMARINE_WALL.get());
        dropSelf(FRBlocks.PALE_PRISMARINE_BRICKS.get());
        dropSelf(FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get());
        add(FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), createSlabItemTable(FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get()));
        dropSelf(FRBlocks.DEEP_PALE_PRISMARINE.get());
        dropSelf(FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get());
        add(FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), createSlabItemTable(FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get()));

        dropSelf(FRBlocks.SEA_GLASS.get());
        dropSelf(FRBlocks.SEA_GLASS_PANE.get());
        dropSelf(FRBlocks.PALE_SEA_GLASS.get());
        dropSelf(FRBlocks.PALE_SEA_GLASS_PANE.get());

        dropSelf(FRBlocks.VIVULITE_ANVIL.get());
        dropSelf(FRBlocks.ITEM_VACUUM.get());

        dropSelf(FRBlocks.OAK_WREATH.get());
        dropSelf(FRBlocks.DARK_OAK_WREATH.get());
        dropSelf(FRBlocks.BIRCH_WREATH.get());
        dropSelf(FRBlocks.SPRUCE_WREATH.get());
        dropSelf(FRBlocks.JUNGLE_WREATH.get());
        dropSelf(FRBlocks.ACACIA_WREATH.get());
        dropSelf(FRBlocks.MANGROVE_WREATH.get());
        dropSelf(FRBlocks.AZALEA_WREATH.get());
        dropSelf(FRBlocks.CHERRY_WREATH.get());
        dropSelf(FRBlocks.BLIGHTED_BIRCH_WREATH.get());

        dropSelf(FRBlocks.EBONCORK.get());
        dropSelf(FRBlocks.EBONCORK_PLANKS.get());
        dropSelf(FRBlocks.EBONCORK_STAIRS.get());
        add(FRBlocks.EBONCORK_SLAB.get(), createSlabItemTable(FRBlocks.EBONCORK_SLAB.get()));
        dropSelf(FRBlocks.EBONCORK_FENCE.get());
        dropSelf(FRBlocks.EBONCORK_FENCE_GATE.get());
        dropSelf(FRBlocks.EBONCORK_PRESSURE_PLATE.get());
        dropSelf(FRBlocks.EBONCORK_BUTTON.get());
        add(FRBlocks.EBONCORK_DOOR.get(), createDoorTable(FRBlocks.EBONCORK_DOOR.get()));
        dropSelf(FRBlocks.EBONCORK_TRAPDOOR.get());

        dropSelf(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get());
        dropSelf(FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get());
        dropSelf(FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get());
        dropSelf(FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get());
        dropSelf(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());
        dropSelf(FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
        dropSelf(FRBlocks.BLIGHTED_BIRCH_PLANKS.get());
        dropSelf(FRBlocks.BLIGHTED_BIRCH_STAIRS.get());
        add(FRBlocks.BLIGHTED_BIRCH_SLAB.get(), createSlabItemTable(FRBlocks.BLIGHTED_BIRCH_SLAB.get()));
        dropSelf(FRBlocks.BLIGHTED_BIRCH_FENCE.get());
        dropSelf(FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get());
        dropSelf(FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get());
        dropSelf(FRBlocks.BLIGHTED_BIRCH_BUTTON.get());
        add(FRBlocks.BLIGHTED_BIRCH_DOOR.get(), createDoorTable(FRBlocks.BLIGHTED_BIRCH_DOOR.get()));
        dropSelf(FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get());

        dropSelf(FRBlocks.SPIRIT_CANDLE.get());
        dropSelf(FRBlocks.MONSTER_BAKERY.get());

        dropSelf(FRBlocks.CREEPER_MODEL.get());
        dropSelf(FRBlocks.SKELETON_MODEL.get());
        dropSelf(FRBlocks.STRAY_MODEL.get());
        dropSelf(FRBlocks.BOGGED_MODEL.get());
        dropSelf(FRBlocks.BLAZE_MODEL.get());
        dropSelf(FRBlocks.WITHER_SKELETON_MODEL.get());
        dropSelf(FRBlocks.ENDERMAN_MODEL.get());
        dropSelf(FRBlocks.SLIME_MODEL.get());
        dropSelf(FRBlocks.MAGMA_CUBE_MODEL.get());
        dropSelf(FRBlocks.PHANTOM_MODEL.get());
    }
}
