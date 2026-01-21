package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModSounds;
import net.minecraft.world.level.block.SoundType;

public class ModBlockSoundGroups
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

    public static void registerSounds()
    {
        //Frontiers.LOGGER.info("Registering sounds for " + Frontiers.MOD_ID);
    }
}
