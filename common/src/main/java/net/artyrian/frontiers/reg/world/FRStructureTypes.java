package net.artyrian.frontiers.reg.world;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.world.structure.white_tower.WhiteTowerStructure;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class FRStructureTypes
{
    public static final Supplier<StructureType<WhiteTowerStructure>> WHITE_TOWER = register("white_tower", () -> WhiteTowerStructure.CODEC);

    private static <T extends Structure> Supplier<StructureType<T>> register(String id, Supplier<MapCodec<T>> codec)
    {
        return VectorLib.REGISTRY.registerStructureType(Frontiers.MOD_ID, id, codec);
    }

    public static void registerStrType()
    {

    }
}
