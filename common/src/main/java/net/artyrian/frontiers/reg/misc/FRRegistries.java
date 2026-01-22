package net.artyrian.frontiers.reg.misc;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.definition.block.entity.renderer.CragsPortalBlockEntityRenderer;
import net.artyrian.frontiers.definition.entity.mob.CrawlerEntity;
import net.artyrian.frontiers.definition.entity.mob.JungleSpiderEntity;
import net.artyrian.frontiers.definition.entity.passive.CrowEntity;
import net.artyrian.frontiers.definition.entity.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.vertisoft.vectorlib.agnostic.registrars.VectorMobAttributes;
import net.vertisoft.vectorlib.agnostic.registrars.VectorPropertyReg;
import net.vertisoft.vectorlib.agnostic.registrars.VectorToolActions;

import java.util.Optional;

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

    public static class WorldEntryReason
    {
        static { ReceivingLevelScreen.Reason.values(); }

        public static ReceivingLevelScreen.Reason CRAGS;
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

    public static class FurnaceFuels
    {
        public static void register()
        {
            VectorPropertyReg.Fuel.add(ModItem.BRIMTAN_INGOT.get(), 24);
            VectorPropertyReg.Fuel.add(ModItem.ONYX_BONE.get(), 12);
            VectorPropertyReg.Fuel.add(ModItem.ONYX_MEAL.get(), 4);
        }
    }

    public static class Sapling
    {
        public static final TreeGrower BLIGHTED_BIRCH = new TreeGrower(
                "frontiers_blighted_birch",
                Optional.empty(),
                Optional.of(ModConfiguredFeatures.BLIGHTED_BIRCH_KEY),
                Optional.empty()
        );
    }

    public static class Flammable
    {
        public static void register()
        {
            VectorPropertyReg.Fire.add(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), 5, 5);
            VectorPropertyReg.Fire.add(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), 5, 5);
            VectorPropertyReg.Fire.add(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), 5, 5);
            VectorPropertyReg.Fire.add(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), 5, 5);
            VectorPropertyReg.Fire.add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get(), 5, 5);
            VectorPropertyReg.Fire.add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), 5, 5);

            VectorPropertyReg.Fire.add(ModBlocks.BLIGHTED_BIRCH_PLANKS.get(), 5, 20);
            VectorPropertyReg.Fire.add(ModBlocks.BLIGHTED_BIRCH_STAIRS.get(), 5, 20);
            VectorPropertyReg.Fire.add(ModBlocks.BLIGHTED_BIRCH_SLAB.get(), 5, 20);
            VectorPropertyReg.Fire.add(ModBlocks.BLIGHTED_BIRCH_FENCE.get(), 5, 20);
            VectorPropertyReg.Fire.add(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), 5, 20);

            VectorPropertyReg.Fire.add(ModBlocks.BLIGHTED_BIRCH_LEAVES.get(), 30, 60);

            VectorPropertyReg.Fire.add(ModBlocks.ROSE.get(), 60, 100);
            VectorPropertyReg.Fire.add(ModBlocks.ANCIENT_ROSE.get(), 60, 100);
            VectorPropertyReg.Fire.add(ModBlocks.VIOLET_ROSE.get(), 60, 100);
            VectorPropertyReg.Fire.add(ModBlocks.ANCIENT_ROSE_BUSH.get(), 60, 100);
            VectorPropertyReg.Fire.add(ModBlocks.VIOLET_ROSE_BUSH.get(), 60, 100);
            VectorPropertyReg.Fire.add(ModBlocks.SNOW_DAHLIA.get(), 60, 100);
            VectorPropertyReg.Fire.add(ModBlocks.EXPERIWINKLE.get(), 60, 100);

            VectorPropertyReg.Fire.add(ModBlocks.PHANTASMIC_TNT.get(), 15, 100);

            VectorPropertyReg.Fire.add(ModBlocks.SLIME_TRAIL.get(), 60, 100);
            VectorPropertyReg.Fire.add(ModBlocks.SLIME_BULB.get(), 60, 100);

            VectorPropertyReg.Fire.add(ModBlocks.OAK_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(ModBlocks.DARK_OAK_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(ModBlocks.BIRCH_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(ModBlocks.SPRUCE_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(ModBlocks.JUNGLE_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(ModBlocks.ACACIA_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(ModBlocks.MANGROVE_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(ModBlocks.AZALEA_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(ModBlocks.CHERRY_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(ModBlocks.BLIGHTED_BIRCH_WREATH.get(), 30, 60);

            if (Frontiers.BOUNTIFUL_FARES_LOADED)
            {
                VectorPropertyReg.Fire.add(BFBlock.HOARY_WREATH.get(), 30, 60);
                VectorPropertyReg.Fire.add(BFBlock.WALNUT_WREATH.get(), 30, 60);
                VectorPropertyReg.Fire.add(BFBlock.APPLE_WREATH.get(), 30, 60);
                VectorPropertyReg.Fire.add(BFBlock.ORANGE_WREATH.get(), 30, 60);
                VectorPropertyReg.Fire.add(BFBlock.LEMON_WREATH.get(), 30, 60);
                VectorPropertyReg.Fire.add(BFBlock.PLUM_WREATH.get(), 30, 60);
                VectorPropertyReg.Fire.add(BFBlock.GOLDEN_WREATH.get(), 30, 60);
            }
        }
    }

    public static class Compostable
    {
        public static void register()
        {
            VectorPropertyReg.Compost.add(ModItem.ANCIENT_ROSE_SEED.get(),0.30f);

            VectorPropertyReg.Compost.add(ModBlocks.ROSE.get(),0.65f);
            VectorPropertyReg.Compost.add(ModBlocks.VIOLET_ROSE.get(),0.65f);
            VectorPropertyReg.Compost.add(ModBlocks.VIOLET_ROSE_BUSH.get(),0.65f);
            VectorPropertyReg.Compost.add(ModBlocks.SNOW_DAHLIA.get(),0.65f);
            VectorPropertyReg.Compost.add(ModBlocks.FUNGAL_DAFFODIL.get(),0.65f);
            VectorPropertyReg.Compost.add(ModBlocks.CRIMCONE.get(),0.65f);
            VectorPropertyReg.Compost.add(ModBlocks.CARVED_MELON.get(),0.65f);
            VectorPropertyReg.Compost.add(ModItem.WARPED_WART.get(),0.65f);

            VectorPropertyReg.Compost.add(ModBlocks.SUGAR_CANE_BLOCK.get(),0.85f);
            VectorPropertyReg.Compost.add(ModBlocks.COCOA_BEAN_BLOCK.get(),0.85f);
            VectorPropertyReg.Compost.add(ModItem.LEVI_ROLL.get(),0.85f);
            VectorPropertyReg.Compost.add(ModBlocks.ANCIENT_ROSE.get(),0.85f);
            VectorPropertyReg.Compost.add(ModBlocks.ANCIENT_ROSE_BUSH.get(),0.85f);
            VectorPropertyReg.Compost.add(ModBlocks.WHITE_PUMPKIN.get(),0.85f);
            VectorPropertyReg.Compost.add(ModBlocks.FUNGAL_DAFFODIL_BLOCK.get(),0.85f);

            VectorPropertyReg.Compost.add(ModItem.TRUFFLE.get(),1.0f);
            VectorPropertyReg.Compost.add(ModItem.EXPERIWINKLE_BULB.get(),1.0f);
            VectorPropertyReg.Compost.add(ModBlocks.EXPERIWINKLE.get(),1.0f);
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
