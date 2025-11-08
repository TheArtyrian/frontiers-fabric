package net.artyrian.frontiers.world.feature;

import net.artyrian.frontiers.world.feature.eboncork_spikes.EboncorkSpikeFeature;
import net.artyrian.frontiers.world.feature.slime_trail.SlimeTrailFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModFeature
{
    public static final Feature<DefaultFeatureConfig> SLIME_TRAIL = register("slime_trail", new SlimeTrailFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> EBONCORK_SPIKE = register("eboncork_spike", new EboncorkSpikeFeature(DefaultFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature)
    {
        return Registry.register(Registries.FEATURE, name, feature);
    }

    public static void registerFeatures()
    {

    }
}
