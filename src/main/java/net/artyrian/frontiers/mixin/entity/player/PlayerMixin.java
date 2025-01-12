package net.artyrian.frontiers.mixin.entity.player;

import net.artyrian.frontiers.misc.ModAttribute;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInteface;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
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

    @Override
    public boolean usedUpgradeApple()
    {
        return (this.getAttributeInstance(ModAttribute.PLAYER_EATEN_APPLE).getValue() > 0.0);
    }

    @Unique
    private void setUpgradeApple(boolean value)
    {
        double valueset = (value) ? 1.0 : 0.0;
        this.getAttributeInstance(ModAttribute.PLAYER_EATEN_APPLE).setBaseValue(valueset);
    }

    @Inject(method = "createPlayerAttributes", at = @At("RETURN"), cancellable = true)
    private static void createPlayerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir)
    {
        DefaultAttributeContainer.Builder inthemix = cir.getReturnValue();
        inthemix.add(ModAttribute.PLAYER_EATEN_APPLE, 0.0);
        inthemix.add(ModAttribute.PLAYER_DEFAULT_HP, 20.0);
        cir.setReturnValue(inthemix);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readNbtAdd(NbtCompound nbt, CallbackInfo ci)
    {
        if (nbt.contains("UsedAppleBuff", NbtElement.BYTE_TYPE))
        {
            boolean frontiers_usedApple = usedUpgradeApple();
            boolean get_value = nbt.getBoolean("UsedAppleBuff");

            if (frontiers_usedApple != get_value)
            {
                double current_max_health = this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).getValue();
                double max_with_or_without_apple = (get_value) ? current_max_health + 6.0 : current_max_health - 6.0;

                setUpgradeApple(get_value);
                this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(max_with_or_without_apple);
            }
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeNbtAdd(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putBoolean("UsedAppleBuff", this.usedUpgradeApple());
    }
}
