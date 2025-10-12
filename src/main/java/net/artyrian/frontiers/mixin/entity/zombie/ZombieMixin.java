package net.artyrian.frontiers.mixin.entity.zombie;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.time.LocalDate;
import java.time.Month;

@Mixin(ZombieEntity.class)
public abstract class ZombieMixin extends LivingEntityMixin
{
    @WrapOperation(method = "initialize", at = @At(value = "INVOKE", target = "Ljava/time/LocalDate;now()Ljava/time/LocalDate;"))
    private LocalDate frontiers$switchUpHalloween(Operation<LocalDate> original)
    {
        if (Frontiers.EVENTS.IS_HALLOWEEN)
        {
            LocalDate now = original.call();
            int yr = now.getYear();
            return LocalDate.of(yr, Month.OCTOBER, 31);
        }
        else return original.call();
    }
}
