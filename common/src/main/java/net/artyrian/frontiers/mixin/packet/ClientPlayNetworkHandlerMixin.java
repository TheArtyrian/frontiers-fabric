package net.artyrian.frontiers.mixin.packet;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.data.packets.BossBarMusicS2CPacket;
import net.artyrian.frontiers.data.packets.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.data.packets.ManaOrbSpawnS2CPacket;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.entity.misc.ManaOrbEntity;
import net.artyrian.frontiers.misc.ModWorldEntryReason;
import net.artyrian.frontiers.mixin_intf.bossbar.BossBarHudImpl;
import net.artyrian.frontiers.mixin_intf.networking.ClientPlayNetImpl;
import net.artyrian.frontiers.particle.ItemPickupToPosParticle;
import net.artyrian.frontiers.sounds.ModSounds;
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
    @Shadow private ClientLevel world;
    @Shadow @Final private RandomSource random;

    @Shadow public abstract boolean sendCommand(String command);

    @Override
    public void frontiers$onManaOrbSpawn(ManaOrbSpawnS2CPacket packet)
    {
        PacketUtils.ensureRunningOnSameThread(packet, (ClientPacketListener)(Object)this, this.client);
        double d = packet.getX();
        double e = packet.getY();
        double f = packet.getZ();
        Entity entity = new ManaOrbEntity(this.world, d, e, f, packet.getMana());
        entity.syncPacketPositionCodec(d, e, f);
        entity.setYRot(0.0F);
        entity.setXRot(0.0F);
        entity.setId(packet.getEntityId());
        this.world.addEntity(entity);
    }

    @Override
    public void frontiers$onBossBarUpdateMusic(BossBarMusicS2CPacket packet)
    {
        PacketUtils.ensureRunningOnSameThread(packet, (ClientPacketListener)(Object)this, this.client);
        ((BossBarHudImpl)this.client.gui.getBossOverlay()).frontiers_1_21x$handleFrontiersMusicPacket(packet);
    }

    @Override
    public void frontiers$onItemToBlockPickupAnim(ItemBlockPickupS2CPacket packet)
    {
        PacketUtils.ensureRunningOnSameThread(packet, (ClientPacketListener)(Object)this, this.client);
        Entity entity = this.world.getEntity(packet.getEntityId());
        Vec3 gotoPos = packet.getPos();

        if (entity != null)
        {
            if (entity instanceof ExperienceOrb)
            {
                this.world
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
                this.world
                        .playLocalSound(
                                entity.getX(),
                                entity.getY(),
                                entity.getZ(),
                                ModSounds.MANA_ORB_PICKUP,
                                SoundSource.BLOCKS,
                                0.2F,
                                (this.random.nextFloat() - this.random.nextFloat()) * 0.35F + 0.9F,
                                false
                        );
            }
            else
            {
                this.world
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

            this.client
                    .particleEngine
                    .add(new ItemPickupToPosParticle(
                            this.client.getEntityRenderDispatcher(),
                            this.client.renderBuffers(),
                            this.world,
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
                    this.world.removeEntity(packet.getEntityId(), Entity.RemovalReason.DISCARDED);
                }
            }
            else if (!(entity instanceof ExperienceOrb))
            {
                this.world.removeEntity(packet.getEntityId(), Entity.RemovalReason.DISCARDED);
            }
        }
    }

    @WrapOperation(method = "onItemPickupAnimation", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/world/ClientWorld;playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V",
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
                    ModSounds.MANA_ORB_PICKUP, SoundSource.PLAYERS,
                    0.2F,
                    (this.random.nextFloat() - this.random.nextFloat()) * 0.35F + 0.9F,
                    false
            );
        }
        else original.call(instance, x, y, z, sound, category, volume, pitch, useDistance);
    }

    @Inject(method = "getWorldEntryReason", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    private void switchToCragsCheck(boolean dead, ResourceKey<Level> from, ResourceKey<Level> to, CallbackInfoReturnable<ReceivingLevelScreen.Reason> cir)
    {
        if (!dead)
        {
            if (from == ModDimension.CRAGS_LEVEL_KEY || to == ModDimension.CRAGS_LEVEL_KEY)
            {
                cir.setReturnValue(ModWorldEntryReason.CRAGS);
            }
        }
    }
}
