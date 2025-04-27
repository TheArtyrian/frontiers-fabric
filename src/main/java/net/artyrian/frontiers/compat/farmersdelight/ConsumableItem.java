package net.artyrian.frontiers.compat.farmersdelight;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.Component;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

// Ad-hoc and based on the BF StackableBowlFoodItem
public class ConsumableItem extends Item
{
    public List<StatusEffectInstance> effects;

    public ConsumableItem(Item.Settings settings)
    {
        super(settings.recipeRemainder(Items.BOWL));
    }
    public ConsumableItem(List<StatusEffectInstance> effects, Item.Settings settings)
    {
        super(settings.recipeRemainder(Items.BOWL));
        this.effects = effects;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    {
        super.finishUsing(stack, world, user);
        if (user instanceof ServerPlayerEntity serverPlayerEntity)
        {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode)
        {
            ItemStack itemStack = new ItemStack(Items.BOWL);
            if (!((PlayerEntity)user).getInventory().insertStack(itemStack))
            {
                ((PlayerEntity)user).dropItem(itemStack, false);
            }
        }

        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (effects != null && !effects.isEmpty()) // && FarmersDelight.CONFIG.effectTooltips TODO: Readable config from mods
        {
            PotionContentsComponent.buildTooltip(effects, tooltip::add, 1.0F, context.getUpdateTickRate());
        }
    }
}