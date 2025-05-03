package net.artyrian.frontiers.criterion;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModCriteria
{
    public static final CurseAltarCriterion USED_CURSE_ALTAR = register("used_curse_altar", new CurseAltarCriterion());

    public static <T extends Criterion<?>> T register(String id, T criterion)
    {
        return Registry.register(Registries.CRITERION, Identifier.of(Frontiers.MOD_ID, id), criterion);
    }

    public static void registerCriterion()
    {
        // RETURN THE SLAB, OR SUFFER MY CURSE
    }
}
