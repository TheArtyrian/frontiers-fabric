package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record CragsMonsterKillPayload(boolean bool) implements CustomPayload
{
    public static final CustomPayload.Id<CragsMonsterKillPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.CRAGS_MONSTER_KILL_PACKET);
    public static final PacketCodec<RegistryByteBuf, CragsMonsterKillPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, CragsMonsterKillPayload::bool, CragsMonsterKillPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}