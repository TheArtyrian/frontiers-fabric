package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.misc.ModDamageType;
import net.artyrian.frontiers.sounds.ModSounds;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class SnowMeltItem extends Item
{
    public SnowMeltItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand)
    {
        if (entity instanceof SnowGolemEntity golem && golem.isAlive())
        {
            golem.getWorld().playSoundFromEntity(user, golem, ModSounds.SNOW_MELT_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            golem.damage(golem.getDamageSources().onFire(), Float.MAX_VALUE);

            if (!user.getWorld().isClient)
            {
                stack.decrement(1);
            }

            return ActionResult.success(user.getWorld().isClient);
        }

        return ActionResult.PASS;
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
            BlockPos checkPos = (state.isOf(Blocks.SNOW)) ? blockPos.down() : blockPos;
            BlockState newState = world.getBlockState(checkPos);
            if (newState.isFullCube(world, checkPos))
            {
                if (!world.isClient)
                {
                    snowMelter((ServerWorld) world, player, checkPos, stack, world.getRandom());
                    world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, checkPos, 15);
                }
                createParticles(world, checkPos, 15);
                world.playSoundAtBlockCenter(checkPos, ModSounds.SNOW_MELT_USE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                return ActionResult.success(world.isClient);
            }
        }

        return ActionResult.PASS;
    }

    public static void createParticles(WorldAccess world, BlockPos pos, int count)
    {
        BlockState blockState = world.getBlockState(pos);
        ParticleUtil.spawnParticlesAround(world, pos, count, 1.0, 1.0, false, ParticleTypes.HAPPY_VILLAGER);
    }

    private void snowMelter(ServerWorld world, PlayerEntity player, BlockPos blockPos, ItemStack stack, Random random)
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
                if (!world.getBlockState(blockPos2.down()).isFullCube(world, blockPos2.down()) || world.getBlockState(blockPos2).isFullCube(world, blockPos2))
                {
                    continue goto_start;
                }
            }

            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.isOf(Blocks.SNOW))
            {
                world.breakBlock(blockPos2, true);
            }
        }
    }
}
