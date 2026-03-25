package net.vertisoft.vectorlib.exclusive.datagen.soundfile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.vertisoft.vectorlib.exclusive.datagen.VectorDatagen;
import org.jetbrains.annotations.ApiStatus.NonExtendable;
import org.jetbrains.annotations.Nullable;

/** A robust data generator for {@code sounds.json}.
 *
 */
public abstract class VectorSoundsheetGen implements DataProvider
{
    private static final String JSON_NAME = "sounds.json";
    private static final String SOUNDS = "sounds";
    private static final String SUB = "subtitle";

    protected final FabricDataOutput dataOutput;
    private final String mod;
    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    protected VectorSoundsheetGen(String modId, FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        this.mod = modId;
        this.dataOutput = dataOutput;
        this.registryLookup = registryLookup;
    }

    public abstract void generateSounds(HolderLookup.Provider lookup, SoundsFactory factory);

    @Override
    public CompletableFuture<?> run(CachedOutput output)
    {
        TreeMap<String, String> entries = new TreeMap();
        return this.registryLookup.thenCompose((lookup) -> {
            JsonObject jason = new JsonObject();

            TreeMap<String, Pair<List<ResourceLocation>, VectorDatagen.Caption>> mapper = new TreeMap<>();
            this.generateSounds(lookup, (path, soundslist, caption) -> {
                Objects.requireNonNull(path);
                Objects.requireNonNull(soundslist);
                if (mapper.containsKey(path)) throw new IllegalArgumentException(String.format("Duplicate definition for %1s", path));
                else mapper.put(path, Pair.of(soundslist, caption));
            });

            for (String path : mapper.keySet())
            {
                Pair<List<ResourceLocation>, VectorDatagen.Caption> pathSet = mapper.get(path);
                VectorDatagen.Caption caption = pathSet.getSecond();

                JsonObject nested = new JsonObject();
                JsonArray sounds = new JsonArray();

                for (ResourceLocation loc : pathSet.getFirst())
                {
                    sounds.add((loc.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) ? loc.getPath() : loc.toString());
                }

                nested.add(SOUNDS, sounds);
                if (caption != null)
                {
                    nested.addProperty(SUB, caption.id());
                }

                jason.add(path, nested);
            }

            // Bundle the JSON together into a neat little package.
            return DataProvider.saveStable(
                    output,
                    jason,
                    this.dataOutput.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(this.mod).resolve(JSON_NAME)
            );
        });
    }

    /**
     * Returns a list with several counts of the same commonpath with numbers appended to the end, in cases of sound variation.
     * <p>
     * For instance, say you have four files located at {@code assets/<modpath>/sounds/block/myblock/}, and each one is simply
     * called {@code place<#>.ogg} in ascending order. Provide {@code "<modpath>"}, {@code "block/myblock/place"}, and {@code 4};
     * a list of all the files will be returned.
     * @param assets The assets folder where your sound is located. Can also be {@code "minecraft"} for Vanilla sounds.
     * @param commonpath The path + filename where your sound is located without numbers, i.e {@code blocks/specialdirt/mine}.
     * @param counts How many variations of this sound exist.
     **/
    protected List<ResourceLocation> multiple(String assets, String commonpath, int counts)
    {
        List<ResourceLocation> returnable = new ArrayList<>();
        for (int i = 0; i < counts; i++)
        {
            returnable.add(ResourceLocation.fromNamespaceAndPath(assets, commonpath + (i + 1)));
        }
        return returnable;
    }

    @Override public String getName() { return "VectorLib sounds.json generator"; }

    @FunctionalInterface @NonExtendable
    public interface SoundsFactory
    {
        void add(String name, List<ResourceLocation> soundfiles, @Nullable VectorDatagen.Caption caption);

        default void addSound(SoundEvent sound, List<ResourceLocation> soundfiles, @Nullable VectorDatagen.Caption captions)
        {
            this.add(sound.getLocation().getPath(), soundfiles, captions);
        }
    }
}
