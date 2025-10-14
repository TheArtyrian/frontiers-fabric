package net.artyrian.frontiers.rendering;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers
{
    public static final EntityModelLayer PUMPKIN_GOLEM = new EntityModelLayer(Identifier.of(Frontiers.MOD_ID, "pumpkin_golem"), "main");
    public static final EntityModelLayer CROW = new EntityModelLayer(Identifier.of(Frontiers.MOD_ID, "crow"), "main");
}
