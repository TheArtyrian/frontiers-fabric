package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.mixin_interfaces.BobberType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CustomFishingRod extends FishingRodItem
{
    private int ENCHANTABILITY = 1;
    private BobberType BOBBER_TYPE = BobberType.DEFAULT;

    public CustomFishingRod(BobberType rod_type, int enchantability, Item.Settings settings)
    {
        super(settings);
        this.BOBBER_TYPE = rod_type;
        this.ENCHANTABILITY = enchantability;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.fishHook != null) {
            if (!world.isClient) {
                int i = user.fishHook.use(itemStack);
                itemStack.damage(i, user, LivingEntity.getSlotForHand(hand));
            }

            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE,
                    SoundCategory.NEUTRAL,
                    1.0F,
                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_FISHING_BOBBER_THROW,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            if (world instanceof ServerWorld serverWorld) {
                int j = (int)(EnchantmentHelper.getFishingTimeReduction(serverWorld, itemStack, user) * 20.0F);
                int k = EnchantmentHelper.getFishingLuckBonus(serverWorld, itemStack, user);
                String parent_rod = itemStack.getItem().toString();

                FishingBobberEntity bobby = new FishingBobberEntity(user, world, k, j);
                NbtCompound bobbys_stuff = new NbtCompound();
                bobby.writeCustomDataToNbt(bobbys_stuff);
                bobbys_stuff.putInt("BobberType", BOBBER_TYPE.getID());
                bobbys_stuff.putString("ParentRod", parent_rod);
                bobby.readCustomDataFromNbt(bobbys_stuff);

                world.spawnEntity(bobby);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public int getEnchantability() {
        return ENCHANTABILITY;
    }
}
