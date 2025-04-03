package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.util.LootTableHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
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

        // Ancient Rose
        addDrop(ModBlocks.ANCIENT_ROSE);
        // Ancient Rose Flower Pot
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

        // All ores
        addDrop(ModBlocks.COBALT_ORE, block -> oreDrops(block, ModItem.RAW_COBALT));
        addDrop(ModBlocks.DEEPSLATE_COBALT_ORE, block -> oreDrops(block, ModItem.RAW_COBALT));
        addDrop(ModBlocks.VERDINITE_ORE, block -> oreDrops(block, ModItem.RAW_VERDINITE));
        addDrop(ModBlocks.DEEPSLATE_VERDINITE_ORE, block -> oreDrops(block, ModItem.RAW_VERDINITE));
        addDrop(ModBlocks.VIVULITE_ORE, block -> oreDrops(block, ModItem.RAW_VIVULITE));
        addDrop(ModBlocks.DEEPSLATE_VIVULITE_ORE, block -> oreDrops(block, ModItem.RAW_VIVULITE));

        // Stone-likes
        addDrop(ModBlocks.HIELOSTONE, block -> this.drops(block, ModBlocks.COBBLEFROST));
        addDrop(ModBlocks.COBBLEFROST);

        // All blocks that drop self
        addDrop(ModBlocks.COBALT_BLOCK);
        addDrop(ModBlocks.FROSTITE_BLOCK);
        addDrop(ModBlocks.MOURNING_GOLD_BLOCK);

        addDrop(ModBlocks.HIELOSTONE_TILES);
        addDrop(ModBlocks.HIELOSTONE_BRICKS);

        addDrop(ModBlocks.TOWER_BRICKS);
        addDrop(ModBlocks.MOSSY_TOWER_BRICKS);

        addDrop(ModBlocks.STRANGE_CORE);
        addDrop(ModBlocks.GLOWING_OBSIDIAN);
        addDrop(ModBlocks.ONYX_BONE_BLOCK);
        addDrop(ModBlocks.QUICKSAND);
        addDrop(ModBlocks.RED_QUICKSAND);

        addDrop(ModBlocks.NACRE_BRICKS);
        addDrop(ModBlocks.NACRE_BRICK_STAIRS);
        addDrop(ModBlocks.NACRE_BRICK_SLAB);
        addDrop(ModBlocks.NACRE_BRICK_WALL);

        addDrop(ModBlocks.TURTLE_SCUTE_BRICKS);
        addDrop(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS);
        addDrop(ModBlocks.TURTLE_SCUTE_BRICK_SLAB);
        addDrop(ModBlocks.TURTLE_SCUTE_BRICK_WALL);

        addDrop(ModBlocks.CRAGULSTANE);
        addDrop(ModBlocks.CRAGULSTANE_BRICKS);
        addDrop(ModBlocks.CRAGULSTANE_BRICK_STAIRS);
        addDrop(ModBlocks.CRAGULSTANE_BRICK_SLAB);
        addDrop(ModBlocks.CRAGULSTANE_BRICK_WALL);
        addDrop(ModBlocks.CHISELED_CRAGULSTANE_BRICKS);

        addDrop(ModBlocks.BLUE_NETHER_BRICKS);
        addDrop(ModBlocks.CRACKED_BLUE_NETHER_BRICKS);
        addDrop(ModBlocks.CHISELED_BLUE_NETHER_BRICKS);
        addDrop(ModBlocks.BLUE_NETHER_BRICK_SLAB);
        addDrop(ModBlocks.BLUE_NETHER_BRICK_STAIRS);
        addDrop(ModBlocks.BLUE_NETHER_BRICK_WALL);
        addDrop(ModBlocks.BLUE_NETHER_BRICK_FENCE);
        addDrop(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE);

        addDrop(ModBlocks.PURPLE_NETHER_BRICKS);
        addDrop(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS);
        addDrop(ModBlocks.CHISELED_PURPLE_NETHER_BRICKS);
        addDrop(ModBlocks.PURPLE_NETHER_BRICK_SLAB);
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
        addDrop(ModBlocks.ECHO_LUMEN);
        addDrop(BFBlock.FELDSPAR_LUMEN);

        addDrop(ModBlocks.PALE_PRISMARINE);
        addDrop(ModBlocks.PALE_PRISMARINE_STAIRS);
        addDrop(ModBlocks.PALE_PRISMARINE_SLAB);
        addDrop(ModBlocks.PALE_PRISMARINE_WALL);
        addDrop(ModBlocks.PALE_PRISMARINE_BRICKS);
        addDrop(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS);
        addDrop(ModBlocks.PALE_PRISMARINE_BRICK_SLAB);
        addDrop(ModBlocks.DEEP_PALE_PRISMARINE);
        addDrop(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS);
        addDrop(ModBlocks.DEEP_PALE_PRISMARINE_SLAB);

        addDrop(ModBlocks.SEA_GLASS);
        addDrop(ModBlocks.SEA_GLASS_PANE);
        addDrop(ModBlocks.PALE_SEA_GLASS);
        addDrop(ModBlocks.PALE_SEA_GLASS_PANE);

        addDrop(ModBlocks.CREEPER_MODEL);
        addDrop(ModBlocks.SKELETON_MODEL);
        addDrop(ModBlocks.STRAY_MODEL);
        addDrop(ModBlocks.BOGGED_MODEL);
        addDrop(ModBlocks.BLAZE_MODEL);
        addDrop(ModBlocks.WITHER_SKELETON_MODEL);
        addDrop(ModBlocks.ENDERMAN_MODEL);
        addDrop(ModBlocks.SLIME_MODEL);
        addDrop(ModBlocks.MAGMA_CUBE_MODEL);
    }
}
