package net.artyrian.frontiers.reg.world;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.world.structure.white_tower.WhiteTowerStructure;
import net.artyrian.frontiers.reg.content.FRTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class FRStructures
{
    public static final ResourceKey<Structure> WHITE_TOWER = reg("white_tower");

    private static ResourceKey<Structure> reg(String id)
    {
        return ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, id));
    }

    public static void registerStructures()
    {
        FRStructureSets.registerStrSet();
        FRStructureTypes.registerStrType();
        FRStructurePieceTypes.registerStrPieceType();
    }

    public static void bootstrap(BootstrapContext<Structure> structureRegisterable)
    {
        HolderGetter<Biome> biomePool = structureRegisterable.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> templatePool = structureRegisterable.lookup(Registries.TEMPLATE_POOL);

        structureRegisterable.register(
                WHITE_TOWER, new WhiteTowerStructure(new Structure.StructureSettings(biomePool.getOrThrow(FRTags.Biomes.WHITE_TOWER_HAS_STRUCTURE)))
        );
    }
}
