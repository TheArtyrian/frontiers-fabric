package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.effect.types.ModStatusEffect;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

public class EndCrystalShardItem extends Item
{
    public EndCrystalShardItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);

        for (int i = 0; i < 12; i++) {
            world
                    .addParticle(
                            new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack),
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

        user.playSound(ModSounds.END_CRYSTAL_SHARD_USE,3.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!world.isClient)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) user;
            Criteria.CONSUME_ITEM.trigger(player, itemStack);
            player.incrementStat(Stats.USED.getOrCreateStat(this));

            itemStack.decrementUnlessCreative(1, user);
            player.getItemCooldownManager().set(this, 200);

            player.addStatusEffect(
                    new StatusEffectInstance(ModStatusEffects.QUICK_FLIGHT, 200, 0, false, true)
            );
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
