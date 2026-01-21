package net.artyrian.frontiers.reg.misc;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.renderer.CragsPortalBlockEntityRenderer;
import net.artyrian.frontiers.definition.entity.mob.CrawlerEntity;
import net.artyrian.frontiers.definition.entity.mob.JungleSpiderEntity;
import net.artyrian.frontiers.definition.entity.passive.CrowEntity;
import net.artyrian.frontiers.definition.entity.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.vertisoft.vectorlib.agnostic.registrars.VectorMobAttributes;
import net.vertisoft.vectorlib.agnostic.registrars.VectorToolActions;

public class FRRegistries
{
    public static class HeartType
    {
        static { Gui.HeartType.values(); }

        public static Gui.HeartType FRONTIERS_PINK;
        public static Gui.HeartType FRONTIERS_PURPLE;
        public static Gui.HeartType FRONTIERS_ONFIRE;
        public static Gui.HeartType FRONTIERS_STORM;
        public static Gui.HeartType FRONTIERS_CONTAINER_STORM;
    }

    public static class Rarities
    {
        static { Rarity.values(); }

        public static Rarity FRONTIERS_MYTHICAL;
        public static Rarity FRONTIERS_LEGENDARY;
        public static Rarity FRONTIERS_UNREAL;
    }

    public static class NoteBlockInst
    {
        static { NoteBlockInstrument.values(); }

        public static NoteBlockInstrument FRONTIERS_LOG_DRUM;
        public static NoteBlockInstrument FRONTIERS_ICE_BELL;
        public static NoteBlockInstrument FRONTIERS_HARPSICHORD;
        public static NoteBlockInstrument FRONTIERS_STEEL_DRUM;
        public static NoteBlockInstrument FRONTIERS_ROBOLUNG;
        public static NoteBlockInstrument FRONTIERS_JESKOLA;
    }

    public static class MobAttributes
    {
        public static void register()
        {
            VectorMobAttributes.add(ModEntity.CRAWLER, CrawlerEntity.createAttr().build());
            VectorMobAttributes.add(ModEntity.JUNGLE_SPIDER, JungleSpiderEntity.createAttr().build());
            VectorMobAttributes.add(ModEntity.PUMPKIN_GOLEM, PumpkinGolemEntity.createAttr().build());
            VectorMobAttributes.add(ModEntity.CROW, CrowEntity.createAttr().build());
            VectorMobAttributes.add(ModEntity.GOLDEN_CHICKEN, Chicken.createAttributes().build());
        }
    }

    public static class ToolActions
    {
        public static void register()
        {
            VectorToolActions.addStrippable(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());
            VectorToolActions.addStrippable(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());

            VectorToolActions.addStrippable(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
            VectorToolActions.addStrippable(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
        }
    }

    public static class ModelLayers
    {
        public static final ModelLayerLocation PUMPKIN_GOLEM = new ModelLayerLocation(Frontiers.id("pumpkin_golem"), "main");
        public static final ModelLayerLocation CROW = new ModelLayerLocation(Frontiers.id("crow"), "main");
    }

    public static class RenderLayers
    {
        private static final RenderType CRAGS_PORTAL = RenderType.create(
                "frontiers_crags_portal",
                DefaultVertexFormat.POSITION,
                VertexFormat.Mode.QUADS,
                1536,
                false,
                false,
                RenderType.CompositeState.builder()
                        .setShaderState(RenderStateShard.RENDERTYPE_END_PORTAL_SHADER)
                        .setTextureState(
                                RenderStateShard.MultiTextureStateShard.builder()
                                        .add(CragsPortalBlockEntityRenderer.FUZZ_TEXTURE, false, false)
                                        .add(CragsPortalBlockEntityRenderer.PORTAL_TEXTURE, false, false)
                                        .build()
                        )
                        .createCompositeState(false)
        );

        public static RenderType getCragsPortal()
        {
            return CRAGS_PORTAL;
        }
    }
}
