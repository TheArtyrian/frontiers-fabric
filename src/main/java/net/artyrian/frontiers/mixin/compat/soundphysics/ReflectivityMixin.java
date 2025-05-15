package net.artyrian.frontiers.mixin.compat.soundphysics;

import com.sonicether.soundphysics.config.ReflectivityConfig;
import com.sonicether.soundphysics.config.blocksound.BlockDefinition;
import com.sonicether.soundphysics.config.blocksound.BlockSoundTypeDefinition;
import net.artyrian.frontiers.sounds.ModBlockSoundGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ReflectivityConfig.class)
public class ReflectivityMixin
{
    // Thanks remap=false i hope you work
    @Inject(method = "addDefaults(Ljava/util/Map;)V", at = @At("TAIL"), remap = false)
    public void addFrontiersDefaults(Map<BlockDefinition, Float> map, CallbackInfo ci)
    {
        map.put(new BlockSoundTypeDefinition(ModBlockSoundGroups.CRAGULSTANE), 1.5F);
    }
}
