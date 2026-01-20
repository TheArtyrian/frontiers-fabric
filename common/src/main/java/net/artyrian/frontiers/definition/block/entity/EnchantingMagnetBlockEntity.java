package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.definition.networking.packet.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.misc.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnchantingMagnetBlockEntity extends BlockEntity
{
    public static final int MIN_EXP = 0;
    public static final int MAX_EXP = 32767;
    public static final int MAX_VIEWABLE_EXP = 1200;
    private static final int TENTH_MAX = MAX_VIEWABLE_EXP / 10;
    public static final int COOLDOWN_TIME = 4;
    private int exp_count = 0;
    private int pickup_cooldown = 0;

    private double rotation = 0;
    private double lastRotation = 0;

    public EnchantingMagnetBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ENCHANTING_MAGNET_BLOCKENTITY.get(), pos, state);
    }

    public int getExp() {return this.exp_count; }
    public void setExp(int value) { this.exp_count = Math.clamp(value, MIN_EXP, MAX_EXP); }
    public void addExp(int value) { this.exp_count = Math.clamp(this.exp_count + value, MIN_EXP, MAX_EXP); }
    public void subtractExp(int value) { this.exp_count = Math.clamp(this.exp_count - value, MIN_EXP, MAX_EXP); }
    public double getRot() { return this.rotation; }
    public double getLastRot() { return this.lastRotation; }

    private float getPercentToViewFull()
    {
        float basic = ((float) this.exp_count / MAX_VIEWABLE_EXP);
        return (basic - (basic % 0.1F));
    }
    public float getSizePercent()
    {
        float wrap = getPercentToViewFull();
        return Math.clamp(Mth.lerp(wrap, 0.3F, 1.0F), 0.3F, 1.0F);
    }
    public int get0To15Percent()
    {
        if (this.exp_count == MIN_EXP) return 0;

        float wrap = getPercentToViewFull();
        return Math.clamp(Mth.lerpInt(wrap, 1, 15), 1, 15);
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput components)
    {
        super.applyImplicitComponents(components);
        this.exp_count = components.getOrDefault(ModDataComponents.EXP_AMOUNT.get(), MIN_EXP);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder componentMapBuilder)
    {
        super.collectImplicitComponents(componentMapBuilder);
        componentMapBuilder.set(ModDataComponents.EXP_AMOUNT.get(), this.getExp());
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.saveAdditional(nbt, registryLookup);
        nbt.putInt("ExpCount", this.exp_count);
        nbt.putInt("PickupCooldown", this.pickup_cooldown);
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.loadAdditional(nbt, registryLookup);
        if (nbt.contains("ExpCount", Tag.TAG_INT))
        {
            this.exp_count = nbt.getInt("ExpCount");
        }
        if (nbt.contains("PickupCooldown", Tag.TAG_INT))
        {
            this.pickup_cooldown = nbt.getInt("PickupCooldown");
        }
    }

    public static void clientTick(Level world, BlockPos pos, BlockState state, EnchantingMagnetBlockEntity blockEntity)
    {
        blockEntity.lastRotation = blockEntity.rotation;
        blockEntity.rotation = blockEntity.rotation + 1.0;

        if (blockEntity.rotation == 36.0)
        {
            blockEntity.lastRotation = -1.0F;
            blockEntity.rotation = 0.0F;
        }
    }

    public static void serverTick(Level world, BlockPos pos, BlockState state, EnchantingMagnetBlockEntity blockEntity)
    {
        if (blockEntity.pickup_cooldown > 0) blockEntity.pickup_cooldown--;

        if (blockEntity.pickup_cooldown == 0)
        {
            blockEntity.pickup_cooldown = COOLDOWN_TIME;
            AABB box = AABB.ofSize(blockEntity.getBlockPos().getCenter(), 4.0, 4.0, 4.0);
            List<ExperienceOrb> orb_list = world.getEntitiesOfClass(ExperienceOrb.class, box, orb -> { return orb instanceof ExpMixImpl; });
            if (orb_list != null && !orb_list.isEmpty())
            {
                ExperienceOrb orb = orb_list.getFirst();
                if (!(blockEntity.getExp() + orb.getValue() > MAX_EXP) && orb instanceof ExpMixImpl)
                {
                    blockEntity.addExp(orb.getValue());
                    ServerChunkCache manager = ((ServerLevel)blockEntity.getLevel()).getChunkSource();
                    if (manager != null)
                    {
                        Vec3 posCen = blockEntity.getBlockPos().getCenter();
                        manager.broadcast(orb, new ItemBlockPickupS2CPacket(orb.getId(), posCen.x, posCen.y, posCen.z, 1));
                        ((ExpMixImpl)orb).frontiers$subtractCount();
                        world.sendBlockUpdated(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
                        world.updateNeighbourForOutputSignal(pos, world.getBlockState(pos).getBlock());
                    }
                }
            }
        }
    }

    @Nullable @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) { return saveWithoutMetadata(registryLookup); }
}
