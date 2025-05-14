package net.artyrian.frontiers.world.gen;

public class ModWorldGeneration
{
    public static void generateModWorldGen()
    {
        ModOreGeneration.generateOres();
        ModVegetationGeneration.generateVeg();
        ModStructureGeneration.generateStructs();
        ModMiscGeneration.generateMisc();
    }
}
