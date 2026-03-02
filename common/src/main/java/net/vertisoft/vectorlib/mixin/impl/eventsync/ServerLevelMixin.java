package net.vertisoft.vectorlib.mixin.impl.eventsync;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.vertisoft.vectorlib.agnostic.networking.data.packets.VectorEventS2CPacket;
import net.vertisoft.vectorlib.mixin_intf.event.VectorLevelAccess;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin implements VectorLevelAccess
{
    @Shadow @Final private MinecraftServer server;

    @Override
    public void vectorLib$fireEvent(@Nullable Player player, int type, BlockPos pos, int data)
    {
        this.server.getPlayerList()
                .broadcast(
                        player,
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        64.0,
                        ((ServerLevel)(Object)this).dimension(),
                        new VectorEventS2CPacket(type, pos, data, false)
                );
    }
}
