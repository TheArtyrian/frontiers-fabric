package net.artyrian.frontiers.definition.menu.curse;

import com.mojang.datafixers.util.Pair;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.advancement.criterion.CurseAltarCriterion;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModScreenHandlers;
import net.artyrian.frontiers.reg.content.ModSounds;
import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.artyrian.frontiers.reg.misc.ModStats;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import java.util.Set;

public class CurseAltarScreenHandler extends AbstractContainerMenu
{
    static final ResourceLocation TABLET_SLOT_TEX = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "item/empty_slot_tablet");

    private final ContainerLevelAccess context;
    private final Container inventory = new SimpleContainer(2)
    {
        @Override
        public void setChanged()
        {
            super.setChanged();
            CurseAltarScreenHandler.this.slotsChanged(this);
        }
    };

    public CurseAltarScreenHandler(int syncId, Inventory playerInventory)
    {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }

    public CurseAltarScreenHandler(int syncId, Inventory playerInventory, ContainerLevelAccess context)
    {
        super(ModScreenHandlers.CURSE_ALTAR.get(), syncId);
        this.context = context;

        // Slot 0 - Tool/Output
        this.addSlot(new Slot(this.inventory, 0, 88, 20)
        {
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return hasCurses(stack);
            }
        });
        // Slot 1 - Tablet
        this.addSlot(new Slot(this.inventory, 1, 132, 20)
        {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItem.CURSED_TABLET.get());
            }

            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon()
            {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, CurseAltarScreenHandler.TABLET_SLOT_TEX);
            }
        });

        // Player Inv
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // Player Hotbar
        for (int i = 0; i < 9; i++) {
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
        if (id >= 0)
        {
            ItemStack itemStack = this.inventory.getItem(0);
            ItemStack itemStack2 = this.inventory.getItem(1);

            if ((itemStack.isEmpty() || itemStack2.isEmpty()))
            {
                return false;
            }
            else
            {
                this.context.execute((world, pos) -> {

                    if (itemStack.is(Items.END_CRYSTAL)) this.inventory.setItem(0, new ItemStack(ModItem.PURIFIED_END_CRYSTAL.get(), itemStack.getCount()));
                    else this.inventory.setItem(0, this.removeCurses(itemStack));

                    itemStack2.consume(1, player);
                    if (itemStack2.isEmpty())
                    {
                        this.inventory.setItem(1, ItemStack.EMPTY);
                    }

                    if (!player.isCreative()) player.giveExperienceLevels(-CurseAltarScreen.REQUIRED_XP);
                    player.awardStat(ModStats.getStat(ModStats.REMOVE_CURSE.get()));
                    if (player instanceof ServerPlayer)
                    {
                        ((CurseAltarCriterion)ModCriteria.USED_CURSE_ALTAR.get()).trigger((ServerPlayer)player, itemStack);
                    }

                    this.inventory.setChanged();
                    this.slotsChanged(this.inventory);
                    world.playSound(null, pos, ModSounds.CURSE_ALTAR_USE.get(), SoundSource.BLOCKS, 1.2F, world.random.nextFloat() * 0.1F + 0.9F);
                });
                return true;
            }
        }
        else
        {
            Util.logAndPauseIfInIde(player.getName() + " pressed invalid button id: " + id);
            return false;
        }
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
                if (!this.moveItemStackTo(itemStack2, 2, 38, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (slot == 1)
            {
                if (!this.moveItemStackTo(itemStack2, 2, 38, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (itemStack2.is(ModItem.CURSED_TABLET.get()))
            {
                if (!this.moveItemStackTo(itemStack2, 1, 2, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (this.slots.get(0).hasItem() || !this.slots.get(0).mayPlace(itemStack2))
                {
                    return ItemStack.EMPTY;
                }

                ItemStack itemStack3 = itemStack2.copyWithCount(1);
                itemStack2.shrink(1);
                this.slots.get(0).setByPlayer(itemStack3);
            }

            if (itemStack2.isEmpty())
            {
                slot2.setByPlayer(ItemStack.EMPTY);
            }
            else
            {
                slot2.setChanged();
            }

            if (itemStack2.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot2.onTake(player, itemStack2);
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(this.context, player, ModBlocks.CURSE_ALTAR.get());
    }

    public boolean hasCurses(ItemStack stack)
    {
        if (stack.is(Items.END_CRYSTAL)) return true;

        ItemEnchantments comp = stack.getEnchantments();
        if (comp != null)
        {
            Set<Holder<Enchantment>> enc_list = comp.keySet();
            for (Holder<Enchantment> enc : enc_list)
            {
                if (enc.is(EnchantmentTags.CURSE))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public ItemStack removeCurses(ItemStack stack)
    {
        ItemEnchantments comp = stack.getEnchantments();
        if (comp != null)
        {
            ItemStack copy = stack.copy();
            copy.set(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);

            Set<Holder<Enchantment>> enc_list = comp.keySet();
            for (Holder<Enchantment> enc : enc_list)
            {
                if (!enc.is(EnchantmentTags.CURSE))
                {
                    copy.enchant(enc, stack.getEnchantments().getLevel(enc));
                }
            }

            return copy;

        }
        return stack;
    }
}
