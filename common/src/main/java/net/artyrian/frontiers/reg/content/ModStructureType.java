package net.artyrian.frontiers.reg.content;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.world.structure.white_tower.WhiteTowerStructure;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class ModStructureType
{
    public static final StructureType<WhiteTowerStructure> WHITE_TOWER = register("white_tower", WhiteTowerStructure.CODEC);

    private static <S extends Structure> StructureType<S> register(String id, MapCodec<S> codec)
    {
        return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, id), () -> codec);
    }

    public static void registerStrType()
    {

    }
}
