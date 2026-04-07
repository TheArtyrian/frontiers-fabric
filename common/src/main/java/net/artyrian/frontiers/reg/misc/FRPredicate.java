package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.definition.item.custom.FrontiersBowItem;
import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.vertisoft.vectorlib.VectorLib;

public class FRPredicate
{
    private static void registerBowPredicate(Item item) {
        VectorLib.client().registerPredicate(item, ResourceLocation.withDefaultNamespace("pull"), (stack, world, entity, seed) ->
        {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / (item instanceof FrontiersBowItem ? ((FrontiersBowItem)item).getPullTickSpeed() : 20.0F);
            }
        });

        VectorLib.client().registerPredicate(
                item,
                ResourceLocation.withDefaultNamespace("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
        );
    }
    // Simple execution script for registering predicates to specific items.
    public static void registerModPredicates()
    {
        // Modded Bow predicates.
        registerBowPredicate(FRItems.COPPER_BOW.get());
        registerBowPredicate(FRItems.IRON_BOW.get());
        registerBowPredicate(FRItems.DIAMOND_BOW.get());
        registerBowPredicate(FRItems.NETHERITE_BOW.get());
        registerBowPredicate(FRItems.ECHO_BOW.get());
        registerBowPredicate(FRItems.VERDINITE_BOW.get());

        // Cobalt Shield predicate
        VectorLib.client().registerPredicate(
                FRItems.COBALT_SHIELD.get(),
                ResourceLocation.withDefaultNamespace("blocking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
        );

        // Cobalt Fishing Rod predicate
        VectorLib.client().registerPredicate(
                FRItems.COBALT_FISHING_ROD.get(),
                ResourceLocation.withDefaultNamespace("cast"),
                (stack, world, entity, seed) -> {
                    if (entity == null)
                    {
                        return 0.0F;
                    }
                    else
                    {
                        boolean bl = entity.getMainHandItem() == stack;
                        boolean bl2 = entity.getOffhandItem() == stack;
                        if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
                            bl2 = false;
                        }

                        return (bl || bl2) && entity instanceof Player && ((Player)entity).fishing != null ? 1.0F : 0.0F;
                    }
                }
        );

        // Pale Trident predicate.
        VectorLib.client().registerPredicate(
                FRItems.PALE_TRIDENT.get(),
                ResourceLocation.withDefaultNamespace("throwing"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
        );
    }
}
