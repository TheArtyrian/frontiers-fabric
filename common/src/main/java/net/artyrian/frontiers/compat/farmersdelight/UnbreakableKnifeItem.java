package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.definition.item.intf.Unbreakable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class UnbreakableKnifeItem extends KnifeItem implements Unbreakable
{
    private final ResourceLocation BROKEN_ITEM_ID;

    public UnbreakableKnifeItem(ResourceLocation broken_item_id, Tier material, Properties settings)
    {
        super(material, settings);
        this.BROKEN_ITEM_ID = broken_item_id;
    }

    @Override @Nullable
    public Item getBrokenItem()
    {
        Optional<Item> checkItem = BuiltInRegistries.ITEM.getOptional(BROKEN_ITEM_ID);
        return checkItem.orElse(null);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        Item returnItem = this.getBrokenItem();
        if (returnItem == null) stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        else
        {
            ItemStack stack2 = stack.hurtAndConvertOnBreak(1, returnItem, attacker, EquipmentSlot.MAINHAND);
            if (stack2 != stack)  attacker.setItemInHand(InteractionHand.MAIN_HAND, stack2);
        }
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        Item returnItem = this.getBrokenItem();
        if (returnItem == null) stack.hurtAndBreak(2, attacker, EquipmentSlot.MAINHAND);
        else
        {
            ItemStack stack2 = stack.hurtAndConvertOnBreak(2, returnItem, attacker, EquipmentSlot.MAINHAND);
            if (stack2 != stack) attacker.setItemInHand(InteractionHand.MAIN_HAND, stack2);
        }
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner)
    {
        Tool toolComponent = stack.get(DataComponents.TOOL);
        if (toolComponent == null)
        {
            return false;
        }
        else
        {
            if (!world.isClientSide && state.getDestroySpeed(world, pos) != 0.0F && toolComponent.damagePerBlock() > 0)
            {
                Item returnItem = this.getBrokenItem();
                if (returnItem == null)
                {
                    stack.hurtAndBreak(toolComponent.damagePerBlock(), miner, EquipmentSlot.MAINHAND);
                }
                else
                {
                    ItemStack stack2 = stack.hurtAndConvertOnBreak(toolComponent.damagePerBlock(), returnItem, miner, EquipmentSlot.MAINHAND);
                    if (stack2 != stack)  miner.setItemInHand(InteractionHand.MAIN_HAND, stack2);
                }
            }
            return true;
        }
    }
}
