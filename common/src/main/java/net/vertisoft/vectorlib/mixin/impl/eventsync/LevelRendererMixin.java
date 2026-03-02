package net.vertisoft.vectorlib.mixin.impl.eventsync;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import net.vertisoft.vectorlib.mixin_intf.event.VectorLevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin implements VectorLevelRenderer
{
    @Shadow @Nullable private ClientLevel level;

    @Override
    public void vectorLib$runGameEvent(int type, BlockPos pos, int data)
    {
        VectorEventSync.VecEventAlias alias = VectorEventSync.EVENT_LIST.get(type);
        alias.execute(this.level, pos, data);
    }
}
