package net.artyrian.frontiers.definition.menu.curse;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.CurseAltarBlockEntity;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.content.FRMenus;
import net.artyrian.frontiers.reg.misc.FRCriteria;
import net.artyrian.frontiers.reg.misc.FRStats;
import net.artyrian.frontiers.reg.sound.FRSounds;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class CurseAltarMenu extends AbstractContainerMenu
{
    private static final ResourceLocation TABLET_SLOT_TEX = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "item/empty_slot_tablet");

    private static final int CURSE_COST = 15;
    private static final int BASE_ENC_COST = 6;

    private static final int CURSE_CHARGES = 4;

    private final ContainerLevelAccess context;
    private final ContainerData containerData;
    private final Container blockInventory;
    private final Container inventory = new SimpleContainer(1)
    {
        @Override
        public void setChanged()
        {
            super.setChanged();
            CurseAltarMenu.this.slotsChanged(this);
        }
    };

    private final List<List<CurseEnchantInst>> pages = new ArrayList<>();

    private int scrollPage = 0;
    public long seed = 0L;
    private final RandomSource source;

    private final Map<ItemLike, ItemLike> specialRecipes;

    public CurseAltarMenu(int syncId, Inventory playerInventory)
    {
        this(syncId, playerInventory, new SimpleContainer(1), new SimpleContainerData(1), ContainerLevelAccess.NULL);
    }

    public CurseAltarMenu(int syncId, Inventory playerInventory, Container container, ContainerData data, ContainerLevelAccess context)
    {
        super(FRMenus.CURSE_ALTAR.get(), syncId);
        this.context = context;
        this.containerData = data;
        this.blockInventory = container;

        this.specialRecipes = specialRecipes();

        this.source = playerInventory.player.getRandom();
        this.seed = source.nextInt();

        this.addDataSlots(this.containerData);

        // Slot 0 - Tool/Output
        this.addSlot(new Slot(this.inventory, 0, 59, 54)
        {
            @Override public int getMaxStackSize() {
                return 1;
            }
            @Override public boolean mayPlace(ItemStack stack)
            {
                return canBePurified(stack);
            }
        });
        // Slot 1 - Tablet
        this.addSlot(new Slot(this.blockInventory, 0, 59, 25)
        {
            @Override public boolean mayPlace(ItemStack stack) {
                return stack.is(FRItems.CURSED_TABLET.get());
            }
            @Override public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() { return Pair.of(InventoryMenu.BLOCK_ATLAS, CurseAltarMenu.TABLET_SLOT_TEX); }
        });

        // Player Inv
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Player Hotbar
        for (int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);
        this.context.execute((world, pos) -> this.clearContainer(player, this.inventory));
    }

    @Override
    public boolean clickMenuButton(Player player, int id)
    {
        // Up arrow
        if (id == 4)
        {
            if (this.scrollPage - 1 < 0) return false;

            this.scrollPage--;
            return true;
        }
        // Down arrow
        else if (id == 5)
        {
            if (this.scrollPage + 1 >= this.pages.size()) return false;

            this.scrollPage++;
            return true;
        }

        Util.logAndPauseIfInIde(player.getName() + " pressed invalid button id: " + id);
        return false;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasItem())
        {
            ItemStack itemStack2 = slot2.getItem();
            itemStack = itemStack2.copy();
            if (slot == 0)
            {
                if (!this.moveItemStackTo(itemStack2, 2, 38, true)) return ItemStack.EMPTY;
            }
            else if (slot == 1)
            {
                if (!this.moveItemStackTo(itemStack2, 2, 38, true)) return ItemStack.EMPTY;
            }
            else if (itemStack2.is(FRItems.CURSED_TABLET.get()))
            {
                if (!this.moveItemStackTo(itemStack2, 1, 2, true)) return ItemStack.EMPTY;
            }
            else
            {
                if (this.slots.get(0).hasItem() || !this.slots.get(0).mayPlace(itemStack2)) return ItemStack.EMPTY;

                ItemStack itemStack3 = itemStack2.copyWithCount(1);
                itemStack2.shrink(1);
                this.slots.get(0).setByPlayer(itemStack3);
            }

            if (itemStack2.isEmpty()) slot2.setByPlayer(ItemStack.EMPTY);
            else slot2.setChanged();

            if (itemStack2.getCount() == itemStack.getCount()) return ItemStack.EMPTY;

            slot2.onTake(player, itemStack2);
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) { return stillValid(this.context, player, FRBlocks.CURSE_ALTAR.get()); }

    @Override
    public void slotsChanged(Container container)
    {
        super.slotsChanged(container);
        if (container == this.inventory) this.inventoryUpdate();
    }

    //////////////////////////////////////////////////////////////////////////////

    private void inventoryUpdate()
    {
        this.pages.clear();
        this.scrollPage = 0;
        ItemStack itemStack = this.inventory.getItem(0);

        if (!itemStack.isEmpty() && canBePurified(itemStack))
        {
            if (itemStack.isEnchanted())
            {
                ItemEnchantments enchantments = itemStack.getEnchantments();
                if (!enchantments.isEmpty())
                {
                    List<CurseEnchantInst> puttable = new ArrayList<>();
                    int i = 0;

                    for (Object2IntMap.Entry<Holder<Enchantment>> setEntry : enchantments.entrySet())
                    {
                        int levelCost = getRemovalCost(setEntry.getKey(), setEntry.getIntValue());
                        int chargeCost = getChargeCost(setEntry.getKey(), setEntry.getIntValue());
                        puttable.add(new CurseEnchantInst(setEntry.getKey(), setEntry.getIntValue(), levelCost,  chargeCost,false));
                        i++;

                        if (i >= 4)
                        {
                            this.pages.add(puttable);
                            puttable = new ArrayList<>();
                            i = 0;
                        }
                    }

                    if (!puttable.isEmpty()) this.pages.add(puttable);
                }
            }
            else
            {
                this.pages.add(List.of(new CurseEnchantInst(Optional.empty(), Optional.empty(), 20, 4, true)));
            }
        }
        this.broadcastChanges();
    }

    public int getCharges() { return this.containerData.get(0); }
    public int getScrollPage() { return this.scrollPage; }
    public boolean onFirstPage() { return this.scrollPage <= 0; }
    public boolean onLastPage() { return this.scrollPage >= this.pages.size() - 1; }
    public List<CurseEnchantInst> getCurrentPage() { return this.pages.get(Math.clamp(this.scrollPage, 0, this.pages.size() - 1)); }
    public boolean arePagesEmpty() { return this.pages.isEmpty(); }

    public boolean playerCanEnchantCurrent(Player player, int slot)
    {
        if (this.arePagesEmpty()) return false;

        boolean charges = this.hasEnoughCharges(slot);
        int xp = player.experienceLevel;
        List<CurseEnchantInst> enchantInstances = this.getCurrentPage();

        if (slot < enchantInstances.size())
        {
            if (player.isCreative()) return charges;

            CurseEnchantInst inst = enchantInstances.get(slot);
            return (xp >= inst.cost() && charges);
        }
        return false;
    }

    public boolean canBePurified(ItemStack stack)
    {
        if (this.specialRecipes.containsKey(stack.getItem())) return true;
        return !stack.getEnchantments().equals(ItemEnchantments.EMPTY);
    }

    public boolean hasEnoughCharges(int slot)
    {
        List<CurseEnchantInst> enchantInstances = this.getCurrentPage();
        if (slot < enchantInstances.size())
        {
            CurseEnchantInst inst = enchantInstances.get(slot);
            int bonusCharges = (!this.blockInventory.getItem(0).isEmpty()) ? CurseAltarBlockEntity.BASE_CHARGES : 0;
            return (this.getCharges() + bonusCharges >= inst.chargeCost());
        }
        return false;
    }

    public void runPurifyProcess(Player player, CurseEnchantInst instance)
    {
        ItemStack itemStack = this.inventory.getItem(0);
        int removingLevels = -instance.cost();

        this.context.execute((world, pos) -> {
            if (!player.isCreative()) player.giveExperienceLevels(removingLevels);
            player.awardStat(FRStats.getStat(FRStats.REMOVE_CURSE.get()));
            if (player instanceof ServerPlayer serverP) FRCriteria.USED_CURSE_ALTAR.get().trigger(serverP, itemStack);

            this.purifyItem(itemStack, instance);
            this.inventory.setChanged();
            this.slotsChanged(this.inventory);

            int setTo = this.containerData.get(0) - instance.chargeCost();
            if (setTo <= 0 && this.blockInventory.getItem(0).is(FRItems.CURSED_TABLET.get()))
            {
                this.blockInventory.getItem(0).shrink(1);
                setTo += 20;
            }

            this.containerData.set(0, setTo);
            if (setTo <= 0)
            {
                BlockState state = world.getBlockState(pos);
                world.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL_IMMEDIATE);
            }

            SoundEvent toPlay = (this.getCharges() > 0) ? FRSounds.CURSE_ALTAR_USE.get() : FRSounds.CURSE_ALTAR_SHATTER.get();
            world.playSound(null, pos, toPlay, SoundSource.BLOCKS, 1.2F, world.random.nextFloat() * 0.1F + 0.9F);
        });
    }

    private void purifyItem(ItemStack stack, CurseEnchantInst instance)
    {
        if (instance.isUnconventional() && this.specialRecipes.containsKey(stack.getItem()))
        {
            this.inventory.setItem(0, new ItemStack(this.specialRecipes.get(stack.getItem()), stack.getCount()));
        }
        else
        {
            ItemEnchantments comp = stack.getEnchantments();
            Optional<Holder<Enchantment>> opt = instance.enchantment();
            if (!comp.isEmpty() && opt.isPresent())
            {
                Holder<Enchantment> target = opt.get();
                ItemStack copy = stack.copy();
                copy.set(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);

                Set<Holder<Enchantment>> enc_list = comp.keySet();
                for (Holder<Enchantment> enc : enc_list)
                {
                    if (!enc.equals(target)) copy.enchant(enc, stack.getEnchantments().getLevel(enc));
                }

                this.inventory.setItem(0, copy);
            }
        }
    }

    public void postPurify()
    {
        this.seed = this.source.nextInt();
        this.scrollPage = 0;
    }

    private Map<ItemLike, ItemLike> specialRecipes()
    {
        if (this.specialRecipes != null) return this.specialRecipes;
        else return Map.of(
                Items.END_CRYSTAL, FRItems.PURIFIED_END_CRYSTAL.get()
        );
    }

    //////////////////////////////////////////////////////////////////////////////

    private static int getRemovalCost(Holder<Enchantment> enchantment, int level)
    {
        if (enchantment.is(EnchantmentTags.CURSE)) return CURSE_COST;
        else return BASE_ENC_COST * level;
    }

    private static int getChargeCost(Holder<Enchantment> enchantment, int level)
    {
        if (enchantment.is(EnchantmentTags.CURSE)) return CURSE_CHARGES;
        else return Math.clamp(level, 1, 4);
    }
}