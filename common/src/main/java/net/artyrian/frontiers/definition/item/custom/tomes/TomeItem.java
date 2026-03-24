package net.artyrian.frontiers.definition.item.custom.tomes;

import net.artyrian.frontiers.definition.data.nbt_sync.PlayerPersistentNBT;
import net.artyrian.frontiers.definition.entity.intf.ManaUser;
import net.artyrian.frontiers.definition.item.intf.Magic;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.ModToolMaterial;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class TomeItem extends Item implements Magic
{
    private static final int[] LEVELS = new int[]{4, 8, 10, 20};
    protected final int enchantability;

    public TomeItem(int enchantability, Properties settings)
    {
        super(settings);
        this.enchantability = enchantability;
    }

    public int getEnchantmentValue() { return this.enchantability; }
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) { return ingredient.is(ModItem.INVOKE_SHARD.get()) || super.isValidRepairItem(stack, ingredient); }

    protected int[] getLvlToMana() { return LEVELS; }

    @Override public boolean onCast(ManaUser user, Level level, ItemStack stack, @Nullable InteractionHand hand, Vec3 position) { return true; }
    @Override public boolean canCast(ManaUser user)
    {
        if (user instanceof Player player && player.isCreative()) return true;
        int lvl = user.getManaLevel();
        int pts = PlayerPersistentNBT.Mana.lvlsToPts(lvl, user.getManaPts());
        int[] levelCap = this.getLvlToMana();

        return switch (lvl)
        {
            case 0 -> (pts > levelCap[0]);
            case 1 -> (pts > levelCap[1]);
            case 2 -> (pts > levelCap[2]);
            default -> (pts > levelCap[3]);
        };
    }
}
