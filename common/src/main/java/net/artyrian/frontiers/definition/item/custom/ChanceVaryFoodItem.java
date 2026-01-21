package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.definition.networking.payload.ChanceFoodItemPayload;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.vertisoft.vectorlib.VectorLib;

import java.util.UUID;

public class ChanceVaryFoodItem extends Item
{
    private final float consume_chance;

    public ChanceVaryFoodItem(float consume_chance, Properties settings)
    {
        super(settings);
        this.consume_chance = consume_chance;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user)
    {
        FoodProperties foodComponent = stack.get(DataComponents.FOOD);
        if (foodComponent != null)
        {
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    user.getEatingSound(stack),
                    SoundSource.NEUTRAL,
                    1.0F,
                    1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F
            );

            if (user instanceof Player playerEntity)
            {
                playerEntity.getFoodData().eat(foodComponent);
                playerEntity.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                world.playSound(
                        null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F
                );
            }

            if (!user.level().isClientSide())
            {
                user.addEatEffect(foodComponent);

                float getter = world.random.nextFloat();
                boolean doEat = (getter < this.consume_chance);
                if (doEat) stack.consume(1, user);

                if (user instanceof ServerPlayer player)
                {
                    if (doEat && !stack.isEmpty())
                    {
                        CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);

                        MinecraftServer server = user.level().getServer();
                        if (server != null)
                        {
                            VectorLib.NETWORK.sendToPlayer(player, new ChanceFoodItemPayload(stack));
                        }
                    }
                }
            }
            user.gameEvent(GameEvent.EAT);
            return stack;
        }
        return stack;
    }
}
