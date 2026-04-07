package net.artyrian.frontiers.reg.sound;

import net.minecraft.world.level.block.SoundType;

public class FRBlockSFX
{
    public static final SoundType CRAGULSTANE = new SoundType(
            1.0F,
            1.0F,
            FRSounds.BLOCK_CRAGULSTANE_BREAK.get(),
            FRSounds.BLOCK_CRAGULSTANE_STEP.get(),
            FRSounds.BLOCK_CRAGULSTANE_PLACE.get(),
            FRSounds.BLOCK_CRAGULSTANE_HIT.get(),
            FRSounds.BLOCK_CRAGULSTANE_FALL.get()
    );
}
