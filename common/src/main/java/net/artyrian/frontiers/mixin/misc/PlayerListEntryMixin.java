package net.artyrian.frontiers.mixin.misc;

import com.mojang.authlib.GameProfile;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

// Special thanks to https://github.com/Yirmiri/ for the guidance :)
@Mixin(PlayerInfo.class)
public abstract class PlayerListEntryMixin
{
    @Shadow public abstract GameProfile getProfile();
    @Shadow @Final private Supplier<PlayerSkin> texturesSupplier;

    @Inject(method = "getSkinTextures", at = @At("HEAD"), cancellable = true)
    private void getFrontiersHelpersCapes(CallbackInfoReturnable<PlayerSkin> cir)
    {
        ResourceLocation texture = null;
        String uuid = getProfile().getId().toString();

        if (Frontiers.CONFIG.doSpecialCapeEnabled() && Frontiers.CONTRIBUTOR_CAPES.containsKey(uuid))
        {
            texture = Frontiers.CONTRIBUTOR_CAPES.get(uuid);
        }

        if (texture != null)
        {
            PlayerSkin textures = texturesSupplier.get();
            cir.setReturnValue(new PlayerSkin(textures.texture(), textures.textureUrl(), texture, texture, textures.model(), textures.secure()));
        }
    }
}
