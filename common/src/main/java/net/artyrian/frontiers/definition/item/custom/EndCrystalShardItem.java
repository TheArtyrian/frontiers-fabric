package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.sound.FRSounds;
import net.artyrian.frontiers.reg.content.FRStatusEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;

public class EndCrystalShardItem extends Item
{
    public EndCrystalShardItem(Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemStack = user.getItemInHand(hand);

        if (!world.isClientSide)
        {
            VectorEventSync.Dual.fireEvent(world, new Vec3(user.getX(), user.getY(0.5), user.getZ()), user.position(), FRLevelEvents.Dual.END_CRYSTAL_SHARD, 0);

            world.playSound(null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    FRSounds.END_CRYSTAL_SHARD_USE.get(),
                    SoundSource.PLAYERS,
                    3.0F,
                    1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );

            ServerPlayer player = (ServerPlayer) user;
            CriteriaTriggers.CONSUME_ITEM.trigger(player, itemStack);
            player.awardStat(Stats.ITEM_USED.get(this));

            itemStack.consume(1, user);
            player.getCooldowns().addCooldown(this, 200);

            player.addEffect(
                    new MobEffectInstance(FRStatusEffects.QUICK_FLIGHT, 200, 0, false, true)
            );
        }

        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}
