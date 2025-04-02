package net.artyrian.frontiers.item.custom.arrow;

import net.artyrian.frontiers.entity.projectile.BouncyArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BouncyArrowItem extends ArrowItem
{
    public BouncyArrowItem(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom)
    {
        return new BouncyArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction)
    {
        BouncyArrowEntity bouncyArrow = new BouncyArrowEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), null);
        bouncyArrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return bouncyArrow;
    }
}
