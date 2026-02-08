package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

// Copies Cake class but doesn't actually inherit due to weirdness with other mods
public class BeefWellingtonBlock extends Block
{
    public static final MapCodec<BeefWellingtonBlock> CODEC = simpleCodec(BeefWellingtonBlock::new);
    public static final int MAX_BITES = 6;
    public static final IntegerProperty BITES = BlockStateProperties.BITES;
    public static final int DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
    protected static final VoxelShape[] BITES_TO_SHAPE = new VoxelShape[]
    {
            Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(3.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(5.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(7.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(9.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(11.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.box(13.0, 0.0, 1.0, 15.0, 8.0, 15.0)
    };

    @Override
    public MapCodec<BeefWellingtonBlock> codec() {
        return CODEC;
    }

    public BeefWellingtonBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return BITES_TO_SHAPE[state.getValue(BITES)];
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        if (world.isClientSide)
        {
            if (tryEat(world, pos, state, player).consumesAction())
            {
                return InteractionResult.SUCCESS;
            }

            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())
            {
                return InteractionResult.CONSUME;
            }
        }

        return tryEat(world, pos, state, player);
    }

    protected static InteractionResult tryEat(LevelAccessor world, BlockPos pos, BlockState state, Player player)
    {
        if (!player.canEat(false))
        {
            return InteractionResult.PASS;
        }
        else
        {
            //player.incrementStat(Stats.EAT_CAKE_SLICE);
            player.getFoodData().eat(6, 1.5F);
            int i = state.getValue(BITES);
            world.gameEvent(player, GameEvent.EAT, pos);

            if (Frontiers.BOUNTIFUL_FARES_LOADED)
            {
                world.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.BLOCKS, 0.5f, 1.0f);
                if (state.getValue(BlockStateProperties.BITES) == 6)
                {
                    world.playSound(null, pos, SoundEvents.PLAYER_BURP, SoundSource.BLOCKS, 0.5f, 1.0f);
                }

                MobEffect ENRICHMENT = BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "enrichment"));
                Holder<MobEffect> ENRICHMENT_REG = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ENRICHMENT);

                player.addEffect(new MobEffectInstance(ENRICHMENT_REG, 600, 0, true, true));
            }

            if (i < 6)
            {
                world.setBlock(pos, state.setValue(BITES, Integer.valueOf(i + 1)), Block.UPDATE_ALL);
            }
            else
            {
                world.removeBlock(pos, false);
                world.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos
    )
    {
        return direction == Direction.DOWN && !state.canSurvive(world, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        return world.getBlockState(pos.below()).isSolid();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(BITES);
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos)
    {
        return getComparatorOutput(state.getValue(BITES));
    }

    public static int getComparatorOutput(int bites)
    {
        return (7 - bites) * 2;
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state)
    {
        return true;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type)
    {
        return false;
    }
}
