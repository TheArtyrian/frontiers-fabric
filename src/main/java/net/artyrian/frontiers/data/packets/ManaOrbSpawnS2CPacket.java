package net.artyrian.frontiers.data.packets;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.artyrian.frontiers.entity.misc.ManaOrbEntity;
import net.artyrian.frontiers.mixin_interfaces.ClientPlayNetImpl;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.PlayPackets;
import net.minecraft.network.packet.s2c.play.ExperienceOrbSpawnS2CPacket;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.util.math.Vec3d;

public class ManaOrbSpawnS2CPacket implements Packet<ClientPlayPacketListener>
{
    public static final PacketCodec<PacketByteBuf, ManaOrbSpawnS2CPacket> CODEC = Packet.createCodec(
            ManaOrbSpawnS2CPacket::write, ManaOrbSpawnS2CPacket::new
    );
    private final int entityId;
    private final double x;
    private final double y;
    private final double z;
    private final int mana;

    public ManaOrbSpawnS2CPacket(ManaOrbEntity orb, EntityTrackerEntry entry)
    {
        this.entityId = orb.getId();
        Vec3d vec3d = entry.getPos();
        this.x = vec3d.getX();
        this.y = vec3d.getY();
        this.z = vec3d.getZ();
        this.mana = orb.getManaAmount();
    }

    private ManaOrbSpawnS2CPacket(PacketByteBuf buf)
    {
        this.entityId = buf.readVarInt();
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.mana = buf.readShort();
    }

    private void write(PacketByteBuf buf)
    {
        buf.writeVarInt(this.entityId);
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeShort(this.mana);
    }

    @Override
    public PacketType<ManaOrbSpawnS2CPacket> getPacketId() { return ModNetworkConstants.SPAWN_MANA_ORB;}

    public void apply(ClientPlayPacketListener clientPlayPacketListener)
    {
        ((ClientPlayNetImpl)clientPlayPacketListener).frontiers$onManaOrbSpawn(this);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public int getMana() {
        return this.mana;
    }
}
