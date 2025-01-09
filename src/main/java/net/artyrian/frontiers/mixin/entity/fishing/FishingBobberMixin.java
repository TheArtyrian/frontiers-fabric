package net.artyrian.frontiers.mixin.entity.fishing;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.mixin_interfaces.BobberMixInterface;
import net.artyrian.frontiers.mixin_interfaces.BobberType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Colors;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Mixes customs into fishing bobber class.
@Debug(export = true)
@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberMixin extends ProjectileMixin implements BobberMixInterface
{
    // Uniques/Shadows
    @Unique private static final TrackedData<Integer> BOBBER_POWER = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.INTEGER);
    @Unique private static final TrackedData<Integer> LINE_COLOR = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.INTEGER);
    @Unique private static final TrackedData<ItemStack> PARENT_ITEMSTACK = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    @Unique private BobberType BOBBER_ENUM = BobberType.DEFAULT;
    @Unique private static ItemStack DEFAULT_PARENT = new ItemStack(Items.FISHING_ROD, 1);
    @Unique private Item parent_item = Items.FISHING_ROD;

    // Interfaces
    @Override public int getBobberLevel()                       { return this.getDataTracker().get(BOBBER_POWER); }
    @Override public ItemStack getParentItemStack()             { return this.getDataTracker().get(PARENT_ITEMSTACK); }
    @Override public Item getParentItem()                       { return this.parent_item; }
    @Override public int getLineColor()                         { return this.getDataTracker().get(LINE_COLOR); }
    @Override public void setBobberLevel(BobberType bobber)     { this.BOBBER_ENUM = bobber; }
    @Override public void setParentItemStack(ItemStack stack)   { this.getDataTracker().set(PARENT_ITEMSTACK, stack); }
    @Override public void setParentItem(Item item)              { this.parent_item = item; }
    @Override public void setLineColor(BobberType bobber)       { this.getDataTracker().set(LINE_COLOR, bobber.getLineColor()); }

    // Injects
    @Inject(method = "initDataTracker", at = @At("TAIL"))
    protected void frontiersAppendInitTrack(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(BOBBER_POWER, BobberType.DEFAULT.ordinal());
        builder.add(LINE_COLOR, Colors.BLACK);
        builder.add(PARENT_ITEMSTACK, DEFAULT_PARENT);
    }
    @Inject(method = "onTrackedDataSet", at = @At("HEAD"))
    public void frontiersAppendOnTrack(TrackedData<?> data, CallbackInfo ci)
    {
        if (BOBBER_POWER.equals(data))
        {
            int inputter = this.getDataTracker().get(BOBBER_POWER);
            this.BOBBER_ENUM = BobberType.getBasedOnInt(inputter);
            this.setLineColor(BOBBER_ENUM);
        }

        if (PARENT_ITEMSTACK.equals(data))
        {
            ItemStack stack = this.getDataTracker().get(PARENT_ITEMSTACK);
            this.parent_item = stack.getItem();
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putInt("BobberType", BOBBER_ENUM.getID());
        nbt.put("ParentRod", this.getDataTracker().get(PARENT_ITEMSTACK).encode(this.getWorld().getRegistryManager()));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci)
    {
        this.setBobberLevel(BobberType.getBasedOnInt(nbt.getInt("BobberType")));
        this.getDataTracker().set(BOBBER_POWER, this.BOBBER_ENUM.ordinal());
        this.setLineColor(this.BOBBER_ENUM);

        ItemStack checkstack = ItemStack.fromNbtOrEmpty(this.getWorld().getRegistryManager(), nbt.getCompound("ParentRod"));
        if (!checkstack.isEmpty()) this.setParentItemStack(checkstack);
        else this.setParentItemStack(DEFAULT_PARENT);
    }

    @Redirect(method = "removeIfInvalid", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean injected(ItemStack instance, Item item)
    {
        return instance.isOf(this.getParentItem());
    }
}