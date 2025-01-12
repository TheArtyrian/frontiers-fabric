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
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;
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

        AdvancementEntry frontiers_smelt_frostite = Advancement.Builder.create()
                .display(
                        ModItem.FROSTITE_INGOT,
                        Text.translatable("advancements.frontiers.smelt_frostite.title"),
                        Text.translatable("advancements.frontiers.smelt_frostite.description"),
                        BG,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        true
                )
                .parent(frontiers_smelt_cobalt)
                .criterion("got_frostite", InventoryChangedCriterion.Conditions.items(ModItem.FROSTITE_INGOT))
                .build(consumer, Frontiers.MOD_ID + ":frontiers/smelt_frostite"
                );

        AdvancementEntry frontiers_eat_hpapple = Advancement.Builder.create()
                .display(
                        ModItem.APPLE_OF_ENLIGHTENMENT,
                        Text.translatable("advancements.frontiers.eat_hpapple.title"),
                        Text.translatable("advancements.frontiers.eat_hpapple.description"),
                        BG,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        true
                )
                .parent(frontiers_root)
                .criterion("ate_apple", ConsumeItemCriterion.Conditions.item(ModItem.APPLE_OF_ENLIGHTENMENT))
                .build(consumer, Frontiers.MOD_ID + ":frontiers/eat_hpapple"
                );
    }

    // Mod advancements - Husbandry.
    private void modAdvHusbandry(Consumer<AdvancementEntry> consumer)
    {
        AdvancementEntry husbandry_violet_rose = Advancement.Builder.create()
                .display(
                        ModBlocks.VIOLET_ROSE,
                        Text.translatable("advancements.husbandry.get_violet_rose.title"),
                        Text.translatable("advancements.husbandry.get_violet_rose.description"),
                        Identifier.ofVanilla("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        true
                )
                .parent(Identifier.ofVanilla("husbandry/plant_any_sniffer_seed"))
                .criterion("got_violet_rose", InventoryChangedCriterion.Conditions.items(ModBlocks.VIOLET_ROSE))
                .build(consumer, "minecraft"+ ":husbandry/get_violet_rose"
                );

        AdvancementEntry find_truffle = Advancement.Builder.create()
                .display(
                        Blocks.MYCELIUM,
                        Text.translatable("advancements.husbandry.find_truffle.title"),
                        Text.translatable("advancements.husbandry.find_truffle.description"),
                        Identifier.ofVanilla("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        true
                )
                .parent(Identifier.ofVanilla("husbandry/obtain_sniffer_egg"))
                .criterion("got_truffle", InventoryChangedCriterion.Conditions.items(ModItem.TRUFFLE))
                .build(consumer, "minecraft"+ ":husbandry/find_truffle"
                );

        AdvancementEntry feed_truffle_to_hoglin = Advancement.Builder.create()
                .display(
                        ModItem.TRUFFLE,
                        Text.translatable("advancements.husbandry.feed_truffle_to_hoglin.title"),
                        Text.translatable("advancements.husbandry.feed_truffle_to_hoglin.description"),
                        Identifier.ofVanilla("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(find_truffle)
                .criterion(
                        "feed_the_loser",
                        PlayerInteractedWithEntityCriterion.Conditions.create(
                                ItemPredicate.Builder.create().items(ModItem.TRUFFLE),
                                Optional.of(
                                        EntityPredicate.contextPredicateFromEntityPredicate(
                                                EntityPredicate.Builder.create().type(EntityType.HOGLIN).flags(EntityFlagsPredicate.Builder.create())
                                        )
                                )
                        )
                )
                .build(consumer, "minecraft"+ ":husbandry/feed_truffle_to_hoglin"
                );
    }

    // Mod advancements - Nether.
    private void modAdvNether(Consumer<AdvancementEntry> consumer)
    {
        AdvancementEntry brew_lightning = Advancement.Builder.create()
                .parent(Identifier.ofVanilla("nether/brew_potion"))
                .display(
                        ModItem.LIGHTNING_IN_A_BOTTLE,
                        Text.translatable("advancements.nether.brew_lightning.title"),
                        Text.translatable("advancements.nether.brew_lightning.description"),
                        Identifier.ofVanilla("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        true
                )
                .criterion("potion", InventoryChangedCriterion.Conditions.items(ModItem.LIGHTNING_IN_A_BOTTLE))
                .build(consumer, "minecraft"+ ":nether/brew_lightning");
    }

    // Vanilla advancements - Husbandry.
    private void vanillaAdvHusbandry(Consumer<AdvancementEntry> consumer)
    {

    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer)
    {
        modAdvFrontiers(consumer);
        modAdvHusbandry(consumer);
        modAdvNether(consumer);
        vanillaAdvHusbandry(consumer);
    }
}
