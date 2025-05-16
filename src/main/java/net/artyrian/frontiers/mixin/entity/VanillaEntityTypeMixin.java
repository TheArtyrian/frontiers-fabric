package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.entity.projectile.BallEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.EndCrystalEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityType.class)
public abstract class VanillaEntityTypeMixin<T extends Entity>
{
    @Shadow @Final public static EntityType<EndCrystalEntity> END_CRYSTAL;

    //@ModifyReturnValue(method = "alwaysUpdateVelocity", at = @At("RETURN"))
    //private boolean addFrontiersEntitiesToVelChk(boolean original)
    //{
    //    EntityType<T> ME = (EntityType<T>)((Object)this);
    //    return original && ((EntityType)((Object)this)) != ModEntity.BALL;
    //}
}
