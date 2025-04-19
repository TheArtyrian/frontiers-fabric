package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModAdvancementFrame;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider
{
    private static final Item[] MODELS_LIST = new Item[] {
            ModBlocks.CREEPER_MODEL.asItem(),
            ModBlocks.SKELETON_MODEL.asItem(),
            ModBlocks.STRAY_MODEL.asItem(),
            ModBlocks.BOGGED_MODEL.asItem(),
            ModBlocks.WITHER_SKELETON_MODEL.asItem(),
            ModBlocks.ENDERMAN_MODEL.asItem(),
            ModBlocks.BLAZE_MODEL.asItem(),
            ModBlocks.SLIME_MODEL.asItem(),
            ModBlocks.MAGMA_CUBE_MODEL.asItem()
    };

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

        AdvancementEntry frontiers_get_one_model = generateAllModels(Advancement.Builder.create())
                .display(
                        ModBlocks.CREEPER_MODEL,
                        Text.translatable("advancements.frontiers.get_a_model.title"),
                        Text.translatable("advancements.frontiers.get_a_model.description"),
                        BG,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        true
                )
                .parent(frontiers_root)
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR)
                .build(consumer, Frontiers.MOD_ID + ":frontiers/get_a_model"
                );

        AdvancementEntry frontiers_get_all_models = generateAllModels(Advancement.Builder.create())
                .display(
                        ModBlocks.ENDERMAN_MODEL,
                        Text.translatable("advancements.frontiers.get_all_models.title"),
                        Text.translatable("advancements.frontiers.get_all_models.description"),
                        BG,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(frontiers_get_one_model)
                .build(consumer, Frontiers.MOD_ID + ":frontiers/get_all_models"
                );

        AdvancementEntry frontiers_vivulite_armor_full = Advancement.Builder.create()
                .display(
                        ModItem.VIVULITE_CHESTPLATE,
                        Text.translatable("advancements.frontiers.full_vivulite_armor.title"),
                        Text.translatable("advancements.frontiers.full_vivulite_armor.description"),
                        BG,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(frontiers_smelt_cobalt)
                .criterion("viv_helmet", InventoryChangedCriterion.Conditions.items(ModItem.VIVULITE_HELMET))
                .criterion("viv_chest", InventoryChangedCriterion.Conditions.items(ModItem.VIVULITE_CHESTPLATE))
                .criterion("viv_leggings", InventoryChangedCriterion.Conditions.items(ModItem.VIVULITE_LEGGINGS))
                .criterion("viv_boots", InventoryChangedCriterion.Conditions.items(ModItem.VIVULITE_BOOTS))
                .build(consumer, Frontiers.MOD_ID + ":frontiers/full_vivulite_armor"
                );

        AdvancementEntry frontiers_obtain_vivulite_anvil = Advancement.Builder.create()
                .display(
                        ModBlocks.VIVULITE_ANVIL,
                        Text.translatable("advancements.frontiers.obtain_vivulite_anvil.title"),
                        Text.translatable("advancements.frontiers.obtain_vivulite_anvil.description"),
                        BG,
                        ModAdvancementFrame.FRONTIERS_ADV,
                        true,
                        true,
                        true
                )
                .parent(frontiers_vivulite_armor_full)
                .criterion("obtain_anvil", InventoryChangedCriterion.Conditions.items(ModBlocks.VIVULITE_ANVIL))
                .build(consumer, Frontiers.MOD_ID + ":frontiers/obtain_vivulite_anvil"
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

    private void modAdvAdventure(Consumer<AdvancementEntry> consumer)
    {
        AdvancementEntry we_ball = Advancement.Builder.create()
                .display(
                        ModItem.BALL,
                        Text.translatable("advancements.adventure.hit_ball_twenty.title"),
                        Text.translatable("advancements.adventure.hit_ball_twenty.description"),
                        Identifier.ofVanilla("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        true
                )
                .criterion("hit", PlayerHurtEntityCriterion.Conditions.create(
                        DamagePredicate.Builder.create().sourceEntity(EntityPredicate.Builder.create().type(ModEntity.BALL).build())
                ))
                .parent(Identifier.ofVanilla("adventure/root"))
                .build(consumer, "minecraft"+ ":adventure/hit_ball_twenty"
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
        modAdvAdventure(consumer);
        modAdvNether(consumer);
        vanillaAdvHusbandry(consumer);
    }

    private Advancement.Builder generateAllModels(Advancement.Builder builder)
    {
        for (Item item : MODELS_LIST) {
            builder.criterion(Registries.ITEM.getId(item).getPath(), InventoryChangedCriterion.Conditions.items(item));
        }

        return builder;
    }
}
