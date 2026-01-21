package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;

public class CorePlateItem extends Item
{
    private static final ChatFormatting TITLE_FORMATTING = ChatFormatting.GRAY;

    private final Component typeText;

    private static final Component APPLIES_TO_TEXT = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"core_plate.header")))
            .withStyle(TITLE_FORMATTING);

    public static final Component DEPTH_TYPE_TEXT = Component.translatable(Util.makeDescriptionId("core_plate_type", ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"depths")))
            .withStyle(TITLE_FORMATTING);
    public static final Component FRONTAL_TYPE_TEXT = Component.translatable(Util.makeDescriptionId("core_plate_type", ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"frontal")))
            .withStyle(TITLE_FORMATTING);
    public static final Component CORE_TEXT = Component.translatable("item.frontiers.unfinished_core")
            .withStyle(Rarity.UNCOMMON.color());

    public CorePlateItem(Component typeText, Properties settings)
    {
        super(settings);
        this.typeText = typeText;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        super.appendHoverText(stack, context, tooltip, type);
        tooltip.add(this.typeText);
        tooltip.add(CommonComponents.EMPTY);
        tooltip.add(APPLIES_TO_TEXT);
        tooltip.add(CommonComponents.space().append(CORE_TEXT));
    }
}
