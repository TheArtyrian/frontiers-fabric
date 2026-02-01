package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.definition.block.custom.SlimeBulbBlock;
import net.artyrian.frontiers.definition.loot.LootTableHelper;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.ModDataComponents;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
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
        dropSelf(ModBlocks.ANCIENT_ROSE.get());
        dropPottedContents(ModBlocks.POTTED_ANCIENT_ROSE.get());
        // Ancient Rose Bush (Bush)
        add(ModBlocks.ANCIENT_ROSE_BUSH.get(), block -> LootTableHelper.newRoseBushDrops(block, ModBlocks.ANCIENT_ROSE.get()));
        // Ancient Rose Seed
        add(
                ModBlocks.ANCIENT_ROSE_CROP.get(),
                applyExplosionDecay(ModBlocks.ANCIENT_ROSE_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItem.ANCIENT_ROSE_SEED.get()))))
        );
        // Rose + Pot
        dropSelf(ModBlocks.ROSE.get());
        dropPottedContents(ModBlocks.POTTED_ROSE.get());
        // (Vanilla) Rose Bush (Bush) - lazy workaround but it explicitly says I dont focus on connectivity :T
        add(Blocks.ROSE_BUSH,block -> LootTableHelper.newRoseBushDrops(block, ModBlocks.ROSE.get()));
        // Violet Rose + Pot
        dropSelf(ModBlocks.VIOLET_ROSE.get());
        dropPottedContents(ModBlocks.POTTED_VIOLET_ROSE.get());
        // Violet Rose Bush (Bush)
        add(ModBlocks.VIOLET_ROSE_BUSH.get(), block -> LootTableHelper.newRoseBushDrops(block, ModBlocks.VIOLET_ROSE.get()));
        // Frostite Ore
        add(ModBlocks.FROSTITE_ORE.get(), createSilkTouchOnlyTable(ModBlocks.FROSTITE_ORE.get()));
        // Fungal Daffodil Block
        add(ModBlocks.FUNGAL_DAFFODIL_BLOCK.get(), block -> createMushroomBlockDrop(block, ModBlocks.FUNGAL_DAFFODIL.get()));
        // Snow Dahlia + Pot
        dropSelf(ModBlocks.SNOW_DAHLIA.get());
        dropPottedContents(ModBlocks.POTTED_SNOW_DAHLIA.get());
        // Fungal Daffodil + Pot
        dropSelf(ModBlocks.FUNGAL_DAFFODIL.get());
        dropPottedContents(ModBlocks.POTTED_FUNGAL_DAFFODIL.get());
        // Crimcone + Pot
        dropSelf(ModBlocks.CRIMCONE.get());
        dropPottedContents(ModBlocks.POTTED_CRIMCONE.get());
        // Experiwinkle + Pot
        add(
                ModBlocks.EXPERIWINKLE.get(),
                block -> this.createSilkTouchOrShearsDispatchTable(
                        block,
                        this.applyExplosionDecay(
                                block,
                                LootItem.lootTableItem(ModItem.EXPERIWINKLE_BULB.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))
                                )
                        )
                )
        );
        dropPottedContents(ModBlocks.POTTED_EXPERIWINKLE.get());
        // Blighted Birch Sapling + Pot
        dropSelf(ModBlocks.BLIGHTED_BIRCH_SAPLING.get());
        dropPottedContents(ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.get());
        // Phantom Bed
        this.add(ModBlocks.PHANTOM_STITCH_BED.get(), block -> this.createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        // All Corrupted Amethyst Buds
        this.add(
                ModBlocks.CORRUPTED_AMETHYST_CLUSTER.get(),
                block -> this.createSilkTouchDispatchTable(
                        block,
                        LootItem.lootTableItem(ModItem.END_CRYSTAL_SHARD.get())
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .otherwise(
                                        (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                                                block, LootItem.lootTableItem(ModItem.END_CRYSTAL_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                        )
                                )
                )
        );
        this.dropWhenSilkTouch(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get());
        // Experiwinkle Bulb
        add(
                ModBlocks.EXPERIWINKLE_CROP.get(),
                applyExplosionDecay(ModBlocks.EXPERIWINKLE_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItem.EXPERIWINKLE_BULB.get()))))
        );
        // Warped Wart
        this.add(
                ModBlocks.WARPED_WART.get(),
                block -> LootTable.lootTable()
                        .withPool(
                                this.applyExplosionDecay(
                                        block,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ModItem.WARPED_WART.get())
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
                ModBlocks.SLIME_TRAIL.get(),
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
                ModBlocks.SLIME_BULB.get(),
                block -> LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                .add(
                                        this.applyExplosionDecay(
                                                block,
                                                LootItem.lootTableItem(ModItem.HARDENED_SLIME.get())
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
        add(ModBlocks.ENCHANTING_MAGNET.get(), block -> LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(
                                        LootItem.lootTableItem(block)
                                                .when(this.hasSilkTouch())
                                                .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY).include(ModDataComponents.EXP_AMOUNT.get()))
                                                .otherwise(LootItem.lootTableItem(block))
                                )
                )
        );

        // Blighted Birch Leaves
        add(ModBlocks.BLIGHTED_BIRCH_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.BLIGHTED_BIRCH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .when(this.doesNotHaveShearsOrSilkTouch())
                                        .add(
                                                (this.applyExplosionCondition(block, LootItem.lootTableItem(ModItem.POMEGRANATE.get())))
                                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), 0.01F, 0.00111111114F, 0.0125F, 0.016666668F, 0.05F))
                                        )
        ));

        // All ores
        add(ModBlocks.COBALT_ORE.get(), block -> createOreDrop(block, ModItem.RAW_COBALT.get()));
        add(ModBlocks.DEEPSLATE_COBALT_ORE.get(), block -> createOreDrop(block, ModItem.RAW_COBALT.get()));
        add(ModBlocks.VERDINITE_ORE.get(), block -> createOreDrop(block, ModItem.RAW_VERDINITE.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(3))));
        add(ModBlocks.DEEPSLATE_VERDINITE_ORE.get(), block -> createOreDrop(block, ModItem.RAW_VERDINITE.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(3))));
        add(ModBlocks.VIVULITE_ORE.get(), block -> createOreDrop(block, ModItem.RAW_VIVULITE.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(2))));
        add(ModBlocks.DEEPSLATE_VIVULITE_ORE.get(), block -> createOreDrop(block, ModItem.RAW_VIVULITE.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(2))));
        add(ModBlocks.BLACK_EMERALD_ORE.get(), block -> createOreDrop(block, ModItem.BLACK_EMERALD.get()));
        add(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get(), block -> createOreDrop(block, ModItem.BLACK_EMERALD.get()));
        add(ModBlocks.BRIMTAN_ORE.get(), block -> createOreDrop(block, ModItem.BRIMTAN_CLUSTER.get())
                .apply(LimitCount.limitCount(IntRange.upperBound(2))));

        // Stone-likes
        add(ModBlocks.HIELOSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, ModBlocks.COBBLEFROST.get()));
        dropSelf(ModBlocks.HIELOSTONE_STAIRS.get());
        add(ModBlocks.HIELOSTONE_SLAB.get(), createSlabItemTable(ModBlocks.HIELOSTONE_SLAB.get()));
        dropSelf(ModBlocks.HIELOSTONE_WALL.get());
        dropSelf(ModBlocks.COBBLEFROST.get());
        dropSelf(ModBlocks.COBBLEFROST_STAIRS.get());
        add(ModBlocks.COBBLEFROST_SLAB.get(), createSlabItemTable(ModBlocks.COBBLEFROST_SLAB.get()));
        dropSelf(ModBlocks.COBBLEFROST_WALL.get());

        add(ModBlocks.PERSONAL_CHEST.get(), this::createNameableBlockEntityTable);
        add(ModBlocks.CURSE_ALTAR.get(), this::createNameableBlockEntityTable);

        // Nothing drops
        add(ModBlocks.BEEF_WELLINGTON.get(), noDrop());
        add(ModBlocks.FRUITCAKE.get(), noDrop());

        // All blocks that drop self
        dropSelf(ModBlocks.BLACK_EMERALD_BLOCK.get());
        dropSelf(ModBlocks.COBALT_BLOCK.get());
        dropSelf(ModBlocks.RAW_COBALT_BLOCK.get());
        dropSelf(ModBlocks.FROSTITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_FROSTITE_BLOCK.get());
        dropSelf(ModBlocks.MOURNING_GOLD_BLOCK.get());
        dropSelf(ModBlocks.VERDINITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_VERDINITE_BLOCK.get());
        dropSelf(ModBlocks.VIVULITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_VIVULITE_BLOCK.get());
        dropSelf(ModBlocks.BRIMTAN_BLOCK.get());
        dropSelf(ModBlocks.NECRO_WEAVE_BLOCK.get());
        dropSelf(ModBlocks.NECRO_RUG.get());
        dropSelf(ModBlocks.SUGAR_CANE_BLOCK.get());
        dropSelf(ModBlocks.COCOA_BEAN_BLOCK.get());
        dropSelf(ModBlocks.COBALT_GRILLES.get());

        dropSelf(ModBlocks.HIELOSTONE_TILES.get());
        dropSelf(ModBlocks.HIELOSTONE_TILE_STAIRS.get());
        add(ModBlocks.HIELOSTONE_TILE_SLAB.get(), createSlabItemTable(ModBlocks.HIELOSTONE_TILE_SLAB.get()));
        dropSelf(ModBlocks.HIELOSTONE_TILE_WALL.get());
        dropSelf(ModBlocks.HIELOSTONE_BRICKS.get());
        dropSelf(ModBlocks.HIELOSTONE_BRICK_STAIRS.get());
        add(ModBlocks.HIELOSTONE_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.HIELOSTONE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.HIELOSTONE_BRICK_WALL.get());
        dropSelf(ModBlocks.HIELOSTONE_PLATES.get());
        dropSelf(ModBlocks.HIELOSTONE_PLATE_STAIRS.get());
        add(ModBlocks.HIELOSTONE_PLATE_SLAB.get(), createSlabItemTable(ModBlocks.HIELOSTONE_PLATE_SLAB.get()));
        dropSelf(ModBlocks.HIELOSTONE_PLATE_WALL.get());

        dropSelf(ModBlocks.TOWER_BRICKS.get());
        dropSelf(ModBlocks.TOWER_BRICK_STAIRS.get());
        add(ModBlocks.TOWER_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.TOWER_BRICK_SLAB.get()));
        dropSelf(ModBlocks.TOWER_BRICK_WALL.get());

        dropSelf(ModBlocks.MOSSY_TOWER_BRICKS.get());
        dropSelf(ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get());
        add(ModBlocks.MOSSY_TOWER_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.MOSSY_TOWER_BRICK_SLAB.get()));
        dropSelf(ModBlocks.MOSSY_TOWER_BRICK_WALL.get());

        dropSelf(ModBlocks.STRANGE_CORE.get());
        dropSelf(ModBlocks.GLOWING_OBSIDIAN.get());
        dropSelf(ModBlocks.ONYX_BONE_BLOCK.get());
        dropSelf(ModBlocks.QUICKSAND.get());
        dropSelf(ModBlocks.RED_QUICKSAND.get());
        dropSelf(ModBlocks.GLISTERING_MELON.get());
        dropSelf(ModBlocks.CARVED_GLISTERING_MELON.get());
        dropSelf(ModBlocks.CARVED_MELON.get());
        dropSelf(ModBlocks.JUNE_O_LANTERN.get());
        dropSelf(ModBlocks.GLISTERING_JUNE_O_LANTERN.get());
        dropSelf(ModBlocks.WHITE_PUMPKIN.get());
        dropSelf(ModBlocks.WHITE_JACK_O_LANTERN.get());

        dropSelf(ModBlocks.NACRE_BRICKS.get());
        dropSelf(ModBlocks.NACRE_BRICK_STAIRS.get());
        add(ModBlocks.NACRE_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.NACRE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.NACRE_BRICK_WALL.get());

        dropSelf(ModBlocks.TURTLE_SCUTE_BRICKS.get());
        dropSelf(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.get());
        add(ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.TURTLE_SCUTE_BRICK_WALL.get());

        dropSelf(ModBlocks.CRAGULSTANE.get());
        dropSelf(ModBlocks.CRAGULSTANE_BRICKS.get());
        dropSelf(ModBlocks.CRAGULSTANE_BRICK_STAIRS.get());
        add(ModBlocks.CRAGULSTANE_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.CRAGULSTANE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.CRAGULSTANE_BRICK_WALL.get());
        dropSelf(ModBlocks.CHISELED_CRAGULSTANE_BRICKS.get());
        dropSelf(ModBlocks.CRACKED_CRAGULSTANE_BRICKS.get());

        dropSelf(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get());
        dropSelf(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get());
        add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get());
        dropSelf(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get());
        dropSelf(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get());

        dropSelf(ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get());
        dropSelf(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get());
        add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get());
        dropSelf(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get());
        dropSelf(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get());

        dropSelf(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get());
        dropSelf(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get());
        add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get());
        dropSelf(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get());
        dropSelf(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get());

        dropSelf(ModBlocks.BLUE_NETHER_BRICKS.get());
        dropSelf(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get());
        dropSelf(ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get());
        add(ModBlocks.BLUE_NETHER_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.BLUE_NETHER_BRICK_SLAB.get()));
        dropSelf(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get());
        dropSelf(ModBlocks.BLUE_NETHER_BRICK_WALL.get());
        dropSelf(ModBlocks.BLUE_NETHER_BRICK_FENCE.get());
        dropSelf(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());

        dropSelf(ModBlocks.PURPLE_NETHER_BRICKS.get());
        dropSelf(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS.get());
        dropSelf(ModBlocks.CHISELED_PURPLE_NETHER_BRICKS.get());
        add(ModBlocks.PURPLE_NETHER_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.PURPLE_NETHER_BRICK_SLAB.get()));
        dropSelf(ModBlocks.PURPLE_NETHER_BRICK_STAIRS.get());
        dropSelf(ModBlocks.PURPLE_NETHER_BRICK_WALL.get());
        dropSelf(ModBlocks.PURPLE_NETHER_BRICK_FENCE.get());
        dropSelf(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get());

        dropSelf(ModBlocks.CRACKED_RED_NETHER_BRICKS.get());
        dropSelf(ModBlocks.CHISELED_RED_NETHER_BRICKS.get());
        dropSelf(ModBlocks.RED_NETHER_BRICK_FENCE.get());
        dropSelf(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get());

        dropSelf(ModBlocks.NETHER_BRICK_FENCE_GATE.get());

        dropSelf(ModBlocks.DIAMOND_LUMEN.get());
        dropSelf(ModBlocks.QUARTZ_LUMEN.get());
        dropSelf(ModBlocks.REDSTONE_LUMEN.get());
        dropSelf(ModBlocks.EMERALD_LUMEN.get());
        dropSelf(ModBlocks.AMETHYST_LUMEN.get());
        dropSelf(ModBlocks.COBALT_LUMEN.get());
        dropSelf(ModBlocks.FROSTITE_LUMEN.get());
        dropSelf(ModBlocks.VERDINITE_LUMEN.get());
        dropSelf(ModBlocks.VIVULITE_LUMEN.get());
        dropSelf(ModBlocks.BRIMTAN_LUMEN.get());
        dropSelf(ModBlocks.ECHO_LUMEN.get());
        dropSelf(BFBlock.FELDSPAR_LUMEN.get());

        dropSelf(ModBlocks.PALE_PRISMARINE.get());
        dropSelf(ModBlocks.PALE_PRISMARINE_STAIRS.get());
        add(ModBlocks.PALE_PRISMARINE_SLAB.get(), createSlabItemTable(ModBlocks.PALE_PRISMARINE_SLAB.get()));
        dropSelf(ModBlocks.PALE_PRISMARINE_WALL.get());
        dropSelf(ModBlocks.PALE_PRISMARINE_BRICKS.get());
        dropSelf(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.get());
        add(ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), createSlabItemTable(ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.DEEP_PALE_PRISMARINE.get());
        dropSelf(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.get());
        add(ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), createSlabItemTable(ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get()));

        dropSelf(ModBlocks.SEA_GLASS.get());
        dropSelf(ModBlocks.SEA_GLASS_PANE.get());
        dropSelf(ModBlocks.PALE_SEA_GLASS.get());
        dropSelf(ModBlocks.PALE_SEA_GLASS_PANE.get());

        dropSelf(ModBlocks.VIVULITE_ANVIL.get());
        dropSelf(ModBlocks.ITEM_VACUUM.get());

        dropSelf(ModBlocks.OAK_WREATH.get());
        dropSelf(ModBlocks.DARK_OAK_WREATH.get());
        dropSelf(ModBlocks.BIRCH_WREATH.get());
        dropSelf(ModBlocks.SPRUCE_WREATH.get());
        dropSelf(ModBlocks.JUNGLE_WREATH.get());
        dropSelf(ModBlocks.ACACIA_WREATH.get());
        dropSelf(ModBlocks.MANGROVE_WREATH.get());
        dropSelf(ModBlocks.AZALEA_WREATH.get());
        dropSelf(ModBlocks.CHERRY_WREATH.get());
        dropSelf(ModBlocks.BLIGHTED_BIRCH_WREATH.get());

        dropSelf(ModBlocks.EBONCORK.get());
        dropSelf(ModBlocks.EBONCORK_PLANKS.get());
        dropSelf(ModBlocks.EBONCORK_STAIRS.get());
        add(ModBlocks.EBONCORK_SLAB.get(), createSlabItemTable(ModBlocks.EBONCORK_SLAB.get()));
        dropSelf(ModBlocks.EBONCORK_FENCE.get());
        dropSelf(ModBlocks.EBONCORK_FENCE_GATE.get());
        dropSelf(ModBlocks.EBONCORK_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.EBONCORK_BUTTON.get());
        add(ModBlocks.EBONCORK_DOOR.get(), createDoorTable(ModBlocks.EBONCORK_DOOR.get()));
        dropSelf(ModBlocks.EBONCORK_TRAPDOOR.get());

        dropSelf(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get());
        dropSelf(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get());
        dropSelf(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get());
        dropSelf(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());
        dropSelf(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
        dropSelf(ModBlocks.BLIGHTED_BIRCH_PLANKS.get());
        dropSelf(ModBlocks.BLIGHTED_BIRCH_STAIRS.get());
        add(ModBlocks.BLIGHTED_BIRCH_SLAB.get(), createSlabItemTable(ModBlocks.BLIGHTED_BIRCH_SLAB.get()));
        dropSelf(ModBlocks.BLIGHTED_BIRCH_FENCE.get());
        dropSelf(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get());
        dropSelf(ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.BLIGHTED_BIRCH_BUTTON.get());
        add(ModBlocks.BLIGHTED_BIRCH_DOOR.get(), createDoorTable(ModBlocks.BLIGHTED_BIRCH_DOOR.get()));
        dropSelf(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.get());

        dropSelf(ModBlocks.SPIRIT_CANDLE.get());
        dropSelf(ModBlocks.MONSTER_BAKERY.get());

        dropSelf(ModBlocks.CREEPER_MODEL.get());
        dropSelf(ModBlocks.SKELETON_MODEL.get());
        dropSelf(ModBlocks.STRAY_MODEL.get());
        dropSelf(ModBlocks.BOGGED_MODEL.get());
        dropSelf(ModBlocks.BLAZE_MODEL.get());
        dropSelf(ModBlocks.WITHER_SKELETON_MODEL.get());
        dropSelf(ModBlocks.ENDERMAN_MODEL.get());
        dropSelf(ModBlocks.SLIME_MODEL.get());
        dropSelf(ModBlocks.MAGMA_CUBE_MODEL.get());

        // VANILLA BLOCKS
        // TODO: REPLACE SO DD DOESN'T INTERFERE
        dropOther(Blocks.SPAWNER, ModItem.SPAWNER_CHUNK.get());
    }
}
