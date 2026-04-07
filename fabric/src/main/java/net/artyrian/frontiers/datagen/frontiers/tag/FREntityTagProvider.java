package net.artyrian.frontiers.datagen.frontiers.tag;

import net.artyrian.frontiers.reg.content.FREntity;
import net.artyrian.frontiers.reg.content.FRTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import java.util.concurrent.CompletableFuture;

public class FREntityTagProvider extends FabricTagProvider.EntityTypeTagProvider
{
    public FREntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, completableFuture);
    }

    private void modEntityTag()
    {
        getOrCreateTagBuilder(FRTags.EntityTypes.QUICKSAND_IMMUNE)
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
                .add(EntityType.OCELOT)
                .add(FREntity.CRAGS_STALKER.get())
                .add(FREntity.CRAGS_MONSTER.get())
                .add(FREntity.JUNGLE_SPIDER.get())
        ;
        getOrCreateTagBuilder(FRTags.EntityTypes.IRON_GOLEM_NO_TARGET)
                .add(FREntity.CRAWLER.get())
                .add(EntityType.CREEPER)
        ;
        getOrCreateTagBuilder(FRTags.EntityTypes.CANNOT_DROP_EXPERIWINKLE)
                .add(FREntity.PUMPKIN_GOLEM.get())
        ;
    }

    // Vanilla tags.
    private void vanillaEntityTag()
    {
        getOrCreateTagBuilder(EntityTypeTags.REDIRECTABLE_PROJECTILE)
                .add(FREntity.BALL.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.IMPACT_PROJECTILES)
                .add(FREntity.BALL.get())
                .add(FREntity.GOLDEN_EGG.get())
                .add(FREntity.FRUITCAKE.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.ARROWS)
                .add(FREntity.BOUNCY_ARROW.get())
                .add(FREntity.DYNAMITE_ARROW.get())
                .add(FREntity.PRISMARINE_ARROW.get())
                .add(FREntity.SUBZERO_ARROW.get())
                .add(FREntity.WARP_ARROW.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.ARTHROPOD)
                .add(FREntity.JUNGLE_SPIDER.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE)
                .add(FREntity.JUNGLE_SPIDER.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.DISMOUNTS_UNDERWATER)
                .add(FREntity.GOLDEN_CHICKEN.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE)
                .add(FREntity.PUMPKIN_GOLEM.get())
                .add(FREntity.CROW.get())
                .add(FREntity.GOLDEN_CHICKEN.get())
        ;
    }


    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modEntityTag();
        vanillaEntityTag();
    }
}
