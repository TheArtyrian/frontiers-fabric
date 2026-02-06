package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends FabricTagProvider<Biome>
{
    public ModBiomeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, Registries.BIOME, completableFuture);
    }

    private void modBiomeTag()
    {
        this.getOrCreateTagBuilder(ModTags.Biomes.WHITE_TOWER_HAS_STRUCTURE)
                .add(Biomes.SNOWY_SLOPES)
                .add(Biomes.JAGGED_PEAKS)
                .add(Biomes.FROZEN_PEAKS)
                .add(Biomes.WINDSWEPT_HILLS)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.BOTTLED_MESSAGE_COMPATIBLE)
                .addOptionalTag(BiomeTags.IS_OCEAN)
                .addOptionalTag(BiomeTags.IS_BEACH)
                .addOptionalTag(BiomeTags.IS_RIVER)
                .add(Biomes.STONY_SHORE)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_QUICKSAND)
                .add(Biomes.JUNGLE)
                .add(Biomes.SPARSE_JUNGLE)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_HIELOSTONE)
                .add(Biomes.SNOWY_PLAINS)
                .add(Biomes.ICE_SPIKES)
                .add(Biomes.FROZEN_OCEAN)
                .add(Biomes.DEEP_FROZEN_OCEAN)
                .add(Biomes.FROZEN_RIVER)
                .add(Biomes.FROZEN_PEAKS)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_BLACK_EMERALD)
                .add(Biomes.MEADOW)
                .add(Biomes.CHERRY_GROVE)
                .add(Biomes.GROVE)
                .add(Biomes.SNOWY_SLOPES)
                .add(Biomes.JAGGED_PEAKS)
                .add(Biomes.FROZEN_PEAKS)
                .add(Biomes.STONY_PEAKS)
                .add(Biomes.WINDSWEPT_HILLS)
                .add(Biomes.WINDSWEPT_GRAVELLY_HILLS)
                .add(Biomes.WINDSWEPT_FOREST)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_FROSTITE)
                .add(Biomes.ICE_SPIKES)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_BRIMTAN)
                .addOptional(ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crags_plains")))
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_FUNGAL_DAFFODIL)
                .add(Biomes.MUSHROOM_FIELDS)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_SNOW_DAHLIA)
                .add(Biomes.FROZEN_RIVER)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_CRIMCONE)
                .add(Biomes.CRIMSON_FOREST)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_EXPERIWINKLE)
                .add(Biomes.FLOWER_FOREST)
                .add(Biomes.MEADOW)
        ;
        this.getOrCreateTagBuilder(ModTags.Biomes.GENERATES_EBONCORK)
                .addOptional(ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crags_plains")))
        ;
    }

    // Vanilla tags.
    private void vanillaBiomeTag()
    {

    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modBiomeTag();
        vanillaBiomeTag();
    }
}
