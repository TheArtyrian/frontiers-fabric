package net.artyrian.frontiers.item.custom.tool;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class BrokenToolItem extends Item
{
    private final Item REPAIRED_TOOL;
    private final ToolMaterial material;

    public BrokenToolItem(Item repairTool, ToolMaterial toolMat, Settings settings)
    {
        super(settings);
        this.REPAIRED_TOOL = repairTool;
        this.material = toolMat;
    }

    public Item getRepairedTool()
    {
        return this.REPAIRED_TOOL;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient)
    {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }
}
