package net.vertisoft.vectorlib.agnostic.networking.netsync;

import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.networking.payloads.NetSyncPayload;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

/** A loader-independent replacement for data attachments for vanilla entities.
 * Takes care of most of the heavy lifting when it comes to syncing data between the server & clients.
 * <p>
 * Since this a purely in-house solution, there's no concerns about other mods clashing with any data stored.
 * <p>
 * Any entity using this MUST implement {@link VectorSyncable VectorSyncable}
 * in order to properly sync.
 * */
public class VectorNetSync
{
    public static final String LEGACY_FABRIC = "fabric:attachments";
    public static final String NBT_NAME = "VectorNetSyncData";
    public static final String ID_TAG_NAME = "VECTOR_ID";

    private final Entity TRACKING;
    private final String ID;
    private CompoundTag TAG;

    public VectorNetSync(Entity trackable, String identifier, Consumer<CompoundTag> impl)
    {
        this.TRACKING = trackable;
        this.ID = identifier;
        this.TAG = new CompoundTag();
        this.TAG.putString(ID_TAG_NAME, ID);
        impl.accept(this.TAG);
    }

    /** Sets the tag for this sync. */
    public void setTag(CompoundTag tag) { this.TAG = tag; }
    /** Gets the CompoundTag attached to this sync. */
    public CompoundTag getTag() { return this.TAG; }

    /** Determines if the identity for the provided compound tag exists. */
    public boolean identityMatches(CompoundTag tag)
    {
        if (this.TAG.contains(ID_TAG_NAME, ByteTag.TAG_STRING) && tag.contains(ID_TAG_NAME, ByteTag.TAG_STRING))
        {
            String x1 = this.TAG.getString(ID_TAG_NAME);
            String x2 = tag.getString(ID_TAG_NAME);
            return x1.equals(x2);
        }
        return false;
    }

    /** Syncs a String. */
    public void syncString(String key, String value, boolean force_if_absent)
    {
        if (this.TAG.contains(key, ByteTag.TAG_STRING) || force_if_absent)
        {
            this.TAG.putString(key, value);
            this.sendToAllTracking();
        }
        else
        {
            VectorLib.LOGGER.warn("Attempted to add a String value {} to a NetSync tag for {} but it doesn't exist.", key, this.TRACKING.toString());
        }
    }
    /** Gets a String, or defaults to another. */
    public String getString(String key, String fallback)
    {
        if (this.TAG.contains(key, ByteTag.TAG_STRING))
        {
            return this.TAG.getString(key);
        }
        else
        {
            VectorLib.LOGGER.warn("Cannot find String value for {}, falling back", key);
            return fallback;
        }
    }

    /** Syncs a Double. */
    public void syncDouble(String key, double value, boolean force_if_absent)
    {
        if (this.TAG.contains(key, ByteTag.TAG_DOUBLE) || force_if_absent)
        {
            this.TAG.putDouble(key, value);
            this.sendToAllTracking();
        }
        else
        {
            VectorLib.LOGGER.warn("Attempted to add a Double value {} to a NetSync tag for {} but it doesn't exist.", key, this.TRACKING.toString());
        }
    }
    /** Gets a double, or defaults to another. */
    public double getDouble(String key, double fallback)
    {
        if (this.TAG.contains(key, ByteTag.TAG_DOUBLE))
        {
            return this.TAG.getDouble(key);
        }
        else
        {
            VectorLib.LOGGER.warn("Cannot find Double value for {}, falling back", key);
            return fallback;
        }
    }

    /** Syncs a Float. */
    public void syncFloat(String key, float value, boolean force_if_absent)
    {
        if (this.TAG.contains(key, ByteTag.TAG_FLOAT) || force_if_absent)
        {
            this.TAG.putFloat(key, value);
            this.sendToAllTracking();
        }
        else
        {
            VectorLib.LOGGER.warn("Attempted to add a Float value {} to a NetSync tag for {} but it doesn't exist.", key, this.TRACKING.toString());
        }
    }
    /** Gets a float, or defaults to another. */
    public float getFloat(String key, float fallback)
    {
        if (this.TAG.contains(key, ByteTag.TAG_FLOAT))
        {
            return this.TAG.getFloat(key);
        }
        else
        {
            VectorLib.LOGGER.warn("Cannot find Float value for {}, falling back", key);
            return fallback;
        }
    }

