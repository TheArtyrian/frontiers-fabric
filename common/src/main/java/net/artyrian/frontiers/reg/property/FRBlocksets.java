package net.artyrian.frontiers.reg.property;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import java.util.Map;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class FRBlocksets
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
                        SoundType.NETHER_WOOD,
                        SoundType.NETHER_WOOD_HANGING_SIGN,
                        SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE,
                        SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN
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
                        BlockSetType.PressurePlateSensitivity.EVERYTHING,
                        SoundType.NETHER_WOOD,
                        SoundEvents.NETHER_WOOD_DOOR_CLOSE,
                        SoundEvents.NETHER_WOOD_DOOR_OPEN,
                        SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE,
                        SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN,
                        SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF,
                        SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
                        SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF,
                        SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON
                )
        );

        private static BlockSetType reg(BlockSetType type)
        {
            VALUES.put(type.name(), type);
            return type;
        }
    }
}
