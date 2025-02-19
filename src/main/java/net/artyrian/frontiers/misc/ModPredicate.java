package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModPredicate
{
    // Simple execution script for registering predicates to specific items.
    public static void registerModPredicates()
    {
        // Shout in log.
        Frontiers.LOGGER.info("Registering Predicates locations for " + Frontiers.MOD_ID);

        // Verdinite Bow predicates.
        ModelPredicateProviderRegistry.register(ModItem.VERDINITE_BOW, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        ModelPredicateProviderRegistry.register(
                ModItem.VERDINITE_BOW,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );

        // Cobalt fishing rod predicate.
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
    }
}
