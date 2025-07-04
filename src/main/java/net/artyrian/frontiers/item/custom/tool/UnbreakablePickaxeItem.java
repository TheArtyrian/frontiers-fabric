package net.artyrian.frontiers.item.custom.tool;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class UnbreakablePickaxeItem extends PickaxeItem implements Unbreakable
{
    private final Identifier BROKEN_ITEM_ID;

    public UnbreakablePickaxeItem(Identifier broken_item_id, ToolMaterial material, Settings settings)
    {
        super(material, settings);
        this.BROKEN_ITEM_ID = broken_item_id;
    }

    @Override @Nullable
    public Item getBrokenItem()
    {
        Optional<Item> checkItem = Registries.ITEM.getOrEmpty(BROKEN_ITEM_ID);
        return checkItem.orElse(null);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        Item returnItem = this.getBrokenItem();
        if (returnItem == null) stack.damage(2, attacker, EquipmentSlot.MAINHAND);
        else
        {
            ItemStack stack2 = stack.damage(2, returnItem, attacker, EquipmentSlot.MAINHAND);
            if (stack2 != stack)  attacker.setStackInHand(Hand.MAIN_HAND, stack2);
        }
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner)
    {
        ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);
        if (toolComponent == null)
        {
            return false;
        }
        else
        {
            if (!world.isClient && state.getHardness(world, pos) != 0.0F && toolComponent.damagePerBlock() > 0)
            {
                Item returnItem = this.getBrokenItem();
                if (returnItem == null)
                {
                    stack.damage(toolComponent.damagePerBlock(), miner, EquipmentSlot.MAINHAND);
                }
                else
                {
                    ItemStack stack2 = stack.damage(toolComponent.damagePerBlock(), returnItem, miner, EquipmentSlot.MAINHAND);
                    if (stack2 != stack)  miner.setStackInHand(Hand.MAIN_HAND, stack2);
                }
            }
            return true;
        }
    }
}
