package net.artyrian.frontiers.mixin.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import org.spongepowered.asm.mixin.*;

@Debug(export = true)
@Mixin(TridentItem.class)
public abstract class TridentItemMixin extends ItemMixinFrontiers
{
    @Override
    public boolean frontiersCanRepairMixinIntf(boolean original, ItemStack stack, ItemStack ingredient)
    {
        if (ingredient.is(Items.PRISMARINE_SHARD)) return true;
        else return original;
    }
}
