package net.artyrian.frontiers.definition.item.custom.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class BrokenToolItem extends Item
{
    private final Item REPAIRED_TOOL;
    private final Tier material;

    public BrokenToolItem(Item repairTool, Tier toolMat, Properties settings)
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
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient)
    {
        return this.material.getRepairIngredient().test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }
}
