package net.artyrian.frontiers.datagen.frontiers.tag;

import net.artyrian.frontiers.reg.world.FRStructures;
import net.artyrian.frontiers.reg.content.FRTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.concurrent.CompletableFuture;

public class FRStructureTagProvider extends FabricTagProvider<Structure>
{
    public FRStructureTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, Registries.STRUCTURE, completableFuture);
    }

    private void modStructureTag()
    {
        this.getOrCreateTagBuilder(FRTags.Structures.ON_WHITE_TOWER_MAPS)
                .add(FRStructures.WHITE_TOWER)
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
