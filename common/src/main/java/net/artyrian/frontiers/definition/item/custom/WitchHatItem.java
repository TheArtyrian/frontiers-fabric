package net.artyrian.frontiers.definition.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class WitchHatItem extends Item implements Equipable
{
    public WitchHatItem(Properties settings)
    {
        super(settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() { return EquipmentSlot.HEAD; }
    @Override
    public int getEnchantmentValue() {
        return 16;
    }
    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient)
    {
        return ingredient.is(Items.LEATHER);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        return this.swapWithEquipmentSlot(this, world, user, hand);
    }
    @Override
    public Holder<SoundEvent> getEquipSound()
    {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }
}
