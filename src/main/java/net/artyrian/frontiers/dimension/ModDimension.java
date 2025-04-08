package net.artyrian.frontiers.dimension;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimension
{
    public static final RegistryKey<DimensionOptions> CRAGS_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(Frontiers.MOD_ID, "crags"));
    public static final RegistryKey<World> CRAGS_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(Frontiers.MOD_ID, "crags"));
    public static final RegistryKey<DimensionType> CRAGS_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(Frontiers.MOD_ID, "crags_type"));

    public static void bootstrapType(Registerable<DimensionType> context)
    {
        context.register(CRAGS_TYPE, new DimensionType(
                OptionalLong.of(18000L),
                false,
                true,
                true,
                false,
                16.0,
                false,
                false,
                -128,
                512,
                384,
                ModTags.Blocks.INFINIBURN_CRAGS,
                DimensionTypes.THE_NETHER_ID,
                0.0F,
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)
        ));
    }
}
