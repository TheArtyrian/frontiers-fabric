package net.artyrian.frontiers.mixin.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends MobEntityMixin
{
    @Shadow public ActionResult interactMob(PlayerEntity player, Hand hand)
    {
        return null;
    }

    @Shadow protected abstract void eat(PlayerEntity player, Hand hand, ItemStack stack);
}
