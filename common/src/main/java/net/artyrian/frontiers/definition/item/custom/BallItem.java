package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.definition.entity.types.projectile.BallEntity;
import net.artyrian.frontiers.reg.sound.ModSounds;
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
    private final int TEXT_COLOR;
    private final int bounces;

    // A map of all vanilla + compat formatting colors
    private static final Map<String, Integer> FORMAT_MAP = Map.ofEntries(
            // VANILLA
            Map.entry(DyeColor.WHITE.getName(), 0xFFFFFF),
            Map.entry(DyeColor.LIGHT_GRAY.getName(), 0xABABAB),
            Map.entry(DyeColor.GRAY.getName(), 0x808080),
            Map.entry(DyeColor.BLACK.getName(), 0x464349),
            Map.entry(DyeColor.BROWN.getName(), 0xA04E14),
            Map.entry(DyeColor.RED.getName(), 0xEB2020),
            Map.entry(DyeColor.ORANGE.getName(), 0xE74b00),
            Map.entry(DyeColor.YELLOW.getName(), 0xDCBB00),
            Map.entry(DyeColor.LIME.getName(), 0x88B500),
            Map.entry(DyeColor.GREEN.getName(), 0x00A100),
            Map.entry(DyeColor.CYAN.getName(), 0x00C0C0),
            Map.entry(DyeColor.LIGHT_BLUE.getName(), 0x32A5CD),
            Map.entry(DyeColor.BLUE.getName(), 0x2626FF),
            Map.entry(DyeColor.PURPLE.getName(), 0xA020F0),
            Map.entry(DyeColor.MAGENTA.getName(), 0xE92CE9),
            Map.entry(DyeColor.PINK.getName(), 0xFF69B4),

            Map.entry("slime", 0x72E962),

            // DELICATE DYES
            Map.entry("coral", 0xC65050),
            Map.entry("canary", 0xBBAF61),
            Map.entry("wasabi", 0x77AC5D),
            Map.entry("sacramento", 0x437A6A),
            Map.entry("sky", 0x7BBEC4),
            Map.entry("blurple", 0x614495),
            Map.entry("sangria", 0x8F2067),
            Map.entry("rose", 0x9C2649),
            Map.entry("lavender", 0x9B66A7),
            Map.entry("umber", 0x883225)
    );

    public BallItem(int color, Properties settings)
    {
        this(color, 0, settings);
    }

    public BallItem(int color, int bounces, Properties settings)
    {
        super(settings);
        this.TEXT_COLOR = color;
        this.bounces = bounces;
    }

    public int getColor() { return TEXT_COLOR; }
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

    public static int getTxtColorOrDefault(DyeColor color) { return getTxtColorOrDefault(color.getName()); }
    public static int getTxtColorOrDefault(String id)  { return FORMAT_MAP.getOrDefault(id, DyeColor.WHITE.getTextColor()); }
}
