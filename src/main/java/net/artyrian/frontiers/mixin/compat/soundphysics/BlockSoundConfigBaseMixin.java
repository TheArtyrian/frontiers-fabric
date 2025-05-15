package net.artyrian.frontiers.mixin.compat.soundphysics;

import com.sonicether.soundphysics.config.blocksound.BlockDefinition;
import com.sonicether.soundphysics.config.blocksound.BlockSoundConfigBase;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(BlockSoundConfigBase.class)
public abstract class BlockSoundConfigBaseMixin
{
    @Shadow protected static void putSoundType(Map<BlockDefinition, Float> map, BlockSoundGroup soundType, float value)
    {

    };
}
