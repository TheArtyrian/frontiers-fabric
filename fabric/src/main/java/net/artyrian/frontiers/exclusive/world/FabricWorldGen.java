package net.artyrian.frontiers.exclusive.world;

import net.artyrian.frontiers.exclusive.world.entity.*;

public class FabricWorldGen
{
    public static void generate()
    {
        FabricOreGeneration.generateOres();
        FabricVegetationGeneration.generateVeg();
        FabricStructureGeneration.generateStructs();
        FabricMiscGeneration.generateMisc();

        FabricEntitySpawning.register();
    }
}
