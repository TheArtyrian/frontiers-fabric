package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class ModStructureSets
{
    public static final ResourceKey<StructureSet> WHITE_TOWERS = reg("white_towers");

    private static ResourceKey<StructureSet> reg(String id)
    {
        return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, id));
    }

    public static void bootstrap(BootstrapContext<StructureSet> structureSetRegisterable)
    {
        HolderGetter<Structure> structLookup = structureSetRegisterable.lookup(Registries.STRUCTURE);

        structureSetRegisterable.register(
                WHITE_TOWERS,
                new StructureSet(structLookup.getOrThrow(ModStructure.WHITE_TOWER),
                new RandomSpreadStructurePlacement(80, 20, RandomSpreadType.TRIANGULAR, 10489327))
        );
    }

    public static void registerStrSet()
    {

    }
}
