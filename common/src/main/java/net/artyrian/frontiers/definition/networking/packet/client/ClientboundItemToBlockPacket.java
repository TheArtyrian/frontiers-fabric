package net.artyrian.frontiers.definition.networking.packet.client;

import net.artyrian.frontiers.mixin_intf.networking.ClientPlayIntf;
import net.artyrian.frontiers.reg.misc.FRNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.phys.Vec3;

public class ClientboundItemToBlockPacket implements Packet<ClientGamePacketListener>
{
    public static final StreamCodec<FriendlyByteBuf, ClientboundItemToBlockPacket> CODEC = Packet.codec(
            ClientboundItemToBlockPacket::write, ClientboundItemToBlockPacket::new
    );
    private final int entityId;
    private final double posX;
    private final double posY;
    private final double posZ;
    private final int stackAmount;

    public ClientboundItemToBlockPacket(int entityId, double posX, double posY, double posZ, int stackAmount)
    {
        this.entityId = entityId;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.stackAmount = stackAmount;
    }

    private ClientboundItemToBlockPacket(FriendlyByteBuf buf)
    {
        this.entityId = buf.readVarInt();
        this.posX = buf.readDouble();
        this.posY = buf.readDouble();
        this.posZ = buf.readDouble();
        this.stackAmount = buf.readVarInt();
    }

    private void write(FriendlyByteBuf buf)
    {
        buf.writeVarInt(this.entityId);
        buf.writeDouble(this.posX);
        buf.writeDouble(this.posY);
        buf.writeDouble(this.posZ);
        buf.writeVarInt(this.stackAmount);
    }

    @Override
    public PacketType<ClientboundItemToBlockPacket> type() {
        return FRNetworking.PICKUP_TO_BLOCK;
    }

    @Override
    public void handle(ClientGamePacketListener clientPlayPacketListener)
    {
        ((ClientPlayIntf)clientPlayPacketListener).frontiers$onItemToBlockPickupAnim(this);
    }

    public int getEntityId() {
        return this.entityId;
    }
    public Vec3 getPos() { return new Vec3(this.posX, this.posY, this.posZ); }
    public int getStackAmount() {
        return this.stackAmount;
    }
}
