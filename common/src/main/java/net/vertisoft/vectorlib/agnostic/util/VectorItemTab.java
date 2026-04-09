package net.vertisoft.vectorlib.agnostic.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.vertisoft.vectorlib.VectorLib;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/** A cross-platform class used to register items to existing creative tabs. */
public class VectorItemTab
{
    private final ResourceKey<CreativeModeTab> key;
    private final boolean enabled;
    private final boolean newType;
    private final List<Pair<ItemStack, ItemStack>> BEFORE = new ArrayList<>();
    private final List<Pair<ItemStack, ItemStack>> AFTER = new ArrayList<>();
    private final List<ItemStack> ORDERED = new ArrayList<>();
    @Nullable private ItemStack icon;
    @Nullable private Component title;

    public static VectorItemTab ofNew(ResourceKey<CreativeModeTab> key, boolean enabled, @Nullable List<VectorItemTab> iterator) { return new VectorItemTab(key, enabled, true, iterator); }
    public static VectorItemTab ofNew(ResourceKey<CreativeModeTab> key, boolean enabled) { return new VectorItemTab(key, enabled, true, null); }

    public static VectorItemTab ofExisting(VanillaTab tab) { return new VectorItemTab(tab.key(), true, false, null); }
    public static VectorItemTab ofExisting(VanillaTab tab, List<VectorItemTab> iterator) { return new VectorItemTab(tab.key(), true, false, iterator); }
    public static VectorItemTab ofExisting(ResourceKey<CreativeModeTab> key, boolean enabled, @Nullable List<VectorItemTab> iterator) { return new VectorItemTab(key, enabled, false, iterator); }

    private VectorItemTab(ResourceKey<CreativeModeTab> key, boolean enabled, boolean newType, @Nullable List<VectorItemTab> iterator)
    {
        this.key = key;
        this.enabled = enabled;
        this.newType = newType;
        if (iterator != null)
        {
            iterator.add(this);
        }
    }

    public void addBefore(ItemLike before, ItemLike whatToAdd) { this.addBefore(before.asItem().getDefaultInstance(), whatToAdd.asItem().getDefaultInstance(), null); }
    public void addBefore(ItemStack before, ItemLike whatToAdd) { this.addBefore(before, whatToAdd.asItem().getDefaultInstance(), null); }
    public void addBefore(ItemStack before, ItemStack whatToAdd) { this.addBefore(before, whatToAdd, null); }
    public void addBefore(ItemLike before, ItemLike whatToAdd, VectorItemTab consolidator) { this.addBefore(before.asItem().getDefaultInstance(), whatToAdd.asItem().getDefaultInstance(), consolidator); }
    public void addBefore(ItemStack before, ItemLike whatToAdd, VectorItemTab consolidator) { this.addBefore(before, whatToAdd.asItem().getDefaultInstance(), consolidator); }
    public void addBefore(ItemStack before, ItemStack whatToAdd, @Nullable VectorItemTab consolidator)
    {
        this.BEFORE.add(Pair.of(before, whatToAdd));
        if (consolidator != null && !consolidator.equals(this)) consolidator.addOrdered(whatToAdd);
    }

    public void addAfter(ItemLike after, ItemLike whatToAdd) { this.addAfter(after.asItem().getDefaultInstance(), whatToAdd.asItem().getDefaultInstance(), null); }
    public void addAfter(ItemStack after, ItemLike whatToAdd) { this.addAfter(after, whatToAdd.asItem().getDefaultInstance(), null); }
    public void addAfter(ItemStack after, ItemStack whatToAdd) { this.addAfter(after, whatToAdd, null); }
    public void addAfter(ItemLike after, ItemLike whatToAdd, VectorItemTab consolidator) { this.addAfter(after.asItem().getDefaultInstance(), whatToAdd.asItem().getDefaultInstance(), consolidator); }
    public void addAfter(ItemStack after, ItemLike whatToAdd, VectorItemTab consolidator) { this.addAfter(after, whatToAdd.asItem().getDefaultInstance(), consolidator); }
    public void addAfter(ItemStack after, ItemStack whatToAdd, @Nullable VectorItemTab consolidator)
    {
        this.AFTER.add(Pair.of(after, whatToAdd));
        if (consolidator != null && !consolidator.equals(this)) consolidator.addOrdered(whatToAdd);
    }

