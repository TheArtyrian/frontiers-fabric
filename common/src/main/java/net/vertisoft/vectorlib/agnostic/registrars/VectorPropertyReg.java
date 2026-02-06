package net.vertisoft.vectorlib.agnostic.registrars;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.vertisoft.vectorlib.VectorLib;

import java.util.HashMap;
import java.util.Map;

/** Provides loader-agnostic re */
public class VectorPropertyReg
{
    public static class Compost
    {
        public static final float TINY = 0.30F;
        public static final float HALF = 0.50F;
        public static final float MED = 0.65F;
        public static final float LARGE = 0.85F;
        public static final float MAX = 1.0F;

        private static final Map<ItemLike, Float> MAP = new HashMap<>();

        public static Map<ItemLike, Float> get() { return MAP; }

        public static void add(ItemLike item, float percent)
        {
            VectorLib.REGISTRY.registerCompostable(item.asItem(), percent);
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
        private static final Map<Item, Integer> MAP = new HashMap<>();

        public static Map<Item, Integer> get() { return MAP; }

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
