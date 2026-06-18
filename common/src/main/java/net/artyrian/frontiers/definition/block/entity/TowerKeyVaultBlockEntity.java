package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.reg.content.FRBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TowerKeyVaultBlockEntity extends BlockEntity
{
    private List<UUID> saved_users = new ArrayList<>();

    public TowerKeyVaultBlockEntity(BlockPos pos, BlockState state)
    {
        super(FRBlockEntities.TOWER_KEY_VAULT.get(), pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup)
    {
        return saveWithoutMetadata(registryLookup);
    }

    public static void tickClient(Level world, BlockPos pos, BlockState state, TowerKeyVaultBlockEntity blockEntity)
    {

    }

    public static void tickServer(Level world, BlockPos pos, BlockState state, TowerKeyVaultBlockEntity blockEntity)
    {

    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        if (nbt.contains("SavedUsers", Tag.TAG_LIST))
        {
            ListTag nbtList = (ListTag)nbt.get("SavedUsers");

            if (nbtList != null && !nbtList.isEmpty())
            {
                for (int i = 0; i < nbtList.size(); i++) this.saved_users.add(NbtUtils.loadUUID(nbtList.get(i)));
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        if (!this.saved_users.isEmpty())
        {
            ListTag allowedlist = new ListTag();
            for (UUID user : this.saved_users) allowedlist.add(NbtUtils.createUUID(user));
            nbt.put("SavedUsers", allowedlist);
        }
    }
}
