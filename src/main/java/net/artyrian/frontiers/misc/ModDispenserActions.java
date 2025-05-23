package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.datagen.ModelHelper;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.custom.BallItem;
import net.artyrian.frontiers.item.custom.OnyxMealItem;
import net.artyrian.frontiers.item.data.BallDispenserBehavior;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class ModDispenserActions
{
    public static void execute()
    {
        // Specialty Arrows
        DispenserBlock.registerProjectileBehavior(ModItem.BOUNCY_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.DYNAMITE_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.PRISMARINE_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.SUBZERO_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.WARP_ARROW);

        // All balls
        for (Item item : Registries.ITEM)
        {
            if (item instanceof BallItem ball)
            {
                DispenserBlock.registerBehavior(ball, new BallDispenserBehavior());
            }
        }

        // Onyx Meal
        //DispenserBlock.registerBehavior(ModItem.ONYX_MEAL, new FallibleItemDispenserBehavior()
        //{
        //    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack)
        //    {
        //        this.setSuccess(true);
        //        World world = pointer.world();

        //        BlockPos blockPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING));

        //        if (!OnyxMealItem.useOnBlock(new ItemUsageContext()))
        //        {
        //            this.setSuccess(false);
        //        }
        //        else if (!world.isClient)
        //        {
        //            world.syncWorldEvent(1505, blockPos, 15);
        //        }

        //        return stack;
        //    }
        //});
    }
}
