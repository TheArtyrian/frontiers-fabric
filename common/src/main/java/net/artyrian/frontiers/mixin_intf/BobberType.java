package net.artyrian.frontiers.mixin_intf;

import net.minecraft.util.CommonColors;

public enum BobberType
{
    DEFAULT(0, CommonColors.BLACK),
    COBALT(1, CommonColors.BLUE);

    private final int ID;
    private final int line_color;

    BobberType(final int ID, final int line_color)
    {
        this.ID = ID;
        this.line_color = line_color;
    }

    public static BobberType getBasedOnInt(int provide)
    {
        return switch (provide)
        {
            case 0 -> DEFAULT;
            case 1 -> COBALT;

            default -> DEFAULT; /*/noinspection DuplicateBranchesInSwitch/*/
        };
    }

    public int getID() { return this.ID; }
    public int getLineColor() { return this.line_color; }
}
