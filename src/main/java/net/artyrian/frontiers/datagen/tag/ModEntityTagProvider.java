package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider
{
    public ModEntityTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    private void modEntityTag()
    {
        getOrCreateTagBuilder(ModTags.EntityTypes.QUICKSAND_IMMUNE)
                .add(EntityType.ENDER_DRAGON)
                .add(EntityType.WITHER)
                .add(EntityType.WARDEN)
                .add(EntityType.PAINTING)
                .add(EntityType.ITEM_FRAME)
                .add(EntityType.GLOW_ITEM_FRAME)
                .add(EntityType.FIREBALL)
                .add(EntityType.SMALL_FIREBALL)
                .add(EntityType.DRAGON_FIREBALL)
                .add(EntityType.ITEM_DISPLAY)
                .add(EntityType.INTERACTION)
                .add(EntityType.MARKER)
                .add(EntityType.AREA_EFFECT_CLOUD)
                .add(EntityType.FALLING_BLOCK)
                .add(EntityType.END_CRYSTAL)
                .add(EntityType.LEASH_KNOT)
                .add(EntityType.LIGHTNING_BOLT)
                .add(ModEntity.CRAGS_STALKER)
                .add(ModEntity.CRAGS_MONSTER)
                .add(ModEntity.JUNGLE_SPIDER)
        ;
    }

    // Vanilla tags.
    private void vanillaEntityTag()
    {
        getOrCreateTagBuilder(EntityTypeTags.REDIRECTABLE_PROJECTILE)
                .add(ModEntity.BALL)
        ;
        getOrCreateTagBuilder(EntityTypeTags.ARROWS)
                .add(ModEntity.BOUNCY_ARROW)
                .add(ModEntity.DYNAMITE_ARROW)
                .add(ModEntity.PRISMARINE_ARROW)
                .add(ModEntity.SUBZERO_ARROW)
                .add(ModEntity.WARP_ARROW)
        ;
        getOrCreateTagBuilder(EntityTypeTags.ARTHROPOD)
                .add(ModEntity.JUNGLE_SPIDER)
        ;
        getOrCreateTagBuilder(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE)
                .add(ModEntity.JUNGLE_SPIDER)
        ;
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        modEntityTag();
        vanillaEntityTag();
    }
}
