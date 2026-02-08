package net.artyrian.frontiers.definition.loot;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.condition.HardmodeLootCondition;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;

public class FRLootMods
{
    private static final String lootmods_loc = "frontiers_lootmods/";

    public static final ResourceKey<LootTable> SNIFFER_DIGS = BuiltInLootTables.SNIFFER_DIGGING;
    public static final ResourceKey<LootTable> DESERT_PYRAMID_SUS = BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY;

    public static final ResourceKey<LootTable> RUINED_PORTAL = BuiltInLootTables.RUINED_PORTAL;
    public static final ResourceKey<LootTable> DESERT_CHEST = BuiltInLootTables.DESERT_PYRAMID;
    public static final ResourceKey<LootTable> PILLAGER_OUTPOST = BuiltInLootTables.PILLAGER_OUTPOST;
    public static final ResourceKey<LootTable> WOODLAND_MANSION = BuiltInLootTables.WOODLAND_MANSION;
    public static final ResourceKey<LootTable> BASTION_TREASURE_CHEST = BuiltInLootTables.BASTION_TREASURE;
    public static final ResourceKey<LootTable> BASTION_BRIDGE_CHEST = BuiltInLootTables.BASTION_BRIDGE;
    public static final ResourceKey<LootTable> BASTION_HOGLIN_STABLE_CHEST = BuiltInLootTables.BASTION_HOGLIN_STABLE;
    public static final ResourceKey<LootTable> BASTION_OTHER_CHEST = BuiltInLootTables.BASTION_OTHER;
    public static final ResourceKey<LootTable> END_CITY = BuiltInLootTables.END_CITY_TREASURE;
    public static final ResourceKey<LootTable> BURIED_TREASURE = BuiltInLootTables.BURIED_TREASURE;
    public static final ResourceKey<LootTable> DUNGEON = BuiltInLootTables.SIMPLE_DUNGEON;

    public static final ResourceKey<LootTable> OMINOUS_VAULT_RARE = BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE;

    public static final ResourceKey<LootTable> GHAST = EntityType.GHAST.getDefaultLootTable();
    public static final ResourceKey<LootTable> RAVAGER = EntityType.RAVAGER.getDefaultLootTable();
    public static final ResourceKey<LootTable> WITCH = EntityType.WITCH.getDefaultLootTable();
    public static final ResourceKey<LootTable> EVOKER = EntityType.EVOKER.getDefaultLootTable();
    public static final ResourceKey<LootTable> GUARDIAN = EntityType.GUARDIAN.getDefaultLootTable();
    public static final ResourceKey<LootTable> ELDER_GUARDIAN = EntityType.ELDER_GUARDIAN.getDefaultLootTable();
    public static final ResourceKey<LootTable> STRAY = EntityType.STRAY.getDefaultLootTable();

    public static final ResourceKey<LootTable> SPAWNER = Blocks.SPAWNER.getLootTable();

    public static LootTable getLootTable(HolderLookup.Provider registries, ResourceKey<LootTable> lootTableKey)
    {
        return registries.lookup(Registries.LOOT_TABLE)
                .flatMap((lookup) -> lookup.get(lootTableKey))
                .map(net.minecraft.core.Holder::value)
                .orElse(LootTable.EMPTY);
    }

    public static class Modify
    {
        private static final String loc = lootmods_loc + "modify/";

        public static ResourceKey<LootTable> SNIFFER = register("sniffer");
        public static ResourceKey<LootTable> RUINED_PORTAL = register("ruined_portal");
        public static ResourceKey<LootTable> MONSTER_ROOM = register("monster_room");
        public static ResourceKey<LootTable> BURIED_TREASURE = register("buried_treasure");
        public static ResourceKey<LootTable> END_CITY = register("end_city");
        public static ResourceKey<LootTable> OMINOUS_TRIAL = register("ominous_trial");
        public static ResourceKey<LootTable> DESERT_TEMPLE = register("desert_temple");
        public static ResourceKey<LootTable> DESERT_TEMPLE_SUS_SAND = register("desert_temple_sus_sand");
        public static ResourceKey<LootTable> PILLAGER_OUTPOST = register("pillager_outpost");
        public static ResourceKey<LootTable> WOODLAND_MANSION = register("woodland_mansion");

