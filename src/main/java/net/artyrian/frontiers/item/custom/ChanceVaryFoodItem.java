package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.payloads.ChanceFoodItemPayload;
import net.artyrian.frontiers.data.payloads.CragsMonsterKillPayload;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.UUID;

public class ChanceVaryFoodItem extends Item
{
    private final float consume_chance;

    public ChanceVaryFoodItem(float consume_chance, Settings settings)
    {
        super(settings);
        this.consume_chance = consume_chance;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        if (foodComponent != null)
        {
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    user.getEatSound(stack),
                    SoundCategory.NEUTRAL,
                    1.0F,
                    1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F
            );

            if (user instanceof PlayerEntity playerEntity)
            {
                playerEntity.getHungerManager().eat(foodComponent);
                playerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                world.playSound(
                        null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F
                );
            }

            if (!user.getWorld().isClient())
            {
                user.applyFoodEffects(foodComponent);

                float getter = world.random.nextFloat();
                boolean doEat = (getter < this.consume_chance);
                if (doEat) stack.decrementUnlessCreative(1, user);

                if (user instanceof ServerPlayerEntity player)
                {
                    if (doEat && !stack.isEmpty())
                    {
                        Criteria.CONSUME_ITEM.trigger(player, stack);

                        MinecraftServer server = user.getWorld().getServer();
                        if (server != null)
                        {
                            server.execute(() ->
                            {
                                ServerPlayNetworking.send(
                                        player,
                                        new ChanceFoodItemPayload(
                                                stack
                                        ));
                            });
                        }
                    }
                }
            }
            user.emitGameEvent(GameEvent.EAT);
            return stack;
        }
        return stack;
    }
}
