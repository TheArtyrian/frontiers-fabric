package net.artyrian.frontiers.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.data.nbt_sync.PlayerPersistentNBT;
import net.artyrian.frontiers.definition.entity.misc.CragsStalkerEntity;
import net.artyrian.frontiers.definition.entity.projectile.BallEntity;
import net.artyrian.frontiers.definition.item.custom.BallItem;
import net.artyrian.frontiers.definition.networking.payload.PlayerAvariceTotemPayload;
import net.artyrian.frontiers.definition.networking.payload.SanitySyncPayload;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.mixin_intf.PlayerIntf;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.misc.ModAttribute;
import net.artyrian.frontiers.reg.misc.ModDimension;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.util.VectorOpcode;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Debug(export = true)
@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntityMixin implements PlayerIntf
{
    @Unique protected CompoundTag frntUserdat;
    @Unique protected boolean frntTriedClientSyncOnNew = false;

    @Shadow public float bob;

    @Shadow public abstract Abilities getAbilities();
    @Shadow public abstract GameProfile getGameProfile();
    @Shadow @Final Inventory inventory;
    @Shadow public abstract Inventory getInventory();
    @Shadow public abstract boolean isCreative();
    @Shadow public abstract String getScoreboardName();
    @Shadow public abstract SoundSource getSoundSource();
    @Shadow public abstract ItemCooldowns getCooldowns();
    @Shadow protected abstract void destroyVanishingCursedItems();
    @Shadow public abstract void setScore(int score);
    @Shadow public abstract boolean isSpectator();

    // ENTITY HOOKS         ////////////////////////////////////////////////////////////

    @Override
    public ItemStack getPickBlockStackMix(ItemStack original)
    {
        ItemStack itemStack = new ItemStack(Items.PLAYER_HEAD);
        itemStack.set(DataComponents.PROFILE, new ResolvableProfile(this.getGameProfile()));
        itemStack.set(DataComponents.NOTE_BLOCK_SOUND, MethodToolbox.getSpecialHeadSound(this.getGameProfile().getName()));

        if (itemStack.isEmpty()) return super.getPickBlockStackMix(original);
        else return itemStack;
    }

    @Override
    public void hurtSoundHook(DamageSource damageSource, CallbackInfo ci)
    {
        super.hurtSoundHook(damageSource, ci);
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.STEVE.get(),
                    this.getSoundSource(), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    @Override
    public void deathSoundHook(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        super.deathSoundHook(source, amount, cir);
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.STEVE.get(),
                    this.getSoundSource(), this.getSoundVolume(), this.getVoicePitch());
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.APRIL_FOOLS_DEATH_SFX.get(),
                    this.getSoundSource(), 1.0F, 1.0F);
        }
    }

    @Override
    public void frontiersTakeCobaltShieldHit(LivingEntity attacker)
    {
        super.frontiersTakeCobaltShieldHit(attacker);
        if (attacker.canDisableShield())
        {
            this.getCooldowns().addCooldown(ModItem.COBALT_SHIELD.get(), 75);
            this.stopUsingItem();
            this.level().broadcastEntityEvent(this.inventory.player, EntityEvent.SHIELD_DISABLED);
        }
    }

    // PLAYERINTF           ////////////////////////////////////////////////////////////

    @Override
    public CompoundTag frontiersArtyrian$getPersistentNbt()
    {
        if (this.frntUserdat == null) this.frntUserdat = PlayerPersistentNBT.init();
        return this.frntUserdat;
    }

    @Override public boolean frontiers_1_21x$usedUpgradeApple() { return PlayerPersistentNBT.fallback(this.frntUserdat, PlayerPersistentNBT.USED_HP_APPLE, false); }
    @Override public boolean frontiers_1_21x$usedAvariceTotem() { return PlayerPersistentNBT.fallback(this.frntUserdat, PlayerPersistentNBT.TOTEM, false); }
    @Override public boolean frontiers_1_21x$killedByCragsMonster() { return PlayerPersistentNBT.fallback(this.frntUserdat, PlayerPersistentNBT.CRAGSMONSTER, false); }
    @Override public int frontiers_1_21x$getSanity() { return PlayerPersistentNBT.fallback(this.frntUserdat, PlayerPersistentNBT.SANITY, 0); }
    @Override public int frontiers_1_21x$getSanityTick() { return PlayerPersistentNBT.fallback(this.frntUserdat, PlayerPersistentNBT.SANITY_TICK, 0); }

    @Override
    public void frontiersArtyrian$checkBuffsStatus()
    {
        boolean hasAppleHealth = (this.getAttribute(Attributes.MAX_HEALTH).hasModifier(ModAttribute.APPLE_HEALTH.id()));

        if (this.frontiers_1_21x$usedUpgradeApple())
        {
            if (!hasAppleHealth) this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(ModAttribute.APPLE_HEALTH);
        }
        else if (hasAppleHealth)
        {
            this.getAttribute(Attributes.MAX_HEALTH).removeModifier(ModAttribute.APPLE_HEALTH);
            float f = this.getMaxHealth();
            if (this.getHealth() > f)
            {
                this.setHealth(f);
            }
        }
    }

    // UNIQUES              ////////////////////////////////////////////////////////////

    @Unique
    protected void frnt$spawnCragsSmog()
    {
        double d = this.getX() + (this.random.nextDouble() - 0.5) * (double)this.getDimensions(this.getPose()).width();
        double e = this.getZ() + (this.random.nextDouble() - 0.5) * (double)this.getDimensions(this.getPose()).width();

        this.level().addParticle(ModParticle.CRAG_SMOG.get(), d, this.getY() + 0.1, e, 0.0, 0.1, 0.0);
    }

    @Unique
    private void frontiersSpawnStalkersNearby()
    {
        BlockPos here = this.blockPosition();
        Level world = this.level();

        AABB box = new AABB(here).expandTowards(20, 20, 20);
        List<CragsStalkerEntity> list = world.getEntitiesOfClass(CragsStalkerEntity.class, box);

        if (list.size() < 4)
        {
            List<BlockPos> occupiedPos = new ArrayList<>();
            for (CragsStalkerEntity cragstalker : list)
            {
                occupiedPos.add(cragstalker.blockPosition());
            }

            int iterator = 0;
            for (BlockPos blockPos : BlockPos.randomInCube(world.random, 80, here, 40))
            {
                if (
                        world.getBlockState(blockPos).is(ModBlocks.CRAGULSTANE.get()) &&
                        !occupiedPos.contains(blockPos.above()) &&
                        world.getBlockState(blockPos.above()).isAir() &&
                        world.getBlockState(blockPos.above().above()).isAir() &&
                        !blockPos.closerThan(here, 10)
                )
                {
                    world.addFreshEntity(new CragsStalkerEntity(world, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() + 0.5));
                    iterator++;
                }

                if (iterator >= 4) break;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readNbtAdd(CompoundTag nbt, CallbackInfo ci)
    {
        if (nbt.contains(PlayerPersistentNBT.ID, Tag.TAG_COMPOUND)) this.frntUserdat = nbt.getCompound(PlayerPersistentNBT.ID);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void writeNbtAdd(CompoundTag nbt, CallbackInfo ci)
    {
        if (this.frntUserdat != null) nbt.put(PlayerPersistentNBT.ID, this.frntUserdat);
        if ((Object)this instanceof ServerPlayer pl2) PlayerPersistentNBT.handleClientReload(pl2);
    }

    @ModifyExpressionValue(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    public boolean checkAvariceTotem(boolean original)
    {
        if (!original)
        {
            boolean has_totem = false;

            ItemStack ord;
            for (int i = 0; i < this.inventory.getContainerSize(); i++)
            {
                ord = this.inventory.getItem(i);
                if (ord.is(ModItem.TOTEM_OF_AVARICE.get()))
                {
                    this.inventory.removeItem(i, 1);
                    this.destroyVanishingCursedItems();
                    has_totem = true;
                    break;
                }
            }

            PlayerPersistentNBT.AvariceTotem.setTotemStatus(((PlayerIntf)this.inventory.player), has_totem);

            MinecraftServer server = this.level().getServer();
            if (server != null)
            {
                ServerPlayer playerEntity = server.getPlayerList().getPlayer(this.getUUID());
                if (playerEntity != null)
                {
                    boolean sendVal = has_totem;
                    server.execute(() -> VectorLib.NETWORK.sendToPlayer(playerEntity, new PlayerAvariceTotemPayload(sendVal)));
                }
            }
            else Frontiers.LOGGER.warn("[FRONTIERS] Avarice Totem check called on client - Artyrian please look into this");

            // Return avarice totem state
            return (has_totem);
        }
        return original;
    }

    /** Checks for a ball in the player's hand - will drop it when hit. */
    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;removeEntitiesOnShoulder()V", shift = At.Shift.AFTER))
    private void checkBall(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        ItemStack handstack = this.getItemInHand(InteractionHand.MAIN_HAND);
        if (handstack.getItem() instanceof BallItem && !this.isCreative())
        {
            Player self = this.getInventory().player;
            BallEntity ballEntity = new BallEntity(self, this.level());
            ballEntity.setItem(handstack);
            ballEntity.setBounces((handstack.getItem() instanceof BallItem ball) ? ball.getBounces() : 0);
            ballEntity.shootFromRotation(self, self.getXRot(), self.getYRot(), 0.0F, 0.8F, 1.0F);
            this.level().addFreshEntity(ballEntity);

            String name = this.getScoreboardName();
            String stackname = handstack.getHoverName().getString();
            int color = ((BallItem)handstack.getItem()).getColor();

            this.getInventory().removeItemNoUpdate(this.getInventory().selected);

            List<Entity> nearby = this.level().getEntities(null, new AABB(
                    new Vec3(this.getBlockX() - 16, this.getBlockY() - 16, this.getBlockZ() - 16),
                    new Vec3(this.getBlockX() + 16, this.getBlockY() + 16, this.getBlockZ() + 16)
            ));

            for (Entity i : nearby)
            {
                if (i instanceof Player player)
                {
                    player.displayClientMessage(Component.translatable("entity.frontiers.ball.dropped", name, stackname).withColor(color), true);
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void frontiersSpecialTicking(CallbackInfo ci)
    {
        if (!this.level().isClientSide())
        {
            ServerPlayer player_server = (ServerPlayer)(Object)this;

            if (!this.frntTriedClientSyncOnNew)
            {
                this.frntTriedClientSyncOnNew = true;
                PlayerPersistentNBT.handleClientReload(player_server);
            }

            boolean is_crags = this.level().dimension() == ModDimension.CRAGS_LEVEL_KEY;
            if (is_crags)
            {
                if (
                        (this.frontiers_1_21x$getSanity() > 0 || this.frontiers_1_21x$getSanityTick() < 1200) &&
                        (!this.isCreative() && !this.isSpectator())
                )
                {
                    int sanityTickAdd =
                            PlayerPersistentNBT.Sanity.addSanityTick((PlayerIntf) player_server, 1);

                    if (sanityTickAdd >= 1200)
                    {
                        PlayerPersistentNBT.Sanity.removeSanity((PlayerIntf) player_server, 1);

                        if (this.frontiers_1_21x$getSanity() > 0)
                        {
                            PlayerPersistentNBT.Sanity.resetSanityTick((PlayerIntf) player_server, false);
                        }
                    }
                }

                // Attempt to spawn crags entities within an area
                if (this.tickCount % 720 == 0)
                {
                    this.frontiersSpawnStalkersNearby();
                }
            }
            else
            {
                if (this.frontiers_1_21x$getSanity() < 20 || this.frontiers_1_21x$getSanityTick() > 0)
                {
                    int sanityTickSub =
                            PlayerPersistentNBT.Sanity.removeSanityTick((PlayerIntf) player_server, 1);

                    if (sanityTickSub <= 0)
                    {
                        PlayerPersistentNBT.Sanity.addSanity((PlayerIntf) player_server, 1);

                        if (this.frontiers_1_21x$getSanity() < 20)
                        {
                            PlayerPersistentNBT.Sanity.resetSanityTick((PlayerIntf) player_server, true);
                        }
                    }
                }
            }

            MinecraftServer server = this.level().getServer();
            if (server != null && !this.isDeadOrDying())
            {
               VectorLib.NETWORK.sendToPlayer(
                       player_server,
                       new SanitySyncPayload(
                               player_server.getUUID(),
                               this.frontiers_1_21x$getSanity(),
                               this.frontiers_1_21x$getSanityTick()
                       ));

                VectorLib.NETWORK.sendToAllTrackingEntity(
                        player_server,
                        new SanitySyncPayload(
                                player_server.getUUID(),
                                this.frontiers_1_21x$getSanity(),
                                this.frontiers_1_21x$getSanityTick()
                        ));
            }
        }

        double velX = this.getDeltaMovement().x();
        double velZ = this.getDeltaMovement().z();
        if (this.frontiers_1_21x$getSanity() == 0 && (velX != 0.0 || velZ != 0.0) && this.level().dimension() == ModDimension.CRAGS_LEVEL_KEY)
        {
            this.frnt$spawnCragsSmog();
        }
    }

    @ModifyExpressionValue(
            method = "aiStep",
            at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Player;fallDistance:F", opcode = VectorOpcode.GETFIELD))
    private float parrotDismountTweak(float original)
    {
        if (original > 0.5F)
        {
            if (!this.isShiftKeyDown() && Frontiers.CONFIG.doParrotDismountChange()) return original -2.0F;
        }
        return original;
    }

    @ModifyConstant(method = "hurtCurrentlyUsedShield", constant = @Constant(floatValue = 3.0F, ordinal = 0))
    private float shieldDamageCapTweak(float original)
    {
        float additive = 0.0F;

        if (this.useItem.is(ModItem.COBALT_SHIELD.get())) additive += 1.0F;

        return original + additive;
    }
}
