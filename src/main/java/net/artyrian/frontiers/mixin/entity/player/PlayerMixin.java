package net.artyrian.frontiers.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.data.payloads.PlayerAvariceTotemPayload;
import net.artyrian.frontiers.data.payloads.WitherHardmodePayload;
import net.artyrian.frontiers.data.player.PlayerData;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModAttribute;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInteface;
import net.artyrian.frontiers.util.MethodToolbox;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntityMixin implements PlayerMixInteface
{
    @Shadow public abstract PlayerAbilities getAbilities();
    @Shadow public abstract GameProfile getGameProfile();
    @Shadow @Final PlayerInventory inventory;

    @Override
    public ItemStack getPickBlockStackMix(ItemStack original)
    {
        ItemStack itemStack = new ItemStack(Items.PLAYER_HEAD);
        itemStack.set(DataComponentTypes.PROFILE, new ProfileComponent(this.getGameProfile()));
        itemStack.set(DataComponentTypes.NOTE_BLOCK_SOUND, MethodToolbox.getSpecialHeadSound(this.getGameProfile().getName()));

        if (itemStack.isEmpty()) return super.getPickBlockStackMix(original);
        else return itemStack;
    }

    @Override
    public boolean frontiers_1_21x$usedUpgradeApple() { return (this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).hasModifier(ModAttribute.APPLE_HEALTH.id())); }
    @Override
    public boolean frontiers_1_21x$usedAvariceTotem() {
        //PlayerData data = StateSaveLoad.getPlayerState(this.getWorld().getPlayerByUuid(this.getUuid()));
        return false;
    }

    @Unique
    private void setUpgradeApple(boolean value)
    {
        double val = (value) ? 1.0 : 0.0;
        this.getAttributeInstance(ModAttribute.PLAYER_EATEN_APPLE).setBaseValue(val);
    }

    @Inject(method = "createPlayerAttributes", at = @At("RETURN"), cancellable = true)
    private static void createPlayerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir)
    {
        DefaultAttributeContainer.Builder inthemix = cir.getReturnValue();
        inthemix.add(ModAttribute.PLAYER_EATEN_APPLE, 0.0);
        cir.setReturnValue(inthemix);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readNbtAdd(NbtCompound nbt, CallbackInfo ci)
    {
        if (nbt.contains("UsedAppleBuff", NbtElement.BYTE_TYPE))
        {
            boolean get_value = nbt.getBoolean("UsedAppleBuff");
            this.setUpgradeApple(get_value);
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeNbtAdd(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putBoolean("UsedAppleBuff", this.frontiers_1_21x$usedUpgradeApple());
    }

    @ModifyExpressionValue(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    public boolean checkAvariceTotem(boolean original)
    {
        // Only execute if the original is false. Will return to event otherwise.
        if (!original)
        {
            // Prepare packet sender.
            MinecraftServer server = getWorld().getServer();
            PlayerData playerState = StateSaveLoad.getPlayerState(this.inventory.player);
            playerState.avarice_totem = false;

            // Check entire inventory. If a totem is found, set true then break.
            ItemStack ord;
            for (int i = 0; i < this.inventory.size(); i++)
            {
                ord = this.inventory.getStack(i);
                if (ord.isOf(ModItem.TOTEM_OF_AVARICE))
                {
                    this.inventory.removeStack(i, 1);
                    playerState.avarice_totem = true;
                    break;
                }
            }

            if (server != null)
            {
                ServerPlayerEntity playerEntity = (ServerPlayerEntity)this.inventory.player;
                server.execute(() ->
                {
                    ServerPlayNetworking.send(playerEntity, new PlayerAvariceTotemPayload(playerState.avarice_totem));
                });
            }
            else Frontiers.LOGGER.error("[FRONTIERS]: Attempted to set Totem of Avarice state for " + this.getUuidAsString() + " but failed.");

            // Return avarice totem state
            return (playerState.avarice_totem);
        }
        return original;
    }
}
