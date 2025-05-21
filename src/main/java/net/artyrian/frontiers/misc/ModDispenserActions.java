package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.datagen.ModelHelper;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.custom.BallItem;
import net.artyrian.frontiers.item.data.BallDispenserBehavior;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class ModDispenserActions
{
    public static void execute()
    {
        DispenserBlock.registerProjectileBehavior(ModItem.BOUNCY_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.DYNAMITE_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.PRISMARINE_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.SUBZERO_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.WARP_ARROW);

        for (Item item : Registries.ITEM)
        {
            if (item instanceof BallItem ball)
            {
                DispenserBlock.registerBehavior(ball, new BallDispenserBehavior());
            }
        }
    }
}
