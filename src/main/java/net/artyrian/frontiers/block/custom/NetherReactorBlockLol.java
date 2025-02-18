package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.List;

public class NetherReactorBlockLol extends Block
{
    public static final MapCodec<NetherReactorBlockLol> CODEC = createCodec(NetherReactorBlockLol::new);
    public static final IntProperty ACTIVE_POWER = ModBlockProperties.ACTIVE_POWER;
    public static final List<Block> VALID_BLOCKS = List.of(
            Blocks.GRASS_BLOCK,
            Blocks.DIRT,
            Blocks.STONE,
            Blocks.AMETHYST_BLOCK,
            Blocks.OBSIDIAN,
            Blocks.GOLD_ORE,
            Blocks.DEEPSLATE_GOLD_ORE,
            Blocks.SAND,
            Blocks.RED_SAND
    );

    public NetherReactorBlockLol(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(ACTIVE_POWER, 0));
    }

    @Override
    public MapCodec<NetherReactorBlockLol> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE_POWER);
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify)
    {
        Frontiers.LOGGER.info("Scheduled a reactor core");
        world.scheduleBlockTick(pos, this, 20);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (world.isClient)
        {
            return ActionResult.CONSUME;
        }
        else
        {
            // Now can check
            if (world.getDimensionEntry().matchesId(DimensionTypes.OVERWORLD_ID))
            {
                if (state.get(ACTIVE_POWER) < 1)
                {
                    // Horrible code shut up
                    boolean is_valid = true;
                    List<BlockPos> AIR_LIST = List.of(
                            pos.add(-1, 1, -1),
                            pos.add(-1, 1, 1),
                            pos.add(1, 1, -1),
                            pos.add(1, 1, 1),
                            pos.add(0, 0, -1),
                            pos.add(0, 0, 1),
                            pos.add(-1, 0, 0),
                            pos.add(1, 0, 0)
                    );
                    List<BlockPos> GOLD_LIST = List.of(
                            pos.add(-1, -1, -1),
                            pos.add(-1, -1, 1),
                            pos.add(1, -1, -1),
                            pos.add(1, -1, 1)
                    );
                    List<BlockPos> CORE_LIST = List.of(
                            pos.add(0, 0, 0)    // lmfao
                    );
                    for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)))
                    {
                        Block blockcheck = Blocks.BLACKSTONE;
                        boolean check_air = false;

                        if (CORE_LIST.contains(blockPos)) blockcheck = ModBlocks.STRANGE_CORE;
                        else if (AIR_LIST.contains(blockPos)) check_air = true;
                        else if (GOLD_LIST.contains(blockPos)) blockcheck = ModBlocks.MOURNING_GOLD_BLOCK;

                        boolean checker = (check_air) ? world.getBlockState(blockPos).isAir() : world.getBlockState(blockPos).isOf(blockcheck);

                        if (!checker)
                        {
                            is_valid = false;
                            Frontiers.LOGGER.info(blockPos.toString());
                            break;
                        }
                    }

                    if (is_valid)
                    {
                        player.sendMessage(Text.translatable("block.frontiers.strange_core.active"), true);
                        world.setBlockState(pos, state.with(ACTIVE_POWER, 1), Block.NOTIFY_LISTENERS);
                        surroundInEvilFreakingObsidian(pos, world);
                    }
                    else
                    {
                        if (isPEType(pos, world)) player.sendMessage(Text.translatable("block.frontiers.strange_core.incorrect_funny"), true);
                        else player.sendMessage(Text.translatable("block.frontiers.strange_core.incorrect"), true);
                    }
                }
            }
            else
            {
                player.sendMessage(Text.translatable("block.frontiers.strange_core.not_overworld"), true);
            }
            return ActionResult.SUCCESS;
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if (state.get(ACTIVE_POWER) == 1)
        {
            for (BlockPos blockPos : BlockPos.iterateRandomly(random, 128, pos, 16))
            {
                Block here = world.getBlockState(blockPos).getBlock();
                if (VALID_BLOCKS.contains(here))
                {
                    Block kill_me = getValidBlockType(here);
                    world.setBlockState(blockPos, kill_me.getDefaultState());
                }
            }

            if (checkForDeath(pos, world)) killCore(pos, world, state);
        }
        world.scheduleBlockTick(pos, this, 20);
    }

    // Get block type.
    private Block getValidBlockType(Block type)
    {
        Block returner = Blocks.NETHERRACK;
        if (type == Blocks.GRASS_BLOCK) returner = Blocks.CRIMSON_NYLIUM;
        else if (type == Blocks.STONE) returner = Blocks.BLACKSTONE;
        else if (type == Blocks.AMETHYST_BLOCK) returner = Blocks.NETHER_QUARTZ_ORE;
        else if (type == Blocks.OBSIDIAN) returner = ModBlocks.GLOWING_OBSIDIAN;
        else if (type == Blocks.GOLD_ORE || type == Blocks.DEEPSLATE_GOLD_ORE) returner = Blocks.NETHER_GOLD_ORE;
        else if (type == Blocks.SAND || type == Blocks.RED_SAND) returner = Blocks.SOUL_SAND;
        return returner;
    }

    // Quick method that wraps all valid blocks
    private void surroundInEvilFreakingObsidian(BlockPos pos, World world)
    {
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)))
        {
            if (!world.getBlockState(blockPos).isAir() && !world.getBlockState(blockPos).isOf(ModBlocks.STRANGE_CORE))
            {
                world.setBlockState(blockPos, ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
            }
        }
    }

    // Check if the core should DIE :ganon:
    private boolean checkForDeath(BlockPos pos, World world)
    {
        List<BlockPos> AIR_LIST = List.of(
                pos.add(-1, 1, -1),
                pos.add(-1, 1, 1),
                pos.add(1, 1, -1),
                pos.add(1, 1, 1),
                pos.add(0, 0, -1),
                pos.add(0, 0, 1),
                pos.add(-1, 0, 0),
                pos.add(1, 0, 0)
        );
        List<BlockPos> CORE_LIST = List.of(
                pos.add(0, 0, 0)    // lmfao
        );

        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)))
        {
            boolean check_air = (AIR_LIST.contains(blockPos));
            Block blockcheck = (CORE_LIST.contains(blockPos)) ? ModBlocks.STRANGE_CORE : ModBlocks.GLOWING_OBSIDIAN;
            boolean checker = check_air || world.getBlockState(blockPos).isOf(blockcheck);

            if (!checker) return true;
        }
        return false;
    }

    // Destroy all core parts
    private void killCore(BlockPos pos, World world, BlockState state)
    {
        world.setBlockState(pos, state.with(ACTIVE_POWER, 2), Block.NOTIFY_LISTENERS);
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)))
        {
            if (!world.getBlockState(blockPos).isAir() && world.getBlockState(blockPos).isOf(ModBlocks.GLOWING_OBSIDIAN))
            {
                world.setBlockState(blockPos, Blocks.OBSIDIAN.getDefaultState());
            }
        }
    }

    // Check if the recipe is the og PE type
    private boolean isPEType(BlockPos pos, World world)
    {
        List<BlockPos> AIR_LIST = List.of(
                pos.add(-1, 1, -1),
                pos.add(-1, 1, 1),
                pos.add(1, 1, -1),
                pos.add(1, 1, 1),
                pos.add(0, 0, -1),
                pos.add(0, 0, 1),
                pos.add(-1, 0, 0),
                pos.add(1, 0, 0)
        );
        List<BlockPos> GOLD_LIST = List.of(
                pos.add(-1, -1, -1),
                pos.add(-1, -1, 1),
                pos.add(1, -1, -1),
                pos.add(1, -1, 1)
        );
        List<BlockPos> CORE_LIST = List.of(
                pos.add(0, 0, 0)    // lmfao
        );
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)))
        {
            Block blockcheck = Blocks.COBBLESTONE;
            boolean check_air = false;

            if (CORE_LIST.contains(blockPos)) blockcheck = ModBlocks.STRANGE_CORE;
            else if (AIR_LIST.contains(blockPos)) check_air = true;
            else if (GOLD_LIST.contains(blockPos)) blockcheck = Blocks.GOLD_BLOCK;

            boolean checker = (check_air) ? world.getBlockState(blockPos).isAir() : world.getBlockState(blockPos).isOf(blockcheck);

            if (!checker) return false;
        }
        return true;
    }
}
