package net.artyrian.frontiers.misc;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

public class ModBlockset
{
    // Mixed into the Vanilla codec
    public static class WoodSet
    {
        public static final Map<String, WoodType> VALUES = new Object2ObjectArrayMap<>();

        public static final WoodType BLIGHTED_BIRCH = reg(new WoodType("frontiers_blighted_birch", BlockSet.BLIGHTED_BIRCH));
        public static final WoodType EBONCORK = reg(
                new WoodType(
                        "frontiers_eboncork",
                        BlockSet.EBONCORK,
                        BlockSoundGroup.NETHER_WOOD,
                        BlockSoundGroup.NETHER_WOOD_HANGING_SIGN,
                        SoundEvents.BLOCK_NETHER_WOOD_FENCE_GATE_CLOSE,
                        SoundEvents.BLOCK_NETHER_WOOD_FENCE_GATE_OPEN
                )
        );

        private static WoodType reg(WoodType type)
        {
            VALUES.put(type.name(), type);
            return type;
        }
    }

    // Mixed into the Vanilla codec
    public static class BlockSet
    {
        public static final Map<String, BlockSetType> VALUES = new Object2ObjectArrayMap<>();

        public static final BlockSetType BLIGHTED_BIRCH = reg(new BlockSetType("frontiers_blighted_birch"));
        public static final BlockSetType EBONCORK = reg(
                new BlockSetType(
                        "frontiers_eboncork",
                        true,
                        true,
                        true,
                        BlockSetType.ActivationRule.EVERYTHING,
                        BlockSoundGroup.NETHER_WOOD,
                        SoundEvents.BLOCK_NETHER_WOOD_DOOR_CLOSE,
                        SoundEvents.BLOCK_NETHER_WOOD_DOOR_OPEN,
                        SoundEvents.BLOCK_NETHER_WOOD_TRAPDOOR_CLOSE,
                        SoundEvents.BLOCK_NETHER_WOOD_TRAPDOOR_OPEN,
                        SoundEvents.BLOCK_NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF,
                        SoundEvents.BLOCK_NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
                        SoundEvents.BLOCK_NETHER_WOOD_BUTTON_CLICK_OFF,
                        SoundEvents.BLOCK_NETHER_WOOD_BUTTON_CLICK_ON
                )
        );

        private static BlockSetType reg(BlockSetType type)
        {
            VALUES.put(type.name(), type);
            return type;
        }
    }
}
