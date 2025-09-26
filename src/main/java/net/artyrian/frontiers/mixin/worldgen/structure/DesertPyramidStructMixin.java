package net.artyrian.frontiers.mixin.worldgen.structure;

import net.minecraft.world.gen.structure.DesertPyramidStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DesertPyramidStructure.class)
public abstract class DesertPyramidStructMixin
{
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 21))
    private static int modWH(int og) { return 42; }
}
