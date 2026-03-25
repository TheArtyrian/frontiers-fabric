package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.vertisoft.vectorlib.exclusive.datagen.VectorDatagen;
import net.vertisoft.vectorlib.exclusive.datagen.soundfile.VectorSoundsheetGen;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FRSoundsJson extends VectorSoundsheetGen
{
    public FRSoundsJson(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(Frontiers.MOD_ID, dataOutput, registryLookup);
    }

    @Override
    public void generateSounds(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        sounds.addSound(
                ModSounds.WITHER_DEFLECT_MACE.get(), multiple(Frontiers.MOD_ID, "entity/wither/deflect", 3),
                VectorDatagen.Caption.of("sounds.frontiers.wither_deflect_mace", Map.ofEntries(
                        VectorDatagen.Caption.englishUS("Mace deflected")
                ))
        );
    }
}
