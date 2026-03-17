package net.artyrian.frontiers.compat.farmersdelight;

import java.util.List;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

// Ad-hoc and based on the BF StackableBowlFoodItem
public class ConsumableItem extends Item
{
    public List<MobEffectInstance> effects;
    private boolean dropBowl;

    public ConsumableItem(boolean dropBowl, Item.Properties settings)
    {
        super(settings);
        this.dropBowl = dropBowl;
    }
    public ConsumableItem(boolean dropBowl, List<MobEffectInstance> effects, Item.Properties settings)
    {
        super(settings);
        this.effects = effects;
        this.dropBowl = dropBowl;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user)
    {
        super.finishUsingItem(stack, world, user);
        if (user instanceof ServerPlayer serverPlayerEntity)
        {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.awardStat(Stats.ITEM_USED.get(this));
        }
        if (this.dropBowl && user instanceof Player && !((Player)user).getAbilities().instabuild)
        {
            ItemStack itemStack = new ItemStack(Items.BOWL);
            if (!((Player)user).getInventory().add(itemStack))
            {
                ((Player)user).drop(itemStack, false);
            }
        }

        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        super.appendHoverText(stack, context, tooltip, type);
        if (effects != null && !effects.isEmpty()) // && FarmersDelight.CONFIG.effectTooltips TODO: Readable config from mods
        {
            PotionContents.addPotionTooltip(effects, tooltip::add, 1.0F, context.tickRate());
        }
    }
}