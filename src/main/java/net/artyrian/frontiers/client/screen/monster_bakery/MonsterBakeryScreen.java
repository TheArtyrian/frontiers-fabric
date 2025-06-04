package net.artyrian.frontiers.client.screen.monster_bakery;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.client.screen.fletching.FletchingTableScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class MonsterBakeryScreen extends HandledScreen<MonsterBakeryScreenHandler>
{
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/gui/container/monster_bakery.png");

    private static final Identifier BURN_TEX = Identifier.of(Frontiers.MOD_ID, "container/monster_bakery/burn_progress");
    private static final Identifier LIT_TEX = Identifier.of(Frontiers.MOD_ID, "container/monster_bakery/lit_progress");
    private static final Identifier CHANCE_TEX = Identifier.of(Frontiers.MOD_ID, "container/monster_bakery/chance");
    private static final Identifier BORDER_TEX = Identifier.of(Frontiers.MOD_ID, "container/monster_bakery/border");

    public MonsterBakeryScreen(MonsterBakeryScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);
    }

    @Override
    protected void init()
    {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        super.render(context, mouseX, mouseY, delta);
        this.tooltipProteus(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY)
    {
        int i = this.x;
        int j = this.y;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);

        if (this.handler.isActive())
        {
            int texWH = 14;
            int texH = MathHelper.ceil(this.handler.getFuelProgress() * 13.0F) + 1;
            context.drawGuiTexture(LIT_TEX, texWH, texWH, 0, texWH - texH, i + 56, j + 36 + texWH - texH, texWH, texH);

            if (this.handler.hasItemCooking())
            {
                int texBarH = MathHelper.ceil(this.handler.getSpawnChance() * 27.0F) + 1;
                context.drawGuiTexture(CHANCE_TEX, 28, 8, 0, 0, i + 110, j + 61, texBarH, 8);

                context.drawGuiTexture(BORDER_TEX, 30, 30, 0, 0, i + 109, j + 28, 30, 30);
            }
        }

        int texWH2 = 24;
        int l = MathHelper.ceil(this.handler.getIncProgress() * (float)texWH2);
        context.drawGuiTexture(BURN_TEX, texWH2, 16, 0, 0, i + 79, j + 34, l, 16);
    }

    /** A custom tooltip render set for the right */
    private void tooltipProteus(DrawContext context, int mouseX, int mouseY)
    {
        if (this.handler.getCursorStack().isEmpty() && this.focusedSlot != null && this.focusedSlot.hasStack())
        {
            if (this.focusedSlot.getIndex() == 2 && this.focusedSlot.getStack().getItem() instanceof SpawnEggItem egg)
            {
                ItemStack stack = this.focusedSlot.getStack();
                EntityType<?> type = egg.getEntityType(stack);
                Text name = type.getName();
                if (name == null) name = Text.translatable("container.frontiers.monster_bakery.error").formatted(Formatting.RED);
                else name = name.copy().formatted(Formatting.GREEN);

                Text spawntxt = Text.translatable("container.frontiers.monster_bakery.output", String.valueOf(this.handler.getSpawnTime())+ "%").formatted(Formatting.GRAY).formatted(Formatting.ITALIC);

                // this.getTooltipFromItem(itemStack)
                ItemStack itemStack = this.focusedSlot.getStack();
                context.drawTooltip(
                        this.textRenderer,
                        List.of(name, spawntxt),
                        itemStack.getTooltipData(),
                        mouseX,
                        mouseY
                );
            }
            else
            {
                ItemStack itemStack = this.focusedSlot.getStack();
                context.drawTooltip(this.textRenderer, this.getTooltipFromItem(itemStack), itemStack.getTooltipData(), mouseX, mouseY);
            }

        }

    }
}
