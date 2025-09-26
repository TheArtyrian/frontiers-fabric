package net.artyrian.frontiers.world.gen;

import net.artyrian.frontiers.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGen
{
    public static final SaplingGenerator BLIGHTED_BIRCH = new SaplingGenerator(
            "frontiers_blighted_birch",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.BLIGHTED_BIRCH_KEY),
            Optional.empty()
    );
}
