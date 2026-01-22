package net.artyrian.frontiers.mixin.entity.fishing;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.mixin_intf.BobberMixInterface;
import net.artyrian.frontiers.mixin_intf.BobberType;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

// Mixes customs into fishing bobber class.
@Debug(export = true)
@Mixin(FishingHook.class)
public abstract class FishingBobberMixin extends ProjectileMixin implements BobberMixInterface
{
    // Uniques/Shadows
    //@Unique private static final TrackedData<Integer> BOBBER_POWER = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.INTEGER);
    //@Unique private static final TrackedData<Integer> LINE_COLOR = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.INTEGER);
    //@Unique private static final TrackedData<ItemStack> PARENT_ITEMSTACK = DataTracker.registerData(FishingBobberEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    @Shadow @Final private int luckBonus;
    @Shadow public abstract @Nullable Player getPlayerOwner();

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

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(CompoundTag nbt, CallbackInfo ci)
    {
        nbt.putInt("BobberType", BOBBER_ENUM.getID());
        nbt.put("ParentRod", ((AttachmentTarget)this)
                .getAttachedOrCreate(ModAttachmentTypes.FISHBOBBER_PARENT_ITEM, ModAttachmentTypes.FISHBOBBER_PARENT_ITEM.initializer())
                .save(this.getWorld().registryAccess()));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(CompoundTag nbt, CallbackInfo ci)
    {
        this.frontiers_1_21x$setBobberLevel(BobberType.getBasedOnInt(nbt.getInt("BobberType")));
        ((AttachmentTarget)this).setAttached(ModAttachmentTypes.FISHBOBBER_BOBBER_POWER, this.BOBBER_ENUM.ordinal());
        this.frontiers_1_21x$setLineColor(this.BOBBER_ENUM);

        ItemStack checkstack = ItemStack.parseOptional(this.getWorld().registryAccess(), nbt.getCompound("ParentRod"));
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
        return itemStack.is(this.frontiers_1_21x$getParentItem());
    }

    @ModifyExpressionValue(method = "removeIfInvalid", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private boolean invalidHalterOffHand(boolean original, @Local(ordinal = 1) ItemStack itemStack2)
    {
        return itemStack2.is(this.frontiers_1_21x$getParentItem());
    }

    @ModifyVariable(method = "use", at = @At("STORE"))
    private List<ItemStack> interceptLootPoolForBottleMessage(List<ItemStack> list)
    {
        // Can only attempt to fish up a bottle if the list is 1 & in a valid biome
        Holder<Biome> biome = this.getWorld().getBiome(this.getBlockPos());
        boolean in_valid_area = (
                biome.is(BiomeTags.IS_OCEAN) ||
                biome.is(BiomeTags.IS_BEACH) ||
                biome.is(BiomeTags.IS_RIVER) ||
                biome.is(Biomes.STONY_SHORE)
        );
        if (list.size() == 1 && in_valid_area)
        {
            int max = 20;
            Player playerEntity = this.getPlayerOwner();
            float comboLuck = (float)this.luckBonus + playerEntity.getLuck();

            float arbit = (float)this.getWorld().getRandom().nextIntBetweenInclusive(0, max);

            if (arbit <= comboLuck)
            {
                MinecraftServer server = getWorld().getServer();
                if (server != null)
                {
                    StateSaveLoad serverState = StateSaveLoad.getServerState(server);

                    List<ItemStack> copy = List.copyOf(serverState.bottleItems);
                    if (!copy.isEmpty())
                    {
                        int listpos = this.getWorld().getRandom().nextIntBetweenInclusive(0, copy.size() - 1);

                        ItemStack returnable = copy.get(listpos);
                        serverState.bottleItems.remove(listpos);

                        //Frontiers.LOGGER.info("caught!");
                        return List.of(returnable);
                    }
                }
            }
        }
        return list;
    }
}