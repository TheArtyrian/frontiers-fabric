package net.artyrian.frontiers.definition.world.structure.white_tower;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.TowerTreasureVaultBlock;
import net.artyrian.frontiers.definition.block.entity.TowerSpawnerBlockEntity;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.world.FRStructurePieceTypes;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.Map;

public class WhiteTowerGenerator
{
    private static final Map<Block, Block> MOSSMAP = Map.of(
            FRBlocks.TOWER_BRICKS.get(), FRBlocks.MOSSY_TOWER_BRICKS.get(),
            FRBlocks.TOWER_BRICK_STAIRS.get(), FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get(),
            FRBlocks.TOWER_BRICK_SLAB.get(), FRBlocks.MOSSY_TOWER_BRICK_SLAB.get(),
            FRBlocks.TOWER_BRICK_WALL.get(), FRBlocks.MOSSY_TOWER_BRICK_WALL.get()
    );
    private static final EntityType<?>[] MOB_SPAWNER_ENTITIES
            = new EntityType[]{EntityType.SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER};

    private static ResourceLocation getId(String identifier)
    {
        return Frontiers.id("white_tower/" + identifier);
    }

    private static StructurePlaceSettings createPlacementData(Mirror mirror, Rotation rotation)
    {
        return new StructurePlaceSettings()
                .setIgnoreEntities(true)
                .setRotation(rotation)
                .setMirror(mirror)
                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
    }

    private static EntityType<?> getMobSpawnerEntity(RandomSource random)
    {
        return Util.getRandom(MOB_SPAWNER_ENTITIES, random);
    }

    // Bottom
    public static class Bottom extends TemplateStructurePiece
    {
        public Bottom(StructureTemplateManager manager, BlockPos pos, Rotation rotation, Mirror mirror)
        {
            super(FRStructurePieceTypes.WHITE_TOWER_BOTTOM.get(), 0, manager, WhiteTowerGenerator.getId("bottom"), "bottom", createPlacementData(mirror, rotation), pos);
        }

        public Bottom(StructureTemplateManager manager, CompoundTag nbt) {
            super(
                    FRStructurePieceTypes.WHITE_TOWER_BOTTOM.get(),
                    nbt,
                    manager,
                    id -> createPlacementData(Mirror.valueOf(nbt.getString("Mi")), Rotation.valueOf(nbt.getString("Rot")))
            );
        }

        @Override protected ResourceLocation makeTemplateLocation() { return getId(this.templateName); }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag nbt)
        {
            super.addAdditionalSaveData(context, nbt);
            nbt.putString("Rot", this.placeSettings.getRotation().name());
            nbt.putString("Mi", this.placeSettings.getMirror().name());
        }

        @Override
        protected void handleDataMarker(String metadata, BlockPos pos, ServerLevelAccessor world, RandomSource random, BoundingBox boundingBox)
        {
            if (metadata.equals("TowerHeart"))
            {
                BlockState blockState = FRBlocks.TOWER_HEART.get().defaultBlockState();
                world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            }
            else if (metadata.equals("TowerTreasureVault"))
            {
                BlockState blockState = FRBlocks.TOWER_TREASURE_VAULT.get().defaultBlockState().setValue(TowerTreasureVaultBlock.FACING, Direction.SOUTH);
                world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            }
        }
    }

    // Single Piece
    public static class Piece extends TemplateStructurePiece
    {
        public Piece(StructureTemplateManager manager, String template, BlockPos pos, Rotation rotation, Mirror mirror)
        {
            super(FRStructurePieceTypes.WHITE_TOWER_PIECE.get(), 0, manager, WhiteTowerGenerator.getId(template), template, createPlacementData(mirror, rotation), pos);
        }

        public Piece(StructureTemplateManager manager, CompoundTag nbt) {
            super(
                    FRStructurePieceTypes.WHITE_TOWER_PIECE.get(),
                    nbt,
                    manager,
                    id -> createPlacementData(Mirror.valueOf(nbt.getString("Mi")), Rotation.valueOf(nbt.getString("Rot")))
            );
        }

        @Override protected ResourceLocation makeTemplateLocation() { return getId(this.templateName); }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag nbt)
        {
            super.addAdditionalSaveData(context, nbt);
            nbt.putString("Rot", this.placeSettings.getRotation().name());
            nbt.putString("Mi", this.placeSettings.getMirror().name());
        }

        @Override
        protected void handleDataMarker(String metadata, BlockPos pos, ServerLevelAccessor world, RandomSource random, BoundingBox boundingBox)
        {
            Rotation blockRotation = this.placeSettings.getRotation();
            if (metadata.startsWith("Chest"))
            {
                BlockState blockState = Blocks.CHEST.defaultBlockState();
                if ("ChestN".equals(metadata))
                {
                    blockState = blockState.setValue(ChestBlock.FACING, blockRotation.rotate(Direction.NORTH));
                }
                else if ("ChestE".equals(metadata))
                {
                    blockState = blockState.setValue(ChestBlock.FACING, blockRotation.rotate(Direction.EAST));
                }
                else if ("ChestS".equals(metadata))
                {
                    blockState = blockState.setValue(ChestBlock.FACING, blockRotation.rotate(Direction.SOUTH));
                }
                else if ("ChestW".equals(metadata))
                {
                    blockState = blockState.setValue(ChestBlock.FACING, blockRotation.rotate(Direction.WEST));
                }

                this.createChest(world, boundingBox, random, pos, BuiltInLootTables.SIMPLE_DUNGEON, blockState);
            }
            else if (metadata.endsWith("Spawner"))
            {
                BlockState blockState = FRBlocks.TOWER_SPAWNER.get().defaultBlockState();
                world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
                if (world.getBlockEntity(pos) instanceof TowerSpawnerBlockEntity spawner)
                {
                    if ("SpiderSpawner".equals(metadata))
                    {
                        spawner.setEntityId(EntityType.SPIDER, random);
                    }
                    else
                    {
                        spawner.setEntityId(getMobSpawnerEntity(random), random);
                    }
                }
                else
                {
                    Frontiers.LOGGER.error("Failed to fetch tower spawner entity at ({}, {}, {})", pos.getX(), pos.getY(), pos.getZ());
                }
            }
            else if (metadata.equals("KeyFragVault"))
            {
                BlockState blockState = FRBlocks.TOWER_KEY_VAULT.get().defaultBlockState();
                world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            }
        }
    }

    // Entryway
    public static class Entry extends TemplateStructurePiece
    {
        public Entry(StructureTemplateManager manager, BlockPos pos, Rotation rotation, Mirror mirror)
        {
            super(FRStructurePieceTypes.WHITE_TOWER_ENTRY.get(), 0, manager, WhiteTowerGenerator.getId("top"), "top", createPlacementData(mirror, rotation), pos);
        }

        public Entry(StructureTemplateManager manager, CompoundTag nbt) {
            super(
                    FRStructurePieceTypes.WHITE_TOWER_ENTRY.get(),
                    nbt,
                    manager,
                    id -> createPlacementData(Mirror.valueOf(nbt.getString("Mi")), Rotation.valueOf(nbt.getString("Rot")))
            );
        }

        @Override protected ResourceLocation makeTemplateLocation() { return getId(this.templateName); }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag nbt)
        {
            super.addAdditionalSaveData(context, nbt);
            nbt.putString("Rot", this.placeSettings.getRotation().name());
            nbt.putString("Mi", this.placeSettings.getMirror().name());
        }

        @Override
        protected void handleDataMarker(String metadata, BlockPos pos, ServerLevelAccessor world, RandomSource random, BoundingBox boundingBox)
        {

        }
    }
}
