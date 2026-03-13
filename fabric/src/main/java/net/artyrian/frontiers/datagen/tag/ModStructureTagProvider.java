package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModStructure;
import net.artyrian.frontiers.reg.content.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.concurrent.CompletableFuture;

public class ModStructureTagProvider extends FabricTagProvider<Structure>
{
    public ModStructureTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, Registries.STRUCTURE, completableFuture);
    }

    private void modStructureTag()
    {
        this.getOrCreateTagBuilder(ModTags.Structures.ON_WHITE_TOWER_MAPS)
                .add(ModStructure.WHITE_TOWER)
        ;
    }

    private void vanillaStructureTag()
    {

    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modStructureTag();
        vanillaStructureTag();
    }
}
