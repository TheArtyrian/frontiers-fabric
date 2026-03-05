package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;

public class SnowMeltItem extends Item
{
    public SnowMeltItem(Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand)
    {
        if (entity instanceof SnowGolem golem && golem.isAlive())
        {
            VectorEventSync.Local.fireEvent(golem.level(), golem.blockPosition(), FRLevelEvents.Local.SNOW_MELT, 15);
            golem.hurt(golem.damageSources().onFire(), Float.MAX_VALUE);

            if (!user.level().isClientSide)
            {
                stack.shrink(1);
            }

            return InteractionResult.sidedSuccess(user.level().isClientSide);
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState state = world.getBlockState(blockPos);
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        BlockPos checkPos = (state.is(Blocks.SNOW)) ? blockPos.below() : blockPos;
        BlockState newState = world.getBlockState(checkPos);
        if (newState.isCollisionShapeFullBlock(world, checkPos))
        {
            if (!world.isClientSide)
            {
                snowMelter((ServerLevel) world, player, checkPos, stack, world.getRandom());
                VectorEventSync.Local.fireEvent(world, blockPos, FRLevelEvents.Local.SNOW_MELT, 15);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        }
        return InteractionResult.PASS;
    }

    public static void createParticles(LevelAccessor world, BlockPos pos, int count)
    {
        ParticleUtils.spawnParticles(world, pos.above(), count * 3, 3.0, 1.0, false, ModParticle.SNOW_GLINT.get());
    }

    private void snowMelter(ServerLevel world, Player player, BlockPos blockPos, ItemStack stack, RandomSource random)
    {
        player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        stack.consume(1, player);

        goto_start:
        for (int i = 0; i < 128; i++)
        {
            BlockPos blockPos2 = blockPos.above();

            for (int j = 0; j < i / 16; j++)
            {
                blockPos2 = blockPos2.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos2.below()).isCollisionShapeFullBlock(world, blockPos2.below()) || world.getBlockState(blockPos2).isCollisionShapeFullBlock(world, blockPos2))
                {
                    continue goto_start;
                }
            }

            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.is(Blocks.SNOW))
            {
                world.destroyBlock(blockPos2, false);
                VectorEventSync.Local.fireEvent(world, blockPos, FRLevelEvents.Local.SNOW_MELT, 2);
            }
        }
    }
}
