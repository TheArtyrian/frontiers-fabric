package net.artyrian.frontiers.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.artyrian.frontiers.definition.block.custom.TowerSpawnerBlock;
import net.artyrian.frontiers.definition.block.custom.TowerWatcherBlock;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.misc.ModArmorMaterials;
import net.artyrian.frontiers.reg.misc.ModBlockProperties;
import net.minecraft.core.Holder;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import java.util.List;
import java.util.Map;

public class ModelHelper
{
    public static final ResourceLocation TRIM_TYPE = ResourceLocation.withDefaultNamespace("trim_type");
    private static final List<ModelHelper.TrimMaterial> TRIM_MATERIALS = List.of(
            // Frontiers
            new ModelHelper.TrimMaterial("cobalt", 0.001F, Map.of(ModArmorMaterials.COBALT_ARMOR_MATERIAL, "cobalt_darker")),
            new ModelHelper.TrimMaterial("verdinite", 0.002F, Map.of(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, "verdinite_darker")),
            new ModelHelper.TrimMaterial("vivulite", 0.003F, Map.of(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, "vivulite_darker")),
            new ModelHelper.TrimMaterial("frostite", 0.004F, Map.of(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, "frostite_darker")),
            new ModelHelper.TrimMaterial("mourning_gold", 0.005F, Map.of(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, "mourning_gold_darker")),
            new ModelHelper.TrimMaterial("brimtan", 0.006F, Map.of(ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, "brimtan_darker")),

            // Vanilla
            new ModelHelper.TrimMaterial("quartz", 0.1F, Map.of()),
            new ModelHelper.TrimMaterial("iron", 0.2F, Map.of(ArmorMaterials.IRON, "iron_darker")),
            new ModelHelper.TrimMaterial("netherite", 0.3F, Map.of(ArmorMaterials.NETHERITE, "netherite_darker")),
            new ModelHelper.TrimMaterial("redstone", 0.4F, Map.of()),
            new ModelHelper.TrimMaterial("copper", 0.5F, Map.of()),
            new ModelHelper.TrimMaterial("gold", 0.6F, Map.of(ArmorMaterials.GOLD, "gold_darker")),
            new ModelHelper.TrimMaterial("emerald", 0.7F, Map.of()),
            new ModelHelper.TrimMaterial("diamond", 0.8F, Map.of(ArmorMaterials.DIAMOND, "diamond_darker")),
            new ModelHelper.TrimMaterial("lapis", 0.9F, Map.of()),
            new ModelHelper.TrimMaterial("amethyst", 1.0F, Map.of())
    );
    static record TrimMaterial(String name, float itemModelIndex, Map<Holder<ArmorMaterial>, String> overrideArmorMaterials)
    {
        public String getAppliedName(Holder<ArmorMaterial> armorMaterial) {
            return this.overrideArmorMaterials.getOrDefault(armorMaterial, this.name);
        }
    }

    /** Registers a lumen-type model for the given block. */
    public static void registerLumen(Block type, BlockModelGenerators generator)
    {
        ResourceLocation identifier = TexturedModel.CUBE.create(type, generator.modelOutput);
        ResourceLocation identifier2 = generator.createSuffixedVariant(type, "_power_1", ModelTemplates.CUBE_ALL, TextureMapping::cube);
        ResourceLocation identifier3 = generator.createSuffixedVariant(type, "_power_2", ModelTemplates.CUBE_ALL, TextureMapping::cube);
        generator.blockStateOutput
                .accept(MultiVariantGenerator.multiVariant(type)
                        .with(PropertyDispatch.property(ModBlockProperties.LUMEN_POWER)
                                .select(0, Variant.variant().with(VariantProperties.MODEL, identifier))
                                .select(1, Variant.variant().with(VariantProperties.MODEL, identifier2))
                                .select(2, Variant.variant().with(VariantProperties.MODEL, identifier3))
                        )
                );
    }

