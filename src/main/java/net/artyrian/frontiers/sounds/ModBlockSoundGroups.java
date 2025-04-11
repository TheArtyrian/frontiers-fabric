package net.artyrian.frontiers.sounds;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModBlockSoundGroups
{
    public static final BlockSoundGroup CRAGULSTANE = new BlockSoundGroup(
            1.0F,
            1.0F,
            ModSounds.BLOCK_CRAGULSTANE_BREAK,
            ModSounds.BLOCK_CRAGULSTANE_STEP,
            ModSounds.BLOCK_CRAGULSTANE_PLACE,
            ModSounds.BLOCK_CRAGULSTANE_HIT,
            ModSounds.BLOCK_CRAGULSTANE_FALL
    );

    public static void registerSounds()
    {
        //Frontiers.LOGGER.info("Registering sounds for " + Frontiers.MOD_ID);
    }
}
