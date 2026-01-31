package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.definition.entity.projectile.BallEntity;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import java.util.Map;

public class BallItem extends Item
{
    private final ChatFormatting TEXT_COLOR;
    private final int bounces;

    // A map of all vanilla + compat formatting colors
    private static final Map<String, ChatFormatting> FORMAT_MAP = Map.ofEntries(
            // VANILLA
            Map.entry(DyeColor.WHITE.getName(), ChatFormatting.WHITE),
            Map.entry(DyeColor.LIGHT_GRAY.getName(), ChatFormatting.GRAY),
            Map.entry(DyeColor.GRAY.getName(), ChatFormatting.GRAY),
            Map.entry(DyeColor.BLACK.getName(), ChatFormatting.DARK_GRAY),
            Map.entry(DyeColor.BROWN.getName(), ChatFormatting.DARK_RED),
            Map.entry(DyeColor.RED.getName(), ChatFormatting.RED),
            Map.entry(DyeColor.ORANGE.getName(), ChatFormatting.GOLD),
            Map.entry(DyeColor.YELLOW.getName(), ChatFormatting.YELLOW),
            Map.entry(DyeColor.LIME.getName(), ChatFormatting.GREEN),
            Map.entry(DyeColor.GREEN.getName(), ChatFormatting.DARK_GREEN),
            Map.entry(DyeColor.CYAN.getName(), ChatFormatting.AQUA),
            Map.entry(DyeColor.LIGHT_BLUE.getName(), ChatFormatting.BLUE),
            Map.entry(DyeColor.BLUE.getName(), ChatFormatting.BLUE),
            Map.entry(DyeColor.PURPLE.getName(), ChatFormatting.DARK_PURPLE),
            Map.entry(DyeColor.MAGENTA.getName(), ChatFormatting.LIGHT_PURPLE),
            Map.entry(DyeColor.PINK.getName(), ChatFormatting.LIGHT_PURPLE),

            // DELICATE DYES
            Map.entry("coral", ChatFormatting.RED),
            Map.entry("canary", ChatFormatting.YELLOW),
            Map.entry("wasabi", ChatFormatting.GREEN),
            Map.entry("sacramento", ChatFormatting.DARK_GREEN),
            Map.entry("sky", ChatFormatting.AQUA),
            Map.entry("blurple", ChatFormatting.BLUE),
            Map.entry("sangria", ChatFormatting.DARK_RED),
            Map.entry("rose", ChatFormatting.DARK_RED),
            Map.entry("lavender", ChatFormatting.LIGHT_PURPLE),
            Map.entry("umber", ChatFormatting.GOLD)
    );

    public BallItem(ChatFormatting color, Properties settings)
    {
        this(color, 0, settings);
    }

    public BallItem(ChatFormatting color, int bounces, Properties settings)
    {
        super(settings);
        this.TEXT_COLOR = color;
        this.bounces = bounces;
    }

    public ChatFormatting getColor() { return TEXT_COLOR; }
    public int getBounces() { return this.bounces; }

    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemStack = user.getItemInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.BALL_THROW.get(), SoundSource.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide)
        {
            boolean sneaking = user.isShiftKeyDown();
            BallEntity ballEntity = new BallEntity(user, world);
            ballEntity.setItem(itemStack);
            ballEntity.setBounces((itemStack.getItem() instanceof BallItem ball) ? ball.getBounces() : 0);
            ballEntity.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0F, (sneaking) ? 0.4F : 0.8F, 1.0F);
            world.addFreshEntity(ballEntity);
        }

        user.awardStat(Stats.ITEM_USED.get(this));
        itemStack.consume(1, user);
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }

    public static ChatFormatting getTxtColorOrDefault(DyeColor color)
    {
        return FORMAT_MAP.getOrDefault(color.getName(), ChatFormatting.WHITE);
    }
}
