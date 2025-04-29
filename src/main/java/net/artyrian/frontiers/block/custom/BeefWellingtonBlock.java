package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

// Copies Cake class but doesn't actually inherit due to weirdness with other mods
public class BeefWellingtonBlock extends Block
{
    public static final MapCodec<BeefWellingtonBlock> CODEC = createCodec(BeefWellingtonBlock::new);
    public static final int MAX_BITES = 6;
    public static final IntProperty BITES = Properties.BITES;
    public static final int DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
    protected static final VoxelShape[] BITES_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(3.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(5.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(7.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(9.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(13.0, 0.0, 1.0, 15.0, 8.0, 15.0)
    };

    @Override
    public MapCodec<BeefWellingtonBlock> getCodec() {
        return CODEC;
    }

    public BeefWellingtonBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BITES, 0));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return BITES_TO_SHAPE[state.get(BITES)];
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient)
        {
            if (tryEat(world, pos, state, player).isAccepted())
            {
                return ActionResult.SUCCESS;
            }

            if (player.getStackInHand(Hand.MAIN_HAND).isEmpty())
            {
                return ActionResult.CONSUME;
            }
        }

        return tryEat(world, pos, state, player);
    }

    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false))
        {
            return ActionResult.PASS;
        }
        else
        {
            //player.incrementStat(Stats.EAT_CAKE_SLICE);
            player.getHungerManager().add(6, 1.5F);
            int i = state.get(BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);

            if (Frontiers.BOUNTIFUL_FARES_LOADED)
            {
                world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 0.5f, 1.0f);
                if (state.get(Properties.BITES) == 6) {
                    world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.BLOCKS, 0.5f, 1.0f);
                }

                StatusEffect ENRICHMENT = Registries.STATUS_EFFECT.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "enrichment"));
                RegistryEntry<StatusEffect> ENRICHMENT_REG = Registries.STATUS_EFFECT.getEntry(ENRICHMENT);

                player.addStatusEffect(new StatusEffectInstance(ENRICHMENT_REG, 600, 0, true, true));
            }

            if (i < 6)
            {
                world.setBlockState(pos, state.with(BITES, Integer.valueOf(i + 1)), Block.NOTIFY_ALL);
            }
            else
            {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    )
    {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        return world.getBlockState(pos.down()).isSolid();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(BITES);
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos)
    {
        return getComparatorOutput(state.get(BITES));
    }

    public static int getComparatorOutput(int bites)
    {
        return (7 - bites) * 2;
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state)
    {
        return true;
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type)
    {
        return false;
    }
}
