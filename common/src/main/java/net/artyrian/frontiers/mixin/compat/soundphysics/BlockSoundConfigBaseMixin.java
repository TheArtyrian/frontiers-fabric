package net.artyrian.frontiers.mixin.compat.soundphysics;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import net.minecraft.world.level.block.SoundType;

@Mixin(BlockSoundConfigBase.class)
public abstract class BlockSoundConfigBaseMixin
{
    @Shadow protected static void putSoundType(Map<BlockDefinition, Float> map, SoundType soundType, float value)
    {

    };
}
