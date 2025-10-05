package net.artyrian.frontiers.mixin.misc;

import com.google.common.collect.Lists;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(SplashTextResourceSupplier.class)
public abstract class SplashMixin
{
    @Shadow @Final private static Random RANDOM;
    @Unique private final List<String> frontiersTexts = Lists.<String>newArrayList();
    @Unique private static final Identifier FRONTIERS_ID = Identifier.of(Frontiers.MOD_ID,"texts/splashes.txt");

    @Unique private final SplashTextRenderer APRIL_FOOLS_FRONTIERS_SPLASHRENDER = new SplashTextRenderer("Pre-beta...?!");
    @Unique private final SplashTextRenderer ARTYS_BDAY_FRONTIERS_SPLASHRENDER = new SplashTextRenderer("Happy birthday, Artyrian!");
    @Unique private final SplashTextRenderer XENS_BDAY_FRONTIERS_SPLASHRENDER = new SplashTextRenderer("Happy birthday, Xenona!");
    @Unique private final SplashTextRenderer WES_BDAY_FRONTIERS_SPLASHRENDER = new SplashTextRenderer("Happy birthday, Yurjezich!");
    @Unique private final SplashTextRenderer HECCO_BDAY_FRONTIERS_SPLASHRENDER = new SplashTextRenderer("Happy birthday, Hecco!");
    @Unique private final List<String> HALLOWEEN_SPLASHES = Lists.newArrayList(
        "OOoooOOOoooo! Spooky!",
            "It's Spooky Month!",
            "Carve a pumpkin, Junior!",
            "Mobs with pumpkin heads!",
            "Dress up as something neat!",
            "Trick or treat!",
            "It's almost time for Halloween! Fahaha!",
            "Don't come to my house or else I'll suck your blood!",
            "Are you guys going trick-or-treating???",
            "Ooh, a piece of candy!",
            "Afraid of the big, black cat!",
            "Go find a Swamp Hut!",
            "Blighted Birch reigns supreme!",
            "Also try Wega's Challenge!",
            "Take ONE!",
            "Take TWO!",
            "2spoopy4me"
    );

    @ModifyReturnValue(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Ljava/util/List;", at = @At(value = "RETURN", ordinal = 0))
    protected List<String> frontiersButtIn(List<String> original, @Local(argsOnly = true) ResourceManager resourceManager, @Local(argsOnly = true) Profiler profiler)
    {
        try {
            BufferedReader bufferedReader = MinecraftClient.getInstance().getResourceManager().openAsReader(FRONTIERS_ID);

            List<String> gotem;
            try {
                gotem = (List<String>)bufferedReader.lines().map(String::trim).filter(splashText -> splashText.hashCode() != 125780783).collect(Collectors.toList());
            } catch (Throwable var7) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            List<String> complete = original;
            boolean worked = complete.addAll(gotem);

            if (worked)
            {
                Frontiers.LOGGER.info("Successfully mixed splash texts.");
                return complete;
            }
            else
            {
                Frontiers.LOGGER.error("Unable to mix splash texts.");
                return original;
            }
        } catch (IOException var8) {
            return original;
        }
    }

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("TAIL"))
    protected void applyFrontiersFunnyHaha(List<String> list, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci)
    {
        this.frontiersTexts.addAll(list);
    }

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void doAprilFoolsReplacer(CallbackInfoReturnable<SplashTextRenderer> cir)
    {
        if (Frontiers.IS_APRIL_FOOLS) cir.setReturnValue(APRIL_FOOLS_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.IS_XENS_BDAY) cir.setReturnValue(XENS_BDAY_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.IS_WES_BDAY) cir.setReturnValue(WES_BDAY_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.IS_HECCO_BDAY) cir.setReturnValue(HECCO_BDAY_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.IS_THE_WORST_DAY_EVER) cir.setReturnValue(ARTYS_BDAY_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.IS_HALLOWEEN) cir.setReturnValue(
                new SplashTextRenderer(this.HALLOWEEN_SPLASHES.get(RANDOM.nextInt(this.HALLOWEEN_SPLASHES.size()))));
    }
}
