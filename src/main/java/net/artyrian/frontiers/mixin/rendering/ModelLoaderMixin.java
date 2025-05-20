package net.artyrian.frontiers.mixin.rendering;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin
{
    @Shadow protected abstract void loadItemModel(ModelIdentifier id);

    @Inject(method = "<init>", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/model/ModelLoader;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V",
            ordinal = 0,
            shift = At.Shift.AFTER)
    )
    private void frontiersInjectCustomModelBakes(BlockColors blockColors, Profiler profiler, Map jsonUnbakedModels, Map blockStates, CallbackInfo ci)
    {
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Frontiers.MOD_ID, "pale_trident_in_hand")));
    }
}
