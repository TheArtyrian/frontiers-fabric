package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.advancement.criterion.BeaconBrimtanCriterion;
import net.artyrian.frontiers.definition.advancement.criterion.CurseAltarCriterion;
import net.artyrian.frontiers.definition.advancement.criterion.EnrageTowerSpawnerCriterion;
import net.artyrian.frontiers.definition.advancement.criterion.EntityKilledNearbyCriterion;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModStructure;
import net.artyrian.frontiers.reg.misc.ModAdvancementFrame;
import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.artyrian.frontiers.reg.misc.ModDimension;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.DamagePredicate;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.FishingRodHookedTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerHurtEntityTrigger;
import net.minecraft.advancements.critereon.PlayerInteractTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.advancements.critereon.SummonedEntityTrigger;
import net.minecraft.advancements.critereon.TagPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider
{
    HolderLookup.Provider registryLookup;

    private static final Item[] MODELS_LIST = new Item[] {
            ModBlocks.CREEPER_MODEL.get().asItem(),
            ModBlocks.SKELETON_MODEL.get().asItem(),
            ModBlocks.STRAY_MODEL.get().asItem(),
            ModBlocks.BOGGED_MODEL.get().asItem(),
            ModBlocks.WITHER_SKELETON_MODEL.get().asItem(),
            ModBlocks.ENDERMAN_MODEL.get().asItem(),
            ModBlocks.BLAZE_MODEL.get().asItem(),
            ModBlocks.SLIME_MODEL.get().asItem(),
            ModBlocks.MAGMA_CUBE_MODEL.get().asItem()
    };

    // Super!
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(output, registryLookup);
        this.registryLookup = registryLookup.resultNow();
    }

    // Mod advancements - Frontiers.
    private void modAdvFrontiers(Consumer<AdvancementHolder> consumer)
    {
        ResourceLocation BG = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/gui/advancements/backgrounds/hielostone.png");

        AdvancementHolder frontiers_root = Advancement.Builder.advancement()
                .display(
                        ModBlocks.COBALT_ORE.get(),
                        Component.translatable("advancements.frontiers.root.title"),
                        Component.translatable("advancements.frontiers.root.description"),
                        BG,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion(
                        "killed_wither",
                        KilledTrigger.TriggerInstance.playerKilledEntity(
                                EntityPredicate.Builder.entity().of(EntityType.WITHER)
                        )
                )
                .save(consumer, Frontiers.MOD_ID + ":frontiers/root"
                );

        AdvancementHolder frontiers_smelt_cobalt = Advancement.Builder.advancement()
                .display(
                        ModItem.COBALT_INGOT.get(),
                        Component.translatable("advancements.frontiers.smelt_cobalt.title"),
                        Component.translatable("advancements.frontiers.smelt_cobalt.description"),
                        BG,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .parent(frontiers_root)
                .addCriterion("got_cobalt", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.COBALT_INGOT.get()))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/smelt_cobalt"
                );

        AdvancementHolder frontiers_smelt_frostite = Advancement.Builder.advancement()
                .display(
                        ModItem.FROSTITE_INGOT.get(),
                        Component.translatable("advancements.frontiers.smelt_frostite.title"),
                        Component.translatable("advancements.frontiers.smelt_frostite.description"),
                        BG,
                        AdvancementType.GOAL,
                        true,
                        true,
                        true
                )
                .parent(frontiers_smelt_cobalt)
                .addCriterion("got_frostite", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.FROSTITE_INGOT.get()))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/smelt_frostite"
                );

        AdvancementHolder frontiers_eat_hpapple = Advancement.Builder.advancement()
                .display(
                        ModItem.APPLE_OF_ENLIGHTENMENT.get(),
                        Component.translatable("advancements.frontiers.eat_hpapple.title"),
                        Component.translatable("advancements.frontiers.eat_hpapple.description"),
                        BG,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .parent(frontiers_root)
                .addCriterion("ate_apple", ConsumeItemTrigger.TriggerInstance.usedItem(ModItem.APPLE_OF_ENLIGHTENMENT.get()))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/eat_hpapple"
                );

        AdvancementHolder frontiers_get_one_model = generateAllModels(Advancement.Builder.advancement())
                .display(
                        ModBlocks.CREEPER_MODEL.get(),
                        Component.translatable("advancements.frontiers.get_a_model.title"),
                        Component.translatable("advancements.frontiers.get_a_model.description"),
                        BG,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .parent(frontiers_root)
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, Frontiers.MOD_ID + ":frontiers/get_a_model"
                );

        AdvancementHolder frontiers_get_all_models = generateAllModels(Advancement.Builder.advancement())
                .display(
                        ModBlocks.ENDERMAN_MODEL.get(),
                        Component.translatable("advancements.frontiers.get_all_models.title"),
                        Component.translatable("advancements.frontiers.get_all_models.description"),
                        BG,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(frontiers_get_one_model)
                .save(consumer, Frontiers.MOD_ID + ":frontiers/get_all_models"
                );

        AdvancementHolder frontiers_vivulite_armor_full = Advancement.Builder.advancement()
                .display(
                        ModItem.VIVULITE_CHESTPLATE.get(),
                        Component.translatable("advancements.frontiers.full_vivulite_armor.title"),
                        Component.translatable("advancements.frontiers.full_vivulite_armor.description"),
                        BG,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(frontiers_smelt_cobalt)
                .addCriterion("viv_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.VIVULITE_HELMET.get()))
                .addCriterion("viv_chest", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.VIVULITE_CHESTPLATE.get()))
                .addCriterion("viv_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.VIVULITE_LEGGINGS.get()))
                .addCriterion("viv_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.VIVULITE_BOOTS.get()))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/full_vivulite_armor"
                );

        AdvancementHolder frontiers_obtain_vivulite_anvil = Advancement.Builder.advancement()
                .display(
                        ModBlocks.VIVULITE_ANVIL.get(),
                        Component.translatable("advancements.frontiers.obtain_vivulite_anvil.title"),
                        Component.translatable("advancements.frontiers.obtain_vivulite_anvil.description"),
                        BG,
                        ModAdvancementFrame.FRONTIERS_ADV,
                        true,
                        true,
                        true
                )
                .parent(frontiers_vivulite_armor_full)
                .addCriterion("obtain_anvil", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.VIVULITE_ANVIL.get()))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/obtain_vivulite_anvil"
                );

        AdvancementHolder frontiers_break_curse = Advancement.Builder.advancement()
                .display(
                        ModItem.CURSED_TABLET.get(),
                        Component.translatable("advancements.frontiers.break_curse.title"),
                        Component.translatable("advancements.frontiers.break_curse.description"),
                        BG,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .parent(frontiers_root)
                .addCriterion("break_curse", CurseAltarCriterion.Conditions.any())
                .save(consumer, Frontiers.MOD_ID + ":frontiers/break_curse"
                );

        AdvancementHolder frontiers_brimtan_armor_full = Advancement.Builder.advancement()
                .display(
                        ModItem.BRIMTAN_CHESTPLATE.get(),
                        Component.translatable("advancements.frontiers.full_brimtan_armor.title"),
                        Component.translatable("advancements.frontiers.full_brimtan_armor.description"),
                        BG,
                        ModAdvancementFrame.FRONTIERS_ADV,
                        true,
                        true,
                        true
                )
                .parent(frontiers_vivulite_armor_full)
                .addCriterion("brim_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.BRIMTAN_HELMET.get()))
                .addCriterion("brim_chest", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.BRIMTAN_CHESTPLATE.get()))
                .addCriterion("brim_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.BRIMTAN_LEGGINGS.get()))
                .addCriterion("brim_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.BRIMTAN_BOOTS.get()))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/full_brimtan_armor"
                );

        AdvancementHolder frontiers_glowing_obsidian = Advancement.Builder.advancement()
                .parent(frontiers_smelt_cobalt)
                .display(
                        ModBlocks.GLOWING_OBSIDIAN.get(),
                        Component.translatable("advancements.frontiers.obtain_glowing_obsidian.title"),
                        Component.translatable("advancements.frontiers.obtain_glowing_obsidian.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("glowing_obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GLOWING_OBSIDIAN.get()))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/obtain_glowing_obsidian");

        AdvancementHolder frontiers_enter_crags = Advancement.Builder.advancement()
                .parent(frontiers_glowing_obsidian)
                .display(
                        Items.LAVA_BUCKET,
                        Component.translatable("advancements.frontiers.enter_crags.title"),
                        Component.translatable("advancements.frontiers.enter_crags.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("enter_crags", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimension.CRAGS_LEVEL_KEY))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/enter_crags");

        AdvancementHolder frontiers_purify_crystal = Advancement.Builder.advancement()
                .parent(frontiers_break_curse)
                .display(
                        ModItem.PURIFIED_END_CRYSTAL.get(),
                        Component.translatable("advancements.frontiers.purify_crystal.title"),
                        Component.translatable("advancements.frontiers.purify_crystal.description"),
                        BG,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .addCriterion("uncurse_crystal", CurseAltarCriterion.Conditions.of(Items.END_CRYSTAL))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/purify_crystal");

        AdvancementHolder frontiers_enter_tower = Advancement.Builder.advancement()
                .display(
                        ModBlocks.TOWER_BRICKS.get(),
                        Component.translatable("advancements.frontiers.enter_tower.title"),
                        Component.translatable("advancements.frontiers.enter_tower.description"),
                        BG,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .parent(frontiers_root)
                .addCriterion("entered_tower", PlayerTrigger.TriggerInstance.located(
                        LocationPredicate.Builder.inStructure(registryLookup
                                        .lookupOrThrow(Registries.STRUCTURE)
                                        .getOrThrow(ModStructure.WHITE_TOWER))
                ))
                .save(consumer, Frontiers.MOD_ID + ":frontiers/enter_tower");

        AdvancementHolder frontiers_enrage_spawner = Advancement.Builder.advancement()
                .display(
                        ModBlocks.TOWER_SPAWNER.get(),
                        Component.translatable("advancements.frontiers.enrage_tower_spawner.title"),
                        Component.translatable("advancements.frontiers.enrage_tower_spawner.description"),
                        BG,
                        AdvancementType.GOAL,
                        true,
                        true,
                        true
                )
                .parent(frontiers_enter_tower)
                .addCriterion("enrage_spawner", EnrageTowerSpawnerCriterion.Conditions.any())
                .save(consumer, Frontiers.MOD_ID + ":frontiers/enrage_tower_spawner");
    }

    // Mod advancements - Husbandry.
    private void modAdvHusbandry(Consumer<AdvancementHolder> consumer)
    {
        AdvancementHolder husbandry_violet_rose = Advancement.Builder.advancement()
                .display(
                        ModBlocks.VIOLET_ROSE.get(),
                        Component.translatable("advancements.husbandry.get_violet_rose.title"),
                        Component.translatable("advancements.husbandry.get_violet_rose.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        true
                )
                .parent(vanillaDummy("husbandry/plant_any_sniffer_seed"))
                .addCriterion("got_violet_rose", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.VIOLET_ROSE.get()))
                .save(consumer, "minecraft"+ ":husbandry/get_violet_rose"
                );

        AdvancementHolder find_truffle = Advancement.Builder.advancement()
                .display(
                        Blocks.MYCELIUM,
                        Component.translatable("advancements.husbandry.find_truffle.title"),
                        Component.translatable("advancements.husbandry.find_truffle.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        true
                )
                .parent(vanillaDummy("husbandry/obtain_sniffer_egg"))
                .addCriterion("got_truffle", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.TRUFFLE.get()))
                .save(consumer, "minecraft"+ ":husbandry/find_truffle"
                );

        AdvancementHolder feed_truffle_to_hoglin = Advancement.Builder.advancement()
                .display(
                        ModItem.TRUFFLE.get(),
                        Component.translatable("advancements.husbandry.feed_truffle_to_hoglin.title"),
                        Component.translatable("advancements.husbandry.feed_truffle_to_hoglin.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(find_truffle)
                .addCriterion(
                        "feed_the_loser",
                        PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(
                                ItemPredicate.Builder.item().of(ModItem.TRUFFLE.get()),
                                Optional.of(
                                        EntityPredicate.wrap(
                                                EntityPredicate.Builder.entity().of(EntityType.HOGLIN).flags(EntityFlagsPredicate.Builder.flags())
                                        )
                                )
                        )
                )
                .save(consumer, "minecraft"+ ":husbandry/feed_truffle_to_hoglin"
                );

        AdvancementHolder catch_bottled_message = Advancement.Builder.advancement()
                .display(
                        ModItem.BOTTLED_MESSAGE.get(),
                        Component.translatable("advancements.husbandry.catch_bottled_message.title"),
                        Component.translatable("advancements.husbandry.catch_bottled_message.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .parent(vanillaDummy("husbandry/fishy_business"))
                .addCriterion(
                        "yes_this_is_a_the_police_reference",
                        FishingRodHookedTrigger.TriggerInstance.fishedItem(Optional.empty(), Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(ModItem.BOTTLED_MESSAGE.get()).build()))
                )
                .save(consumer, "minecraft"+ ":husbandry/catch_bottled_message"
                );

        AdvancementHolder summon_pumpkin_golem = Advancement.Builder.advancement()
                .parent(vanillaDummy("husbandry/plant_seed"))
                .display(
                        ModItem.SPIRIT_CANDLE.get(),
                        Component.translatable("advancements.husbandry.summon_pumpkin_golem.title"),
                        Component.translatable("advancements.husbandry.summon_pumpkin_golem.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("summon_pumpkin_golem", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(ModEntity.PUMPKIN_GOLEM.get())))
                .save(consumer, "adventure/summon_iron_golem");

        AdvancementHolder throw_fruitcake = Advancement.Builder.advancement()
                .parent(vanillaDummy("husbandry/plant_seed"))
                .display(
                        ModItem.FRUITCAKE_SLICE.get(),
                        Component.translatable("advancements.adventure.throw_fruitcake.title"),
                        Component.translatable("advancements.adventure.throw_fruitcake.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        true
                )
                .addCriterion(
                        "throw_fruitcake",
                        KilledTrigger.TriggerInstance.playerKilledEntity(
                                EntityPredicate.Builder.entity(),
                                DamageSourcePredicate.Builder.damageType()
                                        .tag(TagPredicate.is(DamageTypeTags.IS_PROJECTILE))
                                        .direct(EntityPredicate.Builder.entity().of(ModEntity.FRUITCAKE.get()))
                        )
                )
                .save(consumer, "husbandry/throw_fruitcake");
    }

    private void modAdvAdventure(Consumer<AdvancementHolder> consumer)
    {
        AdvancementHolder we_ball = Advancement.Builder.advancement()
                .display(
                        ModItem.BALL.get(),
                        Component.translatable("advancements.adventure.hit_ball_twenty.title"),
                        Component.translatable("advancements.adventure.hit_ball_twenty.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        true
                )
                .addCriterion("hit", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntityWithDamage(
                        DamagePredicate.Builder.damageInstance().sourceEntity(EntityPredicate.Builder.entity().of(ModEntity.BALL.get()).build())
                ))
                .parent(vanillaDummy("adventure/root"))
                .save(consumer, "minecraft"+ ":adventure/hit_ball_twenty"
                );

        AdvancementHolder sleep_in_phantom_bed = Advancement.Builder.advancement()
                .display(
                        ModItem.PHANTOM_STITCH_BED.get(),
                        Component.translatable("advancements.adventure.sleep_in_phantom_bed.title"),
                        Component.translatable("advancements.adventure.sleep_in_phantom_bed.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        true
                )
                .addCriterion("sleep_in_phantom_bed", createSleptInPhantomBed())
                .parent(vanillaDummy("adventure/sleep_in_bed"))
                .save(consumer, "minecraft"+ ":adventure/sleep_in_phantom_bed"
                );
    }

    // Mod advancements - Nether.
    private void modAdvNether(Consumer<AdvancementHolder> consumer)
    {
        AdvancementHolder brew_lightning = Advancement.Builder.advancement()
                .parent(vanillaDummy("nether/brew_potion"))
                .display(
                        ModItem.LIGHTNING_IN_A_BOTTLE.get(),
                        Component.translatable("advancements.nether.brew_lightning.title"),
                        Component.translatable("advancements.nether.brew_lightning.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("potion", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.LIGHTNING_IN_A_BOTTLE.get()))
                .save(consumer, "minecraft"+ ":nether/brew_lightning");

        AdvancementHolder brimtan_hoe = Advancement.Builder.advancement()
                .parent(vanillaDummy("husbandry/obtain_netherite_hoe"))
                .display(
                        ModItem.BRIMTAN_HOE.get(),
                        Component.translatable("advancements.husbandry.obtain_brimtan_hoe.title"),
                        Component.translatable("advancements.husbandry.obtain_brimtan_hoe.description"),
                        null,
                        ModAdvancementFrame.FRONTIERS_ADV,
                        true,
                        true,
                        true
                )
                .rewards(AdvancementRewards.Builder.experience(100))
                .addCriterion("hoe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItem.BRIMTAN_HOE.get()))
                .save(consumer, "minecraft"+ ":husbandry/obtain_brimtan_hoe");

        AdvancementHolder kill_wither = Advancement.Builder.advancement()
                .parent(vanillaDummy("nether/summon_wither"))
                .display(
                        ModItem.WITHERED_ESSENCE.get(),
                        Component.translatable("advancements.nether.kill_wither.title"),
                        Component.translatable("advancements.nether.kill_wither.description"),
                        null,
                        ModAdvancementFrame.FRONTIERS_ADV,
                        true,
                        true,
                        true
                )
                .rewards(AdvancementRewards.Builder.experience(50))
                .addCriterion(
                        "killed_wither",
                        EntityKilledNearbyCriterion.Conditions.of(EntityPredicate.Builder.entity().of(EntityType.WITHER).build())

                )
                .save(consumer, "minecraft"+ ":nether/kill_wither");

        AdvancementHolder brimtan_beacon = Advancement.Builder.advancement()
                .parent(vanillaDummy("nether/create_full_beacon"))
                .display(
                        ModBlocks.BRIMTAN_BLOCK.get(),
                        Component.translatable("advancements.nether.brimtan_beacon.title"),
                        Component.translatable("advancements.nether.brimtan_beacon.description"),
                        null,
                        ModAdvancementFrame.FRONTIERS_ADV,
                        true,
                        true,
                        true
                )
                .rewards(AdvancementRewards.Builder.experience(100))
                .addCriterion("brimtan_beacon", BeaconBrimtanCriterion.Conditions.any())
                .save(consumer, "minecraft"+ ":nether/brimtan_beacon");

        AdvancementHolder use_enchanting_magnet = Advancement.Builder.advancement()
                .parent(vanillaDummy("nether/summon_wither"))
                .addCriterion(
                        "use_enchanting_magnet",
                        ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                                LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(ModBlocks.ENCHANTING_MAGNET.get())),
                                ItemPredicate.Builder.item().of(Items.GLASS_BOTTLE)
                        )
                )
                .display(
                        ModBlocks.ENCHANTING_MAGNET.get(),
                        Component.translatable("advancements.nether.use_enchanting_magnet.title"),
                        Component.translatable("advancements.nether.use_enchanting_magnet.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .save(consumer, "minecraft" + ":nether/use_enchanting_magnet");
    }

    // Vanilla advancements - Husbandry.
    private void vanillaAdvHusbandry(Consumer<AdvancementHolder> consumer)
    {

    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer)
    {
        modAdvFrontiers(consumer);
        modAdvHusbandry(consumer);
        modAdvAdventure(consumer);
        modAdvNether(consumer);
        vanillaAdvHusbandry(consumer);
    }

    public static Criterion<PlayerTrigger.TriggerInstance> createSleptInPhantomBed()
    {
        return ModCriteria.SLEPT_ON_PHANTOM_BED.get()
                .createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty()));
    }

    private static AdvancementHolder vanillaDummy(String id)
    {
        return new Advancement.Builder().build(ResourceLocation.withDefaultNamespace(id));
    }

    private Advancement.Builder generateAllModels(Advancement.Builder builder)
    {
        for (Item item : MODELS_LIST) {
            builder.addCriterion(BuiltInRegistries.ITEM.getKey(item).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(item));
        }

        return builder;
    }
}
