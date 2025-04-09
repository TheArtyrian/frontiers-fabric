package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageType
{
    public static final RegistryKey<DamageType> CORE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "core_touch"));
    public static final RegistryKey<DamageType> QUICKSAND = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "quicksand"));
    public static final RegistryKey<DamageType> APPLEDOGGED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "appledogged"));
    public static final RegistryKey<DamageType> EVOKER_FANGS = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "evoker_fangs"));
    public static final RegistryKey<DamageType> ENDER_PEARL_WARP = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "ender_pearl_warp"));
    public static final RegistryKey<DamageType> STORM_SICKNESS = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "storm_sickness"));
    public static final RegistryKey<DamageType> INSANITY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "insanity"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    public static void registerDamages()
    {

    }
}
