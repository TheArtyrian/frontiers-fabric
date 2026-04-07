package net.artyrian.frontiers.definition.networking.packet.server;

import net.artyrian.frontiers.definition.menu.curse.CurseEnchantInst;
import net.artyrian.frontiers.mixin_intf.networking.ServerPlayIntf;
import net.artyrian.frontiers.reg.misc.FRNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ServerGamePacketListener;

public record ServerboundCurseAltarPacket(CurseEnchantInst instance) implements Packet<ServerGamePacketListener>
{
    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundCurseAltarPacket> STREAM_CODEC = StreamCodec.composite(
            CurseEnchantInst.CODEC, ServerboundCurseAltarPacket::instance,
            ServerboundCurseAltarPacket::new
    );

    @Override public PacketType<ServerboundCurseAltarPacket> type() { return FRNetworking.CURSE_ALTAR_DISENCHANT; }
    @Override public void handle(ServerGamePacketListener serverGamePacketListener) { ((ServerPlayIntf)serverGamePacketListener).frontiers$onCurseAltarPacket(this); }
}
