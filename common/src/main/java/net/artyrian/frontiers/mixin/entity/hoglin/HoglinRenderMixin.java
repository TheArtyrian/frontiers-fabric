package net.artyrian.frontiers.mixin.entity.hoglin;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_intf.HoglinMixInterface;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.HoglinRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(HoglinRenderer.class)
public abstract class HoglinRenderMixin
{
    @Unique private static final ResourceLocation CHILL_TEX = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/hoglin/hoglin_tame.png");
    @Unique private static final ResourceLocation WIZPIG_CHILL_TEX = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/hoglin/hoglin_tame_wizpig.png");
    @Unique private static final ResourceLocation WIZPIG_TEX = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/hoglin/hoglin_wizpig.png");
    @Final @Shadow private static ResourceLocation TEXTURE;

    @Inject(method = "getTexture*", at = @At("RETURN"), cancellable = true)
    public void getTexture(Hoglin hoglinEntity, CallbackInfoReturnable<ResourceLocation> cir)
    {
        String name = ChatFormatting.stripFormatting(hoglinEntity.getName().getString());
        boolean truffled = ((HoglinMixInterface)hoglinEntity).frontiers_1_21x$isTruffled();

        if (name.equals("Wizpig"))
        {
            if (truffled) cir.setReturnValue(WIZPIG_CHILL_TEX);
            else cir.setReturnValue(WIZPIG_TEX);
        }
        else
        {
            if (truffled) cir.setReturnValue(CHILL_TEX);
            else cir.setReturnValue(TEXTURE);
        }
    }
}
