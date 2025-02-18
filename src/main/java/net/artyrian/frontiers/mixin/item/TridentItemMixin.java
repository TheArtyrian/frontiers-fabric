package net.artyrian.frontiers.mixin.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TridentItem;
import org.spongepowered.asm.mixin.*;

@Debug(export = true)
@Mixin(TridentItem.class)
public abstract class TridentItemMixin extends ItemMixinFrontiers
{
    @Override
    public boolean canRepair(boolean original, ItemStack stack, ItemStack ingredient)
    {
        if (ingredient.isOf(Items.PRISMARINE_SHARD)) return true;
        else return original;
    }
}