    public void addOrdered(ItemLike whatToAdd) { this.addOrdered(whatToAdd.asItem().getDefaultInstance(), null); }
    public void addOrdered(ItemLike whatToAdd, VectorItemTab consolidator) { this.addOrdered(whatToAdd.asItem().getDefaultInstance(), consolidator); }
    public void addOrdered(ItemStack whatToAdd) { this.addOrdered(whatToAdd, null); }
    public void addOrdered(ItemStack whatToAdd, @Nullable VectorItemTab consolidator)
    {
        if (this.testOrdered(whatToAdd))
        {
            if (consolidator != null && !consolidator.equals(this)) consolidator.addOrdered(whatToAdd);
        }
    }

    private boolean testOrdered(ItemStack whatToAdd)
    {
        for (ItemStack stack : this.ORDERED)
        {
            if (stack.getItem().equals(whatToAdd.getItem())) return false;
        }
        this.ORDERED.add(whatToAdd);
        return true;
    }

    public boolean isNew() { return this.newType; }
    public String keyNamespace() { return this.key.location().getNamespace(); }
    public String keyPath() { return this.key.location().getPath(); }
    public ItemStack getIconOrDefault() { return (this.icon != null) ? this.icon : Blocks.GRASS_BLOCK.asItem().getDefaultInstance(); }
    public Component getTitleOrDefault() { return (this.title != null) ? this.title : Component.translatable("itemTab.vectorlib.unknown_tab").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD); }
    public void setIcon(ItemLike item) { this.icon = item.asItem().getDefaultInstance(); }
    public void setTitle(Component comp) { this.title = comp; }

    public int validateAgainstRegistry()
    {
        if (!this.isNew()) return 1;
        else if (BuiltInRegistries.CREATIVE_MODE_TAB.containsKey(this.key)) return 2;

        return 0;
    }

    public void build()
    {
        if (this.enabled)
        {
            if (this.newType)
            {
                if (!this.ORDERED.isEmpty()) VectorLib.REGISTRY.newCreativeTab(this);
            }
            else
            {
                if (!this.BEFORE.isEmpty()) VectorLib.REGISTRY.addToCreativeTab(this, AddMode.BEFORE);
                if (!this.AFTER.isEmpty()) VectorLib.REGISTRY.addToCreativeTab(this, AddMode.AFTER);
            }
        }
        else VectorLib.LOGGER.warn("VectorItemTab with ResourceKey {} is disabled, skipping build phase", this.key);
    }

    public Pair<ResourceKey<CreativeModeTab>, List<Pair<ItemStack, ItemStack>>> unpackPairs(VectorItemTab.AddMode mode)
    {
        return Pair.of(
                this.key,
                (mode == VectorItemTab.AddMode.BEFORE) ? this.BEFORE : this.AFTER
        );
    }

    public void pushNew(CreativeModeTab.ItemDisplayParameters param, CreativeModeTab.Output io)
    {
        if (!this.isNew() || this.ORDERED.isEmpty()) throw new IllegalArgumentException("Either the VectorTab isn't marked as new, or its ordered list is empty");

        for (ItemStack stack : this.ORDERED) io.accept(stack);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof VectorItemTab tab)) return false;
        return this.key.equals(tab.key) && this.BEFORE.equals(tab.BEFORE) && this.AFTER.equals(tab.AFTER) && this.ORDERED.equals(tab.ORDERED);
    }

    public enum AddMode
    {
        BEFORE,
        AFTER,
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
