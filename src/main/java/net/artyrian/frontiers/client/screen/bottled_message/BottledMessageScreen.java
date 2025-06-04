package net.artyrian.frontiers.client.screen.bottled_message;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.payloads.BottleMessageWritePayload;
import net.artyrian.frontiers.data.payloads.WitherHardmodePayload;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringHelper;
import org.lwjgl.glfw.GLFW;

public class BottledMessageScreen extends Screen
{
    private static final Identifier PAPER_TEX = Identifier.of(Frontiers.MOD_ID, "container/bottled_message/paper");
    private static final int[] PAPER_XYS = new int[]{200, 36};

    private ButtonWidget doneButton;
    private ButtonWidget signButton;

    private final PlayerEntity player;
    private final ItemStack itemStack;
    private final Hand hand;
    private final boolean is_signed;
    private String message;

    public BottledMessageScreen(PlayerEntity player, ItemStack itemStack, Hand hand, String message)
    {
        super((itemStack.isOf(ModItem.BOTTLED_MESSAGE)) ? NarratorManager.EMPTY : Text.translatable("container.frontiers.bottled_message.title"));
        this.player = player;
        this.itemStack = itemStack;
        this.hand = hand;
        this.message = message;

        this.is_signed = itemStack.isOf(ModItem.BOTTLED_MESSAGE);
    }

    private void finishBottle()
    {
        int slot = (this.hand == Hand.MAIN_HAND) ? this.player.getInventory().selectedSlot : PlayerInventory.OFF_HAND_SLOT;
        ClientPlayNetworking.send(new BottleMessageWritePayload(slot, this.message));

        if (this.client != null) this.client.setScreen(null);
    }

    @Override
    protected void init()
    {
        this.doneButton = this
                .addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button ->
                {
                    if (this.client != null) this.client.setScreen(null);
                }
        ).dimensions((this.width / 2) - 40, (this.height / 2) + 32, 80, 20).build());
        this.signButton = this
                .addDrawableChild(ButtonWidget.builder(Text.translatable("container.frontiers.bottled_message.write"), button ->
                        {
                            this.finishBottle();
                        }
                ).dimensions((this.width / 2) - 40, (this.height / 2) + 32, 80, 20).build());

        this.doneButton.visible = this.is_signed;
        this.signButton.visible = !this.is_signed;
        this.signButton.active = !StringHelper.isBlank(this.message);
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

                        this.signButton.active = !StringHelper.isBlank(this.message);
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
        else if (StringHelper.isValidChar(chr) && this.message.length() < 32 && !this.is_signed)
        {
            this.message = this.message.concat(Character.toString(chr));
            this.signButton.active = !StringHelper.isBlank(this.message);
            return true;
        }
        else return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        super.render(context, mouseX, mouseY, delta);
        this.setFocused(null);

        Text displayed = Text.literal(message);

        int x = ((this.width) / 2) - (textRenderer.getWidth(displayed) / 2);
        int y = ((this.height) / 2) - 4;

        if (!this.is_signed)
        {
            context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, (this.height / 2) - (PAPER_XYS[1] / 2) - 28, 16777215);
        }

        context.drawText(this.textRenderer, displayed, x, y, 0, false);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta)
    {
        this.renderInGameBackground(context);

        int x = ((this.width) / 2) - (PAPER_XYS[0] / 2);
        int y = ((this.height) / 2) - (PAPER_XYS[1] / 2);

        context.drawGuiTexture(PAPER_TEX, x, y, PAPER_XYS[0], PAPER_XYS[1]);
    }
}
