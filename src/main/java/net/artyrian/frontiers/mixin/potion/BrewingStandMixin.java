package net.artyrian.frontiers.mixin.potion;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.mixin.entity.BlockEntityMixin;
import net.artyrian.frontiers.mixin_interfaces.BrewMixInterface;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(BrewingStandBlockEntity.class)
public abstract class BrewingStandMixin extends BlockEntityMixin implements BrewMixInterface
{
    @Shadow
    private DefaultedList<ItemStack> inventory;

    @Shadow
    protected abstract void setHeldStacks(DefaultedList<ItemStack> inventory);

    @Unique
    private ItemStack doLightningCheck(ItemStack input)
    {
        if (input.isEmpty()) return input;
        else
        {
            if (input.isOf(Items.GLASS_BOTTLE)) return new ItemStack(ModItem.LIGHTNING_IN_A_BOTTLE, 1);
            else return input;
        }
    }

    @Override
    public void frontiers_1_21x$craftLightning(World world, BlockPos pos, DefaultedList<ItemStack> slots)
    {
        int successes = 0;
        for (int j = 0; j < 3; j++)
        {
            boolean isGlassBefore = slots.get(j).isOf(Items.GLASS_BOTTLE);
            slots.set(j, doLightningCheck(slots.get(j)));

            if (slots.get(j).isOf(ModItem.LIGHTNING_IN_A_BOTTLE) && isGlassBefore) successes++;
        }

        markDirty(world, pos, world.getBlockState(pos));
        if (successes > 0) world.syncWorldEvent(WorldEvents.BREWING_STAND_BREWS, pos, 0);
    }

    @ModifyReturnValue(method = "isValid", at = @At(value = "RETURN"))
    public boolean validatorLightning(boolean original, @Local(argsOnly = true)ItemStack stack, @Local(argsOnly = true) int slot)
    {
        if (slot != 3 && slot != 4)
        {
            return (original || stack.isOf(ModItem.LIGHTNING_IN_A_BOTTLE));
        }
        return original;
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    private static void lightningBottleCheck(World world, BlockPos pos, BlockState state, BrewingStandBlockEntity blockEntity, CallbackInfo ci)
    {
        DefaultedList<ItemStack> stacks = blockEntity.getHeldStacks();
        boolean b1_1 = state.get(ModBlockProperties.LIGHTNING_0);
        boolean b1_2 = stacks.get(0).isOf(ModItem.LIGHTNING_IN_A_BOTTLE);
        boolean b1_mismatch = (b1_1 != b1_2);

        boolean b2_1 = state.get(ModBlockProperties.LIGHTNING_1);
        boolean b2_2 = stacks.get(1).isOf(ModItem.LIGHTNING_IN_A_BOTTLE);
        boolean b2_mismatch = (b2_1 != b2_2);

        boolean b3_1 = state.get(ModBlockProperties.LIGHTNING_2);
        boolean b3_2 = stacks.get(2).isOf(ModItem.LIGHTNING_IN_A_BOTTLE);
        boolean b3_mismatch = (b3_1 != b3_2);

        boolean any_mistmatch = (b1_mismatch || b2_mismatch || b3_mismatch);

        if (any_mistmatch)
        {
            BlockState currentState = world.getBlockState(pos);

            if (b1_mismatch) currentState = currentState.with(ModBlockProperties.LIGHTNING_0, b1_2);
            if (b2_mismatch) currentState = currentState.with(ModBlockProperties.LIGHTNING_1, b2_2);
            if (b3_mismatch) currentState = currentState.with(ModBlockProperties.LIGHTNING_2, b3_2);

            world.setBlockState(pos, currentState, 2);
        }
    }
}