package net.artyrian.frontiers.mixin.networking.server;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarMenu;
import net.artyrian.frontiers.definition.networking.packet.server.ServerboundCurseAltarPacket;
import net.artyrian.frontiers.mixin_intf.networking.ServerPlayIntf;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerPacketListenerMixin implements ServerPlayIntf
{
    @Shadow public ServerPlayer player;

    @Override
    public void frontiers$onCurseAltarPacket(ServerboundCurseAltarPacket packet)
    {
        PacketUtils.ensureRunningOnSameThread(packet, (ServerGamePacketListenerImpl)(Object)this, this.player.serverLevel());
        AbstractContainerMenu menutype = this.player.containerMenu;
        if (menutype instanceof CurseAltarMenu curseMenu)
        {
            if (!curseMenu.stillValid(this.player))
            {
                Frontiers.LOGGER.debug("Player {} interacted with invalid Curse Altar menu {}", this.player, curseMenu);
                return;
            }

            curseMenu.runPurifyProcess(this.player, packet.instance());
        }
    }
}
