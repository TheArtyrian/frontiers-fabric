package net.artyrian.frontiers.mixin.compat.soundphysics;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.sonicether.soundphysics.config.SoundTypes;
import net.artyrian.frontiers.sounds.ModBlockSoundGroups;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(SoundTypes.class)
public abstract class SoundTypesMixin
{
    @WrapOperation(method = "<clinit>", at = @At(value = "INVOKE", target = "Ljava/util/Collections;unmodifiableMap(Ljava/util/Map;)Ljava/util/Map;"))
    private static Map<BlockSoundGroup, String> addFrontiersVals(Map<BlockSoundGroup, String> map, Operation<Map<BlockSoundGroup, String>> original)
    {
        map.put(ModBlockSoundGroups.CRAGULSTANE, "FRONTIERS_CRAGULSTANE");
        return original.call(map);
    }
}
