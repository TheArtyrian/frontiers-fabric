package net.artyrian.frontiers.mixin.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.ModAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin
{
    @Shadow
    public abstract EntityAttributeInstance getAttributeInstance(RegistryEntry<EntityAttribute> attribute);

    @Shadow
    private void updateAttributes() {}

    @Shadow public abstract AttributeContainer getAttributes();

    @Inject(method = "updateAttribute", at = @At("TAIL"))
    private void updateAttribute(RegistryEntry<EntityAttribute> attribute, CallbackInfo ci)
    {

    }
}
