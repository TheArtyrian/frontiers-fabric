package net.artyrian.frontiers.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.artyrian.frontiers.item.armor.ModArmorMaterials;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public class ModelHelper
{
    public static final Identifier TRIM_TYPE = Identifier.ofVanilla("trim_type");
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
    static record TrimMaterial(String name, float itemModelIndex, Map<RegistryEntry<ArmorMaterial>, String> overrideArmorMaterials)
    {
        public String getAppliedName(RegistryEntry<ArmorMaterial> armorMaterial) {
            return (String)this.overrideArmorMaterials.getOrDefault(armorMaterial, this.name);
        }
    }

    /** Registers a lumen-type model for the given block. */
    public static void registerLumen(Block type, BlockStateModelGenerator generator)
    {
        Identifier identifier = TexturedModel.CUBE_ALL.upload(type, generator.modelCollector);
        Identifier identifier2 = generator.createSubModel(type, "_power_1", Models.CUBE_ALL, TextureMap::all);
        Identifier identifier3 = generator.createSubModel(type, "_power_2", Models.CUBE_ALL, TextureMap::all);
        generator.blockStateCollector
                .accept(VariantsBlockStateSupplier.create(type)
                        .coordinate(BlockStateVariantMap.create(ModBlockProperties.LUMEN_POWER)
                                .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                                .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, identifier2))
                                .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, identifier3))
                        )
                );
    }

    /** Registers a tower watcher model. */
    public static void registerTowerWatcher(Block type, BlockStateModelGenerator generator)
    {
        Identifier top = TextureMap.getSubId(type, "_top");
        Identifier side = TextureMap.getId(type);
        Identifier side_on = TextureMap.getSubId(type, "_on");

        TextureMap disabled = new TextureMap()
                .put(TextureKey.END, top)
                .put(TextureKey.SIDE, side);
        TextureMap enabled = new TextureMap()
                .put(TextureKey.END, top)
                .put(TextureKey.SIDE, side_on);

        generator.blockStateCollector
                .accept(
                        VariantsBlockStateSupplier.create(type)
                                .coordinate(
                                        BlockStateVariantMap.create(Properties.ENABLED)
                                                .register(
                                                        false,
                                                        BlockStateVariant.create()
                                                                .put(
                                                                        VariantSettings.MODEL,
                                                                        Models.CUBE_COLUMN.upload(
                                                                                type,
                                                                                disabled,
                                                                                generator.modelCollector))
                                                )
                                                .register(
                                                        true,
                                                        BlockStateVariant.create()
                                                                .put(
                                                                        VariantSettings.MODEL,
                                                                        Models.CUBE_COLUMN.upload(
                                                                                ModelIds.getBlockSubModelId(type, "_enabled"),
                                                                                enabled,
                                                                                generator.modelCollector)
                                                                )
                                                )
                                )
                );
    }

    /** Registers a mushroom block with a custom inside */
    public static void registerCustomMushroomBlock(Block mushroomBlock, Identifier insideTexture, BlockStateModelGenerator generator) {
        Identifier identifier = Models.TEMPLATE_SINGLE_FACE.upload(mushroomBlock, TextureMap.texture(mushroomBlock), generator.modelCollector);
        generator.blockStateCollector
                .accept(
                        MultipartBlockStateSupplier.create(mushroomBlock)
                                .with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                                .with(
                                        When.create().set(Properties.EAST, true),
                                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true)
                                )
                                .with(
                                        When.create().set(Properties.SOUTH, true),
                                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true)
                                )
                                .with(
                                        When.create().set(Properties.WEST, true),
                                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true)
                                )
                                .with(
                                        When.create().set(Properties.UP, true),
                                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true)
                                )
                                .with(
                                        When.create().set(Properties.DOWN, true),
                                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true)
                                )
                                .with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideTexture))
                                .with(
                                        When.create().set(Properties.EAST, false),
                                        BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, insideTexture)
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                                .put(VariantSettings.UVLOCK, false)
                                )
                                .with(
                                        When.create().set(Properties.SOUTH, false),
                                        BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, insideTexture)
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                                .put(VariantSettings.UVLOCK, false)
                                )
                                .with(
                                        When.create().set(Properties.WEST, false),
                                        BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, insideTexture)
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                                .put(VariantSettings.UVLOCK, false)
                                )
                                .with(
                                        When.create().set(Properties.UP, false),
                                        BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, insideTexture)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R270)
                                                .put(VariantSettings.UVLOCK, false)
                                )
                                .with(
                                        When.create().set(Properties.DOWN, false),
                                        BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, insideTexture)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                                .put(VariantSettings.UVLOCK, false)
                                )
                );
        generator.registerParentedItemModel(mushroomBlock, TexturedModel.CUBE_ALL.upload(mushroomBlock, "_inventory", generator.modelCollector));
    }

    /** Registers a Monster Bakery model for the given block. */
    public static void registerMonsterBakery(Block type, BlockStateModelGenerator generator)
    {
        Identifier top = TextureMap.getSubId(type, "_top");
        Identifier side = TextureMap.getSubId(type, "_side");
        Identifier side_on = TextureMap.getSubId(type, "_side_on");
        Identifier bottom = TextureMap.getSubId(type, "_bottom");

        TextureMap unlit = new TextureMap()
                .put(TextureKey.TOP, top)
                .put(TextureKey.SIDE, side)
                .put(TextureKey.BOTTOM, bottom);
        TextureMap lit = new TextureMap()
                .put(TextureKey.TOP, top)
                .put(TextureKey.SIDE, side_on)
                .put(TextureKey.BOTTOM, bottom);

        generator.blockStateCollector
                .accept(
                        VariantsBlockStateSupplier.create(type)
                                .coordinate(
                                        BlockStateVariantMap.create(Properties.LIT)
                                                .register(
                                                        false,
                                                        BlockStateVariant.create()
                                                                .put(
                                                                        VariantSettings.MODEL,
                                                                        Models.CUBE_BOTTOM_TOP_INNER_FACES.upload(
                                                                                type,
                                                                                unlit,
                                                                                generator.modelCollector))
                                                )
                                                .register(
                                                        true,
                                                        BlockStateVariant.create()
                                                                .put(
                                                                        VariantSettings.MODEL,
                                                                        Models.CUBE_BOTTOM_TOP_INNER_FACES.upload(
                                                                                ModelIds.getBlockSubModelId(type, "_lit"),
                                                                                lit,
                                                                                generator.modelCollector)
                                                                )
                                                )
                                )
                );
    }

    /** Creates the json for the armor item. */
    private static JsonObject createArmorJson(Identifier id, Map<TextureKey, Identifier> textures, RegistryEntry<ArmorMaterial> armorMaterial, ItemModelGenerator itemgen) {
        JsonObject jsonObject = Models.GENERATED_TWO_LAYERS.createJson(id, textures);
        JsonArray jsonArray = new JsonArray();

        for (ModelHelper.TrimMaterial trimMaterial : TRIM_MATERIALS) {
            JsonObject jsonObject2 = new JsonObject();
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty(TRIM_TYPE.getPath(), trimMaterial.itemModelIndex());
            jsonObject2.add("predicate", jsonObject3);
            jsonObject2.addProperty("model", itemgen.suffixTrim(id, trimMaterial.getAppliedName(armorMaterial)).toString());
            jsonArray.add(jsonObject2);
        }

        jsonObject.add("overrides", jsonArray);
        return jsonObject;
    }

    /** Registers armor item models to display Frontiers trims. */
    public static void registerArmorWithFrontiersTrims(ArmorItem armor, ItemModelGenerator itemgen) {
        if (armor.getType().isTrimmable()) {
            Identifier identifier = ModelIds.getItemModelId(armor);
            Identifier identifier2 = TextureMap.getId(armor);
            Identifier identifier3 = TextureMap.getSubId(armor, "_overlay");
            if (armor.getMaterial() == ArmorMaterials.LEATHER)
            {
                Models.GENERATED_TWO_LAYERS
                        .upload(identifier, TextureMap.layered(identifier2, identifier3), itemgen.writer, (id, textures) -> createArmorJson(id, textures, armor.getMaterial(), itemgen));
            }
            else
            {
                Models.GENERATED.upload(identifier, TextureMap.layer0(identifier2), itemgen.writer, (id, textures) -> createArmorJson(id, textures, armor.getMaterial(), itemgen));
            }

            for (ModelHelper.TrimMaterial trimMaterial : TRIM_MATERIALS)
            {
                String string = trimMaterial.getAppliedName(armor.getMaterial());
                Identifier identifier4 = itemgen.suffixTrim(identifier, string);
                String string2 = armor.getType().getName() + "_trim_" + string;
                Identifier identifier5 = Identifier.ofVanilla(string2).withPrefixedPath("trims/items/");

                if (armor.getMaterial().matches(ArmorMaterials.LEATHER))
                {
                    itemgen.uploadArmor(identifier4, identifier2, identifier3, identifier5);
                } else
                {
                    itemgen.uploadArmor(identifier4, identifier2, identifier5);
                }
            }
        }
    }
}
