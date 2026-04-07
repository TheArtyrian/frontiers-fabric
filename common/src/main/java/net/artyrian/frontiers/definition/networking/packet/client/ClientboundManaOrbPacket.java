package net.artyrian.frontiers.definition.networking.packet.client;

import net.artyrian.frontiers.definition.entity.types.misc.ManaOrbEntity;
import net.artyrian.frontiers.mixin_intf.networking.ClientPlayIntf;
import net.artyrian.frontiers.reg.misc.FRNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.phys.Vec3;

public class ClientboundManaOrbPacket implements Packet<ClientGamePacketListener>
{
    public static final StreamCodec<FriendlyByteBuf, ClientboundManaOrbPacket> CODEC = Packet.codec(
            ClientboundManaOrbPacket::write, ClientboundManaOrbPacket::new
    );
    private final int entityId;
    private final double x;
    private final double y;
    private final double z;
    private final int mana;

    public ClientboundManaOrbPacket(ManaOrbEntity orb, ServerEntity entry)
    {
        this.entityId = orb.getId();
        Vec3 vec3d = entry.getPositionBase();
        this.x = vec3d.x();
        this.y = vec3d.y();
        this.z = vec3d.z();
        this.mana = orb.getManaAmount();
    }

    private ClientboundManaOrbPacket(FriendlyByteBuf buf)
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
    public PacketType<ClientboundManaOrbPacket> type() { return FRNetworking.SPAWN_MANA_ORB;}

    @Override
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
