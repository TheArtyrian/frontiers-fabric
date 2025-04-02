package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class ArrowheadItem extends Item
{
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;

    private static final Text CREATE_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Frontiers.MOD_ID,"arrowhead.header")))
            .formatted(TITLE_FORMATTING);

    private final Text arrowText;
    private final Text idText;

    public ArrowheadItem(String id, Item arrow, Settings settings)
    {
        super(settings);
        this.arrowText = Text.translatable(Util.createTranslationKey("item", Identifier.of(Frontiers.MOD_ID,"arrowhead.footer")
        ), Text.translatable(Util.createTranslationKey("item", Registries.ITEM.getId(arrow))
        )).formatted(Formatting.BLUE);
        this.idText = Text.translatable("arrowhead.frontiers." + id).formatted(TITLE_FORMATTING);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(this.idText);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(CREATE_TEXT);
        tooltip.add(ScreenTexts.space().append(this.arrowText));
    }
}
