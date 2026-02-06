package net.vertisoft.vectorlib.mixin.impl;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.vertisoft.vectorlib.agnostic.registrars.VectorPropertyReg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceMixin
{
    // Thank you NexusLib for guidance :>
    @Inject(method = "getFuel", at = @At("RETURN"), cancellable = true)
    private static void vectorLib$fuelGrab(CallbackInfoReturnable<Map<Item, Integer>> cir)
    {
        Map<Item, Integer> newMap = new HashMap<>();
        if (cir.getReturnValue() != null) newMap.putAll(cir.getReturnValue());

        newMap.putAll(VectorPropertyReg.Fuel.get());
        cir.setReturnValue(newMap);
    }
}
