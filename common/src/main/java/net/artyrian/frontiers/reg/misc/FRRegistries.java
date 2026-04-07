package net.artyrian.frontiers.reg.misc;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.definition.block.entity.renderer.CragsPortalBlockEntityRenderer;
import net.artyrian.frontiers.definition.entity.types.mob.CrawlerEntity;
import net.artyrian.frontiers.definition.entity.types.mob.JungleSpiderEntity;
import net.artyrian.frontiers.definition.entity.types.passive.CrowEntity;
import net.artyrian.frontiers.definition.entity.types.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FREntity;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.world.FRFeaturesConfigured;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.registrars.VectorMobAttributes;
import net.vertisoft.vectorlib.agnostic.registrars.VectorPropertyReg;
import net.vertisoft.vectorlib.agnostic.registrars.VectorToolActions;

import java.util.List;
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

    public static class MapDecor
    {
        private static final int NO_MAP_COLOR = -1;

        public static final Holder<MapDecorationType> TOWER = registerIcon(
                "white_tower", "white_tower", true, 0x6D6689, false, true);

        private static Holder<MapDecorationType> registerIcon(String name, String assetId, boolean showOnItemFrame, int mapColor, boolean explorationMapElement, boolean trackCount)
        {
            MapDecorationType mapDecor = new MapDecorationType(Frontiers.id(assetId), showOnItemFrame, mapColor, trackCount, explorationMapElement);
            return VectorLib.REGISTRY.registerHolder(Frontiers.MOD_ID, name, BuiltInRegistries.MAP_DECORATION_TYPE, () -> mapDecor);
        }

        public static void register() { }
    }

    public static class MobAttributes
    {
        public static void register()
        {
            VectorMobAttributes.add(FREntity.CRAWLER, CrawlerEntity.createAttr().build());
            VectorMobAttributes.add(FREntity.JUNGLE_SPIDER, JungleSpiderEntity.createAttr().build());
            VectorMobAttributes.add(FREntity.PUMPKIN_GOLEM, PumpkinGolemEntity.createAttr().build());
            VectorMobAttributes.add(FREntity.CROW, CrowEntity.createAttr().build());
            VectorMobAttributes.add(FREntity.GOLDEN_CHICKEN, Chicken.createAttributes().build());
        }
    }

    public static class ToolActions
    {
        public static void register()
        {
            VectorToolActions.addStrippable(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());
            VectorToolActions.addStrippable(FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());

            VectorToolActions.addStrippable(FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
            VectorToolActions.addStrippable(FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
        }
    }

    public static class FurnaceFuels
    {
        public static void register()
        {
            VectorPropertyReg.Fuel.add(FRItems.BRIMTAN_NUGGET.get(), 2.0);
            VectorPropertyReg.Fuel.add(FRItems.BRIMTAN_INGOT.get(), 24.0);

            VectorPropertyReg.Fuel.add(FRBlocks.SLIME_TRAIL.get(), 0.5);

            VectorPropertyReg.Fuel.add(FRItems.ONYX_BONE.get(), 12.0);
            VectorPropertyReg.Fuel.add(FRItems.ONYX_MEAL.get(), 3.0);
            VectorPropertyReg.Fuel.add(FRBlocks.ONYX_BONE_BLOCK.get(), 24.0);
        }
    }

    public static class Splash
    {
        private static final List<String> HALLOWEEN_SPLASHES = Lists.newArrayList(
                "OOoooOOOoooo! Spooky!",
                "It's Spooky Month!",
                "Carve a pumpkin, Junior!",
                "Mobs with pumpkin heads!",
                "Dress up as something neat!",
                "Trick or treat!",
                "It's almost time for Halloween! Fahaha!",
                "Don't come to my house or else I'll suck your blood!",
                "Are you guys going trick-or-treating???",
                "Ooh, a piece of candy!",
                "Afraid of the big, black cat!",
                "Go find a Swamp Hut!",
                "Blighted Birch reigns supreme!",
                "Also try Wega's Challenge!",
                "Take ONE!",
                "Take TWO!",
                "2spoopy4me",
                "Just the facts!"
        );

        public static void register()
        {
            // Splash text list
            VectorLib.SYSTEM.SPLASHES.registerTextList(Frontiers.MOD_ID, "texts/splashes.txt");

            // Customs
            VectorLib.SYSTEM.SPLASHES.registerSpecial((random) -> new SplashRenderer("Pre-beta...?!"),
                    () -> Frontiers.EVENTS.IS_APRIL_FOOLS);
            VectorLib.SYSTEM.SPLASHES.registerSpecial((random) -> new SplashRenderer("Happy birthday, Artyrian!"),
                    () -> Frontiers.EVENTS.IS_THE_WORST_DAY_EVER);
            VectorLib.SYSTEM.SPLASHES.registerSpecial((random) -> new SplashRenderer("Happy birthday, Xenona!"),
                    () -> Frontiers.EVENTS.IS_XENS_BDAY);
            VectorLib.SYSTEM.SPLASHES.registerSpecial((random) -> new SplashRenderer("Happy birthday, Yurjezich!"),
                    () -> Frontiers.EVENTS.IS_WES_BDAY);
            VectorLib.SYSTEM.SPLASHES.registerSpecial((random) -> new SplashRenderer("Happy birthday, Hecco!"),
                    () -> Frontiers.EVENTS.IS_HECCO_BDAY);
            VectorLib.SYSTEM.SPLASHES.registerSpecial((random) -> new SplashRenderer(HALLOWEEN_SPLASHES.get(random.nextInt(HALLOWEEN_SPLASHES.size()))),
                    () -> Frontiers.EVENTS.IS_HALLOWEEN);
        }
    }

    public static class Sapling
    {
        public static final TreeGrower BLIGHTED_BIRCH = new TreeGrower(
                "frontiers_blighted_birch",
                Optional.empty(),
                Optional.of(FRFeaturesConfigured.BLIGHTED_BIRCH_KEY),
                Optional.empty()
        );
    }

    public static class Flammable
    {
        public static void register()
        {
            VectorPropertyReg.Fire.add(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), 5, 5);
            VectorPropertyReg.Fire.add(FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), 5, 5);
            VectorPropertyReg.Fire.add(FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), 5, 5);
            VectorPropertyReg.Fire.add(FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), 5, 5);
            VectorPropertyReg.Fire.add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get(), 5, 5);
            VectorPropertyReg.Fire.add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), 5, 5);

            VectorPropertyReg.Fire.add(FRBlocks.BLIGHTED_BIRCH_PLANKS.get(), 5, 20);
            VectorPropertyReg.Fire.add(FRBlocks.BLIGHTED_BIRCH_STAIRS.get(), 5, 20);
            VectorPropertyReg.Fire.add(FRBlocks.BLIGHTED_BIRCH_SLAB.get(), 5, 20);
            VectorPropertyReg.Fire.add(FRBlocks.BLIGHTED_BIRCH_FENCE.get(), 5, 20);
            VectorPropertyReg.Fire.add(FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), 5, 20);

            VectorPropertyReg.Fire.add(FRBlocks.BLIGHTED_BIRCH_LEAVES.get(), 30, 60);

            VectorPropertyReg.Fire.add(FRBlocks.ROSE.get(), 60, 100);
            VectorPropertyReg.Fire.add(FRBlocks.ANCIENT_ROSE.get(), 60, 100);
            VectorPropertyReg.Fire.add(FRBlocks.VIOLET_ROSE.get(), 60, 100);
            VectorPropertyReg.Fire.add(FRBlocks.ANCIENT_ROSE_BUSH.get(), 60, 100);
            VectorPropertyReg.Fire.add(FRBlocks.VIOLET_ROSE_BUSH.get(), 60, 100);
            VectorPropertyReg.Fire.add(FRBlocks.SNOW_DAHLIA.get(), 60, 100);
            VectorPropertyReg.Fire.add(FRBlocks.EXPERIWINKLE.get(), 60, 100);

            VectorPropertyReg.Fire.add(FRBlocks.PHANTASMIC_TNT.get(), 15, 100);

            VectorPropertyReg.Fire.add(FRBlocks.SLIME_TRAIL.get(), 60, 100);
            VectorPropertyReg.Fire.add(FRBlocks.SLIME_BULB.get(), 60, 100);

            VectorPropertyReg.Fire.add(FRBlocks.OAK_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(FRBlocks.DARK_OAK_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(FRBlocks.BIRCH_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(FRBlocks.SPRUCE_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(FRBlocks.JUNGLE_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(FRBlocks.ACACIA_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(FRBlocks.MANGROVE_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(FRBlocks.AZALEA_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(FRBlocks.CHERRY_WREATH.get(), 30, 60);
            VectorPropertyReg.Fire.add(FRBlocks.BLIGHTED_BIRCH_WREATH.get(), 30, 60);

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
            VectorPropertyReg.Compost.add(FRItems.ANCIENT_ROSE_SEED.get(), VectorPropertyReg.Compost.TINY);
            VectorPropertyReg.Compost.add(FRBlocks.BLIGHTED_BIRCH_LEAVES.get(), VectorPropertyReg.Compost.TINY);

            VectorPropertyReg.Compost.add(FRBlocks.ROSE.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(FRBlocks.VIOLET_ROSE.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(FRBlocks.VIOLET_ROSE_BUSH.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(FRBlocks.SNOW_DAHLIA.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(FRBlocks.FUNGAL_DAFFODIL.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(FRBlocks.CRIMCONE.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(FRBlocks.CARVED_MELON.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(FRItems.WARPED_WART.get(), VectorPropertyReg.Compost.MED);

            VectorPropertyReg.Compost.add(FRBlocks.OAK_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(FRBlocks.DARK_OAK_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(FRBlocks.BIRCH_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(FRBlocks.SPRUCE_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(FRBlocks.JUNGLE_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(FRBlocks.ACACIA_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(FRBlocks.MANGROVE_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(FRBlocks.AZALEA_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(FRBlocks.CHERRY_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(FRBlocks.BLIGHTED_BIRCH_WREATH.get(), VectorPropertyReg.Compost.HALF);

            VectorPropertyReg.Compost.add(FRBlocks.SUGAR_CANE_BLOCK.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(FRBlocks.COCOA_BEAN_BLOCK.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(FRItems.LEVI_ROLL.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(FRBlocks.ANCIENT_ROSE.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(FRBlocks.ANCIENT_ROSE_BUSH.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(FRBlocks.WHITE_PUMPKIN.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(FRBlocks.FUNGAL_DAFFODIL_BLOCK.get(), VectorPropertyReg.Compost.LARGE);

            VectorPropertyReg.Compost.add(FRItems.TRUFFLE.get(), VectorPropertyReg.Compost.MAX);
            VectorPropertyReg.Compost.add(FRItems.EXPERIWINKLE_BULB.get(), VectorPropertyReg.Compost.MAX);
            VectorPropertyReg.Compost.add(FRBlocks.EXPERIWINKLE.get(), VectorPropertyReg.Compost.MAX);

            if (Frontiers.BOUNTIFUL_FARES_LOADED)
            {
                VectorPropertyReg.Compost.add(BFBlock.HOARY_WREATH.get(), VectorPropertyReg.Compost.HALF);
                VectorPropertyReg.Compost.add(BFBlock.WALNUT_WREATH.get(), VectorPropertyReg.Compost.HALF);
                VectorPropertyReg.Compost.add(BFBlock.APPLE_WREATH.get(), VectorPropertyReg.Compost.HALF);
                VectorPropertyReg.Compost.add(BFBlock.ORANGE_WREATH.get(), VectorPropertyReg.Compost.HALF);
                VectorPropertyReg.Compost.add(BFBlock.LEMON_WREATH.get(), VectorPropertyReg.Compost.HALF);
                VectorPropertyReg.Compost.add(BFBlock.PLUM_WREATH.get(), VectorPropertyReg.Compost.HALF);
                VectorPropertyReg.Compost.add(BFBlock.GOLDEN_WREATH.get(), VectorPropertyReg.Compost.HALF);
            }
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
