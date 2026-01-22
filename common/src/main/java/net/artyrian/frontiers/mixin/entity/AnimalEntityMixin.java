package net.artyrian.frontiers.mixin.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Animal.class)
public abstract class AnimalEntityMixin extends MobEntityMixin
{
    @Shadow public InteractionResult interactMob(Player player, InteractionHand hand)
    {
        return null;
    }
    @Shadow protected abstract void eat(Player player, InteractionHand hand, ItemStack stack);
    @Shadow public abstract boolean isInLove();

    @Inject(method = "canBreedWith", at = @At("HEAD"), cancellable = true)
    public void frontiersCanBreedWithHook(Animal other, CallbackInfoReturnable<Boolean> cir)
    {

    }
}
