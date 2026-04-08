package net.artyrian.frontiers.datagen.bfares;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.datagen.frontiers.FRLangProviderEnglish;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.vertisoft.vectorlib.exclusive.datagen.VectorDatagen;
import net.vertisoft.vectorlib.exclusive.datagen.VectorLangGen;

import java.util.concurrent.CompletableFuture;

public class BFLangProviderEnglish extends VectorLangGen
{
    public BFLangProviderEnglish(FabricDataOutput outgo, CompletableFuture<HolderLookup.Provider> wrapper)
    {
        super(Frontiers.MOD_ID, outgo, VectorDatagen.EN_US, wrapper);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        // BF
        addBlockWithDesc(builder, BFBlock.FELDSPAR_LUMEN.get(),"Feldspar Lumen",
                null,
                FRLangProviderEnglish.YAP_LUMEN
        );
        addBlockWithDesc(builder, BFBlock.HOARY_WREATH.get(),"Hoary Wreath",
                FRLangProviderEnglish.L4J_WREATH_PRE + "Hoary" + FRLangProviderEnglish.L4J_WREATH_POST,
                FRLangProviderEnglish.YAP_WREATH_PRE + "hoary" + FRLangProviderEnglish.YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.WALNUT_WREATH.get(),"Walnut Wreath",
                FRLangProviderEnglish.L4J_WREATH_PRE + "Walnut" + FRLangProviderEnglish.L4J_WREATH_POST,
                FRLangProviderEnglish.YAP_WREATH_PRE + "walnut" + FRLangProviderEnglish.YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.APPLE_WREATH.get(),"Apple Wreath",
                FRLangProviderEnglish.L4J_WREATH_PRE + "Apple" + FRLangProviderEnglish.L4J_WREATH_POST,
                FRLangProviderEnglish.YAP_WREATH_PRE + "apple" + FRLangProviderEnglish.YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.LEMON_WREATH.get(),"Lemon Wreath",
                FRLangProviderEnglish.L4J_WREATH_PRE + "Lemon" + FRLangProviderEnglish.L4J_WREATH_POST,
                FRLangProviderEnglish.YAP_WREATH_PRE + "lemon" + FRLangProviderEnglish.YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.PLUM_WREATH.get(),"Plum Wreath",
                FRLangProviderEnglish.L4J_WREATH_PRE + "Plum" + FRLangProviderEnglish.L4J_WREATH_POST,
                FRLangProviderEnglish.YAP_WREATH_PRE + "plum" + FRLangProviderEnglish.YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.ORANGE_WREATH.get(),"Orange Wreath",
                FRLangProviderEnglish.L4J_WREATH_PRE + "Orange" + FRLangProviderEnglish.L4J_WREATH_POST,
                FRLangProviderEnglish.YAP_WREATH_PRE + "orange" + FRLangProviderEnglish.YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.GOLDEN_WREATH.get(),"Golden Wreath",
                FRLangProviderEnglish.L4J_WREATH_PRE + "Golden" + FRLangProviderEnglish.L4J_WREATH_POST,
                FRLangProviderEnglish.YAP_WREATH_PRE + "golden" + FRLangProviderEnglish.YAP_WREATH_POST
        );
        addItemWithDesc(builder, BFItem.GUARDIAN_SOUP.get(),"Guardian Soup",
                null,
                null
        );
        addItemWithDesc(builder, BFItem.ELDEN_BOWL.get(),"Elden Bowl",
                null,
                null
        );
        addItemWithDesc(builder, BFItem.BREADED_GUARDIAN.get(),"Breaded Guardian",
                null,
                null
        );
        addItemWithDesc(builder, BFItem.MELON_SPRITZER_BOTTLE.get(),"Melon Spritzer Bottle",
                null,
                null
        );
        addItemWithDesc(builder, BFItem.GLISTERING_SPRITZER_BOTTLE.get(),"Glistering Spritzer Bottle",
                null,
                null
        );
    }
}
