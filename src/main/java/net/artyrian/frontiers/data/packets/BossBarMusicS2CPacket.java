package net.artyrian.frontiers.data.packets;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.artyrian.frontiers.mixin_interfaces.networking.ClientPlayNetImpl;
import net.artyrian.frontiers.sounds.ModMusic;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.s2c.play.BossBarS2CPacket;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.MusicType;

import java.util.UUID;

public class BossBarMusicS2CPacket implements Packet<ClientPlayPacketListener>
{
    public static final PacketCodec<RegistryByteBuf, BossBarMusicS2CPacket> CODEC = Packet.createCodec(BossBarMusicS2CPacket::write, BossBarMusicS2CPacket::new);

    private final UUID uuid;
    private final MusicSound music;

    public BossBarMusicS2CPacket(UUID uuid, MusicSound music)
    {
        this.uuid = uuid;
        this.music = music;
    }

    private BossBarMusicS2CPacket(RegistryByteBuf buf)
    {
        this.uuid = buf.readUuid();
        this.music = PacketCodecs.codec(MusicSound.CODEC).decode(buf);
    }

    @Override
    public PacketType<BossBarMusicS2CPacket> getPacketId() { return ModNetworkConstants.UPDATE_BOSSBAR_MUSIC;}

    @Override
    public void apply(ClientPlayPacketListener clientPlayPacketListener)
    {
        ((ClientPlayNetImpl)clientPlayPacketListener).frontiers$onBossBarUpdateMusic(this);
    }

    private void write(RegistryByteBuf buf) 
    {
        buf.writeUuid(this.uuid);
        PacketCodecs.codec(MusicSound.CODEC).encode(buf, this.music);
    }

    public MusicSound getMusic() {
        return this.music;
    }
    public UUID getBossBarUUID() { return this.uuid; }
}
