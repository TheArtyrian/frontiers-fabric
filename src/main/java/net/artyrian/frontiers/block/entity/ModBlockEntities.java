package net.artyrian.frontiers.block.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.entity.entity_model.*;
import net.artyrian.frontiers.block.entity.portal.CragsPortalBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EndPortalBlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities
{
    // Personal Chest
    public static final BlockEntityType<PersonalChestBlockEntity> PERSONAL_CHEST_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "personal_chest"),
                    BlockEntityType.Builder.create(PersonalChestBlockEntity::new, ModBlocks.PERSONAL_CHEST).build());
    // Crags Portal
    public static final BlockEntityType<CragsPortalBlockEntity> CRAGS_PORTAL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "crags_portal"),
                    BlockEntityType.Builder.create(CragsPortalBlockEntity::new, ModBlocks.CRAGS_PORTAL).build());
    // Curse Altar
    public static final BlockEntityType<CurseAltarBlockEntity> CURSE_ALTAR_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "curse_altar"),
                    BlockEntityType.Builder.create(CurseAltarBlockEntity::new, ModBlocks.CURSE_ALTAR).build());
    // Monster Bakery
    public static final BlockEntityType<MonsterBakeryBlockEntity> MONSTER_BAKERY_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "monster_bakery"),
                    BlockEntityType.Builder.create(MonsterBakeryBlockEntity::new, ModBlocks.MONSTER_BAKERY).build());
    // Phantom-Stitch Bed
    public static final BlockEntityType<PhantomBedBlockEntity> PHANTOM_BED_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "phantom_stitch_bed"),
                    BlockEntityType.Builder.create(PhantomBedBlockEntity::new, ModBlocks.PHANTOM_STITCH_BED).build());
    // Enchanting Magnet
    public static final BlockEntityType<EnchantingMagnetBlockEntity> ENCHANTING_MAGNET_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "enchanting_magnet"),
                    BlockEntityType.Builder.create(EnchantingMagnetBlockEntity::new, ModBlocks.ENCHANTING_MAGNET).build());
    // Item Vacuum
    public static final BlockEntityType<ItemVacuumBlockEntity> ITEM_VACUUM =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "item_vacuum"),
                    BlockEntityType.Builder.create(ItemVacuumBlockEntity::new, ModBlocks.ITEM_VACUUM).build());

    // Models
    public static final BlockEntityType<CreeperModelBlockEntity> CREEPER_MODEL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "creeper_model_blockentity"),
                    BlockEntityType.Builder.create(CreeperModelBlockEntity::new, ModBlocks.CREEPER_MODEL).build());

    public static final BlockEntityType<SkeletonModelBlockEntity> SKELETON_MODEL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "skeleton_model_blockentity"),
                    BlockEntityType.Builder.create(SkeletonModelBlockEntity::new, ModBlocks.SKELETON_MODEL).build());

    public static final BlockEntityType<StrayModelBlockEntity> STRAY_MODEL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "stray_model_blockentity"),
                    BlockEntityType.Builder.create(StrayModelBlockEntity::new, ModBlocks.STRAY_MODEL).build());

    public static final BlockEntityType<BoggedModelBlockEntity> BOGGED_MODEL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "bogged_model_blockentity"),
                    BlockEntityType.Builder.create(BoggedModelBlockEntity::new, ModBlocks.BOGGED_MODEL).build());

    public static final BlockEntityType<BlazeModelBlockEntity> BLAZE_MODEL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "blaze_model_blockentity"),
                    BlockEntityType.Builder.create(BlazeModelBlockEntity::new, ModBlocks.BLAZE_MODEL).build());

    public static final BlockEntityType<WitherSkeletonModelBlockEntity> WITHER_SKELETON_MODEL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "wither_skeleton_model_blockentity"),
                    BlockEntityType.Builder.create(WitherSkeletonModelBlockEntity::new, ModBlocks.WITHER_SKELETON_MODEL).build());

    public static final BlockEntityType<EndermanModelBlockEntity> ENDERMAN_MODEL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "enderman_model_blockentity"),
                    BlockEntityType.Builder.create(EndermanModelBlockEntity::new, ModBlocks.ENDERMAN_MODEL).build());

    public static final BlockEntityType<SlimeModelBlockEntity> SLIME_MODEL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "slime_model_blockentity"),
                    BlockEntityType.Builder.create(SlimeModelBlockEntity::new, ModBlocks.SLIME_MODEL).build());

    public static final BlockEntityType<MagmaCubeModelBlockEntity> MAGMA_CUBE_MODEL_BLOCKENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, "magma_cube_model_blockentity"),
                    BlockEntityType.Builder.create(MagmaCubeModelBlockEntity::new, ModBlocks.MAGMA_CUBE_MODEL).build());

    public static void registerBlockEntities()
    {
        //Frontiers.LOGGER.info("register block entity");
    }
}
