package net.artyrian.frontiers.world.structure;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.tag.ModTags;
import net.artyrian.frontiers.world.structure.white_tower.WhiteTowerStructure;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.*;

public class ModStructure
{
    public static final RegistryKey<Structure> WHITE_TOWER = reg("white_tower");

    private static RegistryKey<Structure> reg(String id)
    {
        return RegistryKey.of(RegistryKeys.STRUCTURE, Identifier.of(Frontiers.MOD_ID, id));
    }

    public static void registerStructures()
    {
        ModStructureSets.registerStrSet();
        ModStructureType.registerStrType();
        ModStructurePieceType.registerStrPieceType();
    }

    public static void bootstrap(Registerable<Structure> structureRegisterable)
    {
        RegistryEntryLookup<Biome> biomePool = structureRegisterable.getRegistryLookup(RegistryKeys.BIOME);
        RegistryEntryLookup<StructurePool> templatePool = structureRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);

        structureRegisterable.register(
                WHITE_TOWER, new WhiteTowerStructure(new Structure.Config(biomePool.getOrThrow(ModTags.Biomes.WHITE_TOWER_HAS_STRUCTURE)))
        );
    }
}
