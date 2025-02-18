package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.artyrian.frontiers.util.LootTableHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryWrapper;

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

        // All blocks that drop self
        addDrop(ModBlocks.STRANGE_CORE);
        addDrop(ModBlocks.GLOWING_OBSIDIAN);
        addDrop(ModBlocks.NACRE_BRICKS);
    }
}
