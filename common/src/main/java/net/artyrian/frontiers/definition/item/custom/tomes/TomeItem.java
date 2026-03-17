package net.artyrian.frontiers.definition.item.custom.tomes;

import net.artyrian.frontiers.definition.item.intf.Magic;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.ModToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;

public class TomeItem extends Item implements Magic
{
    protected final int enchantability;
    protected final int durability;

    public TomeItem(int durability, int enchantability, Properties settings)
    {
        super(settings.durability(durability));
        this.durability = durability;
        this.enchantability = enchantability;
    }

    public int getEnchantmentValue() { return this.enchantability; }
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) { return ingredient.is(ModItem.INVOKE_SHARD.get()) || super.isValidRepairItem(stack, ingredient); }

    @Override
    public void onCast(int level) { }

    @Override
    public boolean canCast(int level) { return true; }
}
