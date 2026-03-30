package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.OptionalLong;

public class ModDimension
{
    public static final ResourceKey<LevelStem> CRAGS_KEY = ResourceKey.create(
            Registries.LEVEL_STEM, Frontiers.id(Frontiers.MOD_ID, "crags"));
    public static final ResourceKey<Level> CRAGS_LEVEL_KEY = ResourceKey.create(
            Registries.DIMENSION, Frontiers.id(Frontiers.MOD_ID, "crags"));
    public static final ResourceKey<DimensionType> CRAGS_TYPE = ResourceKey.create(
            Registries.DIMENSION_TYPE, Frontiers.id(Frontiers.MOD_ID, "crags_type"));

    public static void bootstrapType(BootstrapContext<DimensionType> context)
    {
        context.register(CRAGS_TYPE, new DimensionType(
                OptionalLong.of(18000L),
                false,
                true,
                true,
                false,
                8.0,
                false,
                false,
                -128,
                512,
                384,
                ModTags.Blocks.INFINIBURN_CRAGS,
                BuiltinDimensionTypes.NETHER_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(true, false, UniformInt.of(0, 7), 0)
        ));
    }
}
