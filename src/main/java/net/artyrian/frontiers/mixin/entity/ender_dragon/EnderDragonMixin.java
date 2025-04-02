package net.artyrian.frontiers.mixin.entity.ender_dragon;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderDragonEntity.class)
public class EnderDragonMixin
{
    @ModifyReturnValue(method = "createEnderDragonAttributes", at = @At("RETURN"))
    private static DefaultAttributeContainer.Builder buffThisBeautifulWoman(DefaultAttributeContainer.Builder original)
    {
        return original.add(EntityAttributes.GENERIC_MAX_HEALTH, 1500.0);
    }
}
