package net.artyrian.frontiers.reg.misc;

import com.google.common.collect.Lists;
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
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
            VectorPropertyReg.Fuel.add(ModItem.BRIMTAN_NUGGET.get(), 2);
            VectorPropertyReg.Fuel.add(ModItem.BRIMTAN_INGOT.get(), 24);
            VectorPropertyReg.Fuel.add(ModBlocks.BRIMTAN_BLOCK.get(), 216);

            VectorPropertyReg.Fuel.add(ModItem.ONYX_BONE.get(), 12);
            VectorPropertyReg.Fuel.add(ModItem.ONYX_MEAL.get(), 3);
            VectorPropertyReg.Fuel.add(ModBlocks.ONYX_BONE_BLOCK.get(), 24);
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
            VectorPropertyReg.Compost.add(ModItem.ANCIENT_ROSE_SEED.get(), VectorPropertyReg.Compost.TINY);
            VectorPropertyReg.Compost.add(ModBlocks.BLIGHTED_BIRCH_LEAVES.get(), VectorPropertyReg.Compost.TINY);

            VectorPropertyReg.Compost.add(ModBlocks.ROSE.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(ModBlocks.VIOLET_ROSE.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(ModBlocks.VIOLET_ROSE_BUSH.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(ModBlocks.SNOW_DAHLIA.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(ModBlocks.FUNGAL_DAFFODIL.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(ModBlocks.CRIMCONE.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(ModBlocks.CARVED_MELON.get(), VectorPropertyReg.Compost.MED);
            VectorPropertyReg.Compost.add(ModItem.WARPED_WART.get(), VectorPropertyReg.Compost.MED);

            VectorPropertyReg.Compost.add(ModBlocks.OAK_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(ModBlocks.DARK_OAK_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(ModBlocks.BIRCH_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(ModBlocks.SPRUCE_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(ModBlocks.JUNGLE_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(ModBlocks.ACACIA_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(ModBlocks.MANGROVE_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(ModBlocks.AZALEA_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(ModBlocks.CHERRY_WREATH.get(), VectorPropertyReg.Compost.HALF);
            VectorPropertyReg.Compost.add(ModBlocks.BLIGHTED_BIRCH_WREATH.get(), VectorPropertyReg.Compost.HALF);

            VectorPropertyReg.Compost.add(ModBlocks.SUGAR_CANE_BLOCK.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(ModBlocks.COCOA_BEAN_BLOCK.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(ModItem.LEVI_ROLL.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(ModBlocks.ANCIENT_ROSE.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(ModBlocks.ANCIENT_ROSE_BUSH.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(ModBlocks.WHITE_PUMPKIN.get(), VectorPropertyReg.Compost.LARGE);
            VectorPropertyReg.Compost.add(ModBlocks.FUNGAL_DAFFODIL_BLOCK.get(), VectorPropertyReg.Compost.LARGE);

            VectorPropertyReg.Compost.add(ModItem.TRUFFLE.get(), VectorPropertyReg.Compost.MAX);
            VectorPropertyReg.Compost.add(ModItem.EXPERIWINKLE_BULB.get(), VectorPropertyReg.Compost.MAX);
            VectorPropertyReg.Compost.add(ModBlocks.EXPERIWINKLE.get(), VectorPropertyReg.Compost.MAX);

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
