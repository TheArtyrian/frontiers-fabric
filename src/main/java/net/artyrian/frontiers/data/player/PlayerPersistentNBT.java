package net.artyrian.frontiers.data.player;

import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.minecraft.nbt.NbtCompound;

public class PlayerPersistentNBT
{
    public static class AvariceTotem
    {
        public static boolean setTotemStatus(PlayerMixInterface player, boolean used_totem)
        {
            NbtCompound compound = player.frontiersArtyrian$getPersistentNbt();

            compound.putBoolean("totem", used_totem);

            return used_totem;
        }
    }

    public static class Sanity
    {
        public static int addSanity(PlayerMixInterface player, int amount)
        {
            NbtCompound compound = player.frontiersArtyrian$getPersistentNbt();
            int sanity = compound.getInt("sanity");

            sanity = Math.clamp(sanity + amount, 0, 20);

            compound.putInt("sanity", sanity);

            return amount;
        }

        public static int removeSanity(PlayerMixInterface player, int amount)
        {
            return addSanity(player, -amount);
        }

        public static int addSanityTick(PlayerMixInterface player, int amount)
        {
            NbtCompound compound = player.frontiersArtyrian$getPersistentNbt();
            int sanity = compound.getInt("sanity_tick");

            sanity = Math.clamp(sanity + amount, 0, 1200);

            compound.putInt("sanity_tick", sanity);

            return amount;
        }

        public static int removeSanityTick(PlayerMixInterface player, int amount)
        {
            return addSanityTick(player, -amount);
        }
    }
}
