package net.vertisoft.vectorlib.agnostic.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.vertisoft.vectorlib.VectorLib;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VectorItemTab
{
    private final ResourceKey<CreativeModeTab> key;
    private final List<Pair<ItemStack, ItemStack>> BEFORE = new ArrayList<>();
    private final List<Pair<ItemStack, ItemStack>> AFTER = new ArrayList<>();

    public VectorItemTab(VanillaTab tab) { this(tab.key(), null); }
    public VectorItemTab(VanillaTab tab, List<VectorItemTab> list) { this(tab.key(), list); }
    public VectorItemTab(ResourceKey<CreativeModeTab> key) { this(key, null); }

    public VectorItemTab(ResourceKey<CreativeModeTab> key, @Nullable List<VectorItemTab> iterator)
    {
        this.key = key;
        if (iterator != null)
        {
            iterator.add(this);
        }
    }

    public void addBefore(ItemLike before, ItemLike whatToAdd)
    {
        this.addBefore(before.asItem().getDefaultInstance(), whatToAdd.asItem().getDefaultInstance());
    }
    public void addBefore(ItemStack before, ItemLike whatToAdd)
    {
        this.addBefore(before, whatToAdd.asItem().getDefaultInstance());
    }
    public void addBefore(ItemStack before, ItemStack whatToAdd)
    {
        BEFORE.add(Pair.of(before, whatToAdd));
    }

    public void addAfter(ItemLike after, ItemLike whatToAdd)
    {
        this.addAfter(after.asItem().getDefaultInstance(), whatToAdd.asItem().getDefaultInstance());
    }
    public void addAfter(ItemStack after, ItemLike whatToAdd)
    {
        this.addAfter(after, whatToAdd.asItem().getDefaultInstance());
    }
    public void addAfter(ItemStack after, ItemStack whatToAdd)
    {
        AFTER.add(Pair.of(after, whatToAdd));
    }

    public void build()
    {
        if (!BEFORE.isEmpty()) VectorLib.REGISTRY.addToCreativeTab(this, AddMode.BEFORE);
        if (!AFTER.isEmpty()) VectorLib.REGISTRY.addToCreativeTab(this, AddMode.AFTER);
    }

    public Pair<ResourceKey<CreativeModeTab>, List<Pair<ItemStack, ItemStack>>> unpack(VectorItemTab.AddMode mode)
    {
        return Pair.of(
                this.key,
                (mode == VectorItemTab.AddMode.BEFORE) ? BEFORE: AFTER
        );
    }

    public enum AddMode
    {
        BEFORE,
        AFTER
    }

    public enum VanillaTab
    {
        BUILDING(createKey("building_blocks")),
        COLORED(createKey("colored_blocks")),
        NATURAL(createKey("natural_blocks")),
        FUNCTIONAL(createKey("functional_blocks")),
        REDSTONE(createKey("redstone_blocks")),
        TOOLS(createKey("tools_and_utilities")),
        COMBAT(createKey("combat")),
        FOOD(createKey("food_and_drinks")),
        INGREDIENTS(createKey("ingredients")),
        SPAWN_EGGS(createKey("spawn_eggs")),
        OPERATOR(createKey("op_blocks"));

        private final ResourceKey<CreativeModeTab> key;

        VanillaTab(ResourceKey<CreativeModeTab> key) { this.key = key; }

        public ResourceKey<CreativeModeTab> key() { return this.key;}

        private static ResourceKey<CreativeModeTab> createKey(String name) { return ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.withDefaultNamespace(name)); }
    }
}
