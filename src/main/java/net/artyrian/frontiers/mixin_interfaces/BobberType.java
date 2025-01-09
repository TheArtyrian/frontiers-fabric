package net.artyrian.frontiers.mixin_interfaces;

import net.minecraft.util.Colors;

public enum BobberType
{
    DEFAULT(0, Colors.BLACK),
    COBALT(1, Colors.BLUE);

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

            default -> DEFAULT;
        };
    }

    public int getID() { return this.ID; }
    public int getLineColor() { return this.line_color; }
}
