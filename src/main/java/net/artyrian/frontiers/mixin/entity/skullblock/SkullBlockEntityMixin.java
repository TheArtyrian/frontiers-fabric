package net.artyrian.frontiers.mixin.entity.skullblock;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.entity.BlockEntityMixin;
import net.artyrian.frontiers.util.MethodToolbox;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkullBlockEntity.class)
public abstract class SkullBlockEntityMixin extends BlockEntityMixin
{
    // Never. Freaking again.
    //@Shadow private @Nullable Identifier noteBlockSound;

    //@Shadow private @Nullable Text customName;

    //@Shadow private @Nullable ProfileComponent owner;

    //@Shadow protected abstract void addComponents(ComponentMap.Builder componentMapBuilder);

    //@Inject(method = "addComponents", at = @At(
    //        value = "INVOKE",
    //        target = "Lnet/minecraft/block/entity/BlockEntity;addComponents(Lnet/minecraft/component/ComponentMap$Builder;)V",
    //        shift = At.Shift.AFTER)
    //)
    //private void xden(ComponentMap.Builder componentMapBuilder, CallbackInfo ci)
    //{
    //    boolean is_playerhead = this.getCachedState().isOf(Blocks.PLAYER_HEAD) || this.getCachedState().isOf(Blocks.PLAYER_WALL_HEAD);
    //    if (is_playerhead && this.noteBlockSound != null)
    //    {
    //        String name = null;
    //        if (this.owner != null)
    //        {
    //            name = this.owner.gameProfile().getName();
    //            Frontiers.LOGGER.info("Got name from profile!");
    //        }
    //        else if (this.customName != null && this.customName.getLiteralString().matches("Steve"))
    //        {
    //            name = this.customName.getLiteralString();
    //            Frontiers.LOGGER.info("Got name from custom head! " + name);
    //        }

    //        if (name != null)
    //        {
    //            this.noteBlockSound = MethodToolbox.getSpecialHeadSound(name);
    //            Frontiers.LOGGER.info(this.noteBlockSound.toString());
    //        }
    //    }
    //}
}
