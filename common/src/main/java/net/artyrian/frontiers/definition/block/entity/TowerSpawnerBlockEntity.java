package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.definition.block.entity.data.TowerSpawner;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TowerSpawnerBlockEntity extends BlockEntity implements Spawner
{
    private final TowerSpawner spawner = new TowerSpawner();

    public TowerSpawnerBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.TOWER_SPAWNER.get(), pos, state);
    }

    @Nullable @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup)
    {
        CompoundTag compoundTag = this.saveCustomOnly(registryLookup);
        compoundTag.remove("SpawnPotentials");
        return compoundTag;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        this.spawner.load(this.level, this.worldPosition, tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        this.spawner.save(tag);
    }

    public static void tickClient(Level world, BlockPos pos, BlockState state, TowerSpawnerBlockEntity blockEntity)
    {
        blockEntity.spawner.clientTick(world, pos);
    }

    public static void tickServer(Level world, BlockPos pos, BlockState state, TowerSpawnerBlockEntity blockEntity)
    {
        blockEntity.spawner.serverTick((ServerLevel) world, pos);
    }

    public boolean onlyOpCanSetNbt() { return true; }

    @Override
    public boolean triggerEvent(int id, int type)
    {
        return (this.spawner.onEvent(this.level, this.worldPosition, id)) ? true : super.triggerEvent(id, type);
    }

    @Override
    public void setEntityId(EntityType<?> entityType, RandomSource random)
    {
        this.spawner.setEntityId(entityType, this.level, random, this.worldPosition);
        this.setChanged();
    }

    public TowerSpawner getSpawner() { return spawner; }
}
