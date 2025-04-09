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
        Frontiers.LOGGER.warn("Suppressing experimental warnings; remember to make backups of your worlds whenever convenient!");
        return false;
    }
}
