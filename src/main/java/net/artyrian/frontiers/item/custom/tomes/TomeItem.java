package net.artyrian.frontiers.item.custom.tomes;

import net.artyrian.frontiers.item.data.ModToolMaterial;
import net.minecraft.item.ToolItem;

public class TomeItem extends ToolItem
{
    public TomeItem(Settings settings)
    {
        super(ModToolMaterial.TOME, settings);
    }
}
