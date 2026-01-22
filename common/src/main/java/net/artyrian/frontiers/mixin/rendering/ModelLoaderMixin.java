package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ModelBakery.class)
public abstract class ModelLoaderMixin
{
    @Shadow protected abstract void loadSpecialItemModelAndDependencies(ModelResourceLocation id);

    @WrapOperation(method = "<init>", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V",
            ordinal = 0)
    )
    private void frontiers$InjectCustomModelBakes(ProfilerFiller instance, Operation<Void> original)
    {
        this.loadSpecialItemModelAndDependencies(ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "pale_trident_in_hand")));

        original.call(instance);
    }
}
