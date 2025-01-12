package net.artyrian.frontiers.mixin.misc;

import com.google.common.collect.Lists;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
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
    @Unique private final List<String> frontiersTexts = Lists.<String>newArrayList();
    @Unique private static final Identifier FRONTIERS_ID = Identifier.of(Frontiers.MOD_ID,"texts/splashes.txt");

    @Inject(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Ljava/util/List;", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    protected void frontiersButtIn(ResourceManager resourceManager, Profiler profiler, CallbackInfoReturnable<List<String>> cir)
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

            List<String> complete = cir.getReturnValue();
            boolean worked = complete.addAll(gotem);

            if (worked)
            {
                Frontiers.LOGGER.info("Successfully mixed splash texts.");
                cir.setReturnValue(complete);
            }
            else
            {
                Frontiers.LOGGER.error("Unable to mix splash texts.");
                cir.setReturnValue(cir.getReturnValue());
            }
        } catch (IOException var8) {
            cir.setReturnValue(Collections.emptyList());
        }
    }

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("TAIL"))
    protected void applyFrontiersFunnyHaha(List<String> list, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci)
    {
        this.frontiersTexts.addAll(list);
    }
}
