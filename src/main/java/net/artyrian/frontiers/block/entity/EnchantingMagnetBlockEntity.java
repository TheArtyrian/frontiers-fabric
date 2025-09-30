package net.artyrian.frontiers.block.entity;

import net.artyrian.frontiers.data.packets.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.mixin_interfaces.ExpMixImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkManager;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnchantingMagnetBlockEntity extends BlockEntity
{
    public static final int MIN_EXP = 0;
    public static final int MAX_EXP = 32767;
    public static final int MAX_VIEWABLE_EXP = 1200;
    public static final int COOLDOWN_TIME = 4;
    private int exp_count = 0;
    private int pickup_cooldown = 0;

    private double rotation = 0;
    private double lastRotation = 0;

    public EnchantingMagnetBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ENCHANTING_MAGNET_BLOCKENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("ExpCount", this.exp_count);
        nbt.putInt("PickupCooldown", this.pickup_cooldown);
    }

    public int getExp() {return this.exp_count; }
    public void setExp(int value) { this.exp_count = Math.clamp(value, MIN_EXP, MAX_EXP); }
    public void addExp(int value) { this.exp_count += Math.clamp(value, MIN_EXP, MAX_EXP); }
    public void subtractExp(int value) { this.addExp(-value); }
    public double getRot() { return this.rotation; }
    public double getLastRot() { return this.lastRotation; }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("ExpCount", NbtElement.INT_TYPE))
        {
            this.exp_count = nbt.getInt("ExpCount");
        }
        if (nbt.contains("PickupCooldown", NbtElement.INT_TYPE))
        {
            this.pickup_cooldown = nbt.getInt("PickupCooldown");
        }
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, EnchantingMagnetBlockEntity blockEntity)
    {
        blockEntity.lastRotation = blockEntity.rotation;
        blockEntity.rotation = blockEntity.rotation + 1.0;

        if (blockEntity.rotation == 36.0)
        {
            blockEntity.lastRotation = -1.0F;
            blockEntity.rotation = 0.0F;
        }
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, EnchantingMagnetBlockEntity blockEntity)
    {
        if (blockEntity.pickup_cooldown > 0) blockEntity.pickup_cooldown--;

        if (blockEntity.pickup_cooldown == 0)
        {
            blockEntity.pickup_cooldown = COOLDOWN_TIME;
            Box box = Box.of(blockEntity.getPos().toCenterPos(), 4.0, 4.0, 4.0);
            List<ExperienceOrbEntity> orb_list = world.getEntitiesByClass(ExperienceOrbEntity.class, box, orb -> { return orb instanceof ExpMixImpl; });
            if (orb_list != null && !orb_list.isEmpty())
            {
                ExperienceOrbEntity orb = orb_list.getFirst();
                if (!(blockEntity.getExp() + orb.getExperienceAmount() > MAX_EXP) && orb instanceof ExpMixImpl)
                {
                    blockEntity.addExp(orb.getExperienceAmount());
                    ServerChunkManager manager = ((ServerWorld)blockEntity.getWorld()).getChunkManager();
                    if (manager != null)
                    {
                        Vec3d posCen = blockEntity.getPos().toCenterPos();
                        manager.sendToOtherNearbyPlayers(orb, new ItemBlockPickupS2CPacket(orb.getId(), posCen.x, posCen.y, posCen.z, 1));
                        ((ExpMixImpl)orb).frontiers$subtractCount();
                    }
                }
            }
        }
    }

    @Nullable @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) { return createNbt(registryLookup); }
}
