package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.misc.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.phys.BlockHitResult;
import java.util.List;
import java.util.Map;

public class NetherReactorBlockLol extends Block
{
    public static final MapCodec<NetherReactorBlockLol> CODEC = simpleCodec(NetherReactorBlockLol::new);
    public static final IntegerProperty ACTIVE_POWER = ModBlockProperties.ACTIVE_POWER;
    public static final Map<Block, Block> VALID_BLOCKS = Map.of(
            Blocks.GRASS_BLOCK, Blocks.CRIMSON_NYLIUM,
            Blocks.DIRT, Blocks.NETHERRACK,
            Blocks.STONE, Blocks.BLACKSTONE,
            Blocks.AMETHYST_BLOCK, Blocks.NETHER_QUARTZ_ORE,
            Blocks.OBSIDIAN, ModBlocks.GLOWING_OBSIDIAN.get(),
            Blocks.GOLD_ORE, Blocks.NETHER_GOLD_ORE,
            Blocks.DEEPSLATE_GOLD_ORE, Blocks.NETHER_GOLD_ORE,
            Blocks.SAND, Blocks.SOUL_SAND,
            Blocks.RED_SAND, Blocks.SOUL_SAND
    );

    public NetherReactorBlockLol(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE_POWER, 0));
    }

    @Override
    public MapCodec<NetherReactorBlockLol> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE_POWER);
    }

    @Override
    protected void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify)
    {
        world.scheduleTick(pos, this, 20);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        if (world.isClientSide)
        {
            return InteractionResult.CONSUME;
        }
        else
        {
            // Now can check
            if (world.dimensionTypeRegistration().is(BuiltinDimensionTypes.OVERWORLD_EFFECTS))
            {
                if (state.getValue(ACTIVE_POWER) < 1)
                {
                    // Horrible code shut up
                    boolean is_valid = true;
                    List<BlockPos> AIR_LIST = List.of(
                            pos.offset(-1, 1, -1),
                            pos.offset(-1, 1, 1),
                            pos.offset(1, 1, -1),
                            pos.offset(1, 1, 1),
                            pos.offset(0, 0, -1),
                            pos.offset(0, 0, 1),
                            pos.offset(-1, 0, 0),
                            pos.offset(1, 0, 0)
                    );
                    List<BlockPos> GOLD_LIST = List.of(
                            pos.offset(-1, -1, -1),
                            pos.offset(-1, -1, 1),
                            pos.offset(1, -1, -1),
                            pos.offset(1, -1, 1)
                    );
                    List<BlockPos> CORE_LIST = List.of(
                            pos.offset(0, 0, 0)    // lmfao
                    );
                    for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1)))
                    {
                        Block blockcheck = Blocks.BLACKSTONE;
                        boolean check_air = false;

                        if (CORE_LIST.contains(blockPos)) blockcheck = ModBlocks.STRANGE_CORE.get();
                        else if (AIR_LIST.contains(blockPos)) check_air = true;
                        else if (GOLD_LIST.contains(blockPos)) blockcheck = ModBlocks.MOURNING_GOLD_BLOCK.get();

                        boolean checker = (check_air) ? world.getBlockState(blockPos).isAir() : world.getBlockState(blockPos).is(blockcheck);

                        if (!checker)
                        {
                            is_valid = false;
                            //Frontiers.LOGGER.info(blockPos.toString());
                            break;
                        }
                    }

                    if (is_valid)
                    {
                        player.displayClientMessage(Component.translatable("block.frontiers.strange_core.active"), true);
                        world.setBlock(pos, state.setValue(ACTIVE_POWER, 1), Block.UPDATE_CLIENTS);
                        surroundInEvilFreakingObsidian(pos, world);
                    }
                    else
                    {
                        if (isPEType(pos, world)) player.displayClientMessage(Component.translatable("block.frontiers.strange_core.incorrect_funny"), true);
                        else player.displayClientMessage(Component.translatable("block.frontiers.strange_core.incorrect"), true);
                    }
                }
            }
            else
            {
                player.displayClientMessage(Component.translatable("block.frontiers.strange_core.not_overworld"), true);
            }
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        if (state.getValue(ACTIVE_POWER) == 1)
        {
            for (BlockPos blockPos : BlockPos.randomInCube(random, 128, pos, 16))
            {
                Block here = world.getBlockState(blockPos).getBlock();
                if (VALID_BLOCKS.containsKey(here))
                {
                    Block kill_me = VALID_BLOCKS.get(here);
                    world.setBlockAndUpdate(blockPos, kill_me.defaultBlockState());
                }
            }

            if (checkForDeath(pos, world)) killCore(pos, world, state);
        }
        world.scheduleTick(pos, this, 20);
    }

    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player)
    {
        if (state.getValue(ACTIVE_POWER) == 1) killCore(pos, world, state);
        return super.playerWillDestroy(world, pos, state, player);
    }

    // Quick method that wraps all valid blocks
    private void surroundInEvilFreakingObsidian(BlockPos pos, Level world)
    {
        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1)))
        {
            if (!world.getBlockState(blockPos).isAir() && !world.getBlockState(blockPos).is(ModBlocks.STRANGE_CORE.get()))
            {
                world.setBlockAndUpdate(blockPos, ModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
            }
        }
    }

    // Check if the core should DIE :ganon:
    private boolean checkForDeath(BlockPos pos, Level world)
    {
        List<BlockPos> AIR_LIST = List.of(
                pos.offset(-1, 1, -1),
                pos.offset(-1, 1, 1),
                pos.offset(1, 1, -1),
                pos.offset(1, 1, 1),
                pos.offset(0, 0, -1),
                pos.offset(0, 0, 1),
                pos.offset(-1, 0, 0),
                pos.offset(1, 0, 0)
        );
        List<BlockPos> CORE_LIST = List.of(
                pos.offset(0, 0, 0)    // lmfao
        );

        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1)))
        {
            boolean check_air = (AIR_LIST.contains(blockPos));
            Block blockcheck = (CORE_LIST.contains(blockPos)) ? ModBlocks.STRANGE_CORE.get() : ModBlocks.GLOWING_OBSIDIAN.get();
            boolean checker = check_air || world.getBlockState(blockPos).is(blockcheck);

            if (!checker) return true;
        }
        return false;
    }

    // Destroy all core parts
    private void killCore(BlockPos pos, Level world, BlockState state)
    {
        world.setBlock(pos, state.setValue(ACTIVE_POWER, 2), Block.UPDATE_CLIENTS);
        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1)))
        {
            if (!world.getBlockState(blockPos).isAir() && world.getBlockState(blockPos).is(ModBlocks.GLOWING_OBSIDIAN.get()))
            {
                world.setBlockAndUpdate(blockPos, Blocks.OBSIDIAN.defaultBlockState());
            }
        }
    }

    // Check if the recipe is the og PE type
    private boolean isPEType(BlockPos pos, Level world)
    {
        List<BlockPos> AIR_LIST = List.of(
                pos.offset(-1, 1, -1),
                pos.offset(-1, 1, 1),
                pos.offset(1, 1, -1),
                pos.offset(1, 1, 1),
                pos.offset(0, 0, -1),
                pos.offset(0, 0, 1),
                pos.offset(-1, 0, 0),
                pos.offset(1, 0, 0)
        );
        List<BlockPos> GOLD_LIST = List.of(
                pos.offset(-1, -1, -1),
                pos.offset(-1, -1, 1),
                pos.offset(1, -1, -1),
                pos.offset(1, -1, 1)
        );
        List<BlockPos> CORE_LIST = List.of(
                pos.offset(0, 0, 0)    // lmfao
        );
        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1)))
        {
            Block blockcheck = Blocks.COBBLESTONE;
            boolean check_air = false;

            if (CORE_LIST.contains(blockPos)) blockcheck = ModBlocks.STRANGE_CORE.get();
            else if (AIR_LIST.contains(blockPos)) check_air = true;
            else if (GOLD_LIST.contains(blockPos)) blockcheck = Blocks.GOLD_BLOCK;

            boolean checker = (check_air) ? world.getBlockState(blockPos).isAir() : world.getBlockState(blockPos).is(blockcheck);

            if (!checker) return false;
        }
        return true;
    }
}
