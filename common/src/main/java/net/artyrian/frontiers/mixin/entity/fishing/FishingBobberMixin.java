package net.artyrian.frontiers.mixin.entity.fishing;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.data.nbt_sync.FishingBobberPersistentNBT;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.definition.networking.payload.attachment.BobberPayload;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.mixin_intf.BobberIntf;
import net.artyrian.frontiers.mixin_intf.BobberType;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.vertisoft.vectorlib.VectorLib;
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
public abstract class FishingBobberMixin extends ProjectileMixin implements BobberIntf
{
    @Shadow @Final private int luck;
    @Shadow public abstract @Nullable Player getPlayerOwner();

    @Unique private CompoundTag frontiers$persistentData;
    @Unique private static final ItemStack DEFAULT_PARENT = Items.FISHING_ROD.getDefaultInstance();

    @Override
    public int frontiers_1_21x$getLineColor()
    {
        BobberType type = frontiers_1_21x$getBobberLevel();
        return type.getLineColor();
    }

    // Interfaces
    @Override public BobberType frontiers_1_21x$getBobberLevel()
    {
        if (this.frontiers$persistentData != null && this.frontiers$persistentData.contains(FishingBobberPersistentNBT.BOBBER))
        {
            int i = this.frontiers$persistentData.getInt(FishingBobberPersistentNBT.BOBBER);
            return BobberType.getBasedOnInt(i);
        }
        else return BobberType.DEFAULT;
    }
    @Override public void frontiers_1_21x$setBobberLevel(BobberType bobber)
    {
        FishingBobberPersistentNBT.setBobber(this, bobber);
        frontiers$sendToAllTracking();
    }

    @Override public ItemStack frontiers_1_21x$getParentItemStack()
    {
        if (this.frontiers$persistentData != null && this.frontiers$persistentData.contains(FishingBobberPersistentNBT.ROD, CompoundTag.TAG_COMPOUND))
        {
            CompoundTag tag = (CompoundTag)this.frontiers$persistentData.get(FishingBobberPersistentNBT.ROD);
            return ItemStack.parse(this.registryAccess(), tag.getCompound("item")).orElse(DEFAULT_PARENT);
        }
        else return DEFAULT_PARENT;
    }
    @Override public void frontiers_1_21x$setParentItemStack(ItemStack stack)
    {
        FishingBobberPersistentNBT.setParentStack(this, stack, this.registryAccess());
        frontiers$sendToAllTracking();
    }

    @Unique private void frontiers$sendToAllTracking()
    {
        if (this.frontiers$persistentData != null)
        {
            VectorLib.NETWORK.sendToAllTrackingEntity((FishingHook)(Object)this, new BobberPayload(this.getId(), this.frontiers$persistentData));
        }
    }

    @Override
    public CompoundTag frontiersArtyrian$getPersistentNbt()
    {
        if (this.frontiers$persistentData == null)
        {
            this.frontiers$persistentData = new CompoundTag();
            this.frontiers$persistentData.putInt(FishingBobberPersistentNBT.BOBBER, BobberType.DEFAULT.getID());
            this.frontiers$persistentData.put(FishingBobberPersistentNBT.ROD, DEFAULT_PARENT.save(this.registryAccess(), new CompoundTag()));
        }
        return this.frontiers$persistentData;
    }

    @Override
    public void frontiersArtyrian$syncNbt(CompoundTag nbt)
    {
        this.frontiers$persistentData = nbt;
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTRead(CompoundTag nbt, CallbackInfo ci)
    {
        if (nbt.contains("FrontiersPersistentUserdata", Tag.TAG_COMPOUND))
        {
            this.frontiers$persistentData = nbt.getCompound("FrontiersPersistentUserdata");
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTWrite(CompoundTag nbt, CallbackInfo ci)
    {
        if (this.frontiers$persistentData != null)
        {
            nbt.put("FrontiersPersistentUserdata", frontiers$persistentData);
        }
    }

    @ModifyVariable(method = "retrieve", at = @At("STORE"))
    private List<ItemStack> interceptLootPoolForBottleMessage(List<ItemStack> list)
    {
        // Can only attempt to fish up a bottle if the list is 1 & in a valid biome
        Holder<Biome> biome = this.level().getBiome(this.blockPosition());
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
            float comboLuck = (float)this.luck + playerEntity.getLuck();

            float arbit = (float)this.level().getRandom().nextIntBetweenInclusive(0, max);

            if (arbit <= comboLuck)
            {
                MinecraftServer server = level().getServer();
                if (server != null)
                {
                    StateSaveLoad serverState = StateSaveLoad.getServerState(server);

                    List<ItemStack> copy = List.copyOf(serverState.bottleItems);
                    if (!copy.isEmpty())
                    {
                        int listpos = this.level().getRandom().nextIntBetweenInclusive(0, copy.size() - 1);

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