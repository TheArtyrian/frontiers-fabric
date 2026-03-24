package net.artyrian.frontiers.definition.item.custom.arrow;

import net.artyrian.frontiers.definition.entity.types.projectile.BouncyArrowEntity;
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

public class BouncyArrowItem extends ArrowItem
{
    public BouncyArrowItem(Item.Properties settings)
    {
        super(settings);
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom)
    {
        return new BouncyArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction)
    {
        BouncyArrowEntity bouncyArrow = new BouncyArrowEntity(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        bouncyArrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return bouncyArrow;
    }
}
