package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.definition.entity.types.projectile.FruitcakeEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class FruitcakeItem extends Item implements ProjectileItem
{
    public FruitcakeItem(Item.Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        if (!user.isCreative())
        {
            InteractionResultHolder<ItemStack> passer = super.use(world, user, hand);
            if (passer.getResult().consumesAction())
            {
                return passer;
            }
        }

        ItemStack itemStack = user.getItemInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        user.getCooldowns().addCooldown(this, 30);
        if (!world.isClientSide)
        {
            FruitcakeEntity cake = new FruitcakeEntity(world, user);
            cake.setItem(itemStack);
            cake.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0F, 0.8F, 1.0F);
            world.addFreshEntity(cake);
        }

        user.awardStat(Stats.ITEM_USED.get(this));
        itemStack.consume(1, user);
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user)
    {
        if (user instanceof Player pl)
        {
            pl.getCooldowns().addCooldown(this, 10);
        }
        return super.finishUsingItem(stack, world, user);
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction)
    {
        FruitcakeEntity cake = new FruitcakeEntity(world, pos.x(), pos.y(), pos.z());
        cake.setItem(stack);
        return cake;
    }
}
