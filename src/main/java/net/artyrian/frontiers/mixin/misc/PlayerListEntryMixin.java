package net.artyrian.frontiers.mixin.misc;

import com.mojang.authlib.GameProfile;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

// Special thanks to https://github.com/Yirmiri/ for the guidance :)
@Mixin(PlayerListEntry.class)
public abstract class PlayerListEntryMixin
{
    @Shadow public abstract GameProfile getProfile();

    @Shadow @Final private Supplier<SkinTextures> texturesSupplier;

    @Inject(method = "getSkinTextures", at = @At("HEAD"), cancellable = true)
    private void getFrontiersHelpersCapes(CallbackInfoReturnable<SkinTextures> cir)
    {
        Identifier texture = null;
        String uuid = getProfile().getId().toString();

        if (Frontiers.CONFIG.doSpecialCapeEnabled())
        {
            switch (uuid)
            {
                // Yurjezich
                case "2a9c377e-26cc-4d48-a62a-05ce3ac2f405":
                {
                    texture = Identifier.of(Frontiers.MOD_ID, "textures/entity/capes/yurjezich_cape.png");
                }
            }
        }

        if (texture != null)
        {
            SkinTextures textures = texturesSupplier.get();
            cir.setReturnValue(new SkinTextures(textures.texture(), textures.textureUrl(), texture, texture, textures.model(), textures.secure()));
        }
    }
}
