package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import java.util.concurrent.CompletableFuture;

public class ModEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider
{
    public ModEntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, completableFuture);
    }

    private void modEntityTag()
    {
        tag(ModTags.EntityTypes.QUICKSAND_IMMUNE)
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
        tag(ModTags.EntityTypes.IRON_GOLEM_NO_TARGET)
                .add(ModEntity.CRAWLER)
                .add(EntityType.CREEPER)
        ;
    }

    // Vanilla tags.
    private void vanillaEntityTag()
    {
        tag(EntityTypeTags.REDIRECTABLE_PROJECTILE)
                .add(ModEntity.BALL)
        ;
        tag(EntityTypeTags.IMPACT_PROJECTILES)
                .add(ModEntity.BALL)
                .add(ModEntity.GOLDEN_EGG)
                .add(ModEntity.FRUITCAKE)
        ;
        tag(EntityTypeTags.ARROWS)
                .add(ModEntity.BOUNCY_ARROW)
                .add(ModEntity.DYNAMITE_ARROW)
                .add(ModEntity.PRISMARINE_ARROW)
                .add(ModEntity.SUBZERO_ARROW)
                .add(ModEntity.WARP_ARROW)
        ;
        tag(EntityTypeTags.ARTHROPOD)
                .add(ModEntity.JUNGLE_SPIDER)
        ;
        tag(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE)
                .add(ModEntity.JUNGLE_SPIDER)
        ;
        tag(EntityTypeTags.DISMOUNTS_UNDERWATER)
                .add(ModEntity.GOLDEN_CHICKEN)
        ;
        tag(EntityTypeTags.FALL_DAMAGE_IMMUNE)
                .add(ModEntity.PUMPKIN_GOLEM)
                .add(ModEntity.CROW)
                .add(ModEntity.GOLDEN_CHICKEN)
        ;
    }


    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modEntityTag();
        vanillaEntityTag();
    }
}
