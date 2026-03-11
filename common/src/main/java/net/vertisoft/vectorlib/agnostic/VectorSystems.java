package net.vertisoft.vectorlib.agnostic;

import net.minecraft.resources.ResourceLocation;
import net.vertisoft.vectorlib.agnostic.splash.VectorSplash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VectorSystems
{
    // Splash mixin controller
    public final VectorSplash SPLASHES = new VectorSplash();

    // Important contributor IDs
    public final Map<String, String> CONTRIB_IDS = new HashMap<>();

    // Special cape list
    public final Map<String, ResourceLocation> CONTRIBUTOR_CAPES = new HashMap<>();
    public final List<String> TRANSPARENT_CAPES = new ArrayList<>();

    // Suppression warning message
    public static final String SUPPRESSION_WARNING =
            "\n     Suppressing experimental warnings due to VectorLib config settings - as this is a modded installation, you should know the risks already." +
            "\n     You can disable this suppression in the VectorLib config file - set suppressExperimentalWarn to false." +
            "\n     Remember, make backups of your world whenever possible and/or convenient!";

    // Here for making my life easier
    public static final String JEI_PREFIX = "jei.item.desc.";

    public VectorSystems()
    {
        // Default contributor IDs
        CONTRIB_IDS.put("Artyrian", "774e37fc-1ca4-4156-827e-661afa24cb56");
        CONTRIB_IDS.put("Yurjezich", "2a9c377e-26cc-4d48-a62a-05ce3ac2f405");
        CONTRIB_IDS.put("KirbyTG", "651fefc2-fae9-46ea-b383-8e45798fc1b2");
        CONTRIB_IDS.put("Xenona", "708f1c4f-a652-4252-a090-855bafadd403");
        CONTRIB_IDS.put("LucarioDeath", "2f213cea-2443-4313-8aa4-0f4c72687ddd");
        CONTRIB_IDS.put("EmeraldEiscue", "3ab1a668-b818-4d44-b81c-ac1b105c7692");
        CONTRIB_IDS.put("Hecco", "bc56b2c8-9ef8-4532-b045-00f44804bca4");
        CONTRIB_IDS.put("Diemant", "32290fa8-77ed-4794-9cba-25c09e7f4e1d");
        CONTRIB_IDS.put("Yirmiri", "1cedf927-5c8f-4650-95e9-808fc8f94d00");
        CONTRIB_IDS.put("Courtjjester", "95e928ac-0cc8-4bf9-8451-d33da7933fd3");
        CONTRIB_IDS.put("SlimeSlabs", "54701376-b19a-4fc1-b107-74626b0d1bfb");
    }
}
