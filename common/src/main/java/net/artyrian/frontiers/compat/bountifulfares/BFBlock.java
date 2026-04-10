package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.FRIntegReg;
import net.artyrian.frontiers.definition.block.custom.LumenBlock;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

// A list of Bountiful Fares exclusive pack-in blocks.
public class BFBlock
{
    // Blocks added by Frontiers
    public static Supplier<Block> FELDSPAR_LUMEN = null;
    public static Supplier<Block> HOARY_WREATH = null;
    public static Supplier<Block> WALNUT_WREATH = null;
    public static Supplier<Block> APPLE_WREATH = null;
    public static Supplier<Block> ORANGE_WREATH = null;
    public static Supplier<Block> LEMON_WREATH = null;
    public static Supplier<Block> PLUM_WREATH = null;
    public static Supplier<Block> GOLDEN_WREATH = null;

    // Existing blocks (including compats)
    public static Supplier<Block> HOARY_LEAVES = null;
    public static Supplier<Block> WALNUT_LEAVES = null;
    public static Supplier<Block> APPLE_LEAVES = null;
    public static Supplier<Block> FLOWERING_APPLE_LEAVES = null;
    public static Supplier<Block> ORANGE_LEAVES = null;
    public static Supplier<Block> FLOWERING_ORANGE_LEAVES = null;
    public static Supplier<Block> LEMON_LEAVES = null;
    public static Supplier<Block> FLOWERING_LEMON_LEAVES = null;
    public static Supplier<Block> PLUM_LEAVES = null;
    public static Supplier<Block> FLOWERING_PLUM_LEAVES = null;
    public static Supplier<Block> GOLDEN_APPLE_LEAVES = null;

    public static Supplier<Block> APPLEDOG_BLOCK = null;

    // Registers both the Block and Item to their respective Minecraft registry.
    private static Supplier<Block> registerBlock(String name, Supplier<Block> block)
    {
        Supplier<Block> returnable = VectorLib.REGISTRY.registerBlock(Frontiers.MOD_ID, name, block);
        FRIntegReg.INTEG_ITEMS.add(returnable);
        return returnable;
    }

    private static Supplier<Block> registerBlock(String name, Supplier<Block> block, Item.Properties settings)
    {
        Supplier<Block> returnable = VectorLib.REGISTRY.registerBlock(Frontiers.MOD_ID, name, block, settings);
        FRIntegReg.INTEG_ITEMS.add(returnable);
        return returnable;
    }

    private static Supplier<Block> datagenTemp(String id, String name)
    {
        Supplier<Block> sup = () -> new Block(BlockBehaviour.Properties.of());
        return VectorLib.REGISTRY.registerBlock(id, name, sup);
    }

    // Registers mod blocks. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN BF IS ENABLED!
    private static void registerBlocksTrue()
    {
        FELDSPAR_LUMEN = registerBlock("feldspar_lumen", () ->
                new LumenBlock(BlockBehaviour.Properties.ofFullCopy(FRBlocks.DIAMOND_LUMEN.get()).mapColor(MapColor.TERRACOTTA_WHITE)));

        HOARY_WREATH = registerBlock("hoary_wreath", () -> FRBlocks.createWreath(Blocks.OAK_LEAVES));
        WALNUT_WREATH = registerBlock("walnut_wreath", () -> FRBlocks.createWreath(Blocks.OAK_LEAVES));
        APPLE_WREATH = registerBlock("apple_wreath", () -> FRBlocks.createWreath(Blocks.OAK_LEAVES));
        ORANGE_WREATH = registerBlock("orange_wreath", () -> FRBlocks.createWreath(Blocks.OAK_LEAVES));
        LEMON_WREATH = registerBlock("lemon_wreath", () -> FRBlocks.createWreath(Blocks.OAK_LEAVES));
        PLUM_WREATH = registerBlock("plum_wreath", () -> FRBlocks.createWreath(Blocks.OAK_LEAVES));
        GOLDEN_WREATH = registerBlock("golden_wreath", () -> FRBlocks.createWreath(Blocks.OAK_LEAVES), new Item.Properties().rarity(Rarity.UNCOMMON));

        HOARY_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "hoary_leaves"));
        WALNUT_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "walnut_leaves"));
        APPLE_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "apple_leaves"));
        FLOWERING_APPLE_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "flowering_apple_leaves"));
        ORANGE_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "orange_leaves"));
        FLOWERING_ORANGE_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "flowering_orange_leaves"));
        LEMON_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "lemon_leaves"));
        FLOWERING_LEMON_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "flowering_lemon_leaves"));
        PLUM_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "plum_leaves"));
        FLOWERING_PLUM_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "flowering_plum_leaves"));
        GOLDEN_APPLE_LEAVES = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "golden_apple_leaves"));

        if (Frontiers.APPLEDOG_LOADED)
        {
            APPLEDOG_BLOCK = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.APPLEDOG_ID, "appledog_block"));
        }
        else if (Frontiers.AEU_LOADED)
        {
            APPLEDOG_BLOCK = () -> BuiltInRegistries.BLOCK.get(Frontiers.id(Frontiers.AEU_ID, "appledog_block"));
        }
    }

    private static void registerBlocksDatagen()
    {
        FELDSPAR_LUMEN = registerBlock("feldspar_lumen", () ->
            new LumenBlock(BlockBehaviour.Properties.of()));

        HOARY_WREATH = datagenTemp(Frontiers.MOD_ID, "hoary_wreath");
        WALNUT_WREATH = datagenTemp(Frontiers.MOD_ID, "walnut_wreath");
        APPLE_WREATH = datagenTemp(Frontiers.MOD_ID, "apple_wreath");
        ORANGE_WREATH = datagenTemp(Frontiers.MOD_ID, "orange_wreath");
        LEMON_WREATH = datagenTemp(Frontiers.MOD_ID, "lemon_wreath");
        PLUM_WREATH = datagenTemp(Frontiers.MOD_ID, "plum_wreath");
        GOLDEN_WREATH = datagenTemp(Frontiers.MOD_ID, "golden_wreath");

        HOARY_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "hoary_leaves");
        WALNUT_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "walnut_leaves");
        APPLE_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "apple_leaves");
        FLOWERING_APPLE_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "flowering_apple_leaves");
        ORANGE_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "orange_leaves");
        FLOWERING_ORANGE_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "flowering_orange_leaves");
        LEMON_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "lemon_leaves");
        FLOWERING_LEMON_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "flowering_lemon_leaves");
        PLUM_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "plum_leaves");
        FLOWERING_PLUM_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "flowering_plum_leaves");
        GOLDEN_APPLE_LEAVES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "golden_apple_leaves");

        APPLEDOG_BLOCK = datagenTemp(Frontiers.APPLEDOG_ID, "appledog_block");
    }

    public static void registerModBlocks(boolean datagen)
    {
        if (datagen) registerBlocksDatagen();
        else registerBlocksTrue();
    }
}
