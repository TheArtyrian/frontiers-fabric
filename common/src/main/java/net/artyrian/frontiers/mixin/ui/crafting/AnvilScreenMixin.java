package net.artyrian.frontiers.mixin.ui.crafting;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.custom.tool.BrokenToolItem;
import net.artyrian.frontiers.util.MethodToolbox;
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
    @Shadow @Nullable private String newItemName;
    @Shadow @Final private DataSlot levelCost;

    @ModifyVariable(method = "updateResult", at = @At(value = "STORE", ordinal = 0))
    public ItemStack maskBrokenItemAsItsRepairedForm(ItemStack stack)
    {
        ItemStack input = this.input.getItem(0);
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

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/AnvilScreenHandler;sendContentUpdates()V", shift = At.Shift.BEFORE))
    public void updateForHead(CallbackInfo ci, @Local(ordinal = 1) ItemStack itemStack2)
    {
        if (this.output.getItem(0).is(Items.PLAYER_HEAD))
        {
            if (
                    this.newItemName != null &&
                    !StringUtil.isBlank(this.newItemName) &&
                    this.newItemName.equalsIgnoreCase("steve")
            )
            {
                ItemStack steve = new ItemStack(Items.PLAYER_HEAD, this.output.getItem(0).getCount());
                steve.set(DataComponents.NOTE_BLOCK_SOUND, MethodToolbox.getSpecialHeadSound("Steve"));
                steve.set(DataComponents.CUSTOM_NAME, Component.literal(this.newItemName));
                this.output.setItem(0, steve);
            }
        }
    }
}
