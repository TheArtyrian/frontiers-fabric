package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.world.feature.eboncork.EboncorkSpikeFeature;
import net.artyrian.frontiers.definition.world.feature.mushroom.HugeFungalDaffodilFeature;
import net.artyrian.frontiers.definition.world.feature.slime_trail.SlimeTrailFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class ModFeature
{
    public static final Supplier<Feature<NoneFeatureConfiguration>> SLIME_TRAIL = register("slime_trail", () ->
            new SlimeTrailFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> EBONCORK_SPIKE = register("eboncork_spike", () ->
            new EboncorkSpikeFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<HugeMushroomFeatureConfiguration>> HUGE_FUNGAL_DAFFODIL = register("huge_fungal_daffodil", () ->
            new HugeFungalDaffodilFeature(HugeMushroomFeatureConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> Supplier<F> register(String name, Supplier<F> feature)
    {
        return VectorLib.REGISTRY.registerFeature(Frontiers.MOD_ID, name, feature);
    }

    public static void registerFeatures()
    {

    }
}
