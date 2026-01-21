package net.artyrian.frontiers.definition.event;

import net.artyrian.frontiers.Frontiers;

public class ClientEvents
{
    public static void registerDeathScreenMsg()
    {
        Frontiers.DEATH_MSG.init(Frontiers.MOD_ID, "texts/death_messages.txt");
        Frontiers.HARDCORE_MSG.init(Frontiers.MOD_ID, "texts/death_messages_hardcore.txt");
    }
}
