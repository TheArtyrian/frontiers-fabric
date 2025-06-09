package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GoldenEggItem extends Item
{
    public GoldenEggItem(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);

        for (int i = 0; i < 12; i++)
        {
            world
                    .addParticle(
                            new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack),
                            user.getX(),
                            user.getY() + 1.0D,
                            user.getZ(),
                            ((double) world.random.nextFloat() - 0.5) * 0.8,
                            ((double) world.random.nextFloat() - 0.5) * 0.8,
                            ((double) world.random.nextFloat() - 0.5) * 0.8
                    );
        }

        user.playSound(ModSounds.GOLDEN_EGG_USE,1.0F, 1.0F);

        if (!world.isClient)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) user;
            Criteria.CONSUME_ITEM.trigger(player, itemStack);
            player.incrementStat(Stats.USED.getOrCreateStat(this));

            itemStack.decrementUnlessCreative(1, user);

            player.addStatusEffect(
                    new StatusEffectInstance(ModStatusEffects.ALLUREMENT, 1200, 0, false, true)
            );
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
