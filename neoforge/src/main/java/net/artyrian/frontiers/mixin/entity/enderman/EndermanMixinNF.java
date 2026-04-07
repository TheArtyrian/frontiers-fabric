package net.artyrian.frontiers.mixin.entity.enderman;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.event.MixinShortcuts;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderMan.class)
public class EndermanMixinNF
{
    /** I defy your intrusiveness, NeoForge. */
    @ModifyExpressionValue(method = "isLookingAtMe", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/CommonHooks;shouldSuppressEnderManAnger(Lnet/minecraft/world/entity/monster/EnderMan;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean playerGazeProtectionREMOVE_IN_1_21_2(boolean original, @Local ItemStack stack)
    {
        return MixinShortcuts.endermanLookingAtMeOwO(original, stack);
    }
}
