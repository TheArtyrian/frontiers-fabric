package net.artyrian.frontiers.datagen.frontiers.tag;

import net.artyrian.frontiers.reg.content.FRTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.enchantment.Enchantments;
import java.util.concurrent.CompletableFuture;

public class FREnchantTagProvider extends FabricTagProvider.EnchantmentTagProvider
{
    public FREnchantTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, completableFuture);
    }

    private void modEntityTag()
    {
        getOrCreateTagBuilder(FRTags.Enchants.PREVENTS_MAGNET_EXP_DROP)
                .add(Enchantments.SILK_TOUCH)
        ;
    }

    // Vanilla tags.
    private void vanillaEntityTag()
    {

    }


    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modEntityTag();
        vanillaEntityTag();
    }
}
