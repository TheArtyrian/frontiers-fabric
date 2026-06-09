package net.artyrian.frontiers.compat.bountifulfares;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

// Original code used from Bountiful Fares; used to avoid mod dependency
// Source: https://github.com/Heccology/Bountiful-Fares/blob/1.21/src/main/java/net/hecco/bountifulfares/item/custom/EffectFoodItem.java
public class EffectFoodItem extends Item
{
    public final List<MobEffectInstance> effects;

    public EffectFoodItem(List<MobEffectInstance> effects, Properties settings)
    {
        super(settings);
        this.effects = effects;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type)
    {
        super.appendHoverText(stack, context, tooltip, type);
        if (effects != null && !effects.isEmpty()) // && BountifulFares.CONFIG.effectTooltips
        {
            //PotionContents.addPotionTooltip(effects, tooltip::add, 1.0F, context.tickRate());
        }
    }
}