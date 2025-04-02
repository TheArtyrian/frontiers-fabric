package net.artyrian.frontiers.mixin.entity.shulker;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBulletEntity.class)
public abstract class ShulkerBulletMixin extends ProjectileMixin
{
    @Inject(method="damage", at = @At("HEAD"))
    public void dropShulkScum(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        World thisworld = this.getWorld();
        if (!thisworld.isClient())
        {
            float drop_scum = thisworld.getRandom().nextFloat();
            boolean do_loot = thisworld.getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
            if (drop_scum >= 0.5F && do_loot)
            {
                ItemEntity scum = new ItemEntity(thisworld,
                        this.getPos().getX(),
                        this.getPos().getY(),
                        this.getPos().getZ(),
                        new ItemStack(ModItem.SHULKER_RESIDUE, 1)
                );
                scum.setVelocity(
                        .05d * (thisworld.getRandom().nextDouble() * .02d),
                        .1d,
                        .05d * (thisworld.getRandom().nextDouble() * 0.02D));
                scum.setToDefaultPickupDelay();
                thisworld.spawnEntity(scum);
            }
        }
    }

}
