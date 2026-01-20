package net.artyrian.frontiers.definition.block.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;

public class WearableFruitBlock extends CarvedFruitBlock implements Equipable
{
    public WearableFruitBlock(Properties settings)
    {
        super(settings);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
