package net.artyrian.frontiers.mixin.client;

import net.artyrian.frontiers.data.packets.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.misc.ModWorldEntryReason;
import net.artyrian.frontiers.mixin_interfaces.ClientPlayNetImpl;
import net.artyrian.frontiers.particle.ItemPickupToPosParticle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.particle.ItemPickupParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundCategory;
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
                                SoundCategory.PLAYERS,
                                0.1F,
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
                                SoundCategory.PLAYERS,
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
