package net.artyrian.frontiers.definition.networking.packet;

import net.artyrian.frontiers.definition.entity.types.misc.ManaOrbEntity;
import net.artyrian.frontiers.mixin_intf.networking.ClientPlayIntf;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.phys.Vec3;

public class ManaOrbSpawnS2CPacket implements Packet<ClientGamePacketListener>
{
    public static final StreamCodec<FriendlyByteBuf, ManaOrbSpawnS2CPacket> CODEC = Packet.codec(
            ManaOrbSpawnS2CPacket::write, ManaOrbSpawnS2CPacket::new
    );
    private final int entityId;
    private final double x;
    private final double y;
    private final double z;
    private final int mana;

    public ManaOrbSpawnS2CPacket(ManaOrbEntity orb, ServerEntity entry)
    {
        this.entityId = orb.getId();
        Vec3 vec3d = entry.getPositionBase();
        this.x = vec3d.x();
        this.y = vec3d.y();
        this.z = vec3d.z();
        this.mana = orb.getManaAmount();
    }

    private ManaOrbSpawnS2CPacket(FriendlyByteBuf buf)
    {
        this.entityId = buf.readVarInt();
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.mana = buf.readShort();
    }

    private void write(FriendlyByteBuf buf)
    {
        buf.writeVarInt(this.entityId);
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeShort(this.mana);
    }

    @Override
    public PacketType<ManaOrbSpawnS2CPacket> type() { return ModNetworkConstants.SPAWN_MANA_ORB;}

    public void handle(ClientGamePacketListener clientPlayPacketListener)
    {
        ((ClientPlayIntf)clientPlayPacketListener).frontiers$onManaOrbSpawn(this);
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
