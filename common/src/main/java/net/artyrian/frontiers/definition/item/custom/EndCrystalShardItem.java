package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.reg.content.ModSounds;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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

        for (int i = 0; i < 12; i++) {
            world
                    .addParticle(
                            new ItemParticleOption(ParticleTypes.ITEM, itemStack),
                            user.getX(),
                            user.getY() + 1.0D,
                            user.getZ(),
                            ((double)world.random.nextFloat() - 0.5) * 0.8,
                            ((double)world.random.nextFloat() - 0.5) * 0.8,
                            ((double)world.random.nextFloat() - 0.5) * 0.8
                    );

            world
                    .addParticle(
                            ParticleTypes.PORTAL,
                            user.getX(),
                            user.getY() + 1.0D,
                            user.getZ(),
                            ((double)world.random.nextFloat() - 0.5) * 0.8,
                            ((double)world.random.nextFloat() - 0.5) * 0.8,
                            ((double)world.random.nextFloat() - 0.5) * 0.8
                    );
        }

        world
                .addParticle(
                        ParticleTypes.EXPLOSION_EMITTER,
                        user.getX(),
                        user.getY() + 1.0D,
                        user.getZ(),
                        ((double)world.random.nextFloat() - 0.5) * 0.8,
                        ((double)world.random.nextFloat() - 0.5) * 0.8,
                        ((double)world.random.nextFloat() - 0.5) * 0.8
                );

        user.playSound(ModSounds.END_CRYSTAL_SHARD_USE.get(),3.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!world.isClientSide)
        {
            ServerPlayer player = (ServerPlayer) user;
            CriteriaTriggers.CONSUME_ITEM.trigger(player, itemStack);
            player.awardStat(Stats.ITEM_USED.get(this));

            itemStack.consume(1, user);
            player.getCooldowns().addCooldown(this, 200);

            player.addEffect(
                    new MobEffectInstance(ModStatusEffects.QUICK_FLIGHT, 200, 0, false, true)
            );
        }

        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}
