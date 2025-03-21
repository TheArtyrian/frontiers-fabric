package net.artyrian.frontiers.mixin.entity.elder_guardian;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElderGuardianEntity.class)
public abstract class ElderGuardianMixin extends MobEntityMixin
{
    @Unique
    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(
            (Frontiers.LEGACY4J_LOADED) ? this.getDisplayName().copy().formatted(Formatting.DARK_PURPLE) : this.getDisplayName(),
            (Frontiers.LEGACY4J_LOADED) ? BossBar.Color.PINK : BossBar.Color.GREEN,
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
    }

    @Override
    public void injectCustomName(Text name, CallbackInfo ci)
    {
        super.injectCustomName(name, ci);
        this.bossBar.setName(
                (Frontiers.LEGACY4J_LOADED) ? this.getDisplayName().copy().formatted(Formatting.DARK_PURPLE) : this.getDisplayName()
        );
    }

    @Override
    public void damageHook(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        if (cir.getReturnValue())
        {
            if (!this.getWorld().isClient)
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

    @ModifyReturnValue(method = "createElderGuardianAttributes", at = @At("RETURN"))
    private static DefaultAttributeContainer.Builder heNeedsSomeMilk(DefaultAttributeContainer.Builder original)
    {
        return original.add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0);
    }
}
