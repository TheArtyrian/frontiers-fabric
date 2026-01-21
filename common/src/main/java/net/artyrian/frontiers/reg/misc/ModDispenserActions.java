package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.definition.block.custom.WearableFruitBlock;
import net.artyrian.frontiers.definition.item.custom.BallItem;
import net.artyrian.frontiers.definition.item.dispenser.BallDispenserBehavior;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;

public class ModDispenserActions
{
    private static final DefaultDispenseItemBehavior SPAWN_EGG_BEHAVIOR = new DefaultDispenseItemBehavior() {
        @Override
        public ItemStack execute(BlockSource pointer, ItemStack stack) {
            Direction direction = pointer.state().getValue(DispenserBlock.FACING);
            EntityType<?> entityType = ((SpawnEggItem)stack.getItem()).getType(stack);

            try {
                entityType.spawn(pointer.level(), stack, null, pointer.pos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);
            } catch (Exception var6) {
                LOGGER.error("Error while dispensing spawn egg from dispenser at {}", pointer.pos(), var6);
                return ItemStack.EMPTY;
            }

            stack.shrink(1);
            pointer.level().gameEvent(null, GameEvent.ENTITY_PLACE, pointer.pos());
            return stack;
        }
};

    public static void execute()
    {
        // Specialty Arrows
        DispenserBlock.registerProjectileBehavior(ModItem.BOUNCY_ARROW.get());
        DispenserBlock.registerProjectileBehavior(ModItem.DYNAMITE_ARROW.get());
        DispenserBlock.registerProjectileBehavior(ModItem.PRISMARINE_ARROW.get());
        DispenserBlock.registerProjectileBehavior(ModItem.SUBZERO_ARROW.get());
        DispenserBlock.registerProjectileBehavior(ModItem.WARP_ARROW.get());

        // Misc
        DispenserBlock.registerProjectileBehavior(ModItem.FRUITCAKE_SLICE.get());
        DispenserBlock.registerProjectileBehavior(ModItem.BAIT.get());
        DispenserBlock.registerProjectileBehavior(ModItem.GOLDEN_EGG.get());

        // Spawn Eggs
        DispenserBlock.registerBehavior(ModItem.CRAWLER_SPAWN_EGG.get(), SPAWN_EGG_BEHAVIOR);
        DispenserBlock.registerBehavior(ModItem.JUNGLE_SPIDER_SPAWN_EGG.get(), SPAWN_EGG_BEHAVIOR);
        DispenserBlock.registerBehavior(ModItem.PUMPKIN_GOLEM_SPAWN_EGG.get(), SPAWN_EGG_BEHAVIOR);
        DispenserBlock.registerBehavior(ModItem.CROW_SPAWN_EGG.get(), SPAWN_EGG_BEHAVIOR);
        DispenserBlock.registerBehavior(ModItem.GOLDEN_CHICKEN_SPAWN_EGG.get(), SPAWN_EGG_BEHAVIOR);

        // All balls
        for (Item item : BuiltInRegistries.ITEM)
        {
            if (item instanceof BallItem ball)
            {
                DispenserBlock.registerBehavior(ball, new BallDispenserBehavior(ball.getBounces()));
            }
        }

        // All Pumpkin Head-likes
        for (Block block : BuiltInRegistries.BLOCK)
        {
            if (block instanceof WearableFruitBlock fruit)
            {
                DispenserBlock.registerBehavior(fruit, new OptionalDispenseItemBehavior()
                {
                    protected ItemStack execute(BlockSource pointer, ItemStack stack)
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
