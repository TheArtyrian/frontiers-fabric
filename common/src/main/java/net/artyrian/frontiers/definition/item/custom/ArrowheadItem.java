package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;

public class ArrowheadItem extends Item
{
    private static final ChatFormatting TITLE_FORMATTING = ChatFormatting.GRAY;

    private static final Component CREATE_TEXT = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"arrowhead.header")))
            .withStyle(TITLE_FORMATTING);

    private final Component arrowText;
    private final Component idText;

    public ArrowheadItem(String id, Item arrow, Properties settings)
    {
        super(settings);
        this.arrowText = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"arrowhead.footer")
        ), Component.translatable(Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(arrow))
        )).withStyle(ChatFormatting.BLUE);
        this.idText = Component.translatable("arrowhead.frontiers." + id).withStyle(TITLE_FORMATTING);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        super.appendHoverText(stack, context, tooltip, type);
        tooltip.add(this.idText);
        tooltip.add(CommonComponents.EMPTY);
        tooltip.add(CREATE_TEXT);
        tooltip.add(CommonComponents.space().append(this.arrowText));
    }
}
