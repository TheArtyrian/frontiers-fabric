package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageType
{
    public static final RegistryKey<DamageType> CORE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "core_touch"));
    public static final RegistryKey<DamageType> QUICKSAND = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "quicksand"));
    public static final RegistryKey<DamageType> APPLEDOGGED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Frontiers.MOD_ID, "appledogged"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    public static void registerDamages()
    {

    }
}
