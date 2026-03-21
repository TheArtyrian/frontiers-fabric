package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.CragsPortalBlockEntity;
import net.artyrian.frontiers.definition.util.CragsPortal;
import net.artyrian.frontiers.mixin_intf.PortalForceIntf;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.misc.ModDimension;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.artyrian.frontiers.reg.misc.ModPointOfInterest;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.networking.data.packets.VectorEventS2CPacket;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CragsPortalBlock extends BaseEntityBlock implements Portal
{
    public static final MapCodec<CragsPortalBlock> CODEC = simpleCodec(CragsPortalBlock::new);
    protected static final VoxelShape SHAPE = Block.box(0.0, 6.0, 0.0, 16.0, 12.0, 16.0);

    public static final DimensionTransition.PostDimensionTransition CRAGS_WOOSH = CragsPortalBlock::playWoosh;

    public CragsPortalBlock(Properties settings) { super(settings); }

    @Override protected MapCodec<CragsPortalBlock> codec() { return CODEC; }
    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new CragsPortalBlockEntity(pos, state); }
    @Override protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return SHAPE; }
    @Override public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state) { return ItemStack.EMPTY; }
    @Override protected boolean canBeReplaced(BlockState state, Fluid fluid) { return false; }

    @Override
    protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity)
    {
        if (entity.canUsePortal(false) && (world.dimension() == ModDimension.CRAGS_LEVEL_KEY || world.dimension() == Level.NETHER))
        {
            entity.setAsInsidePortal(this, pos);
        }
    }

    @Nullable
    @Override
    public DimensionTransition getPortalDestination(ServerLevel world, Entity entity, BlockPos pos)
    {
        ResourceKey<Level> registryKey = world.dimension() == ModDimension.CRAGS_LEVEL_KEY ? Level.NETHER : ModDimension.CRAGS_LEVEL_KEY;
        ServerLevel serverWorld = world.getServer().getLevel(registryKey);
        if (serverWorld == null) return null;
        else
        {
            boolean bl = serverWorld.dimension() == ModDimension.CRAGS_LEVEL_KEY;
            WorldBorder worldBorder = serverWorld.getWorldBorder();
            double d = DimensionType.getTeleportationScale(world.dimensionType(), serverWorld.dimensionType());
            BlockPos blockPos = worldBorder.clampToBounds(entity.getX() * d, entity.getY(), entity.getZ() * d);
            return this.getOrCreateExitPortalTarget(serverWorld, entity, pos, blockPos, bl, worldBorder);
        }
    }

    @Nullable
    private DimensionTransition getOrCreateExitPortalTarget(ServerLevel world, Entity entity, BlockPos pos, BlockPos scaledPos, boolean inNether, WorldBorder worldBorder)
    {
        Optional<BlockPos> optional = ((PortalForceIntf) world.getPortalForcer()).frontiers_1_21x$getPortalAdv(scaledPos, 1, worldBorder, ModPointOfInterest.CRAGS_PORTAL.get());
        BlockUtil.FoundRectangle rectangle;
        DimensionTransition.PostDimensionTransition postDimensionTransition;

        if (optional.isPresent())
        {
            BlockPos blockPos = optional.get();
            BlockState blockState = world.getBlockState(blockPos);
            rectangle = BlockUtil.getLargestRectangleAround(
                    blockPos, Direction.Axis.X, 21, Direction.Axis.Y, 21, posx -> world.getBlockState(posx) == blockState
            );
            postDimensionTransition = CRAGS_WOOSH.then(entityx -> entityx.placePortalTicket(blockPos));
        }
        else
        {
            Optional<BlockUtil.FoundRectangle> optional2 = ((PortalForceIntf) world.getPortalForcer()).frontiers_1_21x$_createCragsPortal(scaledPos);

            if (optional2.isEmpty())
            {
                Frontiers.LOGGER.error("[FRONTIERS] Unable to create a Crags portal! Is the target out of the world border? :(");
                return null;
            }

            rectangle = optional2.get();
            postDimensionTransition = CRAGS_WOOSH.then(DimensionTransition.PLACE_PORTAL_TICKET);
        }

        return this.getExitPortalTarget(world, rectangle, scaledPos.getCenter(), entity, postDimensionTransition);
    }

    private DimensionTransition getExitPortalTarget(
            ServerLevel world,
            BlockUtil.FoundRectangle exitPortalRectangle,
            Vec3 posInPortal,
            Entity entity,
            DimensionTransition.PostDimensionTransition postDimensionTransition
    )
    {
        return new DimensionTransition(
                world,
                posInPortal,
                entity.getDeltaMovement(),
                entity.getYRot(),
                entity.getXRot(),
                postDimensionTransition
        );
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random)
    {
        double d = (double)pos.getX() + random.nextDouble();
        double e = (double)pos.getY() + 0.8;
        double f = (double)pos.getZ() + random.nextDouble();

        double upwardX = 0.1 * random.nextInt(-1, 1);
        double upwardY = 0.1 * random.nextInt(1, 3);
        double upwardZ = 0.1 * random.nextInt(-1, 1);

        world.addParticle(ModParticle.VEX_CHARGE_PARTICLE_R, d, e, f, 0.0, 0.4, 0.0);
        d = (double)pos.getX() + random.nextDouble();
        e = (double)pos.getY() + 0.8;
        f = (double)pos.getZ() + random.nextDouble();
        world.addParticle(ModParticle.CRAG_SMOG.get(), d, e, f, upwardX, upwardY, upwardZ);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos)
    {
        Optional<BlockPos> checker = CragsPortal.checkForExistingPortal((Level) world, pos);
        return (checker.isEmpty()) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    private static void playWoosh(Entity entity)
    {
        if (entity instanceof ServerPlayer serverplayer) VectorEventSync.Local.fireToPlayer(serverplayer, FRLevelEvents.Local.CRAGS_TELEPORT, BlockPos.ZERO, 0);
    }
}
