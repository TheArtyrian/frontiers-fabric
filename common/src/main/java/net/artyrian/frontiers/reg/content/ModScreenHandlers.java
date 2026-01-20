package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarScreenHandler;
import net.artyrian.frontiers.definition.menu.fletching.FletchingTableScreenHandler;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryScreenHandler;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.platform.VectorRegistryIntf;

import java.util.function.Supplier;

public class ModScreenHandlers
{
    public static final Supplier<MenuType<CurseAltarScreenHandler>> CURSE_ALTAR = register("curse_altar", CurseAltarScreenHandler::new);
    public static final Supplier<MenuType<FletchingTableScreenHandler>> FLETCHING_TABLE = register("fletching_table", FletchingTableScreenHandler::new);
    public static final Supplier<MenuType<MonsterBakeryScreenHandler>> MONSTER_BAKERY = register("monster_bakery", MonsterBakeryScreenHandler::new);

    private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, VectorRegistryIntf.MenuData<T> data)
    {
        return VectorLib.REGISTRY.registerMenu(Frontiers.MOD_ID, id, data);
    }

    public static void registerScreens()
    {

    }
}
