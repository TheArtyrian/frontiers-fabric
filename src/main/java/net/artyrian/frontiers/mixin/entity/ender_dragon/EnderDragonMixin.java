package net.artyrian.frontiers.mixin.entity.ender_dragon;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.criterion.ModCriteria;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonMixin extends MobEntityMixin
{
    @Shadow private @Nullable EnderDragonFight fight;

    @ModifyReturnValue(method = "createEnderDragonAttributes", at = @At("RETURN"))
    private static DefaultAttributeContainer.Builder buffThisBeautifulWoman(DefaultAttributeContainer.Builder original)
    {
        return original.add(EntityAttributes.GENERIC_MAX_HEALTH, 1500.0);
    }

    @Inject(method = "updatePostDeath", at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;)V")
    )
    private void emitGameEvent(CallbackInfo ci)
    {
        if (!this.getWorld().isClient)
        {
            for (ServerPlayerEntity targeter : PlayerLookup.tracking((ServerWorld) this.getWorld(), this.getBlockPos()))
            {
                ModCriteria.ENTITY_KILLED_NEARBY.trigger(targeter, this.getType());
            }
        }
    }
}
