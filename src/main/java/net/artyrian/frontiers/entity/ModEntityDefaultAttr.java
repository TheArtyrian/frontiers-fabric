package net.artyrian.frontiers.entity;

import net.artyrian.frontiers.entity.mob.CrawlerEntity;
import net.artyrian.frontiers.entity.mob.JungleSpiderEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class ModEntityDefaultAttr
{
    public static void register()
    {
        FabricDefaultAttributeRegistry.register(ModEntity.CRAWLER, CrawlerEntity.createAttr());
        FabricDefaultAttributeRegistry.register(ModEntity.JUNGLE_SPIDER, JungleSpiderEntity.createAttr());
    }
}
