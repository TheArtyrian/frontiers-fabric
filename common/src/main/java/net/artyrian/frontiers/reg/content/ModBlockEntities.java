package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.*;
import net.artyrian.frontiers.definition.block.entity.model.*;
import net.minecraft.world.level.block.Block;
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
    // Models
    public static final Supplier<BlockEntityType<CreeperModelBlockEntity>> CREEPER_MODEL_BLOCKENTITY = registerBlockEntity("creeper_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(CreeperModelBlockEntity::new, ModBlocks.CREEPER_MODEL));
    public static final Supplier<BlockEntityType<SkeletonModelBlockEntity>> SKELETON_MODEL_BLOCKENTITY = registerBlockEntity("skeleton_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(SkeletonModelBlockEntity::new, ModBlocks.SKELETON_MODEL));
    public static final Supplier<BlockEntityType<StrayModelBlockEntity>> STRAY_MODEL_BLOCKENTITY = registerBlockEntity("stray_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(StrayModelBlockEntity::new, ModBlocks.STRAY_MODEL));
    public static final Supplier<BlockEntityType<BoggedModelBlockEntity>> BOGGED_MODEL_BLOCKENTITY = registerBlockEntity("bogged_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(BoggedModelBlockEntity::new, ModBlocks.BOGGED_MODEL));
    public static final Supplier<BlockEntityType<WitherSkeletonModelBlockEntity>> WITHER_SKELETON_MODEL_BLOCKENTITY = registerBlockEntity("wither_skeleton_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(WitherSkeletonModelBlockEntity::new, ModBlocks.WITHER_SKELETON_MODEL));
    public static final Supplier<BlockEntityType<EndermanModelBlockEntity>> ENDERMAN_MODEL_BLOCKENTITY = registerBlockEntity("enderman_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(EndermanModelBlockEntity::new, ModBlocks.ENDERMAN_MODEL));
    public static final Supplier<BlockEntityType<SlimeModelBlockEntity>> SLIME_MODEL_BLOCKENTITY = registerBlockEntity("slime_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(SlimeModelBlockEntity::new, ModBlocks.SLIME_MODEL));
    public static final Supplier<BlockEntityType<MagmaCubeModelBlockEntity>> MAGMA_CUBE_MODEL_BLOCKENTITY = registerBlockEntity("magma_cube_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(MagmaCubeModelBlockEntity::new, ModBlocks.MAGMA_CUBE_MODEL));
    // Blaze model
    public static final Supplier<BlockEntityType<BlazeModelBlockEntity>> BLAZE_MODEL_BLOCKENTITY = registerBlockEntity("blaze_model_blockentity", () ->
            VectorLib.REGISTRY.registerBlockEntity(BlazeModelBlockEntity::new, ModBlocks.BLAZE_MODEL));

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> type)
    {
        return VectorLib.REGISTRY.registerBlockEntityType(Frontiers.MOD_ID, id, type);
    }

    public static void registerBlockEntities()
    {
        //Frontiers.LOGGER.info("register block entity");
    }
}