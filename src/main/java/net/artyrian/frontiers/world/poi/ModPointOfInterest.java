package net.artyrian.frontiers.world.poi;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class ModPointOfInterest
{
    public static final PointOfInterestType CRAGS_PORTAL =
            PointOfInterestHelper.register(
                    Identifier.of(Frontiers.MOD_ID, "crags_portal"),
                    0,
                    1,
                    ModBlocks.CRAGS_PORTAL);

    public static void registerPOIs()
    {

    }
}
