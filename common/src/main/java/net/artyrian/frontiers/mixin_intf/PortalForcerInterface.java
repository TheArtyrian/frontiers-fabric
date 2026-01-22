package net.artyrian.frontiers.mixin_intf;

import java.util.Optional;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.border.WorldBorder;

public interface PortalForcerInterface
{
    public Optional<BlockPos> frontiers_1_21x$getPortalAdv(BlockPos pos, int scale, WorldBorder worldBorder, PoiType POI);

    public Optional<BlockUtil.FoundRectangle> frontiers_1_21x$_createCragsPortal(BlockPos pos);
}