    /** Registers a tower watcher model. */
    public static void registerTowerWatcher(Block type, BlockModelGenerators generator)
    {
        ResourceLocation top = TextureMapping.getBlockTexture(type, "_top");
        ResourceLocation side = TextureMapping.getBlockTexture(type);
        ResourceLocation side_on = TextureMapping.getBlockTexture(type, "_on");

        ResourceLocation top_dead = TextureMapping.getBlockTexture(type, "_defeated_top");
        ResourceLocation side_dead = TextureMapping.getBlockTexture(type, "_defeated");

        TextureMapping disabled = new TextureMapping()
                .put(TextureSlot.END, top)
                .put(TextureSlot.SIDE, side);
        TextureMapping enabled = new TextureMapping()
                .put(TextureSlot.END, top)
                .put(TextureSlot.SIDE, side_on);
        TextureMapping defeated = new TextureMapping()
                .put(TextureSlot.END, top_dead)
                .put(TextureSlot.SIDE, side_dead);

        generator.blockStateOutput
                .accept(
                        MultiVariantGenerator.multiVariant(type)
                                .with(
                                        PropertyDispatch.properties(TowerWatcherBlock.ENABLED, TowerWatcherBlock.DEFEATED)
                                                .select(
                                                        false, false,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.CUBE_COLUMN.create(
                                                                                type,
                                                                                disabled,
                                                                                generator.modelOutput))
                                                )
                                                .select(
                                                        true, false,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.CUBE_COLUMN.create(
                                                                                ModelLocationUtils.getModelLocation(type, "_enabled"),
                                                                                enabled,
                                                                                generator.modelOutput)
                                                                )
                                                )
                                                .select(
                                                        false, true,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.CUBE_COLUMN.create(
                                                                                ModelLocationUtils.getModelLocation(type, "_defeated"),
                                                                                defeated,
                                                                                generator.modelOutput))
                                                )
                                                .select(
                                                        true, true,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.CUBE_COLUMN.create(
                                                                                ModelLocationUtils.getModelLocation(type, "_defeated_enabled"),
                                                                                defeated,
                                                                                generator.modelOutput)
                                                                )
                                                )
                                )
                );
    }

    /** Registers a tower vault-like model. */
    public static void registerTowerVault(Block type, BlockModelGenerators generator)
    {
        ResourceLocation top = TextureMapping.getBlockTexture(type, "_top");
        ResourceLocation side = TextureMapping.getBlockTexture(type, "_side");
        ResourceLocation front = TextureMapping.getBlockTexture(type, "_front");
        ResourceLocation bottom = TextureMapping.getBlockTexture(ModBlocks.TOWER_WATCHER.get(), "_top");

        TextureMapping basic = new TextureMapping()
                .put(TextureSlot.TOP, top)
                .put(TextureSlot.BOTTOM, bottom)
                .put(TextureSlot.SIDE, side)
                .put(TextureSlot.FRONT, front);

        generator.blockStateOutput
                .accept(
                        BlockModelGenerators.createSimpleBlock(type, ModelTemplates.VAULT.create(
                                        ModelLocationUtils.getModelLocation(type),
                                        basic,
                                        generator.modelOutput)
                        )
                );
    }

    /** Registers a tower spawner-like model. */
    public static void registerTowerSpawner(Block type, BlockModelGenerators generator)
    {
        ResourceLocation top = TextureMapping.getBlockTexture(type, "_top");
        ResourceLocation side = TextureMapping.getBlockTexture(type, "_side");
        ResourceLocation bottom = TextureMapping.getBlockTexture(ModBlocks.TOWER_WATCHER.get(), "_top");

        ResourceLocation enraged_top = TextureMapping.getBlockTexture(type, "_enraged_top");
        ResourceLocation enraged_side = TextureMapping.getBlockTexture(type, "_enraged_side");

        ResourceLocation defeated_top = TextureMapping.getBlockTexture(type, "_defeated_top");
        ResourceLocation defeated_side = TextureMapping.getBlockTexture(type, "_defeated_side");
        ResourceLocation defeated_bottom = TextureMapping.getBlockTexture(ModBlocks.TOWER_WATCHER.get(), "_defeated_top");

        TextureMapping basic = new TextureMapping()
                .put(TextureSlot.TOP, top)
                .put(TextureSlot.BOTTOM, bottom)
                .put(TextureSlot.SIDE, side)
                .put(TextureSlot.FRONT, side);
        TextureMapping enraged = new TextureMapping()
                .put(TextureSlot.TOP, enraged_top)
                .put(TextureSlot.BOTTOM, bottom)
                .put(TextureSlot.SIDE, enraged_side)
                .put(TextureSlot.FRONT, enraged_side);
        TextureMapping defeated = new TextureMapping()
                .put(TextureSlot.TOP, defeated_top)
                .put(TextureSlot.BOTTOM, defeated_bottom)
                .put(TextureSlot.SIDE, defeated_side)
                .put(TextureSlot.FRONT, defeated_side);

        generator.blockStateOutput
                .accept(
                        MultiVariantGenerator.multiVariant(type)
                                .with(
                                        PropertyDispatch.properties(TowerSpawnerBlock.ENRAGED, TowerSpawnerBlock.DEFEATED)
                                                .select(
                                                        true, false,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.VAULT.create(
                                                                                ModelLocationUtils.getModelLocation(type, "_enraged"),
                                                                                enraged,
                                                                                generator.modelOutput)
                                                                )
                                                )
                                                .select(
                                                        false, false,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.VAULT.create(
                                                                                type,
                                                                                basic,
                                                                                generator.modelOutput))
                                                )
                                                .select(
                                                        true, true,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.VAULT.create(
                                                                                ModelLocationUtils.getModelLocation(type, "_defeated_enraged"),
                                                                                defeated,
                                                                                generator.modelOutput)
                                                                )
                                                )
                                                .select(
                                                        false, true,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.VAULT.create(
                                                                                ModelLocationUtils.getModelLocation(type, "_defeated"),
                                                                                defeated,
                                                                                generator.modelOutput))
                                                )
                                )
                );
    }

    /** Registers a tower heart-like model. */
    public static void registerTowerHeart(Block type, BlockModelGenerators generator)
    {
        ResourceLocation top = TextureMapping.getBlockTexture(type, "_top");
        ResourceLocation side = TextureMapping.getBlockTexture(type, "_side");
        ResourceLocation bottom = TextureMapping.getBlockTexture(ModBlocks.TOWER_WATCHER.get(), "_top");

        TextureMapping basic = new TextureMapping()
                .put(TextureSlot.TOP, top)
                .put(TextureSlot.BOTTOM, bottom)
                .put(TextureSlot.SIDE, side);

        generator.blockStateOutput
                .accept(
                        BlockModelGenerators.createSimpleBlock(type, ModelTemplates.CUBE_BOTTOM_TOP_INNER_FACES.create(
                                ModelLocationUtils.getModelLocation(type),
                                basic,
                                generator.modelOutput)
                        )
                );
    }

    /** Registers a mushroom block with a custom inside */
    public static void registerCustomMushroomBlock(Block mushroomBlock, ResourceLocation insideTexture, BlockModelGenerators generator) {
        ResourceLocation identifier = ModelTemplates.SINGLE_FACE.create(mushroomBlock, TextureMapping.defaultTexture(mushroomBlock), generator.modelOutput);
        generator.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(mushroomBlock)
                                .with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, identifier))
                                .with(
                                        Condition.condition().term(BlockStateProperties.EAST, true),
                                        Variant.variant().with(VariantProperties.MODEL, identifier).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.SOUTH, true),
                                        Variant.variant().with(VariantProperties.MODEL, identifier).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.WEST, true),
                                        Variant.variant().with(VariantProperties.MODEL, identifier).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.UP, true),
                                        Variant.variant().with(VariantProperties.MODEL, identifier).with(VariantProperties.X_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.DOWN, true),
                                        Variant.variant().with(VariantProperties.MODEL, identifier).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)
                                )
                                .with(Condition.condition().term(BlockStateProperties.NORTH, false), Variant.variant().with(VariantProperties.MODEL, insideTexture))
                                .with(
                                        Condition.condition().term(BlockStateProperties.EAST, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, insideTexture)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.SOUTH, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, insideTexture)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.WEST, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, insideTexture)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.UP, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, insideTexture)
                                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R270)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.DOWN, false),
                                        Variant.variant()
                                                .with(VariantProperties.MODEL, insideTexture)
                                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                                                .with(VariantProperties.UV_LOCK, false)
                                )
                );
        generator.delegateItemModel(mushroomBlock, TexturedModel.CUBE.createWithSuffix(mushroomBlock, "_inventory", generator.modelOutput));
    }

    /** Registers a Monster Bakery model for the given block. */
    public static void registerMonsterBakery(Block type, BlockModelGenerators generator)
    {
        ResourceLocation top = TextureMapping.getBlockTexture(type, "_top");
        ResourceLocation side = TextureMapping.getBlockTexture(type, "_side");
        ResourceLocation side_on = TextureMapping.getBlockTexture(type, "_side_on");
        ResourceLocation bottom = TextureMapping.getBlockTexture(type, "_bottom");

        TextureMapping unlit = new TextureMapping()
                .put(TextureSlot.TOP, top)
                .put(TextureSlot.SIDE, side)
                .put(TextureSlot.BOTTOM, bottom);
        TextureMapping lit = new TextureMapping()
                .put(TextureSlot.TOP, top)
                .put(TextureSlot.SIDE, side_on)
                .put(TextureSlot.BOTTOM, bottom);

        generator.blockStateOutput
                .accept(
                        MultiVariantGenerator.multiVariant(type)
                                .with(
                                        PropertyDispatch.property(BlockStateProperties.LIT)
                                                .select(
                                                        false,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.CUBE_BOTTOM_TOP_INNER_FACES.create(
                                                                                type,
                                                                                unlit,
                                                                                generator.modelOutput))
                                                )
                                                .select(
                                                        true,
                                                        Variant.variant()
                                                                .with(
                                                                        VariantProperties.MODEL,
                                                                        ModelTemplates.CUBE_BOTTOM_TOP_INNER_FACES.create(
                                                                                ModelLocationUtils.getModelLocation(type, "_lit"),
                                                                                lit,
                                                                                generator.modelOutput)
                                                                )
                                                )
                                )
                );
    }

    /** Creates the json for the armor item. */
    private static JsonObject createArmorJson(ResourceLocation id, Map<TextureSlot, ResourceLocation> textures, Holder<ArmorMaterial> armorMaterial, ItemModelGenerators itemgen) {
        JsonObject jsonObject = ModelTemplates.TWO_LAYERED_ITEM.createBaseTemplate(id, textures);
        JsonArray jsonArray = new JsonArray();

        for (ModelHelper.TrimMaterial trimMaterial : TRIM_MATERIALS) {
            JsonObject jsonObject2 = new JsonObject();
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty(TRIM_TYPE.getPath(), trimMaterial.itemModelIndex());
            jsonObject2.add("predicate", jsonObject3);
            jsonObject2.addProperty("model", itemgen.getItemModelForTrimMaterial(id, trimMaterial.getAppliedName(armorMaterial)).toString());
            jsonArray.add(jsonObject2);
        }

        jsonObject.add("overrides", jsonArray);
        return jsonObject;
    }

    /** Registers armor item models to display Frontiers trims. */
    public static void registerArmorWithFrontiersTrims(ArmorItem armor, ItemModelGenerators itemgen) {
        if (armor.getType().hasTrims()) {
            ResourceLocation identifier = ModelLocationUtils.getModelLocation(armor);
            ResourceLocation identifier2 = TextureMapping.getItemTexture(armor);
            ResourceLocation identifier3 = TextureMapping.getItemTexture(armor, "_overlay");
            if (armor.getMaterial() == ArmorMaterials.LEATHER)
            {
                ModelTemplates.TWO_LAYERED_ITEM
                        .create(identifier, TextureMapping.layered(identifier2, identifier3), itemgen.output, (id, textures) -> createArmorJson(id, textures, armor.getMaterial(), itemgen));
            }
            else
            {
                ModelTemplates.FLAT_ITEM.create(identifier, TextureMapping.layer0(identifier2), itemgen.output, (id, textures) -> createArmorJson(id, textures, armor.getMaterial(), itemgen));
            }

            for (ModelHelper.TrimMaterial trimMaterial : TRIM_MATERIALS)
            {
                String string = trimMaterial.getAppliedName(armor.getMaterial());
                ResourceLocation identifier4 = itemgen.getItemModelForTrimMaterial(identifier, string);
                String string2 = armor.getType().getName() + "_trim_" + string;
                ResourceLocation identifier5 = ResourceLocation.withDefaultNamespace(string2).withPrefix("trims/items/");

                if (armor.getMaterial().is(ArmorMaterials.LEATHER))
                {
                    itemgen.generateLayeredItem(identifier4, identifier2, identifier3, identifier5);
                } else
                {
                    itemgen.generateLayeredItem(identifier4, identifier2, identifier5);
                }
            }
        }
    }
}
