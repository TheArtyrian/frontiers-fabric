package net.artyrian.frontiers.data.player;

import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;

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
        /** Adds to the sanity NBT. */
        public static int addSanity(PlayerMixInterface player, int amount)
        {
            NbtCompound compound = player.frontiersArtyrian$getPersistentNbt();
            int sanity = compound.getInt("sanity");

            sanity = Math.clamp(sanity + amount, 0, 20);

            compound.putInt("sanity", sanity);

            return sanity;
        }

        /** Subtracts from the sanity NBT. */
        public static int removeSanity(PlayerMixInterface player, int amount)
        {
            return addSanity(player, -amount);
        }

        /** Adds to the sanity tick NBT. */
        public static int addSanityTick(PlayerMixInterface player, int amount)
        {
            NbtCompound compound = player.frontiersArtyrian$getPersistentNbt();
            int sanity = compound.getInt("sanity_tick");

            sanity = Math.clamp(sanity + amount, 0, 1200);

            compound.putInt("sanity_tick", sanity);

            return sanity;
        }

        /** Subtracts from the sanity tick NBT. */
        public static int removeSanityTick(PlayerMixInterface player, int amount)
        {
            return addSanityTick(player, -amount);
        }

        public static int resetSanityTick(PlayerMixInterface player, boolean subtracting)
        {
            NbtCompound compound = player.frontiersArtyrian$getPersistentNbt();

            int setup = (subtracting) ? 1200 : 0;
            compound.putInt("sanity_tick", setup);

            return setup;
        }

        public static boolean setCragsMonsterKill(PlayerMixInterface player)
        {
            NbtCompound compound = player.frontiersArtyrian$getPersistentNbt();

            compound.putBoolean("cragsmonster_kill", true);

            return true;
        }
    }
}
