package net.vertisoft.vectorlib.agnostic.registrars;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.vertisoft.vectorlib.VectorLib;

import java.util.HashMap;
import java.util.Map;

public class VectorPropertyReg
{
    public static class Compost
    {
        public static void add(ItemLike item, float time)
        {
            VectorLib.REGISTRY.registerCompostable(item.asItem(), time);
        }
    }

    public static class Fire
    {
        public static void add(Block block, int burnChance, int spreadChance)
        {
            VectorLib.REGISTRY.registerFlammable(block, burnChance, spreadChance);
        }
    }

    public static class Fuel
    {
        private static final int BASIC_SMELT_TIME = 200;
        private static final Map<ItemLike, Integer> MAP = new HashMap<>();

        public static Map<ItemLike, Integer> get() { return MAP; }

        public static void add(ItemLike item, int smeltedItems)
        {
            addRaw(item, smeltedItems * BASIC_SMELT_TIME);
        }

        public static void addRaw(ItemLike item, int smeltTicks)
        {
            MAP.put(item.asItem(), smeltTicks);
        }
    }
}
