package net.artyrian.frontiers.mixin.exp_suppress;

import com.mojang.serialization.Lifecycle;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.server.integrated.IntegratedServerLoader;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true)
@Mixin(IntegratedServerLoader.class)
public class IntegratedServerLoadMixin
{
    @ModifyVariable(method = "checkBackupAndStart", at = @At(value = "STORE"), ordinal = 1)
    private boolean x(boolean value)
    {
        if (Frontiers.CONFIG.doSuppressExperimentalWarn())
        {
            Frontiers.LOGGER.warn(
                    "\n     Suppressing all experimental warnings due to Frontiers config settings - " +
                    "as this is a modded client, you likely know the risks already." +
                    "\n     You can disable this suppression in the Frontiers config file - set suppressExperimentalWarn to false." +
                    "\n     Remember, make backups of your world whenever possible and/or convenient!"
            );
            return false;
        }
        return value;
    }
}
