package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.entity.projectile.FruitcakeEntity;
import net.artyrian.frontiers.entity.projectile.ManaBottleEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class FruitcakeItem extends Item implements ProjectileItem
{
    public FruitcakeItem(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        if (!user.isCreative())
        {
            TypedActionResult<ItemStack> passer = super.use(world, user, hand);
            if (passer.getResult().isAccepted())
            {
                return passer;
            }
        }

        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        user.getItemCooldownManager().set(this, 30);
        if (!world.isClient)
        {
            FruitcakeEntity cake = new FruitcakeEntity(world, user);
            cake.setItem(itemStack);
            cake.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 0.8F, 1.0F);
            world.spawnEntity(cake);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    {
        if (user instanceof PlayerEntity pl)
        {
            pl.getItemCooldownManager().set(this, 10);
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction)
    {
        FruitcakeEntity cake = new FruitcakeEntity(world, pos.getX(), pos.getY(), pos.getZ());
        cake.setItem(stack);
        return cake;
    }
}
