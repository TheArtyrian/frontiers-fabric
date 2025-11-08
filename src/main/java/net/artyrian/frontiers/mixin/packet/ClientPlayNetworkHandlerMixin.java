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
import net.artyrian.frontiers.mixin_interfaces.bossbar.BossBarHudImpl;
import net.artyrian.frontiers.mixin_interfaces.networking.ClientPlayNetImpl;
import net.artyrian.frontiers.particle.ItemPickupToPosParticle;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin extends ClientCommonNetworkhandlerMix implements ClientPlayNetImpl
{
    @Shadow private ClientWorld world;
    @Shadow @Final private Random random;

    @Shadow public abstract boolean sendCommand(String command);

    @Override
    public void frontiers$onManaOrbSpawn(ManaOrbSpawnS2CPacket packet)
    {
        NetworkThreadUtils.forceMainThread(packet, (ClientPlayNetworkHandler)(Object)this, this.client);
        double d = packet.getX();
        double e = packet.getY();
        double f = packet.getZ();
        Entity entity = new ManaOrbEntity(this.world, d, e, f, packet.getMana());
        entity.updateTrackedPosition(d, e, f);
        entity.setYaw(0.0F);
        entity.setPitch(0.0F);
        entity.setId(packet.getEntityId());
        this.world.addEntity(entity);
    }

    @Override
    public void frontiers$onBossBarUpdateMusic(BossBarMusicS2CPacket packet)
    {
        NetworkThreadUtils.forceMainThread(packet, (ClientPlayNetworkHandler)(Object)this, this.client);
        ((BossBarHudImpl)this.client.inGameHud.getBossBarHud()).frontiers_1_21x$handleFrontiersMusicPacket(packet);
    }

    @Override
    public void frontiers$onItemToBlockPickupAnim(ItemBlockPickupS2CPacket packet)
    {
        NetworkThreadUtils.forceMainThread(packet, (ClientPlayNetworkHandler)(Object)this, this.client);
        Entity entity = this.world.getEntityById(packet.getEntityId());
        Vec3d gotoPos = packet.getPos();

        if (entity != null)
        {
            if (entity instanceof ExperienceOrbEntity)
            {
                this.world
                        .playSound(
                                entity.getX(),
                                entity.getY(),
                                entity.getZ(),
                                SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                                SoundCategory.BLOCKS,
                                0.1F,
                                (this.random.nextFloat() - this.random.nextFloat()) * 0.35F + 0.9F,
                                false
                        );
            }
            else if (entity instanceof ManaOrbEntity)
            {
                this.world
                        .playSound(
                                entity.getX(),
                                entity.getY(),
                                entity.getZ(),
                                ModSounds.MANA_ORB_PICKUP,
                                SoundCategory.BLOCKS,
                                0.2F,
                                (this.random.nextFloat() - this.random.nextFloat()) * 0.35F + 0.9F,
                                false
                        );
            }
            else
            {
                this.world
                        .playSound(
                                entity.getX(),
                                entity.getY(),
                                entity.getZ(),
                                SoundEvents.ENTITY_ITEM_PICKUP,
                                SoundCategory.BLOCKS,
                                0.2F,
                                (this.random.nextFloat() - this.random.nextFloat()) * 1.4F + 2.0F,
                                false
                        );
            }

            this.client
                    .particleManager
                    .addParticle(new ItemPickupToPosParticle(
                            this.client.getEntityRenderDispatcher(),
                            this.client.getBufferBuilders(),
                            this.world,
                            entity,
                            gotoPos)
                    );

            if (entity instanceof ItemEntity itemEntity)
            {
                ItemStack itemStack = itemEntity.getStack();
                if (!itemStack.isEmpty()) {
                    itemStack.decrement(packet.getStackAmount());
                }

                if (itemStack.isEmpty()) {
                    this.world.removeEntity(packet.getEntityId(), Entity.RemovalReason.DISCARDED);
                }
            }
            else if (!(entity instanceof ExperienceOrbEntity))
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
            ClientWorld instance,
            double x,
            double y,
            double z,
            SoundEvent sound, SoundCategory category, float volume, float pitch, boolean useDistance, Operation<Void> original,
            @Local Entity entity)
    {
        if (entity instanceof ManaOrbEntity)
        {
            original.call(instance, x, y, z,
                    ModSounds.MANA_ORB_PICKUP, SoundCategory.PLAYERS,
                    0.2F,
                    (this.random.nextFloat() - this.random.nextFloat()) * 0.35F + 0.9F,
                    false
            );
        }
        else original.call(instance, x, y, z, sound, category, volume, pitch, useDistance);
    }

    @Inject(method = "getWorldEntryReason", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    private void switchToCragsCheck(boolean dead, RegistryKey<World> from, RegistryKey<World> to, CallbackInfoReturnable<DownloadingTerrainScreen.WorldEntryReason> cir)
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
