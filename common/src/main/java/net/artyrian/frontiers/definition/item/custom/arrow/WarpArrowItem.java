package net.artyrian.frontiers.definition.item.custom.arrow;

import net.artyrian.frontiers.definition.entity.types.projectile.WarpArrowEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class WarpArrowItem extends ArrowItem
{
    public WarpArrowItem(Item.Properties settings)
    {
        super(settings);
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom)
    {
        return new WarpArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction)
    {
        WarpArrowEntity warpArrow = new WarpArrowEntity(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        warpArrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return warpArrow;
    }
}
