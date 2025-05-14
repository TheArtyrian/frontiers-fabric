package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;

public class WearableFruitBlock extends CarvedFruitBlock implements Equipment
{
    public WearableFruitBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}
