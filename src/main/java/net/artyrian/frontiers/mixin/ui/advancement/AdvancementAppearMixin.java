package net.artyrian.frontiers.mixin.ui.advancement;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.ModAdvancementFrame;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.client.gui.screen.advancement.AdvancementObtainedStatus;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AdvancementObtainedStatus.class)
public abstract class AdvancementAppearMixin
{
    @Shadow @Final private Identifier boxTexture;

    @Final
    @Shadow
    public static AdvancementObtainedStatus UNOBTAINED;

    // Blah blah inefficient who care
    @Inject(method = "getFrameTexture", at = @At("HEAD"), cancellable = true)
    public void kwhat(AdvancementFrame frame, CallbackInfoReturnable<Identifier> cir)
    {
        if (frame == ModAdvancementFrame.FRONTIERS_ADV)
        {
            if (this.boxTexture == UNOBTAINED.getBoxTexture())
            {
                cir.setReturnValue(Identifier.of(Frontiers.MOD_ID, "advancements/frontier_adv_unobtained"));
            }
            else
            {
                cir.setReturnValue(Identifier.of(Frontiers.MOD_ID, "advancements/frontier_adv_obtained"));
            }
        }
    }
}
