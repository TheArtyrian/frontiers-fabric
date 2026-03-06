package net.artyrian.frontiers.mixin.entity.fishing;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.data.nbt_sync.NBTSync;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.mixin_intf.BobberIntf;
import net.artyrian.frontiers.mixin_intf.BobberType;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorNetSync;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorSyncable;
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
public abstract class FishingBobberMixin extends ProjectileMixin implements BobberIntf, VectorSyncable
{
    @Shadow @Final private int luck;
    @Shadow public abstract @Nullable Player getPlayerOwner();

    @Unique private static final ItemStack DEFAULT_PARENT = Items.FISHING_ROD.getDefaultInstance();

    @Unique private final VectorNetSync vectorLib$netSync = new VectorNetSync((FishingHook)(Object)this, NBTSync.BOBBER$ID, true, (nbt) -> {
        nbt.putInt(NBTSync.BOBBER$BOBBER, BobberType.DEFAULT.getID());
        nbt.put(NBTSync.BOBBER$ROD, DEFAULT_PARENT.save(this.registryAccess(), new CompoundTag()));
    });

    @Override public VectorNetSync getVectorLibNetsync() { return vectorLib$netSync; }

    @Override public int frontiers_1_21x$getLineColor() { return frontiers_1_21x$getBobberLevel().getLineColor(); }

    @Override public BobberType frontiers_1_21x$getBobberLevel() { return BobberType.getBasedOnInt(this.vectorLib$netSync.getInt(NBTSync.BOBBER$BOBBER, 0)); }
    @Override public void frontiers_1_21x$setBobberLevel(BobberType bobber) { this.vectorLib$netSync.syncInt(NBTSync.BOBBER$BOBBER, bobber.getID(), false); }

    @Override public ItemStack frontiers_1_21x$getParentItemStack() { return this.vectorLib$netSync.getItemStack(NBTSync.BOBBER$ROD, DEFAULT_PARENT); }
    @Override public void frontiers_1_21x$setParentItemStack(ItemStack stack) { this.vectorLib$netSync.syncItemStack(NBTSync.BOBBER$ROD, stack, false); }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTRead(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.readNetSyncFromNBT(nbt); }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTWrite(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.saveToNBT(nbt); }

    @ModifyVariable(method = "retrieve", at = @At("STORE"))
    private List<ItemStack> interceptLootPoolForBottleMessage(List<ItemStack> list)
    {
        // Can only attempt to fish up a bottle if the list is 1 & in a valid biome
        Holder<Biome> biome = this.level().getBiome(this.blockPosition());
        boolean in_valid_area = (biome.is(ModTags.Biomes.BOTTLED_MESSAGE_COMPATIBLE));
        if (list.size() == 1 && in_valid_area)
        {
            int max = 50;
            Player playerEntity = this.getPlayerOwner();
            float comboLuck = Math.clamp((float)this.luck + playerEntity.getLuck(), 0.0F, 3.0F);

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

                        return List.of(returnable);
                    }
                }
            }
        }
        return list;
    }
}