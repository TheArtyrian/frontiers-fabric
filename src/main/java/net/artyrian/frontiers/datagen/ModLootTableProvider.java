package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.AncientRoseCropBlock;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.BeetrootsBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    // Generate tables.
    @Override
    public void generate()
    {
        addDrop(ModBlocks.ANCIENT_ROSE);
        addDrop(ModBlocks.ANCIENT_ROSE_BUSH, block -> dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addPottedPlantDrops(ModBlocks.POTTED_ANCIENT_ROSE);
        addDrop(
                ModBlocks.ANCIENT_ROSE_CROP,
                applyExplosionDecay(ModBlocks.ANCIENT_ROSE_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ModItem.ANCIENT_ROSE_SEED))))
        );
    }
}
