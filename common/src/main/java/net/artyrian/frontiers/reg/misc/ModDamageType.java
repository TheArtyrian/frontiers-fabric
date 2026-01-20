package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class ModDamageType
{
    public static final ResourceKey<DamageType> CORE = ResourceKey.create(Registries.DAMAGE_TYPE, Frontiers.id("core_touch"));
    public static final ResourceKey<DamageType> QUICKSAND = ResourceKey.create(Registries.DAMAGE_TYPE, Frontiers.id("quicksand"));
    public static final ResourceKey<DamageType> APPLEDOGGED = ResourceKey.create(Registries.DAMAGE_TYPE, Frontiers.id("appledogged"));
    public static final ResourceKey<DamageType> EVOKER_FANGS = ResourceKey.create(Registries.DAMAGE_TYPE, Frontiers.id("evoker_fangs"));
    public static final ResourceKey<DamageType> ENDER_PEARL_WARP = ResourceKey.create(Registries.DAMAGE_TYPE, Frontiers.id("ender_pearl_warp"));
    public static final ResourceKey<DamageType> STORM_SICKNESS = ResourceKey.create(Registries.DAMAGE_TYPE, Frontiers.id("storm_sickness"));
    public static final ResourceKey<DamageType> INSANITY = ResourceKey.create(Registries.DAMAGE_TYPE, Frontiers.id("insanity"));

    public static DamageSource of(Level world, ResourceKey<DamageType> key)
    {
        return new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
    }

    public static void registerDamages()
    {

    }
}
