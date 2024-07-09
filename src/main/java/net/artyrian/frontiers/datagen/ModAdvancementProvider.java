package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider
{
    // Super!
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(output, registryLookup);
    }

    // Mod advancements - Frontiers.
    private void modAdvFrontiers(Consumer<AdvancementEntry> consumer)
    {
        Identifier BG = Identifier.ofVanilla("textures/gui/advancements/backgrounds/stone.png");

        AdvancementEntry frontiers_root = Advancement.Builder.create()
                .display(
                        ModBlocks.COBALT_ORE,
                        Text.translatable("advancements.frontiers.root.title"),
                        Text.translatable("advancements.frontiers.root.description"),
                        BG,
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("got_cobalt", InventoryChangedCriterion.Conditions.items(ModItem.RAW_COBALT))
                .build(consumer, Frontiers.MOD_ID + ":frontiers/root"
                );

        AdvancementEntry frontiers_smelt_cobalt = Advancement.Builder.create()
                .display(
                        ModItem.COBALT_INGOT,
                        Text.translatable("advancements.frontiers.smelt_cobalt.title"),
                        Text.translatable("advancements.frontiers.smelt_cobalt.description"),
                        BG,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .parent(frontiers_root)
                .criterion("got_cobalt", InventoryChangedCriterion.Conditions.items(ModItem.COBALT_INGOT))
                .build(consumer, Frontiers.MOD_ID + ":frontiers/smelt_cobalt"
                );
    }

    // Vanilla advancements - Husbandry.
    private void vanillaAdvHusbandry(Consumer<AdvancementEntry> consumer)
    {

    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer)
    {
        modAdvFrontiers(consumer);
        vanillaAdvHusbandry(consumer);
    }
}
