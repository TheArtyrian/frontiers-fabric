package net.artyrian.frontiers.reg.sound;

import net.minecraft.world.level.block.SoundType;

public class FRBlockSFX
{
    public static final SoundType CRAGULSTANE = new SoundType(
            1.0F,
            1.0F,
            ModSounds.BLOCK_CRAGULSTANE_BREAK.get(),
            ModSounds.BLOCK_CRAGULSTANE_STEP.get(),
            ModSounds.BLOCK_CRAGULSTANE_PLACE.get(),
            ModSounds.BLOCK_CRAGULSTANE_HIT.get(),
            ModSounds.BLOCK_CRAGULSTANE_FALL.get()
    );
}
