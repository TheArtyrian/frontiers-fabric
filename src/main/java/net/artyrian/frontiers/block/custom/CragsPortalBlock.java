package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.entity.portal.CragsPortalBlockEntity;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.mixin.world.PortalForcerMixin;
import net.artyrian.frontiers.mixin_interfaces.PortalForcerInterface;
import net.artyrian.frontiers.particle.ModParticle;
import net.artyrian.frontiers.world.poi.ModPointOfInterest;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EndPortalBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.PortalForcer;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CragsPortalBlock extends BlockWithEntity implements Portal
{
    public static final MapCodec<CragsPortalBlock> CODEC = createCodec(CragsPortalBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 6.0, 0.0, 16.0, 12.0, 16.0);

    public CragsPortalBlock(Settings settings) { super(settings); }

    @Override
    protected MapCodec<CragsPortalBlock> getCodec() { return CODEC; }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return new CragsPortalBlockEntity(pos, state); }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return SHAPE; }
    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) { return ItemStack.EMPTY; }
    @Override
    protected boolean canBucketPlace(BlockState state, Fluid fluid) { return false; }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (
                entity.canUsePortals(false) &&
                (world.getRegistryKey() == ModDimension.CRAGS_LEVEL_KEY || world.getRegistryKey() == World.NETHER)
        )
        {
            entity.tryUsePortal(this, pos);
        }
    }

    @Nullable
    @Override
    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos)
    {
        RegistryKey<World> registryKey = world.getRegistryKey() == ModDimension.CRAGS_LEVEL_KEY ? World.NETHER : ModDimension.CRAGS_LEVEL_KEY;
        ServerWorld serverWorld = world.getServer().getWorld(registryKey);
        if (serverWorld == null) {
            return null;
        } else {
            boolean bl = serverWorld.getRegistryKey() == ModDimension.CRAGS_LEVEL_KEY;
            WorldBorder worldBorder = serverWorld.getWorldBorder();
            double d = DimensionType.getCoordinateScaleFactor(world.getDimension(), serverWorld.getDimension());
            BlockPos blockPos = worldBorder.clamp((double)pos.getX() * d, (double)pos.getY(), (double)pos.getZ() * d);
            return this.getOrCreateExitPortalTarget(serverWorld, entity, pos, blockPos, bl, worldBorder);
        }
    }

    @Nullable
    private TeleportTarget getOrCreateExitPortalTarget(
            ServerWorld world, Entity entity, BlockPos pos, BlockPos scaledPos, boolean inNether, WorldBorder worldBorder
    )
    {
        Optional<BlockPos> optional = ((PortalForcerInterface) world.getPortalForcer()).frontiers_1_21x$getPortalAdv(scaledPos, 1, worldBorder, ModPointOfInterest.CRAGS_PORTAL);
        BlockLocating.Rectangle rectangle;
        TeleportTarget.PostDimensionTransition postDimensionTransition;

        if (optional.isPresent())
        {
            BlockPos blockPos = optional.get();
            BlockState blockState = world.getBlockState(blockPos);
            rectangle = BlockLocating.getLargestRectangle(
                    blockPos, Direction.Axis.X, 21, Direction.Axis.Y, 21, posx -> world.getBlockState(posx) == blockState
            );
            postDimensionTransition = TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(entityx -> entityx.addPortalChunkTicketAt(blockPos));
        }
        else
        {
            Optional<BlockLocating.Rectangle> optional2 = ((PortalForcerInterface) world.getPortalForcer()).frontiers_1_21x$_createCragsPortal(scaledPos);

            if (optional2.isEmpty())
            {
                Frontiers.LOGGER.error("[FRONTIERS] Unable to create a Crags portal! Is the target out of the world border? :(");
                return null;
            }

            rectangle = optional2.get();
            postDimensionTransition = TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(TeleportTarget.ADD_PORTAL_CHUNK_TICKET);
        }

        return this.getExitPortalTarget(world, rectangle, scaledPos.toCenterPos(), entity, postDimensionTransition);
    }

    private TeleportTarget getExitPortalTarget(
            ServerWorld world,
            BlockLocating.Rectangle exitPortalRectangle,
            Vec3d posInPortal,
            Entity entity,
            TeleportTarget.PostDimensionTransition postDimensionTransition
    )
    {
        return new TeleportTarget(
                world,
                posInPortal,
                entity.getVelocity(),
                entity.getYaw(),
                entity.getPitch(),
                postDimensionTransition
        );
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random)
    {
        double d = (double)pos.getX() + random.nextDouble();
        double e = (double)pos.getY() + 0.8;
        double f = (double)pos.getZ() + random.nextDouble();
        world.addParticle(ModParticle.VEX_CHARGE_PARTICLE_R, d, e, f, 0.0, 0.4, 0.0);
        d = (double)pos.getX() + random.nextDouble();
        e = (double)pos.getY() + 0.8;
        f = (double)pos.getZ() + random.nextDouble();
        world.addParticle(ModParticle.VEX_CHARGE_PARTICLE_LR, d, e, f, 0.0, 0.2, 0.0);
    }
}
