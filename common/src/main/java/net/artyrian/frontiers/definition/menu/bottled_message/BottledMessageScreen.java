package net.artyrian.frontiers.definition.menu.bottled_message;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.networking.payload.BottleMessageWritePayload;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.vertisoft.vectorlib.VectorLib;
import org.lwjgl.glfw.GLFW;

public class BottledMessageScreen extends Screen
{
    private static final ResourceLocation PAPER_TEX = Frontiers.id("container/bottled_message/paper");
    private static final int[] PAPER_XYS = new int[]{200, 36};

    private Button doneButton;
    private Button signButton;

    private final Player player;
    private final ItemStack itemStack;
    private final InteractionHand hand;
    private final boolean is_signed;
    private String message;

    public BottledMessageScreen(Player player, ItemStack itemStack, InteractionHand hand, String message)
    {
        super((itemStack.is(ModItem.BOTTLED_MESSAGE.get())) ? GameNarrator.NO_TITLE : Component.translatable("container.frontiers.bottled_message.title"));
        this.player = player;
        this.itemStack = itemStack;
        this.hand = hand;
        this.message = message;

        this.is_signed = itemStack.is(ModItem.BOTTLED_MESSAGE.get());
    }

    private void finishBottle()
    {
        int slot = (this.hand == InteractionHand.MAIN_HAND) ? this.player.getInventory().selected : Inventory.SLOT_OFFHAND;
        VectorLib.NETWORK.sendToServer(new BottleMessageWritePayload(slot, this.message));

        if (this.minecraft != null) this.minecraft.setScreen(null);
    }

    @Override
    protected void init()
    {
        this.doneButton = this
                .addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button ->
                {
                    if (this.minecraft != null) this.minecraft.setScreen(null);
                }
        ).bounds((this.width / 2) - 40, (this.height / 2) + 32, 80, 20).build());
        this.signButton = this
                .addRenderableWidget(Button.builder(Component.translatable("container.frontiers.bottled_message.write"), button ->
                        {
                            this.finishBottle();
                        }
                ).bounds((this.width / 2) - 40, (this.height / 2) + 32, 80, 20).build());

        this.doneButton.visible = this.is_signed;
        this.signButton.visible = !this.is_signed;
        this.signButton.active = !StringUtil.isBlank(this.message);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if (super.keyPressed(keyCode, scanCode, modifiers))
        {
            return true;
        }
        else if (!this.is_signed)
        {
            return switch (keyCode)
            {
                case GLFW.GLFW_KEY_BACKSPACE ->
                {
                    if (!message.isEmpty())
                    {
                        int par = this.message.length();
                        this.message = new StringBuilder(this.message).deleteCharAt(par - 1).toString();

                        this.signButton.active = !StringUtil.isBlank(this.message);
                    }
                    yield true;
                }

                case GLFW.GLFW_KEY_ENTER ->
                {
                    this.finishBottle();
                    yield true;
                }

                default -> false;
            };
        }
        else return false;
    }

    @Override
    public boolean charTyped(char chr, int modifiers)
    {
        if (super.charTyped(chr, modifiers))
        {
            return true;
        }
        else if (StringUtil.isAllowedChatCharacter(chr) && this.message.length() < 32 && !this.is_signed)
        {
            this.message = this.message.concat(Character.toString(chr));
            this.signButton.active = !StringUtil.isBlank(this.message);
            return true;
        }
        else return false;
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta)
    {
        super.render(context, mouseX, mouseY, delta);
        this.setFocused(null);

        Component displayed = Component.literal(message);

        int x = ((this.width) / 2) - (font.width(displayed) / 2);
        int y = ((this.height) / 2) - 4;

        if (!this.is_signed)
        {
            context.drawCenteredString(this.font, this.title, this.width / 2, (this.height / 2) - (PAPER_XYS[1] / 2) - 28, 16777215);
        }

        context.drawString(this.font, displayed, x, y, 0, false);
    }

    @Override
    public void renderBackground(GuiGraphics context, int mouseX, int mouseY, float delta)
    {
        this.renderTransparentBackground(context);

        int x = ((this.width) / 2) - (PAPER_XYS[0] / 2);
        int y = ((this.height) / 2) - (PAPER_XYS[1] / 2);

        context.blitSprite(PAPER_TEX, x, y, PAPER_XYS[0], PAPER_XYS[1]);
    }
}
