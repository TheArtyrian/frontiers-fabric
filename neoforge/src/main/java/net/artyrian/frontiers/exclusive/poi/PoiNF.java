package net.artyrian.frontiers.exclusive.poi;

import com.google.common.collect.ImmutableSet;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.misc.ModPointOfInterest;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PoiNF
{
    public static final DeferredRegister<PoiType> POIS = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, Frontiers.MOD_ID);

    public static void doHookup(IEventBus eventBus)
    {
        POIS.register(eventBus);

        ModPointOfInterest.CRAGS_PORTAL = POIS.register("crags_portal", () -> new PoiType(
                ImmutableSet.of(ModBlocks.CRAGS_PORTAL.get().defaultBlockState()),
                0,
                1
        ));
    }
}
