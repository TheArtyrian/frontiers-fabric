package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.registry.FuelRegistry;

// Fuel registry for fuelable items
// Haha reg like bravoo reg from gunstar heroes :DDDDD
public class ModFuelReg
{
    public static void execute()
    {
        // Default smelting time - can be *'d by whatever to specify how many items should be smelted.
        int basicSmeltTime = 200;

        FuelRegistry.INSTANCE.add(ModItem.BRIMTAN_INGOT, basicSmeltTime * 16);
    }
}
