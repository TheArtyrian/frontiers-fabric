package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class OnyxMealItem extends Item
{
    public OnyxMealItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState state = world.getBlockState(blockPos);
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();

        if (player instanceof PlayerEntity)
        {
            if (state.isOf(Blocks.NETHER_WART) || state.isOf(ModBlocks.WARPED_WART))
            {
                int i = state.get(NetherWartBlock.AGE);
                if (i < 3)
                {
                    if (!world.isClient)
                    {
                        state = state.with(NetherWartBlock.AGE, i + 1);
                        world.setBlockState(blockPos, state, Block.NOTIFY_LISTENERS);

                        player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
                        world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos, 15);

                        stack.decrementUnlessCreative(1, player);
                    }
                    createParticles(world, blockPos, 15);
                    return ActionResult.success(world.isClient);
                }
            }
            else if (state.isOf(Blocks.GRASS_BLOCK))
            {
                if (!world.isClient) weedKiller((ServerWorld) world, player, blockPos, stack, world.getRandom());
                createBadParticles(world, blockPos, 15);
                world.playSoundAtBlockCenter(blockPos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                return ActionResult.success(world.isClient);
            }
        }

        return ActionResult.PASS;
    }

    public static void createParticles(WorldAccess world, BlockPos pos, int count) {
        BlockState blockState = world.getBlockState(pos);
        ParticleUtil.spawnParticlesAround(world, pos, count, 1.0, 1.0, false, ParticleTypes.HAPPY_VILLAGER);
    }

    public static void createBadParticles(WorldAccess world, BlockPos pos, int count) {
        BlockState blockState = world.getBlockState(pos);
        ParticleUtil.spawnParticlesAround(world, pos, count, 1.0, 1.0, false, ParticleTypes.ANGRY_VILLAGER);
    }

    private void weedKiller(ServerWorld world, PlayerEntity player, BlockPos blockPos, ItemStack stack, Random random)
    {
        player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
        stack.decrementUnlessCreative(1, player);

        goto_start:
        for (int i = 0; i < 128; i++)
        {
            BlockPos blockPos2 = blockPos.up();

            for (int j = 0; j < i / 16; j++)
            {
                blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos2.down()).isOf(Blocks.GRASS_BLOCK) || world.getBlockState(blockPos2).isFullCube(world, blockPos2))
                {
                    continue goto_start;
                }
            }

            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.isIn(BlockTags.REPLACEABLE))
            {
                world.breakBlock(blockPos2, true);
            }
        }
    }
}
