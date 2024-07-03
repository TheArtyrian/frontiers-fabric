package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class CobaltBobber extends FishingBobberEntity
{
    public CobaltBobber(EntityType<? extends CobaltBobber> type, World world, int luckBonus, int waitTimeReductionTicks)
    {
        super(type, world, luckBonus, waitTimeReductionTicks);
    }

    public CobaltBobber(EntityType<? extends CobaltBobber> entityType, World world)
    {
        super(entityType, world);
    }

    public CobaltBobber(PlayerEntity thrower, World world, int luckBonus, int waitTimeReductionTicks)
    {
        super(thrower, world, luckBonus, waitTimeReductionTicks);
    }

    private boolean removeIfInvalid(PlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack2 = player.getOffHandStack();
        boolean bl = itemStack.isOf(ModItem.COBALT_FISHING_ROD);
        boolean bl2 = itemStack2.isOf(ModItem.COBALT_FISHING_ROD);
        if (!player.isRemoved() && player.isAlive() && (bl || bl2) && !(this.squaredDistanceTo(player) > 1024.0)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }
}
