package net.artyrian.frontiers.compat.recipeview;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/** "For when one recipe viewer just won't cut it." <p>god i should patent that phrase*/
public class FRRecViewCom
{
    // Fletching Table Sheet Data
    private static final String FLETCH_LOC = "textures/gui/recviewer/fletching.png";
    public static final ResourceLocation FLETCHING_SHEET = Frontiers.id(FLETCH_LOC);
    public static final int FLETCH_TEX_DIMSQ = 256;
    public static final int FLETCH_W = 82;
    public static final int FLETCH_H = 60;
    public static final int[] FLETCH_ISLOT = new int[]{1, 1, 1, 21, 1, 41};
    public static final int[] FLETCH_OSLOT = new int[]{59, 21};

    // Widget Sheet Data
    private static final String WIDGET_LOC = "textures/gui/recviewer/widgets.png";
    public static final ResourceLocation WIDGET_SHEET = Frontiers.id(WIDGET_LOC);
    public static final int WIDGET_TEX_DIMSQ = 256;     // thanks jei for making me take up 30000000x more spaces than i fucking need

    // Misc
    public static final int[] FLAME_DIM = new int[]{14, 14};
    public static final int[] ARROW_OFF_XY = new int[]{40, 0};
    public static final int[] ARROW_ON_XY = new int[]{40, 16};
    public static final int[] ARROW_DIM = new int[]{24, 16};
    public static final int[] CAGE_OUTPUT_XY = new int[]{14, 0};
    public static final int[] CAGE_OUTPUT_DIM = new int[]{26, 26};

    // EMI Icons
    public static final int[] EMI_ICO_DIM = new int[]{16, 16};
    public static final int[] EMI_FLETCH_ICO = new int[]{240, 240};
    public static final int[] EMI_BAKE_ICO = new int[]{224, 240};

    // JEI Colors
    public static final int JEI_TEXT_GRAY = -8355712;

    // Monster Bakery display name
    public static Component bakeryEntityText(String name) { return Component.translatable(name).withStyle(ChatFormatting.GREEN); }
}
