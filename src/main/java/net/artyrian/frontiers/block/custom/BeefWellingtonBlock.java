package net.artyrian.frontiers.block.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public class BeefWellingtonBlock extends CakeBlock
{
    public BeefWellingtonBlock(Settings settings)
    {
        super(settings);
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
}
