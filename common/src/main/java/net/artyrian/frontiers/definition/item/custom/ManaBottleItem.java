package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.definition.entity.projectile.ManaBottleEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class ManaBottleItem extends Item implements ProjectileItem
{
    public ManaBottleItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemStack = user.getItemInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.EXPERIENCE_BOTTLE_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (!world.isClientSide)
        {
            ManaBottleEntity bottle = new ManaBottleEntity(world, user);
            bottle.setItem(itemStack);
            bottle.shootFromRotation(user, user.getXRot(), user.getYRot(), -20.0F, 0.7F, 1.0F);
            world.addFreshEntity(bottle);
        }

        user.awardStat(Stats.ITEM_USED.get(this));
        itemStack.consume(1, user);
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction)
    {
        ManaBottleEntity bottle = new ManaBottleEntity(world, pos.x(), pos.y(), pos.z());
        bottle.setItem(stack);
        return bottle;
    }

    @Override
    public ProjectileItem.DispenseConfig createDispenseConfig()
    {
        return ProjectileItem.DispenseConfig.builder()
                .uncertainty(ProjectileItem.DispenseConfig.DEFAULT.uncertainty() * 0.5F)
                .power(ProjectileItem.DispenseConfig.DEFAULT.power() * 1.25F)
                .build();
    }
}
