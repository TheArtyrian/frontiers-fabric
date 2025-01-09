package net.artyrian.frontiers.mixin.entity.fishing;

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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Mixes customs into fishing bobber class.
@Debug(export = true)
@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberMixin extends ProjectileMixin implements BobberMixInterface
{
    // Uniques/Shadows
    @Unique private static final TrackedData<Integer> BOBBER_POWER = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.INTEGER);
    @Unique private BobberType BOBBER_ENUM = BobberType.DEFAULT;
    @Unique private int line_color = Colors.BLACK;
    @Unique private Item PARENT_ITEM = Items.FISHING_ROD;

    // Interfaces
    @Override public int getBobberLevel()                       { return this.BOBBER_ENUM.ordinal(); }
    @Override public int getLineColor()                         { return this.line_color; }
    @Override public void setBobberLevel(BobberType bobber)     { this.BOBBER_ENUM = bobber; }
    @Override public void setLineColor(BobberType bobber)       { this.line_color = bobber.getLineColor(); }

    // Injects
    @Inject(method = "initDataTracker", at = @At("TAIL"))
    protected void frontiersAppendInitTrack(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(BOBBER_POWER, BobberType.DEFAULT.ordinal());
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
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putInt("BobberType", BOBBER_ENUM.getID());
        nbt.putString("ParentRod", PARENT_ITEM.toString());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci)
    {
        this.setBobberLevel(BobberType.getBasedOnInt(nbt.getInt("BobberType")));
        this.setLineColor(BOBBER_ENUM);

        NbtCompound itempack = new NbtCompound();
        itempack.putString("Rod", nbt.getString("ParentRod"));
        this.PARENT_ITEM = ItemStack.fromNbtOrEmpty(this.getRegistryManager(), itempack).getItem();
    }
}