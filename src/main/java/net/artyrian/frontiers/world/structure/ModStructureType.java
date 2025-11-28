package net.artyrian.frontiers.world.structure;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.world.structure.white_tower.WhiteTowerStructure;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class ModStructureType
{
    public static final StructureType<WhiteTowerStructure> WHITE_TOWER = register("white_tower", WhiteTowerStructure.CODEC);

    private static <S extends Structure> StructureType<S> register(String id, MapCodec<S> codec)
    {
        return Registry.register(Registries.STRUCTURE_TYPE, Identifier.of(Frontiers.MOD_ID, id), () -> codec);
    }

    public static void registerStrType()
    {

    }
}
