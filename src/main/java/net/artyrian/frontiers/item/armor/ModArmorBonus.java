package net.artyrian.frontiers.item.armor;

import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;

public enum ModArmorBonus
{
    CHAINMAIL(Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS),
    NECRO(ModItem.NECRO_WEAVE_HELMET, ModItem.NECRO_WEAVE_CHESTPLATE, ModItem.NECRO_WEAVE_LEGGINGS, ModItem.NECRO_WEAVE_BOOTS),
    FROSTITE(ModItem.FROSTITE_HELMET, ModItem.FROSTITE_CHESTPLATE, ModItem.FROSTITE_LEGGINGS, ModItem.FROSTITE_BOOTS);

    private final Item helmet;
    private final Item chestplate;
    private final Item leggings;
    private final Item boots;

    private ModArmorBonus(final Item helmet, final Item chestplate, final Item leggings, final Item boots)
    {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    /** Determines if a provided entity is wearing full armor ofkind. */
    public static boolean wearingSetOf(LivingEntity entity, ModArmorBonus armor)
    {
        boolean helmet = false;
        boolean chestplate = false;
        boolean leggings = false;
        boolean boots = false;

        Iterable<ItemStack> stacks = entity.getAllArmorItems();

        for (ItemStack stack : stacks)
        {
            if (stack.isOf(armor.helmet))
            {
                helmet = true;
            }
            else if (stack.isOf(armor.chestplate))
            {
                chestplate = true;
            }
            else if (stack.isOf(armor.leggings))
            {
                leggings = true;
            }
            else if (stack.isOf(armor.boots))
            {
                boots = true;
            }
        }

        return (helmet && chestplate && leggings && boots);
    }
}
