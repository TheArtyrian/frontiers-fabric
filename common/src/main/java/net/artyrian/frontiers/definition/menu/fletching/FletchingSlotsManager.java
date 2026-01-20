package net.artyrian.frontiers.definition.menu.fletching;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** The slot manager for the Fletching Table UI. */
public class FletchingSlotsManager
{
    private final List<FletchingSlot> inputSlots;
    private final FletchingSlot resultSlot;

    FletchingSlotsManager(List<FletchingSlot> inputSlots, FletchingSlot resultSlot)
    {
        if (!inputSlots.isEmpty() && !resultSlot.equals(FletchingSlot.DEFAULT))
        {
            this.inputSlots = inputSlots;
            this.resultSlot = resultSlot;
        }
        else
        {
            throw new IllegalArgumentException("[FRONTIERS] Input and Result slots must be defined for FlectingSlotsManager!");
        }
    }

    public static Builder create() {
        return new Builder();
    }

    public boolean hasSlotIndex(int index) {
        return this.inputSlots.size() >= index;
    }

    public FletchingSlot getInputSlot(int index)
    {
        return this.inputSlots.get(index);
    }
    public FletchingSlot getResultSlot()
    {
        return this.resultSlot;
    }

    public List<FletchingSlot> getInputSlots() {
        return this.inputSlots;
    }
    public int getInputSlotCount() {
        return this.inputSlots.size();
    }
    public int getResultSlotIndex() { return this.getInputSlotCount(); }

    public List<Integer> getInputSlotIndices()
    {
        return this.inputSlots.stream().map(FletchingSlot::slotId).collect(Collectors.toList());
    }

    public static class Builder
    {
        private final List<FletchingSlot> inputSlots = new ArrayList<>();
        private FletchingSlot resultSlot = FletchingSlot.DEFAULT;

        public Builder input(int slotId, int x, int y, Predicate<ItemStack> mayPlace, ResourceLocation slotTex)
        {
            this.inputSlots.add(new FletchingSlot(slotId, x, y, mayPlace, slotTex));
            return this;
        }

        public Builder output(int slotId, int x, int y)
        {
            this.resultSlot = new FletchingSlot(slotId, x, y, stack -> false, null);
            return this;
        }

        public FletchingSlotsManager build() {
            return new FletchingSlotsManager(this.inputSlots, this.resultSlot);
        }
    }

    public record FletchingSlot(int slotId, int x, int y, Predicate<ItemStack> mayPlace, @Nullable ResourceLocation slotTex)
    {
        static final FletchingSlot DEFAULT = new FletchingSlot(0, 0, 0,
                stack -> true,
                null
        );
    }
}
