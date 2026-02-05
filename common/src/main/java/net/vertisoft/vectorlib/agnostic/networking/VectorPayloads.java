package net.vertisoft.vectorlib.agnostic.networking;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorNetSync;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorSyncable;
import net.vertisoft.vectorlib.agnostic.networking.payloads.NetSyncPayload;

public class VectorPayloads
{
    public static final ResourceLocation VECTOR_NETSYNC = ResourceLocation.fromNamespaceAndPath(VectorLib.ID, "netsynchro");

    public static void netsync(NetSyncPayload payload, Level level)
    {
        String ERROR_CODE = "Ran into an error while handling a VectorNetSync packet, suppressing without changes";
        Entity target = level.getEntity(payload.id());

        try
        {
            if (target instanceof VectorSyncable synchro)
            {
                try
                {
                    VectorNetSync sync = synchro.getVectorLibNetsync();
                    CompoundTag tag = payload.nbt();

                    if (sync.identityMatches(tag))
                    {
                        sync.getTag().merge(tag);
                        synchro.vectorLibNetsyncPost();
                    }
                    else
                    {
                        throw new IllegalArgumentException("The provided inbound NetSync NBT's ID does not match the existing one!");
                    }
                }
                catch (IllegalArgumentException excpr)
                {
                    Frontiers.LOGGER.error(ERROR_CODE, excpr);
                }
            }
            else
            {
                throw new IllegalArgumentException("The targeted entity does not inherit VectorSyncable!");
            }
        }
        catch (IllegalArgumentException excpr)
        {
            Frontiers.LOGGER.error(ERROR_CODE, excpr);
        }
    }
}