        public static ResourceKey<LootTable> BASTION_TREASURE = register("bastion_treasure");
        public static ResourceKey<LootTable> BASTION_STABLE = register("bastion_stable");
        public static ResourceKey<LootTable> BASTION_BRIDGE = register("bastion_bridge");
        public static ResourceKey<LootTable> BASTION_OTHER = register("bastion_other");

        public static ResourceKey<LootTable> RAVAGER = register("ravager");
        public static ResourceKey<LootTable> GHAST = register("ghast");
        public static ResourceKey<LootTable> WITCH = register("witch");
        public static ResourceKey<LootTable> SPAWNER = register("spawner");

        private static ResourceKey<LootTable> register(String id) { return ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + id)); }

        public static List<LootPoolSingletonContainer.Builder<?>> sniffer(HolderLookup.Provider wrapperLookup)
        {
            return List.of(
                    LootItem.lootTableItem(ModItem.ANCIENT_ROSE_SEED.get()),
                    LootItem.lootTableItem(ModItem.TRUFFLE.get())
                            .when(LocationCheck.checkLocation(
                                            LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(Blocks.MYCELIUM)),
                                            BlockPos.ZERO.relative(Direction.DOWN, 1)
                            )
                    )
            );
        }

        public static List<LootPoolSingletonContainer.Builder<?>> ruinedPortalTemplate(HolderLookup.Provider wrapperLookup)
        {
            return List.of(
                    LootItem.lootTableItem(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(2)
            );
        }

        public static LootPool.Builder monsterRoom(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(EmptyLootItem.emptyItem().setWeight(9))
                    .add(LootItem.lootTableItem(ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get()).setWeight(1));
        }

        public static LootPool.Builder buriedTreasure(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(EmptyLootItem.emptyItem().setWeight(3))
                    .add(LootItem.lootTableItem(ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get()).setWeight(1));
        }

        public static LootPool.Builder endCity(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(EmptyLootItem.emptyItem().setWeight(4))
                    .add(LootItem.lootTableItem(ModItem.END_CRYSTAL_SHARD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))));
        }

        public static List<LootPoolSingletonContainer.Builder<?>> ominousTrialSpawner(HolderLookup.Provider wrapperLookup)
        {
            return List.of(
                    LootItem.lootTableItem(ModItem.COBALT_HORSE_ARMOR.get())
                        .setWeight(2)
                        .when(HardmodeLootCondition.builder(true))
            );
        }

        public static LootPool.Builder desertTemple(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(EmptyLootItem.emptyItem().setWeight(1))
                    .add(LootItem.lootTableItem(ModItem.TABLET_FRAGMENT.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))));
        }

        public static List<LootPoolSingletonContainer.Builder<?>> desertSusSand(HolderLookup.Provider wrapperLookup)
        {
            return List.of(
                    LootItem.lootTableItem(ModItem.TABLET_FRAGMENT.get()),
                    LootItem.lootTableItem(ModItem.CURSED_TABLET.get())
            );
        }

        public static LootPool.Builder pillagerOutpost(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(UniformGenerator.between(1.0F, 2.0F))
                    .add(EmptyLootItem.emptyItem().setWeight(3))
                    .add(LootItem.lootTableItem(ModItem.INVOKE_SHARD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))));
        }

        public static LootPool.Builder woodlandMansion(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(UniformGenerator.between(2.0F, 3.0F))
                    .add(EmptyLootItem.emptyItem().setWeight(1))
                    .add(LootItem.lootTableItem(ModItem.INVOKE_SHARD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))));
        }

        public static LootPool.Builder bastionTreasure(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(ModItem.UNFINISHED_CORE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))));
        }

        public static LootPool.Builder bastionStable(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(EmptyLootItem.emptyItem().setWeight(9))
                    .add(LootItem.lootTableItem(ModItem.UNFINISHED_CORE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))));
        }

        public static LootPool.Builder bastionBridge(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(EmptyLootItem.emptyItem().setWeight(9))
                    .add(LootItem.lootTableItem(ModItem.UNFINISHED_CORE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))));
        }

        public static LootPool.Builder bastionOther(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(EmptyLootItem.emptyItem().setWeight(9))
                    .add(LootItem.lootTableItem(ModItem.UNFINISHED_CORE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))));
        }

        public static LootPool.Builder ravager(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(ModItem.RAVAGER_TOOTH.get())
                            .setWeight(1)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                    );
        }

        public static LootPool.Builder ghast(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(ModItem.ECTOPLASM.get())
                            .setWeight(1)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                    );
        }

        public static LootPool.Builder witch(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(ModItem.WITCH_HAT.get())
                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(wrapperLookup, 0.025F, 0.01F))
                    );
        }

        public static LootPool.Builder spawner(HolderLookup.Provider wrapperLookup)
        {
            return LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(ModItem.SPAWNER_CHUNK.get()));
        }
    }

    public static class Replace
    {
        private static final String loc = lootmods_loc + "replace/";

        public static ResourceKey<LootTable> EVOKER = register("evoker");
        public static ResourceKey<LootTable> GUARDIAN = register("guardian");
        public static ResourceKey<LootTable> ELDER_GUARDIAN = register("elder_guardian");
        public static ResourceKey<LootTable> STRAY = register("stray");

        private static ResourceKey<LootTable> register(String id) { return ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + id)); }

        public static LootTable.Builder evokerRebalance(HolderLookup.Provider wrapperLookup)
        {
            return LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(
                                            LootItem.lootTableItem(ModItem.INVOKE_SHARD.get())
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                    )
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(
                                            LootItem.lootTableItem(Items.EMERALD)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                    )
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                    );
        }

        public static LootTable.Builder guardian(HolderLookup.Provider wrapperLookup)
        {
            return LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(
                                            LootItem.lootTableItem(Items.PRISMARINE_SHARD)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                    )
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(
                                            LootItem.lootTableItem(ModItem.GUARDIAN_SLICE.get())
                                                    .setWeight(2)
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                                    .apply(SmeltItemFunction.smelted().when(MethodToolbox.onfireCheck(wrapperLookup)))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS)
                                                    .setWeight(2)
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                    )
                                    .add(EmptyLootItem.emptyItem())
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(NestedLootTable.lootTableReference(BuiltInLootTables.FISHING)
                                            .apply(SmeltItemFunction.smelted().when(MethodToolbox.onfireCheck(wrapperLookup))))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                    .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(wrapperLookup, 0.025F, 0.01F))
                    );
        }

        public static LootTable.Builder elderGuardian(HolderLookup.Provider wrapperLookup)
        {
            return LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(
                                            LootItem.lootTableItem(ModItem.PALE_PRISMARINE_SHARD.get())
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 2.0F)))
                                    )
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(
                                            LootItem.lootTableItem(ModItem.ELDER_GUARDIAN_SLICE.get())
                                                    .setWeight(3)
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(1.0F, 2.0F)))
                                                    .apply(SmeltItemFunction.smelted().when(MethodToolbox.onfireCheck(wrapperLookup)))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS)
                                                    .setWeight(2)
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                    )
                                    .add(EmptyLootItem.emptyItem())
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(Blocks.WET_SPONGE))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(ModItem.ELDER_GUARDIAN_SPINE.get()))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                    .when(HardmodeLootCondition.builder(true))
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(NestedLootTable.lootTableReference(BuiltInLootTables.FISHING)
                                            .apply(SmeltItemFunction.smelted().when(MethodToolbox.onfireCheck(wrapperLookup))))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                    .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(wrapperLookup, 0.025F, 0.01F))
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(EmptyLootItem.emptyItem().setWeight(4))
                                    .add(LootItem.lootTableItem(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1))
                    );
        }

        public static LootTable.Builder stray(HolderLookup.Provider wrapperLookup)
        {
            return LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(
                                            LootItem.lootTableItem(Items.ARROW)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                    )
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(
                                            LootItem.lootTableItem(ModItem.FROST_BONE.get())
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                    )
                    )
                    .withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(
                                            LootItem.lootTableItem(Items.TIPPED_ARROW)
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)).setLimit(1))
                                                    .apply(SetPotionFunction.setPotion(Potions.SLOWNESS))
                                                    .when(HardmodeLootCondition.builder(false))
                                    )
                                    .add(
                                            LootItem.lootTableItem(ModItem.SUBZERO_ARROW.get())
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)).setLimit(1))
                                                    .when(HardmodeLootCondition.builder(true))
                                    )
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                    );
        }
    }
}
