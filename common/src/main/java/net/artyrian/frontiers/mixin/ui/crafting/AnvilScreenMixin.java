package net.artyrian.frontiers.mixin.ui.crafting;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.item.custom.tool.BrokenToolItem;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
@Debug(export = true)
public abstract class AnvilScreenMixin extends ForgingScreenMixin
{
    @Shadow @Nullable private String itemName;
    @Shadow @Final private DataSlot cost;

    @ModifyVariable(method = "createResult", at = @At(value = "STORE", ordinal = 0))
    public ItemStack maskBrokenItemAsItsRepairedForm(ItemStack stack)
    {
        ItemStack input = this.inputSlots.getItem(0);
        if (stack.getItem() instanceof BrokenToolItem tool)
        {
            ItemStack returner = input.transmuteCopy(tool.getRepairedTool(), 1);
            if (returner.getOrDefault(DataComponents.MAX_DAMAGE, 0) != 0)
            {
                returner.set(DataComponents.DAMAGE, returner.getMaxDamage());
            }
            return returner;
        }
        return stack;
    }

    @Inject(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/AnvilMenu;broadcastChanges()V", shift = At.Shift.BEFORE))
    public void updateForHead(CallbackInfo ci, @Local(ordinal = 1) ItemStack itemStack2)
    {
        if (this.resultSlots.getItem(0).is(Items.PLAYER_HEAD))
        {
            if (
                    this.itemName != null &&
                    !StringUtil.isBlank(this.itemName) &&
                    this.itemName.equalsIgnoreCase("steve")
            )
            {
                ItemStack steve = new ItemStack(Items.PLAYER_HEAD, this.resultSlots.getItem(0).getCount());
                steve.set(DataComponents.NOTE_BLOCK_SOUND, MethodToolbox.getSpecialHeadSound("Steve"));
                steve.set(DataComponents.CUSTOM_NAME, Component.literal(this.itemName));
                this.resultSlots.setItem(0, steve);
            }
        }
    }
}
