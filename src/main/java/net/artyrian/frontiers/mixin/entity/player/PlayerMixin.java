package net.artyrian.frontiers.mixin.entity.player;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.ModAttribute;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInteface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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

    @Override
    public boolean usedUpgradeApple()
    {
        return (this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).hasModifier(ModAttribute.APPLE_HEALTH.id()));
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
        nbt.putBoolean("UsedAppleBuff", this.usedUpgradeApple());
    }
}
