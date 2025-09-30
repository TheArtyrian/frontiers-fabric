package net.artyrian.frontiers.data.packets;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.artyrian.frontiers.mixin_interfaces.ClientPlayNetImpl;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;
import net.minecraft.util.math.Vec3d;

public class ItemBlockPickupS2CPacket implements Packet<ClientPlayPacketListener>
{
    public static final PacketCodec<PacketByteBuf, ItemBlockPickupS2CPacket> CODEC = Packet.createCodec(
            ItemBlockPickupS2CPacket::write, ItemBlockPickupS2CPacket::new
    );
    private final int entityId;
    private final double posX;
    private final double posY;
    private final double posZ;
    private final int stackAmount;

    public ItemBlockPickupS2CPacket(int entityId, double posX, double posY, double posZ, int stackAmount)
    {
        this.entityId = entityId;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.stackAmount = stackAmount;
    }

    private ItemBlockPickupS2CPacket(PacketByteBuf buf)
    {
        this.entityId = buf.readVarInt();
        this.posX = buf.readDouble();
        this.posY = buf.readDouble();
        this.posZ = buf.readDouble();
        this.stackAmount = buf.readVarInt();
    }

    private void write(PacketByteBuf buf)
    {
        buf.writeVarInt(this.entityId);
        buf.writeDouble(this.posX);
        buf.writeDouble(this.posY);
        buf.writeDouble(this.posZ);
        buf.writeVarInt(this.stackAmount);
    }

    @Override
    public PacketType<ItemBlockPickupS2CPacket> getPacketId() {
        return ModNetworkConstants.PICKUP_TO_BLOCK;
    }

    public void apply(ClientPlayPacketListener clientPlayPacketListener)
    {
        ((ClientPlayNetImpl)clientPlayPacketListener).frontiers$onItemToBlockPickupAnim(this);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public Vec3d getPos() { return new Vec3d(this.posX, this.posY, this.posZ); }

    public int getStackAmount() {
        return this.stackAmount;
    }
}
