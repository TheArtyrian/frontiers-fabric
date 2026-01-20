package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class ModBlockEntities
{
    // Personal Chest
    public static final Supplier<BlockEntityType<PersonalChestBlockEntity>> PERSONAL_CHEST_BLOCKENTITY = registerBlockEntity("personal_chest", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    PersonalChestBlockEntity::new,
                    ModBlocks.PERSONAL_CHEST
            )
    );
    // Crags Portal
    public static final Supplier<BlockEntityType<CragsPortalBlockEntity>> CRAGS_PORTAL_BLOCKENTITY = registerBlockEntity("crags_portal", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    CragsPortalBlockEntity::new,
                    ModBlocks.CRAGS_PORTAL
            )
    );
    // Curse Altar
    public static final Supplier<BlockEntityType<CurseAltarBlockEntity>> CURSE_ALTAR_BLOCKENTITY = registerBlockEntity("curse_altar", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    CurseAltarBlockEntity::new,
                    ModBlocks.CURSE_ALTAR
            )
    );
    // Monster Bakery
    public static final Supplier<BlockEntityType<MonsterBakeryBlockEntity>> MONSTER_BAKERY_BLOCKENTITY = registerBlockEntity("monster_bakery", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    MonsterBakeryBlockEntity::new,
                    ModBlocks.MONSTER_BAKERY
            )
    );
    // Phantom-Stitch Bed
    public static final Supplier<BlockEntityType<PhantomBedBlockEntity>> PHANTOM_BED_BLOCKENTITY = registerBlockEntity("phantom_stitch_bed", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    PhantomBedBlockEntity::new,
                    ModBlocks.PHANTOM_STITCH_BED
            )
    );
    // Enchanting Magnet
    public static final Supplier<BlockEntityType<EnchantingMagnetBlockEntity>> ENCHANTING_MAGNET_BLOCKENTITY = registerBlockEntity("enchanting_magnet", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    EnchantingMagnetBlockEntity::new,
                    ModBlocks.ENCHANTING_MAGNET
            )
    );
    // Item Vacuum
    public static final Supplier<BlockEntityType<ItemVacuumBlockEntity>> ITEM_VACUUM = registerBlockEntity("item_vacuum", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    ItemVacuumBlockEntity::new,
                    ModBlocks.ITEM_VACUUM
            )
    );
    // Tower Watcher
    public static final Supplier<BlockEntityType<TowerWatcherBlockEntity>> TOWER_WATCHER = registerBlockEntity("tower_watcher", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    TowerWatcherBlockEntity::new,
                    ModBlocks.TOWER_WATCHER
            )
    );
    // Model (default)
    public static final Supplier<BlockEntityType<EntityModelBlockEntity>> ENTITY_MODEL_BLOCKENTITY = registerBlockEntity("entity_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    EntityModelBlockEntity::new,
                    ModBlocks.CREEPER_MODEL,
                    ModBlocks.SKELETON_MODEL,
                    ModBlocks.STRAY_MODEL,
                    ModBlocks.BOGGED_MODEL,
                    ModBlocks.ENDERMAN_MODEL,
                    ModBlocks.SLIME_MODEL,
                    ModBlocks.MAGMA_CUBE_MODEL,
                    ModBlocks.WITHER_SKELETON_MODEL
            )
    );
    // Blaze model
    public static final Supplier<BlockEntityType<BlazeModelBlockEntity>> BLAZE_MODEL_BLOCKENTITY = registerBlockEntity("blaze_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(
                    BlazeModelBlockEntity::new,
                    ModBlocks.BLAZE_MODEL
            )
    );

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> type)
    {
        return VectorLib.REGISTRY.registerBlockEntityType(Frontiers.MOD_ID, id, type);
    }

    public static void registerBlockEntities()
    {
        //Frontiers.LOGGER.info("register block entity");
    }
}