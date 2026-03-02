package net.vertisoft.vectorlib.mixin.impl.eventsync;

import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.vertisoft.vectorlib.mixin_intf.event.VectorLevelAccess;
import net.vertisoft.vectorlib.mixin_intf.event.VectorLevelRenderer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin implements VectorLevelAccess
{
    @Shadow @Final private LevelRenderer levelRenderer;

    @Override
    public void vectorLib$fireEvent(@Nullable Player player, int type, BlockPos pos, int data)
    {
        try
        {
            ((VectorLevelRenderer)this.levelRenderer).vectorLib$runGameEvent(type, pos, data);
        }
        catch (Throwable throwable)
        {
            CrashReport crashReport = CrashReport.forThrowable(throwable, "Playing VectorLib level event - REPORT THIS TO VECTORLIB DEVS, NOT MOJANG! ");
            CrashReportCategory crashReportCategory = crashReport.addCategory("VLib event being played");
            crashReportCategory.setDetail("Block coordinates", CrashReportCategory.formatLocation((ClientLevel)(Object)this, pos));
            crashReportCategory.setDetail("Event source", player);
            crashReportCategory.setDetail("Event type", type);
            crashReportCategory.setDetail("Event data", data);
            throw new ReportedException(crashReport);
        }
    }
}
