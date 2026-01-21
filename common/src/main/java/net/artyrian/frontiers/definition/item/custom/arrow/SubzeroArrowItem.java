package net.artyrian.frontiers.definition.item.custom.arrow;

import net.artyrian.frontiers.definition.entity.projectile.SubzeroArrowEntity;
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

public class SubzeroArrowItem extends ArrowItem
{
    public SubzeroArrowItem(Item.Properties settings)
    {
        super(settings);
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom)
    {
        return new SubzeroArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction)
    {
        SubzeroArrowEntity subzeroArrow = new SubzeroArrowEntity(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        subzeroArrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return subzeroArrow;
    }
}
