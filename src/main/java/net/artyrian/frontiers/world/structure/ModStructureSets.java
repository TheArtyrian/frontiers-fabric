package net.artyrian.frontiers.world.structure;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.structure.StructureSetKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureKeys;

public class ModStructureSets
{
    public static final RegistryKey<StructureSet> WHITE_TOWERS = reg("white_towers");

    private static RegistryKey<StructureSet> reg(String id)
    {
        return RegistryKey.of(RegistryKeys.STRUCTURE_SET, Identifier.of(Frontiers.MOD_ID, id));
    }

    public static void bootstrap(Registerable<StructureSet> structureSetRegisterable)
    {
        RegistryEntryLookup<Structure> structLookup = structureSetRegisterable.getRegistryLookup(RegistryKeys.STRUCTURE);

        structureSetRegisterable.register(
                WHITE_TOWERS,
                new StructureSet(structLookup.getOrThrow(ModStructure.WHITE_TOWER),
                new RandomSpreadStructurePlacement(80, 20, SpreadType.TRIANGULAR, 10489327))
        );
    }

    public static void registerStrSet()
    {

    }
}
