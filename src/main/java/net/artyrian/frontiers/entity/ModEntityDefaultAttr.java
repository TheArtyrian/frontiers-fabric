package net.artyrian.frontiers.entity;

import net.artyrian.frontiers.entity.mob.CrawlerEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class ModEntityDefaultAttr
{
    public static void register()
    {
        FabricDefaultAttributeRegistry.register(ModEntity.CRAWLER, CrawlerEntity.createAttr());
    }
}
