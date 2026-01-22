package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityType.class)
public abstract class VanillaEntityTypeMixin<T extends Entity>
{
    @Shadow @Final public static EntityType<EndCrystal> END_CRYSTAL;

    //@ModifyReturnValue(method = "alwaysUpdateVelocity", at = @At("RETURN"))
    //private boolean addFrontiersEntitiesToVelChk(boolean original)
    //{
    //    EntityType<T> ME = (EntityType<T>)((Object)this);
    //    return original;
    //}
}
