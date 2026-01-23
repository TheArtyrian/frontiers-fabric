package net.artyrian.frontiers.mixin.entity.item;

import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends EntityMixin
{
    @Shadow public abstract ItemStack getItem();

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V", shift = At.Shift.AFTER))
    private void doBottleMessageDropCheck(CallbackInfo ci)
    {
        ItemStack stack = this.getItem();
        Holder<Biome> biome = this.level().getBiome(this.blockPosition());
        boolean in_valid_area = (
                biome.is(BiomeTags.IS_OCEAN) ||
                biome.is(BiomeTags.IS_BEACH) ||
                biome.is(BiomeTags.IS_RIVER) ||
                biome.is(Biomes.STONY_SHORE)
        );
        if (
                stack.is(ModItem.BOTTLED_MESSAGE.get()) &&
                stack.getCount() == 1 &&
                this.isUnderWater() &&
                in_valid_area &&
                this.getDeltaMovement().y < 0.0 &&
                !this.level().isClientSide)
        {
            (this.level()).playSound(null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    ModSounds.MESSAGE_BOTTLE_DEPOSIT.get(),
                    SoundSource.NEUTRAL,
                    0.5F,
                    1.3F);

            // Get MC server, and if not null add this item to the list.
            MinecraftServer server = level().getServer();
            if (server != null)
            {
                StateSaveLoad serverState = StateSaveLoad.getServerState(server);
                serverState.bottleItems.add(this.getItem());
            }

            this.discard();
        }
    }
}
