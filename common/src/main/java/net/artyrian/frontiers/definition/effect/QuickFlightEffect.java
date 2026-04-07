package net.artyrian.frontiers.definition.effect;

import net.minecraft.world.effect.MobEffectCategory;

public class QuickFlightEffect extends PublicStatusEffect
{
    public QuickFlightEffect(MobEffectCategory category, int color)
    {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
        return true;
    }
}
