package net.artyrian.frontiers.world.structure.white_tower;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.world.structure.ModStructurePieceType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;

public class WhiteTowerGenerator
{
    private static final EntityType<?>[] MOB_SPAWNER_ENTITIES
            = new EntityType[]{EntityType.SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER};

    private static Identifier getId(String identifier)
    {
        return Identifier.of(Frontiers.MOD_ID,"white_tower/" + identifier);
    }

    private static StructurePlacementData createPlacementData(BlockMirror mirror, BlockRotation rotation)
    {
        return new StructurePlacementData()
                .setIgnoreEntities(true)
                .setRotation(rotation)
                .setMirror(mirror)
                .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
    }

    private static EntityType<?> getMobSpawnerEntity(Random random)
    {
        return Util.getRandom(MOB_SPAWNER_ENTITIES, random);
    }

    // Bottom
    public static class Bottom extends SimpleStructurePiece
    {
        public Bottom(StructureTemplateManager manager, BlockPos pos, BlockRotation rotation, BlockMirror mirror)
        {
            super(ModStructurePieceType.WHITE_TOWER_BOTTOM, 0, manager, WhiteTowerGenerator.getId("bottom"), "bottom", createPlacementData(mirror, rotation), pos);
        }

        public Bottom(StructureTemplateManager manager, NbtCompound nbt) {
            super(
                    ModStructurePieceType.WHITE_TOWER_BOTTOM,
                    nbt,
                    manager,
                    id -> createPlacementData(BlockMirror.valueOf(nbt.getString("Mi")), BlockRotation.valueOf(nbt.getString("Rot")))
            );
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt)
        {
            super.writeNbt(context, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
            nbt.putString("Mi", this.placementData.getMirror().name());
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox)
        {
            if (metadata.equals("TowerTreasureVault"))
            {
                BlockState blockState = Blocks.VAULT.getDefaultState();
                world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            }
        }
    }

    // Single Piece
    public static class Piece extends SimpleStructurePiece
    {
        public Piece(StructureTemplateManager manager, String template, BlockPos pos, BlockRotation rotation, BlockMirror mirror)
        {
            super(ModStructurePieceType.WHITE_TOWER_PIECE, 0, manager, WhiteTowerGenerator.getId(template), template, createPlacementData(mirror, rotation), pos);
        }

        public Piece(StructureTemplateManager manager, NbtCompound nbt) {
            super(
                    ModStructurePieceType.WHITE_TOWER_PIECE,
                    nbt,
                    manager,
                    id -> createPlacementData(BlockMirror.valueOf(nbt.getString("Mi")), BlockRotation.valueOf(nbt.getString("Rot")))
            );
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt)
        {
            super.writeNbt(context, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
            nbt.putString("Mi", this.placementData.getMirror().name());
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox)
        {
            BlockRotation blockRotation = this.placementData.getRotation();
            if (metadata.startsWith("Chest"))
            {
                BlockState blockState = Blocks.CHEST.getDefaultState();
                if ("ChestN".equals(metadata))
                {
                    blockState = blockState.with(ChestBlock.FACING, blockRotation.rotate(Direction.NORTH));
                }
                else if ("ChestE".equals(metadata))
                {
                    blockState = blockState.with(ChestBlock.FACING, blockRotation.rotate(Direction.EAST));
                }
                else if ("ChestS".equals(metadata))
                {
                    blockState = blockState.with(ChestBlock.FACING, blockRotation.rotate(Direction.SOUTH));
                }
                else if ("ChestW".equals(metadata))
                {
                    blockState = blockState.with(ChestBlock.FACING, blockRotation.rotate(Direction.WEST));
                }

                this.addChest(world, boundingBox, random, pos, LootTables.SIMPLE_DUNGEON_CHEST, blockState);
            }
            else if (metadata.endsWith("Spawner"))
            {
                BlockState blockState = Blocks.SPAWNER.getDefaultState();
                world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
                if (world.getBlockEntity(pos) instanceof MobSpawnerBlockEntity spawner)
                {
                    if ("SpiderSpawner".equals(metadata))
                    {
                        spawner.setEntityType(EntityType.SPIDER, random);
                    }
                    else
                    {
                        spawner.setEntityType(getMobSpawnerEntity(random), random);
                    }
                }
                else
                {
                    Frontiers.LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", pos.getX(), pos.getY(), pos.getZ());
                }
            }
            else if (metadata.equals("KeyFragVault"))
            {
                BlockState blockState = Blocks.VAULT.getDefaultState();
                world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            }
        }
    }

    // Entryway
    public static class Entry extends SimpleStructurePiece
    {
        public Entry(StructureTemplateManager manager, BlockPos pos, BlockRotation rotation, BlockMirror mirror)
        {
            super(ModStructurePieceType.WHITE_TOWER_ENTRY, 0, manager, WhiteTowerGenerator.getId("top"), "top", createPlacementData(mirror, rotation), pos);
        }

        public Entry(StructureTemplateManager manager, NbtCompound nbt) {
            super(
                    ModStructurePieceType.WHITE_TOWER_ENTRY,
                    nbt,
                    manager,
                    id -> createPlacementData(BlockMirror.valueOf(nbt.getString("Mi")), BlockRotation.valueOf(nbt.getString("Rot")))
            );
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt)
        {
            super.writeNbt(context, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
            nbt.putString("Mi", this.placementData.getMirror().name());
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox)
        {

        }
    }
}
