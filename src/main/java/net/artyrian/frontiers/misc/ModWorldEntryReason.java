package net.artyrian.frontiers.misc;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;

public class ModWorldEntryReason
{
    static
    {
        DownloadingTerrainScreen.WorldEntryReason.values();        // Ensure class is loaded.
    }

    // Vanilla values start:
    //                  NETHER_PORTAL
    //                  END_PORTAL
    //                  OTHER
    public static DownloadingTerrainScreen.WorldEntryReason CRAGS;
}
