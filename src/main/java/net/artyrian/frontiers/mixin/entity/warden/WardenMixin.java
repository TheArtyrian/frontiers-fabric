package net.artyrian.frontiers.mixin.entity.warden;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.artyrian.frontiers.mixin.entity.HostileEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WardenEntity.class)
public abstract class WardenMixin extends MobEntityMixin
{
    @Shadow abstract boolean isDiggingOrEmerging();

    @Unique
    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(
            (Frontiers.LEGACY4J_LOADED) ? this.getDisplayName().copy().formatted(Formatting.DARK_PURPLE) : this.getDisplayName(),
            (Frontiers.LEGACY4J_LOADED) ? BossBar.Color.PINK : BossBar.Color.BLUE,
            BossBar.Style.PROGRESS).setDarkenSky(true);

    @Override
    public void injectOnStopTrack(ServerPlayerEntity player, CallbackInfo ci)
    {
        super.injectOnStopTrack(player, ci);
        if (this.bossBar.getPlayers().contains(player)) this.bossBar.removePlayer(player);
    }

    @Override
    public void injectOnStartTrack(ServerPlayerEntity player, CallbackInfo ci)
    {
        super.injectOnStartTrack(player, ci);
        //if (this.getHealth() < this.getMaxHealth()) this.bossBar.addPlayer(player);
    }

    @Override
    public void injectCustomName(Text name, CallbackInfo ci)
    {
        super.injectCustomName(name, ci);
        this.bossBar.setName(
                (Frontiers.LEGACY4J_LOADED) ? this.getDisplayName().copy().formatted(Formatting.DARK_PURPLE) : this.getDisplayName()
        );
    }

    @Inject(method = "damage", at = @At("TAIL"))
    public void damageInject(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        if (cir.getReturnValue())
        {
            if (!this.getWorld().isClient && !this.isAiDisabled() && !this.isDiggingOrEmerging())
            {
                Entity entity = source.getAttacker();
                if (entity instanceof ServerPlayerEntity)
                {
                    if (!this.bossBar.getPlayers().contains(entity)) this.bossBar.addPlayer((ServerPlayerEntity) entity);
                }
            }
        }
    }

    @Inject(method = "mobTick", at = @At("TAIL"))
    protected void updateBar(CallbackInfo ci)
    {
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }
}
