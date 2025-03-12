package net.artyrian.frontiers.mixin.entity.fishing;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.mixin_interfaces.BobberMixInterface;
import net.artyrian.frontiers.mixin_interfaces.BobberType;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Mixes customs into fishing bobber class.
@Debug(export = true)
@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberMixin extends ProjectileMixin implements BobberMixInterface
{
    // Uniques/Shadows
    //@Unique private static final TrackedData<Integer> BOBBER_POWER = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.INTEGER);
    //@Unique private static final TrackedData<Integer> LINE_COLOR = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.INTEGER);
    //@Unique private static final TrackedData<ItemStack> PARENT_ITEMSTACK = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    @Unique private final Integer BOBBER_POWER = ((AttachmentTarget)this)
            .getAttachedOrCreate(ModAttachmentTypes.FISHBOBBER_BOBBER_POWER, ModAttachmentTypes.FISHBOBBER_BOBBER_POWER.initializer());
    @Unique private final Integer LINE_COLOR = ((AttachmentTarget)this)
            .getAttachedOrCreate(ModAttachmentTypes.FISHBOBBER_LINE_COLOR, ModAttachmentTypes.FISHBOBBER_LINE_COLOR.initializer());
    @Unique private final ItemStack PARENT_ITEMSTACK = ((AttachmentTarget)this)
            .getAttachedOrCreate(ModAttachmentTypes.FISHBOBBER_PARENT_ITEM, ModAttachmentTypes.FISHBOBBER_PARENT_ITEM.initializer());

    @Unique private BobberType BOBBER_ENUM = BobberType.DEFAULT;
    @Unique private static ItemStack DEFAULT_PARENT = new ItemStack(Items.FISHING_ROD, 1);
    @Unique private Item parent_item = Items.FISHING_ROD;

    // Interfaces
    @Override public int frontiers_1_21x$getBobberLevel()                       { return ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.FISHBOBBER_BOBBER_POWER, ModAttachmentTypes.FISHBOBBER_BOBBER_POWER.initializer()); }
    @Override public ItemStack frontiers_1_21x$getParentItemStack()             { return ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.FISHBOBBER_PARENT_ITEM, ModAttachmentTypes.FISHBOBBER_PARENT_ITEM.initializer()); }
    @Override public Item frontiers_1_21x$getParentItem()                       { return this.parent_item; }
    @Override public int frontiers_1_21x$getLineColor()                         { return ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.FISHBOBBER_LINE_COLOR, ModAttachmentTypes.FISHBOBBER_LINE_COLOR.initializer()); }
    @Override public void frontiers_1_21x$setBobberLevel(BobberType bobber)     { this.BOBBER_ENUM = bobber; }
    @Override public void frontiers_1_21x$setParentItemStack(ItemStack stack)   { ((AttachmentTarget)this).setAttached(ModAttachmentTypes.FISHBOBBER_PARENT_ITEM, stack); }
    @Override public void frontiers_1_21x$setParentItem(Item item)              { this.parent_item = item; }
    @Override public void frontiers_1_21x$setLineColor(BobberType bobber)       { ((AttachmentTarget)this).setAttached(ModAttachmentTypes.FISHBOBBER_LINE_COLOR, bobber.getLineColor()); }

    // Injects
    //@Inject(method = "initDataTracker", at = @At("TAIL"))
    //protected void frontiersAppendInitTrack(DataTracker.Builder builder, CallbackInfo ci) {
    //    builder.add(BOBBER_POWER, BobberType.DEFAULT.ordinal());
    //    builder.add(LINE_COLOR, Colors.BLACK);
    //    builder.add(PARENT_ITEMSTACK, DEFAULT_PARENT);
    //}
    //@Inject(method = "onTrackedDataSet", at = @At("HEAD"))
    //public void frontiersAppendOnTrack(TrackedData<?> data, CallbackInfo ci)
    //{
    //    if (BOBBER_POWER.equals(data))
    //    {
    //        int inputter = this.getDataTracker().get(BOBBER_POWER);
    //        this.BOBBER_ENUM = BobberType.getBasedOnInt(inputter);
    //        this.setLineColor(BOBBER_ENUM);
    //    }
    //
    //    if (PARENT_ITEMSTACK.equals(data))
    //    {
    //        ItemStack stack = this.getDataTracker().get(PARENT_ITEMSTACK);
    //        this.parent_item = stack.getItem();
    //    }
    //}

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putInt("BobberType", BOBBER_ENUM.getID());
        nbt.put("ParentRod", ((AttachmentTarget)this)
                .getAttachedOrCreate(ModAttachmentTypes.FISHBOBBER_PARENT_ITEM, ModAttachmentTypes.FISHBOBBER_PARENT_ITEM.initializer())
                .encode(this.getWorld().getRegistryManager()));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci)
    {
        this.frontiers_1_21x$setBobberLevel(BobberType.getBasedOnInt(nbt.getInt("BobberType")));
        ((AttachmentTarget)this).setAttached(ModAttachmentTypes.FISHBOBBER_BOBBER_POWER, this.BOBBER_ENUM.ordinal());
        this.frontiers_1_21x$setLineColor(this.BOBBER_ENUM);

        ItemStack checkstack = ItemStack.fromNbtOrEmpty(this.getWorld().getRegistryManager(), nbt.getCompound("ParentRod"));
        if (!checkstack.isEmpty())
        {
            this.frontiers_1_21x$setParentItemStack(checkstack);
            this.frontiers_1_21x$setParentItem(checkstack.getItem());
        }
        else
        {
            this.frontiers_1_21x$setParentItemStack(DEFAULT_PARENT);
            this.frontiers_1_21x$setParentItem(DEFAULT_PARENT.getItem());
        }
    }

    @ModifyExpressionValue(method = "removeIfInvalid", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean invalidHalterMainHand(boolean original, @Local(ordinal = 0) ItemStack itemStack)
    {
        return itemStack.isOf(this.frontiers_1_21x$getParentItem());
    }

    @ModifyExpressionValue(method = "removeIfInvalid", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private boolean invalidHalterOffHand(boolean original, @Local(ordinal = 1) ItemStack itemStack2)
    {
        return itemStack2.isOf(this.frontiers_1_21x$getParentItem());
    }
}