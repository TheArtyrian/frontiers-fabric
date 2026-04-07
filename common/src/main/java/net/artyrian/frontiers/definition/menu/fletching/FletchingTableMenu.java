package net.artyrian.frontiers.definition.menu.fletching;

import com.mojang.datafixers.util.Pair;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipe;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipeInput;
import net.artyrian.frontiers.reg.content.FRMenus;
import net.artyrian.frontiers.reg.sound.FRSounds;
import net.artyrian.frontiers.reg.property.FRRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FletchingTableMenu extends AbstractContainerMenu
{
    static final ResourceLocation HEAD_SLOT_TEX = Frontiers.id("item/empty_slot_arrowhead");
    static final ResourceLocation STICK_SLOT_TEX = Frontiers.id("item/empty_slot_stick");
    static final ResourceLocation FEATHER_SLOT_TEX = Frontiers.id("item/empty_slot_feather");

    private final ContainerLevelAccess context;
    protected final Container input;
    protected final ResultContainer output = new ResultContainer();
    private final List<Integer> inputSlotIndices;
    private final int resultSlotIndex;

    private ResourceLocation arrow_texture = null;

    private final Level world;
    long lastTakeTime;

    @Nullable
    private RecipeHolder<ArrowFletchingRecipe> currentRecipe;
    private final List<RecipeHolder<ArrowFletchingRecipe>> recipes;

    public FletchingTableMenu(int syncId, Inventory playerInventory)
    {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }

    public FletchingTableMenu(int syncId, Inventory playerInventory, ContainerLevelAccess context)
    {
        super(FRMenus.FLETCHING_TABLE.get(), syncId);
        this.context = context;
        this.world = playerInventory.player.level();
        this.recipes = this.world.getRecipeManager().getAllRecipesFor(FRRecipes.ARROW_FLETCHING.get());

        // Setup slots
        FletchingSlotsManager slotMan = this.getSlotManager();
        this.input = this.createInputInventory(slotMan.getInputSlotCount());
        this.inputSlotIndices = slotMan.getInputSlotIndices();
        this.resultSlotIndex = slotMan.getResultSlotIndex();
        this.addInputSlots(slotMan);
        this.addResultSlot(slotMan);

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

    protected FletchingSlotsManager getSlotManager()
    {
        return FletchingSlotsManager.create()
                .input(0, 80, 16, stack -> true, HEAD_SLOT_TEX)         // Arrowhead
                .input(1, 80, 36, stack -> true, STICK_SLOT_TEX)        // Stick
                .input(2, 80, 56, stack -> true, FEATHER_SLOT_TEX)      // Feather
                .output(3, 138, 36)
                .build();
    }

    private void addInputSlots(FletchingSlotsManager slotMan)
    {
        for (final FletchingSlotsManager.FletchingSlot fletchingSlot : slotMan.getInputSlots())
        {
            if (fletchingSlot.slotTex() == null)
            {
                this.addSlot(new Slot(this.input, fletchingSlot.slotId(), fletchingSlot.x(), fletchingSlot.y())
                {
                    @Override
                    public boolean mayPlace(ItemStack stack)
                    {
                        return fletchingSlot.mayPlace().test(stack);
                    }
                });
            }
            else
            {
                this.addSlot(new Slot(this.input, fletchingSlot.slotId(), fletchingSlot.x(), fletchingSlot.y())
                {
                    @Override
                    public boolean mayPlace(ItemStack stack)
                    {
                        return fletchingSlot.mayPlace().test(stack);
                    }

                    @Override
                    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon()
                    {
                        return Pair.of(InventoryMenu.BLOCK_ATLAS, fletchingSlot.slotTex());
                    }
                });
            }
        }
    }

    private void addResultSlot(FletchingSlotsManager slotMan)
    {
        this.addSlot(
                new Slot(this.output, slotMan.getResultSlot().slotId(), slotMan.getResultSlot().x(), slotMan.getResultSlot().y())
                {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return false;
                    }

                    @Override
                    public boolean mayPickup(Player playerEntity)
                    {
                        return FletchingTableMenu.this.canTakeOutput(playerEntity, this.hasItem());
                    }

                    @Override
                    public void onTake(Player player, ItemStack stack) {
                        FletchingTableMenu.this.onTakeOutput(player, stack);
                    }
                }
        );
    }

    private SimpleContainer createInputInventory(int size)
    {
        return new SimpleContainer(size)
        {
            @Override
            public void setChanged()
            {
                super.setChanged();
                FletchingTableMenu.this.slotsChanged(this);
            }
        };
    }

    private ArrowFletchingRecipeInput createRecipeInput()
    {
        return new ArrowFletchingRecipeInput(this.input.getItem(0), this.input.getItem(1), this.input.getItem(2));
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);
        this.context.execute((world, pos) -> this.clearContainer(player, this.input));
    }

    @Override
    public void slotsChanged(Container inventory)
    {
        super.slotsChanged(inventory);
        if (inventory == this.input)
        {
            this.updateResult();
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
            int i = this.getPlayerInventoryStartIndex();
            int j = this.getPlayerHotbarEndIndex();

            if (slot == this.getResultSlotIndex())
            {
                if (!this.moveItemStackTo(itemStack2, i, j, true))
                {
                    return ItemStack.EMPTY;
                }

                slot2.onQuickCraft(itemStack2, itemStack);
            }
            else if (this.inputSlotIndices.contains(slot))
            {
                if (!this.moveItemStackTo(itemStack2, i, j, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (slot >= this.getPlayerInventoryStartIndex() && slot < this.getPlayerHotbarEndIndex())
            {
                int k = this.getSlotFor(itemStack);
                if (!this.moveItemStackTo(itemStack2, k, this.getResultSlotIndex(), false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (slot >= this.getPlayerInventoryStartIndex() && slot < this.getPlayerInventoryEndIndex())
            {
                if (!this.moveItemStackTo(itemStack2, this.getPlayerHotbarStartIndex(), this.getPlayerHotbarEndIndex(), false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (
                    slot >= this.getPlayerHotbarStartIndex()
                    && slot < this.getPlayerHotbarEndIndex()
                    && !this.moveItemStackTo(itemStack2, this.getPlayerInventoryStartIndex(), this.getPlayerInventoryEndIndex(), false)
            )
            {
                return ItemStack.EMPTY;
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
        return stillValid(this.context, player, Blocks.FLETCHING_TABLE);
    }


    public void updateResult()
    {
        ArrowFletchingRecipeInput recipeInput = this.createRecipeInput();
        List<RecipeHolder<ArrowFletchingRecipe>> list = this.world.getRecipeManager().getRecipesFor(FRRecipes.ARROW_FLETCHING.get(), recipeInput, this.world);
        if (list.isEmpty())
        {
            this.output.setItem(0, ItemStack.EMPTY);
            this.arrow_texture = null;
        }
        else
        {
            RecipeHolder<ArrowFletchingRecipe> recipeEntry = list.get(0);
            ItemStack itemStack = recipeEntry.value().assemble(recipeInput, this.world.registryAccess());
            if (itemStack.isItemEnabled(this.world.enabledFeatures()))
            {
                this.currentRecipe = recipeEntry;
                this.output.setRecipeUsed(recipeEntry);
                this.output.setItem(0, itemStack);
                if (recipeEntry.value().getArrowTex() != null)
                {
                    this.arrow_texture = recipeEntry.value().getArrowTex();
                }
            }
        }
    }

    protected boolean canTakeOutput(Player player, boolean present)
    {
        return this.currentRecipe != null && this.currentRecipe.value().matches(this.createRecipeInput(), this.world);
    }

    public void onTakeOutput(Player player, ItemStack stack)
    {
        stack.onCraftedBy(player.level(), player, stack.getCount());
        this.output.awardUsedRecipes(player, this.getInputStacks());
        this.decrementStack(0);
        this.decrementStack(1);
        this.decrementStack(2);
        this.context.execute((world, pos) -> {
            long l = world.getGameTime();
            if (FletchingTableMenu.this.lastTakeTime != l) {
                world.playSound(
                        null,
                        pos,
                        FRSounds.FLETCHING_TABLE_USE.get(),
                        SoundSource.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F
                );
                FletchingTableMenu.this.lastTakeTime = l;
            }
        });
    }

    private void decrementStack(int slot)
    {
        ItemStack itemStack = this.input.getItem(slot);
        if (!itemStack.isEmpty())
        {
            itemStack.shrink(1);
            this.input.setItem(slot, itemStack);
        }
    }

    // A bunch of methods for getting slot indices.
    public int getSlotFor(ItemStack stack) { return this.input.isEmpty() ? 0 : this.inputSlotIndices.get(0); }
    public int getResultSlotIndex() { return this.resultSlotIndex; }
    public ResourceLocation getArrowTex() { return arrow_texture; }
    private List<ItemStack> getInputStacks() { return List.of(this.input.getItem(0), this.input.getItem(1), this.input.getItem(2)); }
    private int getPlayerInventoryStartIndex() { return this.getResultSlotIndex() + 1; }
    private int getPlayerInventoryEndIndex() { return this.getPlayerInventoryStartIndex() + 27; }
    private int getPlayerHotbarStartIndex() { return this.getPlayerInventoryEndIndex(); }
    private int getPlayerHotbarEndIndex() { return this.getPlayerHotbarStartIndex() + 9; }
}
