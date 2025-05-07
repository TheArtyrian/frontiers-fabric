package net.artyrian.frontiers.item.custom.arrow;

import net.artyrian.frontiers.entity.projectile.BouncyArrowEntity;
import net.artyrian.frontiers.entity.projectile.DynamiteArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DynamiteArrowItem extends ArrowItem
{
    public DynamiteArrowItem(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom)
    {
        return new DynamiteArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction)
    {
        DynamiteArrowEntity bombArrow = new DynamiteArrowEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), null);
        bombArrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return bombArrow;
    }
}
