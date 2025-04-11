package net.artyrian.frontiers.mixin.exp_suppress;

import com.mojang.serialization.Lifecycle;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin
{
    // https://www.youtube.com/watch?v=iOaCQkR8FXU
    // (its 1:18 am the day after ludum dare 57 help)
    @ModifyArg(method = "createLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/integrated/IntegratedServerLoader;tryLoad(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/gui/screen/world/CreateWorldScreen;Lcom/mojang/serialization/Lifecycle;Ljava/lang/Runnable;Z)V"))
    private boolean replaceLifecycler(boolean value)
    {
        if (Frontiers.CONFIG.doSuppressExperimentalWarn())
        {
            Frontiers.LOGGER.warn(
                "\n     Suppressing all experimental warnings due to Frontiers config settings - " +
                "as this is a modded client, you likely know the risks already." +
                "\n     You can disable this suppression in the Frontiers config file - set suppressExperimentalWarn to false." +
                "\n     Remember, make backups of your world whenever possible and/or convenient!"
            );
            return true;
        }
        return value;
    }
}
