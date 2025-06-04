package net.artyrian.frontiers.mixin.entity.item;

import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends EntityMixin
{
    @Shadow public abstract ItemStack getStack();

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;tick()V", shift = At.Shift.AFTER))
    private void doBottleMessageDropCheck(CallbackInfo ci)
    {
        ItemStack stack = this.getStack();
        RegistryEntry<Biome> biome = this.getWorld().getBiome(this.getBlockPos());
        boolean in_valid_area = (
                biome.isIn(BiomeTags.IS_OCEAN) ||
                biome.isIn(BiomeTags.IS_BEACH) ||
                biome.matchesKey(BiomeKeys.STONY_SHORE)
        );
        if (
                stack.isOf(ModItem.BOTTLED_MESSAGE) &&
                stack.getCount() == 1 &&
                this.isSubmergedInWater() &&
                in_valid_area &&
                this.getVelocity().y < 0.0 &&
                !this.getWorld().isClient)
        {
            (this.getWorld()).playSound(null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    ModSounds.MESSAGE_BOTTLE_DEPOSIT,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    1.3F);

            // Get MC server, and if not null add this item to the list.
            MinecraftServer server = getWorld().getServer();
            if (server != null)
            {
                StateSaveLoad serverState = StateSaveLoad.getServerState(server);
                serverState.bottleItems.add(this.getStack());
            }

            this.discard();
        }
    }
}
