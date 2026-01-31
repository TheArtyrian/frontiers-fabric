package net.artyrian.frontiers.mixin.packet;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.entity.misc.ManaOrbEntity;
import net.artyrian.frontiers.definition.networking.packet.BossBarMusicS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ManaOrbSpawnS2CPacket;
import net.artyrian.frontiers.definition.particle.ItemPickupToPosParticle;
import net.artyrian.frontiers.mixin_intf.bossbar.BossBarHudImpl;
import net.artyrian.frontiers.mixin_intf.networking.ClientPlayNetImpl;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModDimension;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPacketListener.class)
public abstract class ClientPlayNetworkHandlerMixin extends ClientCommonNetworkhandlerMix implements ClientPlayNetImpl
{
    @Shadow private ClientLevel level;
    @Shadow @Final private RandomSource random;

    @Shadow public abstract boolean sendUnsignedCommand(String command);

    @Override
    public void frontiers$onManaOrbSpawn(ManaOrbSpawnS2CPacket packet)
    {
        PacketUtils.ensureRunningOnSameThread(packet, (ClientPacketListener)(Object)this, this.minecraft);
        double d = packet.getX();
        double e = packet.getY();
        double f = packet.getZ();
        Entity entity = new ManaOrbEntity(this.level, d, e, f, packet.getMana());
        entity.syncPacketPositionCodec(d, e, f);
        entity.setYRot(0.0F);
        entity.setXRot(0.0F);
        entity.setId(packet.getEntityId());
        this.level.addEntity(entity);
    }

    @Override
    public void frontiers$onBossBarUpdateMusic(BossBarMusicS2CPacket packet)
    {
        PacketUtils.ensureRunningOnSameThread(packet, (ClientPacketListener)(Object)this, this.minecraft);
        ((BossBarHudImpl)this.minecraft.gui.getBossOverlay()).frontiers_1_21x$handleFrontiersMusicPacket(packet);
    }

    @Override
    public void frontiers$onItemToBlockPickupAnim(ItemBlockPickupS2CPacket packet)
    {
        PacketUtils.ensureRunningOnSameThread(packet, (ClientPacketListener)(Object)this, this.minecraft);
        Entity entity = this.level.getEntity(packet.getEntityId());
        Vec3 gotoPos = packet.getPos();

        if (entity != null)
        {
            if (entity instanceof ExperienceOrb)
            {
                this.level
                        .playLocalSound(
                                entity.getX(),
                                entity.getY(),
                                entity.getZ(),
                                SoundEvents.EXPERIENCE_ORB_PICKUP,
                                SoundSource.BLOCKS,
                                0.1F,
                                (this.random.nextFloat() - this.random.nextFloat()) * 0.35F + 0.9F,
                                false
                        );
            }
            else if (entity instanceof ManaOrbEntity)
            {
                this.level
                        .playLocalSound(
                                entity.getX(),
                                entity.getY(),
                                entity.getZ(),
                                ModSounds.MANA_ORB_PICKUP.get(),
                                SoundSource.BLOCKS,
                                0.2F,
                                (this.random.nextFloat() - this.random.nextFloat()) * 0.35F + 0.9F,
                                false
                        );
            }
            else
            {
                this.level
                        .playLocalSound(
                                entity.getX(),
                                entity.getY(),
                                entity.getZ(),
                                SoundEvents.ITEM_PICKUP,
                                SoundSource.BLOCKS,
                                0.2F,
                                (this.random.nextFloat() - this.random.nextFloat()) * 1.4F + 2.0F,
                                false
                        );
            }

            this.minecraft
                    .particleEngine
                    .add(new ItemPickupToPosParticle(
                            this.minecraft.getEntityRenderDispatcher(),
                            this.minecraft.renderBuffers(),
                            this.level,
                            entity,
                            gotoPos)
                    );

            if (entity instanceof ItemEntity itemEntity)
            {
                ItemStack itemStack = itemEntity.getItem();
                if (!itemStack.isEmpty()) {
                    itemStack.shrink(packet.getStackAmount());
                }

                if (itemStack.isEmpty()) {
                    this.level.removeEntity(packet.getEntityId(), Entity.RemovalReason.DISCARDED);
                }
            }
            else if (!(entity instanceof ExperienceOrb))
            {
                this.level.removeEntity(packet.getEntityId(), Entity.RemovalReason.DISCARDED);
            }
        }
    }

    @WrapOperation(method = "handleTakeItemEntity", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/multiplayer/ClientLevel;playLocalSound(DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V",
            ordinal = 1
    ))
    private void frontiersManaOrbSwapSoundCheck(
            ClientLevel instance,
            double x,
            double y,
            double z,
            SoundEvent sound, SoundSource category, float volume, float pitch, boolean useDistance, Operation<Void> original,
            @Local Entity entity)
    {
        if (entity instanceof ManaOrbEntity)
        {
            original.call(instance, x, y, z,
                    ModSounds.MANA_ORB_PICKUP.get(), SoundSource.PLAYERS,
                    0.2F,
                    (this.random.nextFloat() - this.random.nextFloat()) * 0.35F + 0.9F,
                    false
            );
        }
        else original.call(instance, x, y, z, sound, category, volume, pitch, useDistance);
    }

    @Inject(method = "determineLevelLoadingReason", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    private void switchToCragsCheck(boolean dead, ResourceKey<Level> from, ResourceKey<Level> to, CallbackInfoReturnable<ReceivingLevelScreen.Reason> cir)
    {
        if (!dead)
        {
            if (from == ModDimension.CRAGS_LEVEL_KEY || to == ModDimension.CRAGS_LEVEL_KEY)
            {
                cir.setReturnValue(FRRegistries.WorldEntryReason.CRAGS);
            }
        }
    }
}
