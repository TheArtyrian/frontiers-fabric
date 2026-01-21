package net.artyrian.frontiers.definition.item.custom.tomes;

import net.artyrian.frontiers.reg.misc.ModToolMaterial;
import net.minecraft.world.item.TieredItem;

public class TomeItem extends TieredItem
{
    public TomeItem(Properties settings)
    {
        super(ModToolMaterial.TOME, settings);
    }
}
