package net.artyrian.frontiers.mixin.level;

import net.artyrian.frontiers.mixin_intf.PortalForceIntf;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.PortalForcer;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Comparator;
import java.util.Optional;

@Mixin(PortalForcer.class)
public abstract class PortalForcerMixin implements PortalForceIntf
{
    @Shadow @Final private ServerLevel level;

    @Shadow protected abstract boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos);

    @Shadow protected abstract boolean canHostFrame(BlockPos pos, BlockPos.MutableBlockPos temp, Direction portalDirection, int distanceOrthogonalToPortal);

    @Override
    public Optional<BlockPos> frontiers_1_21x$getPortalAdv(BlockPos pos, int scale, WorldBorder worldBorder, PoiType POI)
    {
        Optional<ResourceKey<PoiType>> KEYCHECKER = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getResourceKey(POI);
        ResourceKey<PoiType> keycheck = KEYCHECKER.orElse(PoiTypes.NETHER_PORTAL);

        PoiManager pointOfInterestStorage = this.level.getPoiManager();
        pointOfInterestStorage.ensureLoadedAndValid(this.level, pos, scale);
        return pointOfInterestStorage.getInSquare(
                        poiType -> poiType.is(keycheck), pos, scale, PoiManager.Occupancy.ANY
                )
                .map(PoiRecord::getPos)
                .filter(worldBorder::isWithinBounds)
                .min(Comparator.comparingDouble(blockPos2 -> (blockPos2.distSqr(pos))));
    }

    @Override
    public Optional<BlockUtil.FoundRectangle> frontiers_1_21x$_createCragsPortal(BlockPos pos)
    {
        WorldBorder worldBorder = this.level.getWorldBorder();
        boolean build = true;

        AABB box = AABB.encapsulatingFullBlocks(pos.offset(-2, 0, -2), pos.offset(2, 0, 2));
        if (!worldBorder.isWithinBounds(box))
        {
            return Optional.empty();
        }
        for (BlockPos checker: BlockPos.betweenClosed(pos.offset(-2, 0, -2), pos.offset(2, 0, 2)))
        {
            if (this.level.getBlockState(checker).is(ModBlocks.CRAGS_PORTAL.get())) build = false;
        }

        if (build)
        {
            for (BlockPos outerpos : BlockPos.betweenClosed(pos.offset(-5, 9, -5), pos.offset(5, 0, 5)))
            {
                if (
                        this.level.getFluidState(outerpos).is(Fluids.FLOWING_LAVA) || this.level.getFluidState(outerpos).is(Fluids.LAVA)
                )
                {
                    this.level.setBlockAndUpdate(outerpos, Blocks.MAGMA_BLOCK.defaultBlockState());
                }
            }

            for (BlockPos newpos : BlockPos.betweenClosed(pos.offset(-4, 8, -4), pos.offset(4, 1, 4)))
            {
                this.level.setBlockAndUpdate(newpos, Blocks.AIR.defaultBlockState());
            }

            this.level.setBlockAndUpdate(pos.offset(0, -1, 0), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
            for (int xi = -1; xi < 2; xi++)
            {
                if (xi != 0)
                {
                    this.level.setBlockAndUpdate(pos.offset(xi, 0, 1), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
                    this.level.setBlockAndUpdate(pos.offset(xi, -1, 1), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
                    this.level.setBlockAndUpdate(pos.offset(xi, -1, 0), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
                    this.level.setBlockAndUpdate(pos.offset(xi, 0, -1), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
                    this.level.setBlockAndUpdate(pos.offset(xi, -1, -1), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());

                    this.level.setBlockAndUpdate(pos.offset(0, -1, xi), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
                }
            }

            this.level.setBlockAndUpdate(pos.offset(-2, 0, 0), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(-2, -1, 0), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(2, 0, 0), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(2, -1, 0), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(0, 0, -2), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(0, -1, -2), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(0, 0, 2), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(0, -1, 2), ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());

            this.level.setBlockAndUpdate(pos, ModBlocks.CRAGS_PORTAL.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(0, 0, -1), ModBlocks.CRAGS_PORTAL.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(0, 0, 1), ModBlocks.CRAGS_PORTAL.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(-1, 0, 0), ModBlocks.CRAGS_PORTAL.get().defaultBlockState());
            this.level.setBlockAndUpdate(pos.offset(1, 0, 0), ModBlocks.CRAGS_PORTAL.get().defaultBlockState());
        }

        return Optional.of(new BlockUtil.FoundRectangle(pos, 3, 3));
    }
}
