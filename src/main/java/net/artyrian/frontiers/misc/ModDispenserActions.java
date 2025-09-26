package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.WearableFruitBlock;
import net.artyrian.frontiers.datagen.ModelHelper;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.custom.BallItem;
import net.artyrian.frontiers.item.custom.OnyxMealItem;
import net.artyrian.frontiers.item.data.BallDispenserBehavior;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ModDispenserActions
{
    private static final ItemDispenserBehavior SPAWN_EGG_BEHAVIOR = new ItemDispenserBehavior() {
        @Override
        public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
            Direction direction = pointer.state().get(DispenserBlock.FACING);
            EntityType<?> entityType = ((SpawnEggItem)stack.getItem()).getEntityType(stack);

            try {
                entityType.spawnFromItemStack(pointer.world(), stack, null, pointer.pos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
            } catch (Exception var6) {
                LOGGER.error("Error while dispensing spawn egg from dispenser at {}", pointer.pos(), var6);
                return ItemStack.EMPTY;
            }

            stack.decrement(1);
            pointer.world().emitGameEvent(null, GameEvent.ENTITY_PLACE, pointer.pos());
            return stack;
        }
};

    public static void execute()
    {
        // Specialty Arrows
        DispenserBlock.registerProjectileBehavior(ModItem.BOUNCY_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.DYNAMITE_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.PRISMARINE_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.SUBZERO_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.WARP_ARROW);
        DispenserBlock.registerProjectileBehavior(ModItem.WARP_ARROW);

        // Spawn Eggs
        DispenserBlock.registerBehavior(ModItem.CRAWLER_SPAWN_EGG, SPAWN_EGG_BEHAVIOR);
        DispenserBlock.registerBehavior(ModItem.JUNGLE_SPIDER_SPAWN_EGG, SPAWN_EGG_BEHAVIOR);

        // All balls
        for (Item item : Registries.ITEM)
        {
            if (item instanceof BallItem ball)
            {
                DispenserBlock.registerBehavior(ball, new BallDispenserBehavior());
            }
        }

        // All Pumpkin Head-likes
        for (Block block : Registries.BLOCK)
        {
            if (block instanceof WearableFruitBlock fruit)
            {
                DispenserBlock.registerBehavior(fruit, new FallibleItemDispenserBehavior()
                {
                    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack)
                    {
                        this.setSuccess(ArmorItem.dispenseArmor(pointer, stack));
                        return stack;
                    }
                });
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
