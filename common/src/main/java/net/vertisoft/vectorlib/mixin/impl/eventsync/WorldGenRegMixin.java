package net.vertisoft.vectorlib.mixin.impl.eventsync;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.entity.player.Player;
import net.vertisoft.vectorlib.mixin_intf.event.VectorLevelAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldGenRegion.class)
public abstract class WorldGenRegMixin implements VectorLevelAccess
{
    @Override
    public void vectorLib$fireEvent(@Nullable Player player, int type, BlockPos pos, int data)
    {

    }
}
