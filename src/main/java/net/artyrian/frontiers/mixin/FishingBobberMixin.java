package net.artyrian.frontiers.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FishingBobberEntity.class)
public class FishingBobberMixin
{
    //@WrapOperation(method = "removeIfInvalid", )
    //private boolean removeIfInvalid(ItemStack stack, Item item, Operation<Boolean> original)
    //{
    //
    //}
}