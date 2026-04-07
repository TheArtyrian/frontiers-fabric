package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.definition.block.entity.TowerSpawnerBlockEntity;
import net.artyrian.frontiers.definition.block.entity.data.TowerSpawner;
import net.artyrian.frontiers.reg.content.FRBlockEntities;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.property.FRBlockProperties;
import net.artyrian.frontiers.reg.misc.FRCriteria;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiConsumer;

public class TowerSpawnerBlock extends BaseEntityBlock implements EntityBlock
{
    public static final BooleanProperty ENRAGED = FRBlockProperties.ENRAGED;
    public static final BooleanProperty DEFEATED = FRBlockProperties.DEFEATED;

    private static final VoxelShape BASIC = Shapes.block();
    private static final VoxelShape HOLLOW = Shapes.join(BASIC, Block.box(1.0, 3.0, 1.0, 15.0, 16.0, 15.0), BooleanOp.ONLY_FIRST);

    public static final MapCodec<TowerSpawnerBlock> CODEC = TowerSpawnerBlock.simpleCodec(TowerSpawnerBlock::new);

    public TowerSpawnerBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(ENRAGED, false)
                .setValue(DEFEATED, false)
        );
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide
                ? createTickerHelper(type, FRBlockEntities.TOWER_SPAWNER.get(), TowerSpawnerBlockEntity::tickClient)
                : createTickerHelper(type, FRBlockEntities.TOWER_SPAWNER.get(), TowerSpawnerBlockEntity::tickServer);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return CODEC;
    }

    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new TowerSpawnerBlockEntity(pos, state); }

    @Override
    protected RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return (state.getValue(DEFEATED)) ? HOLLOW : BASIC;
    }

    @Override
    protected VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return BASIC;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return (state.getValue(DEFEATED)) ? HOLLOW : BASIC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder
                .add(ENRAGED)
                .add(DEFEATED);
    }

    @Override
    protected void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer)
    {
        if (explosion.getBlockInteraction() != Explosion.BlockInteraction.TRIGGER_BLOCK && !state.getValue(ENRAGED) && !state.getValue(DEFEATED))
        {
            enrage(level, pos, state);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
    {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        Spawner.appendHoverText(stack, tooltipComponents, "SpawnData");
    }

    private static void enrage(Level level, BlockPos pos, BlockState state)
    {
        level.setBlock(pos, FRBlocks.TOWER_SPAWNER.get().defaultBlockState().setValue(ENRAGED, true), 3);
        if (!level.isClientSide)
        {
            VectorEventSync.Local.fireEvent(level, pos, FRLevelEvents.Local.TOWER_SPAWNER_ENRAGE, 0);
            List<ServerPlayer> playerList = level.getEntitiesOfClass(ServerPlayer.class, new AABB(
                    Vec3.atCenterOf(pos.offset(TowerSpawner.AABB_OFFSET_MIN)), Vec3.atCenterOf(pos.offset(TowerSpawner.AABB_OFFSET_MAX)))
            );

            for (ServerPlayer player : playerList)
            {
                FRCriteria.ENRAGE_TOWER_SPAWNER.get().trigger(player);
            }
        }
    }
}
