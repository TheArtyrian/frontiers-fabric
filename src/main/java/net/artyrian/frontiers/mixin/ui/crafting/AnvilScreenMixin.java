package net.artyrian.frontiers.mixin.ui.crafting;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.util.MethodToolbox;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.StringHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenMixin extends ForgingScreenMixin
{
    @Shadow @Nullable private String newItemName;

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/AnvilScreenHandler;sendContentUpdates()V", shift = At.Shift.BEFORE))
    public void updatePainting(CallbackInfo ci, @Local(ordinal = 1) ItemStack itemStack2)
    {
        if (this.output.getStack(0).isOf(Items.PLAYER_HEAD))
        {
            if (
                    this.newItemName != null &&
                    !StringHelper.isBlank(this.newItemName) &&
                    this.newItemName.equalsIgnoreCase("steve")
            )
            {
                ItemStack steve = new ItemStack(Items.PLAYER_HEAD, this.output.getStack(0).getCount());
                steve.set(DataComponentTypes.NOTE_BLOCK_SOUND, MethodToolbox.getSpecialHeadSound("Steve"));
                steve.set(DataComponentTypes.CUSTOM_NAME, Text.literal(this.newItemName));
                this.output.setStack(0, steve);
            }
        }
    }
}
