package net.artyrian.frontiers.mixin.misc;

import com.google.common.collect.Lists;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;
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

@Mixin(SplashManager.class)
public abstract class SplashMixin
{
    @Shadow @Final private static RandomSource RANDOM;
    @Unique private final List<String> frontiersTexts = Lists.<String>newArrayList();
    @Unique private static final ResourceLocation FRONTIERS_ID = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"texts/splashes.txt");

    @Unique private final SplashRenderer APRIL_FOOLS_FRONTIERS_SPLASHRENDER = new SplashRenderer("Pre-beta...?!");
    @Unique private final SplashRenderer ARTYS_BDAY_FRONTIERS_SPLASHRENDER = new SplashRenderer("Happy birthday, Artyrian!");
    @Unique private final SplashRenderer XENS_BDAY_FRONTIERS_SPLASHRENDER = new SplashRenderer("Happy birthday, Xenona!");
    @Unique private final SplashRenderer WES_BDAY_FRONTIERS_SPLASHRENDER = new SplashRenderer("Happy birthday, Yurjezich!");
    @Unique private final SplashRenderer HECCO_BDAY_FRONTIERS_SPLASHRENDER = new SplashRenderer("Happy birthday, Hecco!");
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
            "2spoopy4me",
            "Just the facts!"
    );

    @ModifyReturnValue(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Ljava/util/List;", at = @At(value = "RETURN", ordinal = 0))
    protected List<String> frontiersButtIn(List<String> original, @Local(argsOnly = true) ResourceManager resourceManager, @Local(argsOnly = true) ProfilerFiller profiler)
    {
        try {
            BufferedReader bufferedReader = Minecraft.getInstance().getResourceManager().openAsReader(FRONTIERS_ID);

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
    protected void applyFrontiersFunnyHaha(List<String> list, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci)
    {
        this.frontiersTexts.addAll(list);
    }

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void doAprilFoolsReplacer(CallbackInfoReturnable<SplashRenderer> cir)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS) cir.setReturnValue(APRIL_FOOLS_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.EVENTS.IS_XENS_BDAY) cir.setReturnValue(XENS_BDAY_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.EVENTS.IS_WES_BDAY) cir.setReturnValue(WES_BDAY_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.EVENTS.IS_HECCO_BDAY) cir.setReturnValue(HECCO_BDAY_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.EVENTS.IS_THE_WORST_DAY_EVER) cir.setReturnValue(ARTYS_BDAY_FRONTIERS_SPLASHRENDER);
        else if (Frontiers.EVENTS.IS_HALLOWEEN) cir.setReturnValue(
                new SplashRenderer(this.HALLOWEEN_SPLASHES.get(RANDOM.nextInt(this.HALLOWEEN_SPLASHES.size()))));
    }
}
