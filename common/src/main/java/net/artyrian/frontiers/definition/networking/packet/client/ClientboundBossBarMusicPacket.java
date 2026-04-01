package net.artyrian.frontiers.definition.networking.packet.client;

import net.artyrian.frontiers.mixin_intf.networking.ClientPlayIntf;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.Music;
import java.util.UUID;

public class ClientboundBossBarMusicPacket implements Packet<ClientGamePacketListener>
{
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundBossBarMusicPacket> CODEC = Packet.codec(ClientboundBossBarMusicPacket::write, ClientboundBossBarMusicPacket::new);

    private final UUID uuid;
    private final Music music;

    public ClientboundBossBarMusicPacket(UUID uuid, Music music)
    {
        this.uuid = uuid;
        this.music = music;
    }

    private ClientboundBossBarMusicPacket(RegistryFriendlyByteBuf buf)
    {
        this.uuid = buf.readUUID();
        this.music = ByteBufCodecs.fromCodec(Music.CODEC).decode(buf);
    }

    @Override
    public PacketType<ClientboundBossBarMusicPacket> type() { return ModNetworkConstants.UPDATE_BOSSBAR_MUSIC;}

    @Override
    public void handle(ClientGamePacketListener clientPlayPacketListener)
    {
        ((ClientPlayIntf)clientPlayPacketListener).frontiers$onBossBarUpdateMusic(this);
    }

    private void write(RegistryFriendlyByteBuf buf) 
    {
        buf.writeUUID(this.uuid);
        ByteBufCodecs.fromCodec(Music.CODEC).encode(buf, this.music);
    }

    public Music getMusic() {
        return this.music;
    }
    public UUID getBossBarUUID() { return this.uuid; }
}