    /** Syncs a boolean. */
    public void syncBool(String key, boolean value, boolean force_if_absent)
    {
        if (this.TAG.contains(key, ByteTag.TAG_BYTE) || force_if_absent)
        {
            this.TAG.putBoolean(key, value);
            this.sendToAllTracking();
        }
        else
        {
            VectorLib.LOGGER.warn("Attempted to add a Boolean value {} to a NetSync tag for {} but it doesn't exist.", key, this.TRACKING.toString());
        }
    }
    /** Gets a boolean, or defaults to another. */
    public boolean getBool(String key, boolean fallback)
    {
        if (this.TAG.contains(key, ByteTag.TAG_BYTE))
        {
            return this.TAG.getBoolean(key);
        }
        else
        {
            VectorLib.LOGGER.warn("Cannot find Boolean value for {}, falling back", key);
            return fallback;
        }
    }

    /** Syncs an ItemStack. */
    public void syncItemStack(String key, ItemStack stack, boolean force_if_absent)
    {
        if (stack.isEmpty())
        {
            VectorLib.LOGGER.warn("Cannot encode an empty ItemStack for {}!", this.TRACKING.toString());
            return;
        }

        if (this.TAG.contains(key, ByteTag.TAG_COMPOUND) || force_if_absent)
        {
            this.TAG.put(key, stack.save(this.TRACKING.registryAccess(), new CompoundTag()));
            this.sendToAllTracking();
        }
        else
        {
            VectorLib.LOGGER.warn("Attempted to add an ItemStack Compound value {} to a NetSync tag for {} but it doesn't exist.", key, this.TRACKING.toString());
        }
    }
    /** Gets an ItemStack, or defaults to another. */
    public ItemStack getItemStack(String key, ItemStack fallback)
    {
        if (this.TAG.contains(key, ByteTag.TAG_COMPOUND))
        {
            CompoundTag tag = (CompoundTag)this.TAG.get(key);
            return ItemStack.parse(this.TRACKING.registryAccess(), tag.getCompound("item")).orElse(fallback);
        }
        else
        {
            VectorLib.LOGGER.warn("Cannot find ItemStack value for {}, falling back", key);
            return fallback;
        }
    }

    /** Syncs a UUID. */
    public void syncUUID(String key, UUID value, boolean force_if_absent)
    {
        if (this.TAG.contains(key, ByteTag.TAG_INT_ARRAY) || force_if_absent)
        {
            this.TAG.putUUID(key, value);
            this.sendToAllTracking();
        }
        else
        {
            VectorLib.LOGGER.warn("Attempted to add a UUID value {} to a NetSync tag for {} but it doesn't exist.", key, this.TRACKING.toString());
        }
    }
    /** Gets an optional UUID. */
    public Optional<UUID> getUUID(String key)
    {
        if (this.TAG.contains(key, ByteTag.TAG_INT_ARRAY))
        {
            return Optional.of(this.TAG.getUUID(key));
        }
        else
        {
            VectorLib.LOGGER.warn("Cannot find UUID value for {}, falling back", key);
            return Optional.empty();
        }
    }

    /** Sends the NBT to all tracking. */
    private void sendToAllTracking()
    {
        if (!this.TRACKING.level().isClientSide)
        {
            VectorLib.NETWORK.sendToAllTrackingEntity(this.TRACKING, new NetSyncPayload(this.TRACKING.getId(), this.TAG));
        }
    }

    /** Sends the NBT to one player. */
    public void sendToPlayer(ServerPlayer player)
    {
        if (!this.TRACKING.level().isClientSide)
        {
            VectorLib.NETWORK.sendToPlayer(player, new NetSyncPayload(this.TRACKING.getId(), this.TAG));
        }
    }

    /** Attempts to read NBT from a provided compound. Also has legacy fabric support. */
    public void readNetSyncFromNBT(CompoundTag reader)
    {
        if (reader.contains(NBT_NAME, ByteTag.TAG_COMPOUND))
        {
            this.TAG = reader.getCompound(NBT_NAME);
        }

        if (reader.contains(LEGACY_FABRIC, ByteTag.TAG_COMPOUND))
        {
            CompoundTag legacy = reader.getCompound(LEGACY_FABRIC);
            this.TAG.merge(legacy);
        }

        sendToAllTracking();
    }

    /** Saves this compound to NBT. */
    public void saveToNBT(CompoundTag reader)
    {
        reader.put(NBT_NAME, this.TAG);
        sendToAllTracking();
    }
}
