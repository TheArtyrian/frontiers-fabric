package net.artyrian.frontiers.mixin_interfaces;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.Optional;

public interface PortalForcerInterface
{
    public Optional<BlockPos> frontiers_1_21x$getPortalAdv(BlockPos pos, int scale, WorldBorder worldBorder, PointOfInterestType POI);

    public Optional<BlockLocating.Rectangle> frontiers_1_21x$_createCragsPortal(BlockPos pos);
}
