package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;

import java.util.List;

public class CorePlateItem extends Item
{
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;

    private final Text typeText;

    private static final Text APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Frontiers.MOD_ID,"core_plate.header")))
            .formatted(TITLE_FORMATTING);

    public static final Text DEPTH_TYPE_TEXT = Text.translatable(Util.createTranslationKey("core_plate_type", Identifier.of(Frontiers.MOD_ID,"depths")))
            .formatted(TITLE_FORMATTING);
    public static final Text FRONTAL_TYPE_TEXT = Text.translatable(Util.createTranslationKey("core_plate_type", Identifier.of(Frontiers.MOD_ID,"frontal")))
            .formatted(TITLE_FORMATTING);
    public static final Text CORE_TEXT = Text.translatable("item.frontiers.unfinished_core")
            .formatted(Rarity.UNCOMMON.getFormatting());

    public CorePlateItem(Text typeText, Settings settings)
    {
        super(settings);
        this.typeText = typeText;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(this.typeText);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(APPLIES_TO_TEXT);
        tooltip.add(ScreenTexts.space().append(CORE_TEXT));
    }
}
