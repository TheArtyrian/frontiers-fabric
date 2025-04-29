package net.artyrian.frontiers.mixin.world;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.mixin_interfaces.PortalForcerInterface;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.*;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.Heightmap;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.dimension.PortalForcer;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Comparator;
import java.util.Optional;

@Mixin(PortalForcer.class)
public abstract class PortalForcerMixin implements PortalForcerInterface
{
    @Shadow @Final private ServerWorld world;

    @Shadow protected abstract boolean isBlockStateValid(BlockPos.Mutable pos);

    @Shadow protected abstract boolean isValidPortalPos(BlockPos pos, BlockPos.Mutable temp, Direction portalDirection, int distanceOrthogonalToPortal);

    @Override
    public Optional<BlockPos> frontiers_1_21x$getPortalAdv(BlockPos pos, int scale, WorldBorder worldBorder, PointOfInterestType POI)
    {
        Optional<RegistryKey<PointOfInterestType>> KEYCHECKER = Registries.POINT_OF_INTEREST_TYPE.getKey(POI);
        RegistryKey<PointOfInterestType> keycheck = KEYCHECKER.orElse(PointOfInterestTypes.NETHER_PORTAL);

        PointOfInterestStorage pointOfInterestStorage = this.world.getPointOfInterestStorage();
        pointOfInterestStorage.preloadChunks(this.world, pos, scale);
        return pointOfInterestStorage.getInSquare(
                        poiType -> poiType.matchesKey(keycheck), pos, scale, PointOfInterestStorage.OccupationStatus.ANY
                )
                .map(PointOfInterest::getPos)
                .filter(worldBorder::contains)
                .min(Comparator.comparingDouble(blockPos2 -> (blockPos2.getSquaredDistance(pos))));
    }

    @Override
    public Optional<BlockLocating.Rectangle> frontiers_1_21x$_createCragsPortal(BlockPos pos)
    {
        WorldBorder worldBorder = this.world.getWorldBorder();
        boolean build = true;

        Box box = Box.enclosing(pos.add(-2, 0, -2), pos.add(2, 0, 2));
        if (!worldBorder.contains(box))
        {
            return Optional.empty();
        }
        for (BlockPos checker: BlockPos.iterate(pos.add(-2, 0, -2), pos.add(2, 0, 2)))
        {
            if (this.world.getBlockState(checker).isOf(ModBlocks.CRAGS_PORTAL)) build = false;
        }

        if (build)
        {
            for (BlockPos newpos : BlockPos.iterate(pos.add(-4, 8, -4), pos.add(4, 1, 4)))
            {
                this.world.setBlockState(newpos, Blocks.AIR.getDefaultState());
            }

            this.world.setBlockState(pos, ModBlocks.CRAGS_PORTAL.getDefaultState());
            this.world.setBlockState(pos.add(0, -1, 0), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
            for (int xi = -1; xi < 2; xi++)
            {
                if (xi != 0)
                {
                    this.world.setBlockState(pos.add(xi, 0, 0), ModBlocks.CRAGS_PORTAL.getDefaultState());
                    this.world.setBlockState(pos.add(xi, 0, 1), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
                    this.world.setBlockState(pos.add(xi, -1, 1), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
                    this.world.setBlockState(pos.add(xi, -1, 0), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
                    this.world.setBlockState(pos.add(xi, 0, -1), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
                    this.world.setBlockState(pos.add(xi, -1, -1), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());

                    this.world.setBlockState(pos.add(0, 0, xi), ModBlocks.CRAGS_PORTAL.getDefaultState());
                    this.world.setBlockState(pos.add(0, -1, xi), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
                }
            }

            this.world.setBlockState(pos.add(-2, 0, 0), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
            this.world.setBlockState(pos.add(-2, -1, 0), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
            this.world.setBlockState(pos.add(2, 0, 0), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
            this.world.setBlockState(pos.add(2, -1, 0), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
            this.world.setBlockState(pos.add(0, 0, -2), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
            this.world.setBlockState(pos.add(0, -1, -2), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
            this.world.setBlockState(pos.add(0, 0, 2), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
            this.world.setBlockState(pos.add(0, -1, 2), ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
        }

        return Optional.of(new BlockLocating.Rectangle(pos, 3, 3));
    }
}
