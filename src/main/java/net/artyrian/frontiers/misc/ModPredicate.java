package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModPredicate
{
    private static void registerBowPredicate(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        ModelPredicateProviderRegistry.register(
                item,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }
    // Simple execution script for registering predicates to specific items.
    public static void registerModPredicates()
    {
        // Shout in log.
        Frontiers.LOGGER.info("Registering Predicates locations for " + Frontiers.MOD_ID);

        // Modded Bow predicates.
        registerBowPredicate(ModItem.COPPER_BOW);
        registerBowPredicate(ModItem.IRON_BOW);
        registerBowPredicate(ModItem.DIAMOND_BOW);
        registerBowPredicate(ModItem.NETHERITE_BOW);
        registerBowPredicate(ModItem.ECHO_BOW);
        registerBowPredicate(ModItem.VERDINITE_BOW);

        // Cobalt Shield predicate
        ModelPredicateProviderRegistry.register(
                ModItem.COBALT_SHIELD,
                Identifier.ofVanilla("blocking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );

        // Cobalt Fishing Rod predicate
        ModelPredicateProviderRegistry.register(ModItem.COBALT_FISHING_ROD, Identifier.ofVanilla("cast"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                boolean bl = entity.getMainHandStack() == stack;
                boolean bl2 = entity.getOffHandStack() == stack;
                if (entity.getMainHandStack().getItem() instanceof FishingRodItem) {
                    bl2 = false;
                }

                return (bl || bl2) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishHook != null ? 1.0F : 0.0F;
            }
        });

        // Pale Trident predicate.
        ModelPredicateProviderRegistry.register(
                ModItem.PALE_TRIDENT,
                Identifier.ofVanilla("throwing"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );

        // FD Item predicates
        if (Frontiers.FARMERS_DELIGHT_LOADED)
        {
            ModelPredicateProviderRegistry.register(
                    FDItem.OBSIDIAN_KNIFE, Identifier.ofVanilla("broken"), (stack, world, entity, seed) -> 1.0F);
        }
    }
}
