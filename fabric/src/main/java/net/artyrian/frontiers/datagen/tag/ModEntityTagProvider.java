package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModTags;
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
                .add(EntityType.OCELOT)
                .add(ModEntity.CRAGS_STALKER.get())
                .add(ModEntity.CRAGS_MONSTER.get())
                .add(ModEntity.JUNGLE_SPIDER.get())
        ;
        getOrCreateTagBuilder(ModTags.EntityTypes.IRON_GOLEM_NO_TARGET)
                .add(ModEntity.CRAWLER.get())
                .add(EntityType.CREEPER)
        ;
    }

    // Vanilla tags.
    private void vanillaEntityTag()
    {
        getOrCreateTagBuilder(EntityTypeTags.REDIRECTABLE_PROJECTILE)
                .add(ModEntity.BALL.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.IMPACT_PROJECTILES)
                .add(ModEntity.BALL.get())
                .add(ModEntity.GOLDEN_EGG.get())
                .add(ModEntity.FRUITCAKE.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.ARROWS)
                .add(ModEntity.BOUNCY_ARROW.get())
                .add(ModEntity.DYNAMITE_ARROW.get())
                .add(ModEntity.PRISMARINE_ARROW.get())
                .add(ModEntity.SUBZERO_ARROW.get())
                .add(ModEntity.WARP_ARROW.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.ARTHROPOD)
                .add(ModEntity.JUNGLE_SPIDER.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE)
                .add(ModEntity.JUNGLE_SPIDER.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.DISMOUNTS_UNDERWATER)
                .add(ModEntity.GOLDEN_CHICKEN.get())
        ;
        getOrCreateTagBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE)
                .add(ModEntity.PUMPKIN_GOLEM.get())
                .add(ModEntity.CROW.get())
                .add(ModEntity.GOLDEN_CHICKEN.get())
        ;
    }


    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modEntityTag();
        vanillaEntityTag();
    }
}
