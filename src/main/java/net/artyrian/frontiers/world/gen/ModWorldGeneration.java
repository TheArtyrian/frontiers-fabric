package net.artyrian.frontiers.world.gen;

import net.artyrian.frontiers.world.entity.ModEntitySpawning;

public class ModWorldGeneration
{
    public static void generateModWorldGen()
    {
        ModOreGeneration.generateOres();
        ModVegetationGeneration.generateVeg();
        ModStructureGeneration.generateStructs();
        ModMiscGeneration.generateMisc();

        ModEntitySpawning.register();
    }
}
