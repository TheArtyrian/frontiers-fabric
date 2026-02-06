package net.vertisoft.vectorlib.agnostic.util;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

/** Stores information for a villager trade. */
public class VectorTrade
{
    public static final float LOW_MULT = 0.05F;
    public static final float HIGH_MULT = 0.2F;

    private final VillagerTrades.ItemListing offer;
    private final VillagerProfession profession;
    private final int level;

    public VectorTrade(VillagerProfession prof, int level, VillagerTrades.ItemListing offer)
    {
        this.profession = prof;
        this.level = level;
        this.offer = offer;
    }

    public int getLvl() { return level; }
    public VillagerProfession getJob() { return profession; }
    public VillagerTrades.ItemListing getTrade() { return offer; }
}
