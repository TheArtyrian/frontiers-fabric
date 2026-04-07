package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.intf.OnyxMealableBlock;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.property.FRDamageType;
import net.artyrian.frontiers.reg.content.FRParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;

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

        if (attemptMeal(stack, world, blockPos))
        {
            if (!world.isClientSide)
            {
                if (player != null) player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                VectorEventSync.Local.fireEvent(world, blockPos, FRLevelEvents.Local.ONYX_MEAL, 15);
            }
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
                            FRDamageType.of(world, FRDamageType.APPLEDOGGED),
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
        return InteractionResult.PASS;
    }

    public static boolean attemptMeal(ItemStack stack, Level level, BlockPos pos)
    {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof OnyxMealableBlock mealable && mealable.isValidOnyxMealFRTarget(level, pos, state))
        {
            if (level instanceof ServerLevel server)
            {
                mealable.performOnyxMealFRAction(server, server.random, pos, state);
                stack.shrink(1);
            }
            return true;
        }
        return false;
    }

    public static void createBadParticles(LevelAccessor level, BlockPos pos, int amount)
    {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.getBlock() instanceof OnyxMealableBlock mealable)
        {
            mealable.createOnyxMealFRParticles(level, pos, amount);
        }
        else if (blockstate.is(Blocks.WATER)) ParticleUtils.spawnParticles(level, pos, amount * 3, 3.0, 1.0, false, FRParticles.WITHER_GLINT.get());
    }
}
