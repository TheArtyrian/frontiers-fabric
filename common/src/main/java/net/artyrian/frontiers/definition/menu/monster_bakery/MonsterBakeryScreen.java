package net.artyrian.frontiers.definition.menu.monster_bakery;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import java.util.List;

public class MonsterBakeryScreen extends AbstractContainerScreen<MonsterBakeryScreenHandler>
{
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/gui/container/monster_bakery.png");

    private static final ResourceLocation BURN_TEX = Frontiers.id("container/monster_bakery/burn_progress");
    private static final ResourceLocation LIT_TEX = Frontiers.id("container/monster_bakery/lit_progress");
    private static final ResourceLocation CHANCE_TEX = Frontiers.id("container/monster_bakery/chance");
    private static final ResourceLocation BORDER_TEX = Frontiers.id("container/monster_bakery/border");

    private static final ResourceLocation LIT_TEX_DD = Frontiers.id("container/monster_bakery/lit_progress_dd");
    private static final ResourceLocation BORDER_TEX_DD = Frontiers.id("container/monster_bakery/border_dd");

    public MonsterBakeryScreen(MonsterBakeryScreenHandler handler, Inventory inventory, Component title)
    {
        super(handler, inventory, title);
    }

    @Override
    protected void init()
    {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta)
    {
        super.render(context, mouseX, mouseY, delta);
        this.tooltipProteus(context, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY)
    {
        int i = this.leftPos;
        int j = this.topPos;
        context.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);

        if (this.menu.isActive())
        {
            boolean dd_enabled = Frontiers.DUNGEONS_DELIGHT_LOADED;

            int texWH = 14;
            int texH = Mth.ceil(this.menu.getFuelProgress() * 13.0F) + 1;
            context.blitSprite(dd_enabled ? LIT_TEX_DD : LIT_TEX, texWH, texWH, 0, texWH - texH, i + 56, j + 36 + texWH - texH, texWH, texH);

            if (this.menu.hasItemCooking())
            {
                int texBarH = Mth.ceil(this.menu.getSpawnChance() * 27.0F) + 1;
                context.blitSprite(CHANCE_TEX, 28, 8, 0, 0, i + 110, j + 61, texBarH, 8);

                context.blitSprite(dd_enabled ? BORDER_TEX_DD : BORDER_TEX, 30, 30, 0, 0, i + 109, j + 28, 30, 30);
            }
        }

        int texWH2 = 24;
        int l = Mth.ceil(this.menu.getIncProgress() * (float)texWH2);
        context.blitSprite(BURN_TEX, texWH2, 16, 0, 0, i + 79, j + 34, l, 16);
    }

    /** A custom tooltip render set for the right */
    private void tooltipProteus(GuiGraphics context, int mouseX, int mouseY)
    {
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem())
        {
            if (this.hoveredSlot.getContainerSlot() == 2 && this.hoveredSlot.getItem().getItem() instanceof SpawnEggItem egg)
            {
                ItemStack stack = this.hoveredSlot.getItem();
                EntityType<?> type = egg.getType(stack);
                Component name = type.getDescription();
                if (name == null) name = Component.translatable("container.frontiers.monster_bakery.error").withStyle(ChatFormatting.RED);
                else name = name.copy().withStyle(ChatFormatting.GREEN);

                Component spawntxt = Component.translatable("container.frontiers.monster_bakery.output", String.valueOf(this.menu.getSpawnTime())+ "%").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC);

                // this.getTooltipFromItem(itemStack)
                ItemStack itemStack = this.hoveredSlot.getItem();
                context.renderTooltip(
                        this.font,
                        List.of(name, spawntxt),
                        itemStack.getTooltipImage(),
                        mouseX,
                        mouseY
                );
            }
            else
            {
                ItemStack itemStack = this.hoveredSlot.getItem();
                context.renderTooltip(this.font, this.getTooltipFromContainerItem(itemStack), itemStack.getTooltipImage(), mouseX, mouseY);
            }

        }

    }
}
