package net.artyrian.frontiers.tag;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

// Custom Frontiers block tags.
public class ModBlockTags
{
    // List of all block tags.
    public static final TagKey<Block> INCORRECT_FOR_COBALT_TOOL = of("incorrect_for_cobalt_tool");
    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = of("needs_netherite_tool");
    public static final TagKey<Block> NEEDS_COBALT_TOOL = of("needs_cobalt_tool");

    // Creates a block tag.
    private static TagKey<Block> of(String id)
    {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Frontiers.MOD_ID, id));
    }
}
