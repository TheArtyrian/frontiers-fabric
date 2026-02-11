package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.ModDamageType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class OnyxMealItem extends Item
{
    private static final ExplosionDamageCalculator APPLEDOG_LOL = new ExplosionDamageCalculator();

    public OnyxMealItem(Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState state = world.getBlockState(blockPos);
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (player instanceof Player)
        {
            if (state.is(Blocks.NETHER_WART) || state.is(ModBlocks.WARPED_WART.get()))
            {
                int i = state.getValue(NetherWartBlock.AGE);
                if (i < 3)
                {
                    if (!world.isClientSide)
                    {
                        if (world.random.nextFloat() > 0.5F)
                        {
                            state = state.setValue(NetherWartBlock.AGE, i + 1);
                            world.setBlock(blockPos, state, Block.UPDATE_CLIENTS);
                        }

                        player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                        world.levelEvent(LevelEvent.PARTICLES_AND_SOUND_PLANT_GROWTH, blockPos, 15);

                        stack.consume(1, player);
                    }
                    createParticles(world, blockPos, 15);
                    return InteractionResult.sidedSuccess(world.isClientSide);
                }
            }
            else if (state.is(Blocks.GRASS_BLOCK))
            {
                if (!world.isClientSide) weedKiller((ServerLevel) world, player, blockPos, stack, world.getRandom());
                createBadParticles(world, blockPos, 5);
                world.playLocalSound(blockPos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
            else if (Frontiers.BOUNTIFUL_FARES_LOADED && (Frontiers.APPLEDOG_LOADED || Frontiers.AEU_LOADED) && Frontiers.CONFIG.doAppledogCompat())
            {
                boolean appledog_block = state.is(BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.APPLEDOG_ID, "appledog_block")));
                boolean aeu_block = state.is(BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.AEU_ID, "appledog_block")));
                if (aeu_block || appledog_block)
                {
                    world.playLocalSound(blockPos, SoundEvents.WOLF_DEATH, SoundSource.BLOCKS, 1.0F, 1.0F, false);

                    if (!world.isClientSide)
                    {
                        player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                        stack.consume(1, player);

                        world.removeBlock(blockPos, false);

                        player.level().explode(
                                null,
                                ModDamageType.of(world, ModDamageType.APPLEDOGGED),
                                null,
                                blockPos.getX(),
                                blockPos.getY(),
                                blockPos.getZ(),
                                5.0F,
                                true,
                                Level.ExplosionInteraction.BLOCK
                        );
                    }

                    return InteractionResult.sidedSuccess(world.isClientSide);
                }
            }
        }

        return InteractionResult.PASS;
    }

    public static void createParticles(LevelAccessor world, BlockPos pos, int count) {
        BlockState blockState = world.getBlockState(pos);
        ParticleUtils.spawnParticles(world, pos, count, 1.0, 1.0, false, ParticleTypes.HAPPY_VILLAGER);
    }

    public static void createBadParticles(LevelAccessor world, BlockPos pos, int count) {
        BlockState blockState = world.getBlockState(pos);
        ParticleUtils.spawnParticles(world, pos, count, 1.0, 1.0, false, ParticleTypes.ANGRY_VILLAGER);
    }

    private void weedKiller(ServerLevel world, Player player, BlockPos blockPos, ItemStack stack, RandomSource random)
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
                if (!world.getBlockState(blockPos2.below()).is(Blocks.GRASS_BLOCK) || world.getBlockState(blockPos2).isCollisionShapeFullBlock(world, blockPos2))
                {
                    continue goto_start;
                }
            }

            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.is(ModTags.Blocks.ONYX_MEAL_DECAYABLE))
            {
                world.destroyBlock(blockPos2, true);
                //ParticleUtil.spawnParticlesAround(world, blockPos2, 2, 1.0, 1.0, false, ParticleTypes.ANGRY_VILLAGER);
            }
        }
    }
}
