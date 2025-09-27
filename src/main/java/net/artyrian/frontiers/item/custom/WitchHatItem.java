package net.artyrian.frontiers.item.custom;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WitchHatItem extends Item implements Equipment
{
    public WitchHatItem(Settings settings)
    {
        super(settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public EquipmentSlot getSlotType() { return EquipmentSlot.HEAD; }
    @Override
    public int getEnchantability() {
        return 16;
    }
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient)
    {
        return ingredient.isOf(Items.LEATHER);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        return this.equipAndSwap(this, world, user, hand);
    }
    @Override
    public RegistryEntry<SoundEvent> getEquipSound()
    {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }
}
