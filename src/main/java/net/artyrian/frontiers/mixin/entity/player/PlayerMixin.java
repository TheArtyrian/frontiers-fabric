package net.artyrian.frontiers.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.payloads.PlayerAvariceTotemPayload;
import net.artyrian.frontiers.data.player.PlayerPersistentNBT;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.entity.projectile.BallEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.custom.BallItem;
import net.artyrian.frontiers.misc.ModAttribute;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.artyrian.frontiers.sounds.ModSounds;
import net.artyrian.frontiers.util.MethodToolbox;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Debug(export = true)
@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntityMixin implements PlayerMixInterface
{
    @Shadow public abstract PlayerAbilities getAbilities();
    @Shadow public abstract GameProfile getGameProfile();
    @Shadow @Final PlayerInventory inventory;
    @Shadow public abstract PlayerInventory getInventory();
    @Shadow public abstract boolean isCreative();
    @Shadow public abstract String getNameForScoreboard();
    @Shadow public abstract SoundCategory getSoundCategory();
    @Shadow public abstract ItemCooldownManager getItemCooldownManager();
    @Shadow protected abstract void vanishCursedItems();
    @Shadow public int experienceLevel;
    @Shadow public int totalExperience;
    @Shadow public float experienceProgress;

    @Shadow public abstract void setScore(int score);

    @Unique
    private NbtCompound persistentData;

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
    public void hurtSoundHook(DamageSource damageSource, CallbackInfo ci)
    {
        super.hurtSoundHook(damageSource, ci);
        if (Frontiers.IS_APRIL_FOOLS)
        {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.STEVE,
                    this.getSoundCategory(), this.getSoundVolume(), this.getSoundPitch());
        }
    }

    @Override
    public void deathSoundHook(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        super.deathSoundHook(source, amount, cir);
        if (Frontiers.IS_APRIL_FOOLS)
        {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.STEVE,
                    this.getSoundCategory(), this.getSoundVolume(), this.getSoundPitch());
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.APRIL_FOOLS_DEATH_SFX,
                    this.getSoundCategory(), 1.0F, 1.0F);
        }
    }

    @Override
    public boolean frontiers_1_21x$usedUpgradeApple() { return (this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).hasModifier(ModAttribute.APPLE_HEALTH.id())); }
    @Override
    public boolean frontiers_1_21x$usedAvariceTotem()
    {
        if (this.persistentData != null && this.persistentData.contains("totem"))
        {
            return this.persistentData.getBoolean("totem");
        }
        else return false;
    }

    @Override
    public int frontiers_1_21x$getSanity()
    {
        if (this.persistentData != null && this.persistentData.contains("sanity"))
        {
            return this.persistentData.getInt("sanity");
        }
        else return 0;
    }
    @Override
    public int frontiers_1_21x$getSanityTick()
    {
        if (this.persistentData != null && this.persistentData.contains("sanity_tick"))
        {
            return this.persistentData.getInt("sanity_tick");
        }
        else return 0;
    }

    @Override
    public NbtCompound frontiersArtyrian$getPersistentNbt()
    {
        if (this.persistentData == null)
        {
            this.persistentData = new NbtCompound();
            this.persistentData.putInt("sanity_tick", 0);
            this.persistentData.putInt("sanity", 15);
        }

        return this.persistentData;
    }

    @Override
    public void frontiersTakeCobaltShieldHit(LivingEntity attacker)
    {
        super.frontiersTakeCobaltShieldHit(attacker);
        if (attacker.disablesShield())
        {
            this.getItemCooldownManager().set(ModItem.COBALT_SHIELD, 75);
            this.clearActiveItem();
            this.getWorld().sendEntityStatus(this.inventory.player, EntityStatuses.BREAK_SHIELD);
        }
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

        if (nbt.contains("FrontiersPersistentUserdata", NbtElement.COMPOUND_TYPE))
        {
            this.persistentData = nbt.getCompound("FrontiersPersistentUserdata");
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeNbtAdd(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putBoolean("UsedAppleBuff", this.frontiers_1_21x$usedUpgradeApple());

        if (this.persistentData != null)
        {
            nbt.put("FrontiersPersistentUserdata", persistentData);
        }
    }

    @ModifyExpressionValue(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    public boolean checkAvariceTotem(boolean original)
    {
        // Only execute if the original is false. Will return to event otherwise.
        if (!original)
        {
            // Prepare packet sender.
            boolean has_totem = false;

            // Check entire inventory. If a totem is found, set true then break.
            ItemStack ord;
            for (int i = 0; i < this.inventory.size(); i++)
            {
                ord = this.inventory.getStack(i);
                if (ord.isOf(ModItem.TOTEM_OF_AVARICE))
                {
                    this.inventory.removeStack(i, 1);
                    this.vanishCursedItems();
                    has_totem = true;
                    break;
                }
            }

            PlayerPersistentNBT.AvariceTotem.setTotemStatus(((PlayerMixInterface)this.inventory.player), has_totem);
            //if (this.persistentData != null && persistentData.contains("totem"))
            //{
            //    Frontiers.LOGGER.info("Player avarice check -> " + String.valueOf(persistentData.getBoolean("totem")) + ", Server: " + String.valueOf(!getWorld().isClient));
            //}

            MinecraftServer server = this.getWorld().getServer();
            if (server != null)
            {
                ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(this.getUuid());
                if (playerEntity != null)
                {
                    boolean sendVal = has_totem;
                    server.execute(() -> ServerPlayNetworking.send(playerEntity, new PlayerAvariceTotemPayload(sendVal)));
                }
            }
            else
            {
                Frontiers.LOGGER.warn("[FRONTIERS] Avarice Totem check called on client - Artyrian please look into this");
            }

            // Return avarice totem state
            return (has_totem);
        }
        return original;
    }

    /** Checks for a ball in the player's hand - will drop it when hit. */
    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;dropShoulderEntities()V", shift = At.Shift.AFTER))
    private void checkBall(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        ItemStack handstack = this.getStackInHand(Hand.MAIN_HAND);
        if (handstack.getItem() instanceof BallItem && !this.isCreative())
        {
            PlayerEntity self = this.getInventory().player;
            BallEntity ballEntity = new BallEntity(self, this.getWorld());
            ballEntity.setItem(handstack);
            ballEntity.setVelocity(self, self.getPitch(), self.getYaw(), 0.0F, 0.8F, 1.0F);
            this.getWorld().spawnEntity(ballEntity);

            String name = this.getNameForScoreboard();
            String stackname = handstack.getName().getString();
            Formatting color = ((BallItem)handstack.getItem()).getColor();

            this.getInventory().removeStack(this.getInventory().selectedSlot);

            List<Entity> nearby = this.getWorld().getOtherEntities(null, new Box(
                    new Vec3d(this.getBlockX() - 16, this.getBlockY() - 16, this.getBlockZ() - 16),
                    new Vec3d(this.getBlockX() + 16, this.getBlockY() + 16, this.getBlockZ() + 16)
            ));

            for (Entity i : nearby)
            {
                if (i instanceof PlayerEntity player)
                {
                    player.sendMessage(Text.translatable("entity.frontiers.ball.dropped", name, stackname).formatted(color), true);
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void frontiersSpecialTicking(CallbackInfo ci)
    {
        if (!this.getWorld().isClient())
        {
            boolean is_crags = this.getWorld().getRegistryKey() == ModDimension.CRAGS_LEVEL_KEY;
            ServerPlayerEntity player_server = (ServerPlayerEntity)(Object)this;

            if (is_crags)
            {
                PlayerPersistentNBT.Sanity.addSanityTick((PlayerMixInterface) player_server, 1);
            }
            else
            {
                PlayerPersistentNBT.Sanity.removeSanityTick((PlayerMixInterface) player_server, 1);
            }
        }
    }

    @ModifyExpressionValue(
            method = "tickMovement",
            at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;fallDistance:F", opcode = Opcodes.GETFIELD))
    private float parrotDismountTweak(float original)
    {
        if (original > 0.5F)
        {
            if (!this.isSneaking() && Frontiers.CONFIG.doParrotDismountChange()) return original -2.0F;
        }
        return original;
    }

    @ModifyExpressionValue(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean doUniqueShieldChecks(boolean original)
    {
        return original || this.activeItemStack.isOf(ModItem.COBALT_SHIELD);
    }

    @ModifyConstant(method = "damageShield", constant = @Constant(floatValue = 3.0F, ordinal = 0))
    private float shieldDamageCapTweak(float original)
    {
        float additive = 0.0F;

        if (this.activeItemStack.isOf(ModItem.COBALT_SHIELD)) additive += 1.0F;

        return original + additive;
    }
}
