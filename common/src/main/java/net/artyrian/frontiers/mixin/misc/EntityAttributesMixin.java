package net.artyrian.frontiers.mixin.misc;

import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Debug(export = true)
@Mixin(Attributes.class)
public class EntityAttributesMixin
{
    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=generic.armor")),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/attributes/RangedAttribute;<init>(Ljava/lang/String;DDD)V", ordinal = 0),
            index = 3
    )
    private static double frontiers$buffArmor(double translationKey)
    {
        return 60.0;
    }

    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=generic.max_health")),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/attributes/RangedAttribute;<init>(Ljava/lang/String;DDD)V", ordinal = 0),
            index = 3
    )
    private static double frontiers$buffHP(double translationKey)
    {
        return 2048.0;
    }
}
