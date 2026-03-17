package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import java.util.function.Supplier;

// Materials added by Frontiers.
public enum ModToolMaterial implements Tier
{
    // Tool mats
    // Add 235 per durability
    COBALT(ModTags.Blocks.INCORRECT_FOR_COBALT_TOOL, 2501, 9.0f, 5.0f, 17, () -> Ingredient.of(ModItem.COBALT_INGOT.get())),
    VERDINITE(ModTags.Blocks.INCORRECT_FOR_VERDINITE_TOOL, 2736, 9.0f, 6.0f, 12, () -> Ingredient.of(ModItem.VERDINITE_INGOT.get())),
    FROSTITE(ModTags.Blocks.INCORRECT_FOR_VERDINITE_TOOL, 2736, 9.0f, 6.0f, 18, () -> Ingredient.of(ModItem.FROSTITE_INGOT.get())),
    VIVULITE(ModTags.Blocks.INCORRECT_FOR_VIVULITE_TOOL, 2971, 9.0f, 7.0f, 20, () -> Ingredient.of(ModItem.VIVULITE_INGOT.get())),
    BRIMTAN(ModTags.Blocks.INCORRECT_FOR_VIVULITE_TOOL, 3206, 10.0f, 8.0f, 12, () -> Ingredient.of(ModItem.BRIMTAN_INGOT.get())),
    MOURNING_GOLD(BlockTags.INCORRECT_FOR_IRON_TOOL, 875, 7.0f, 2.5f, 15, () -> Ingredient.of(ModItem.MOURNING_GOLD_INGOT.get())),
    OBSIDIAN(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 125, 12.0f, 3.0f, 22, () -> Ingredient.of(Items.OBSIDIAN));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient)
    {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses()
    {
        return this.itemDurability;
    }

    @Override
    public float getSpeed()
    {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamageBonus()
    {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops()
    {
        return this.inverseTag;
    }

    @Override
    public int getEnchantmentValue()
    {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }
}