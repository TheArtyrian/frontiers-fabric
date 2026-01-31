package net.artyrian.frontiers.mixin.compat.soundphysics;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.sonicether.soundphysics.config.SoundTypes;
import net.artyrian.frontiers.reg.sound.FRBlockSFX;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.world.level.block.SoundType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(SoundTypes.class)
public abstract class SoundTypesMixin
{
    @WrapOperation(method = "<clinit>", at = @At(value = "INVOKE", target = "Ljava/util/Collections;unmodifiableMap(Ljava/util/Map;)Ljava/util/Map;"))
    private static Map<SoundType, String> addFrontiersVals(Map<SoundType, String> map, Operation<Map<SoundType, String>> original)
    {
        map.put(FRBlockSFX.CRAGULSTANE, "FRONTIERS_CRAGULSTANE");
        return original.call(map);
    }
}
