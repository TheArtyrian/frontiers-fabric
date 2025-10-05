package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEnchantTagProvider extends FabricTagProvider.EnchantmentTagProvider
{
    public ModEnchantTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    private void modEntityTag()
    {
        getOrCreateTagBuilder(ModTags.Enchants.PREVENTS_MAGNET_EXP_DROP)
                .add(Enchantments.SILK_TOUCH)
        ;
    }

    // Vanilla tags.
    private void vanillaEntityTag()
    {

    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        modEntityTag();
        vanillaEntityTag();
    }
}
