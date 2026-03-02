package net.vertisoft.vectorlib.mixin_intf.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public interface VectorLevelAccess
{
    void vectorLib$fireEvent(@Nullable Player player, int type, BlockPos pos, int data);
}
