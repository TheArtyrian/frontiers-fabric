package net.artyrian.frontiers.definition.item.intf;

public interface Magic
{
    void onCast(int level);
    boolean canCast(int level);
}
