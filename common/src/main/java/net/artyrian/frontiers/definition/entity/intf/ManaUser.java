package net.artyrian.frontiers.definition.entity.intf;

public interface ManaUser
{
    int getManaLevel();
    int getManaPts();
    void removeMana(int subtract);
}