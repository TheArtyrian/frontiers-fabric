package net.artyrian.frontiers.mixin.entity.warden;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.artyrian.frontiers.mixin.entity.HostileEntityMixin;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Warden.class)
public abstract class WardenMixin extends MobEntityMixin
{
    @Shadow abstract boolean isDiggingOrEmerging();

    @Unique
    private final ServerBossEvent bossBar = (ServerBossEvent)new ServerBossEvent(
            (Frontiers.LEGACY4J_LOADED) ? this.getDisplayName().copy().withStyle(ChatFormatting.DARK_PURPLE) : this.getDisplayName(),
            (Frontiers.LEGACY4J_LOADED) ? BossEvent.BossBarColor.PINK : BossEvent.BossBarColor.BLUE,
            BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(true);

    @Override
    public void injectOnStopTrack(ServerPlayer player, CallbackInfo ci)
    {
        super.injectOnStopTrack(player, ci);
        if (this.bossBar.getPlayers().contains(player)) this.bossBar.removePlayer(player);
    }

    @Override
    public void injectOnStartTrack(ServerPlayer player, CallbackInfo ci)
    {
        super.injectOnStartTrack(player, ci);
        //if (this.getHealth() < this.getMaxHealth()) this.bossBar.addPlayer(player);
    }

    @Override
    public void injectCustomName(Component name, CallbackInfo ci)
    {
        super.injectCustomName(name, ci);
        this.bossBar.setName(
                (Frontiers.LEGACY4J_LOADED) ? this.getDisplayName().copy().withStyle(ChatFormatting.DARK_PURPLE) : this.getDisplayName()
        );
    }

    @Inject(method = "hurt", at = @At("TAIL"))
    public void damageInject(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        if (cir.getReturnValue())
        {
            if (!this.getWorld().isClientSide && !this.isNoAi() && !this.isDiggingOrEmerging())
            {
                Entity entity = source.getEntity();
                if (entity instanceof ServerPlayer)
                {
                    if (!this.bossBar.getPlayers().contains(entity)) this.bossBar.addPlayer((ServerPlayer) entity);
                }
            }
        }
    }

    @Inject(method = "customServerAiStep", at = @At("TAIL"))
    protected void updateBar(CallbackInfo ci)
    {
        this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());
    }
}
