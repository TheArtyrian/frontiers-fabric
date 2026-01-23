package net.artyrian.frontiers.mixin.potion;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.mixin.entity.BlockEntityMixin;
import net.artyrian.frontiers.mixin_intf.BrewMixInterface;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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
    private NonNullList<ItemStack> items;

    @Shadow
    protected abstract void setItems(NonNullList<ItemStack> inventory);

    @Unique
    private ItemStack doLightningCheck(ItemStack input)
    {
        if (input.isEmpty()) return input;
        else
        {
            if (input.is(Items.GLASS_BOTTLE)) return new ItemStack(ModItem.LIGHTNING_IN_A_BOTTLE.get(), 1);
            else return input;
        }
    }

    @Override
    public void frontiers_1_21x$craftLightning(Level world, BlockPos pos, NonNullList<ItemStack> slots)
    {
        int successes = 0;
        for (int j = 0; j < 3; j++)
        {
            boolean isGlassBefore = slots.get(j).is(Items.GLASS_BOTTLE);
            slots.set(j, doLightningCheck(slots.get(j)));

            if (slots.get(j).is(ModItem.LIGHTNING_IN_A_BOTTLE.get()) && isGlassBefore) successes++;
        }

        setChanged(world, pos, world.getBlockState(pos));
        if (successes > 0) world.levelEvent(LevelEvent.SOUND_BREWING_STAND_BREW, pos, 0);
    }

    @ModifyReturnValue(method = "canPlaceItem", at = @At(value = "RETURN"))
    public boolean validatorLightning(boolean original, @Local(argsOnly = true)ItemStack stack, @Local(argsOnly = true) int slot)
    {
        if (slot != 3 && slot != 4)
        {
            return (original || stack.is(ModItem.LIGHTNING_IN_A_BOTTLE.get()));
        }
        return original;
    }

    @Inject(method = "serverTick", at = @At(value = "TAIL"))
    private static void lightningBottleCheck(Level world, BlockPos pos, BlockState state, BrewingStandBlockEntity blockEntity, CallbackInfo ci)
    {
        NonNullList<ItemStack> stacks = blockEntity.getItems();
        boolean b1_1 = state.getValue(ModBlockProperties.LIGHTNING_0);
        boolean b1_2 = stacks.get(0).is(ModItem.LIGHTNING_IN_A_BOTTLE.get());
        boolean b1_mismatch = (b1_1 != b1_2);

        boolean b2_1 = state.getValue(ModBlockProperties.LIGHTNING_1);
        boolean b2_2 = stacks.get(1).is(ModItem.LIGHTNING_IN_A_BOTTLE.get());
        boolean b2_mismatch = (b2_1 != b2_2);

        boolean b3_1 = state.getValue(ModBlockProperties.LIGHTNING_2);
        boolean b3_2 = stacks.get(2).is(ModItem.LIGHTNING_IN_A_BOTTLE.get());
        boolean b3_mismatch = (b3_1 != b3_2);

        boolean any_mistmatch = (b1_mismatch || b2_mismatch || b3_mismatch);

        if (any_mistmatch)
        {
            BlockState currentState = world.getBlockState(pos);

            if (b1_mismatch) currentState = currentState.setValue(ModBlockProperties.LIGHTNING_0, b1_2);
            if (b2_mismatch) currentState = currentState.setValue(ModBlockProperties.LIGHTNING_1, b2_2);
            if (b3_mismatch) currentState = currentState.setValue(ModBlockProperties.LIGHTNING_2, b3_2);

            world.setBlock(pos, currentState, 2);
        }
    }
}