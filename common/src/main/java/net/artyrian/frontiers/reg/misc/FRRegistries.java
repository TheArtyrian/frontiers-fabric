package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.mob.CrawlerEntity;
import net.artyrian.frontiers.definition.entity.mob.JungleSpiderEntity;
import net.artyrian.frontiers.definition.entity.passive.CrowEntity;
import net.artyrian.frontiers.definition.entity.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.vertisoft.vectorlib.agnostic.registrars.VectorMobAttributes;

public class FRRegistries
{
    public static class NoteBlockInst
    {
        static
        {
            NoteBlockInstrument.values();        // Ensure class is loaded.
        }

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

    public static class ModelLayers
    {
        public static final ModelLayerLocation PUMPKIN_GOLEM = new ModelLayerLocation(Frontiers.id("pumpkin_golem"), "main");
        public static final ModelLayerLocation CROW = new ModelLayerLocation(Frontiers.id("crow"), "main");
    }
}
