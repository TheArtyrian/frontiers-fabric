package net.artyrian.frontiers.reg.property;

import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum FRArmorBonus
{
    CHAINMAIL(Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS),
    NECRO(FRItems.NECRO_WEAVE_HELMET.get(), FRItems.NECRO_WEAVE_CHESTPLATE.get(), FRItems.NECRO_WEAVE_LEGGINGS.get(), FRItems.NECRO_WEAVE_BOOTS.get()),
    FROSTITE(FRItems.FROSTITE_HELMET.get(), FRItems.FROSTITE_CHESTPLATE.get(), FRItems.FROSTITE_LEGGINGS.get(), FRItems.FROSTITE_BOOTS.get());

    private final Item helmet;
    private final Item chestplate;
    private final Item leggings;
    private final Item boots;

    private FRArmorBonus(final Item helmet, final Item chestplate, final Item leggings, final Item boots)
    {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    /** Determines if a provided entity is wearing full armor ofkind. */
    public static boolean wearingSetOf(LivingEntity entity, FRArmorBonus armor)
    {
        boolean helmet = false;
        boolean chestplate = false;
        boolean leggings = false;
        boolean boots = false;

        Iterable<ItemStack> stacks = entity.getArmorAndBodyArmorSlots();

        for (ItemStack stack : stacks)
        {
            if (stack.is(armor.helmet))
            {
                helmet = true;
            }
            else if (stack.is(armor.chestplate))
            {
                chestplate = true;
            }
            else if (stack.is(armor.leggings))
            {
                leggings = true;
            }
            else if (stack.is(armor.boots))
            {
                boots = true;
            }
        }

        return (helmet && chestplate && leggings && boots);
    }
}
