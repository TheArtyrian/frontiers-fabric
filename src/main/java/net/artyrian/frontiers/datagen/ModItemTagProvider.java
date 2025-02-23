package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    // Mod tags.
    private void modItemTag()
    {
        getOrCreateTagBuilder(ModTags.Items.EVERTREE_BOOSTABLE)
                .add(Items.CARROT)
                .add(Items.POTATO)
                .add(Items.BEETROOT_SEEDS)
                .add(Items.WHEAT_SEEDS)
                .add(Items.PUMPKIN_SEEDS)
                .add(Items.MELON_SEEDS)
                .add(Items.PITCHER_POD)
                .add(Items.TORCHFLOWER_SEEDS)
                .add(Items.SWEET_BERRIES)
                .add(Items.GLOW_BERRIES)
                .add(Items.NETHER_WART)
                .add(Items.COCOA_BEANS)
                .add(Items.SUGAR_CANE)

                .add(ModItem.ANCIENT_ROSE_SEED)
                .add(ModItem.WARPED_WART)

                .add(FDItem.RICE)
                .add(FDItem.ONION)
                .add(FDItem.CABBAGE_SEEDS)
                .add(FDItem.TOMATO_SEEDS)
        ;
    }

    // Vanilla tags.
    private void vanillaItemTag()
    {
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(Identifier.of(Frontiers.MOD_ID, "ancient_rose"))
                .add(Identifier.of(Frontiers.MOD_ID, "rose"))
                .add(Identifier.of(Frontiers.MOD_ID, "violet_rose"))
                .add(Identifier.of(Frontiers.MOD_ID, "snow_dahlia"))
                .add(Identifier.of(Frontiers.MOD_ID, "fungal_daffodil"))
                .add(Identifier.of(Frontiers.MOD_ID, "crimcone"))

        ;
        getOrCreateTagBuilder(ItemTags.TALL_FLOWERS)
                .add(Identifier.of(Frontiers.MOD_ID, "ancient_rose_bush"))
                .add(Identifier.of(Frontiers.MOD_ID, "violet_rose_bush"))
        ;

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItem.NECRO_WEAVE_HELMET)
                .add(ModItem.NECRO_WEAVE_CHESTPLATE)
                .add(ModItem.NECRO_WEAVE_LEGGINGS)
                .add(ModItem.NECRO_WEAVE_BOOTS)
                .add(ModItem.FROSTITE_HELMET)
                .add(ModItem.FROSTITE_CHESTPLATE)
                .add(ModItem.FROSTITE_LEGGINGS)
                .add(ModItem.FROSTITE_BOOTS)
                .add(ModItem.COBALT_HELMET)
                .add(ModItem.COBALT_CHESTPLATE)
                .add(ModItem.COBALT_LEGGINGS)
                .add(ModItem.COBALT_BOOTS)
                .add(ModItem.PLATE_HELMET)
                .add(ModItem.PLATE_CHESTPLATE)
        ;

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItem.NECRO_WEAVE_HELMET)
                .add(ModItem.FROSTITE_HELMET)
                .add(ModItem.COBALT_HELMET)
                .add(ModItem.PLATE_HELMET)
        ;
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItem.NECRO_WEAVE_CHESTPLATE)
                .add(ModItem.FROSTITE_CHESTPLATE)
                .add(ModItem.COBALT_CHESTPLATE)
                .add(ModItem.PLATE_CHESTPLATE)
        ;
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItem.NECRO_WEAVE_LEGGINGS)
                .add(ModItem.FROSTITE_LEGGINGS)
                .add(ModItem.COBALT_LEGGINGS)
        ;
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItem.NECRO_WEAVE_BOOTS)
                .add(ModItem.FROSTITE_BOOTS)
                .add(ModItem.COBALT_BOOTS)
        ;

        //getOrCreateTagBuilder(ItemTags.HOGLIN_FOOD)
        //        .add(ModItem.TRUFFLE)
        //;
    }

    // Fabric tags.
    private void fabricItemTag()
    {

    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        modItemTag();
        vanillaItemTag();
        fabricItemTag();
    }
}
