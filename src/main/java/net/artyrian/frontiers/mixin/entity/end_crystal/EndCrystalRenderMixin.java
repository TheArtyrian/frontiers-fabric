package net.artyrian.frontiers.mixin.entity.end_crystal;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EndCrystalEntityRenderer.class)
public class EndCrystalRenderMixin
{
    @Unique private static final Identifier TEXTURE_CRACKED1 = Identifier.of(Frontiers.MOD_ID,"textures/entity/end_crystal/end_crystal_damaged1.png");
    @Unique private static final RenderLayer LAYER_CRACKED1 = RenderLayer.getEntityCutoutNoCull(TEXTURE_CRACKED1);
    @Unique private static final Identifier TEXTURE_CRACKED2 = Identifier.of(Frontiers.MOD_ID,"textures/entity/end_crystal/end_crystal_damaged2.png");
    @Unique private static final RenderLayer LAYER_CRACKED2 = RenderLayer.getEntityCutoutNoCull(TEXTURE_CRACKED2);
    @Unique private static final Identifier TEXTURE_FRIENDLY = Identifier.of(Frontiers.MOD_ID,"textures/entity/end_crystal/friendly_end_crystal.png");
    @Unique private static final RenderLayer LAYER_FRIENDLY = RenderLayer.getEntityCutoutNoCull(TEXTURE_FRIENDLY);


}
