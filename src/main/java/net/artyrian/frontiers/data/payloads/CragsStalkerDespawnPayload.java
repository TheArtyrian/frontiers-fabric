package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public record CragsStalkerDespawnPayload(double x, double y, double z) implements CustomPayload
{
    public static final CustomPayload.Id<CragsStalkerDespawnPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.CRAGS_STALKER_DESPAWN_PACKET);
    public static final PacketCodec<RegistryByteBuf, CragsStalkerDespawnPayload> CODEC = PacketCodec.tuple(
                PacketCodecs.DOUBLE, CragsStalkerDespawnPayload::x,
                PacketCodecs.DOUBLE, CragsStalkerDespawnPayload::y,
                PacketCodecs.DOUBLE, CragsStalkerDespawnPayload::z,
                CragsStalkerDespawnPayload::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}