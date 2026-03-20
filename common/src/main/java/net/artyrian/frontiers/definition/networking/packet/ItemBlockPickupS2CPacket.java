package net.artyrian.frontiers.definition.networking.packet;

import net.artyrian.frontiers.mixin_intf.networking.ClientPlayIntf;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.phys.Vec3;

public class ItemBlockPickupS2CPacket implements Packet<ClientGamePacketListener>
{
    public static final StreamCodec<FriendlyByteBuf, ItemBlockPickupS2CPacket> CODEC = Packet.codec(
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

    private ItemBlockPickupS2CPacket(FriendlyByteBuf buf)
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
    public PacketType<ItemBlockPickupS2CPacket> type() {
        return ModNetworkConstants.PICKUP_TO_BLOCK;
    }

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
