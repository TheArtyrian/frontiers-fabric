package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarMenu;
import net.artyrian.frontiers.definition.menu.fletching.FletchingTableMenu;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.platform.VectorRegistryIntf;

import java.util.function.Supplier;

public class FRMenus
{
    public static final Supplier<MenuType<CurseAltarMenu>> CURSE_ALTAR = register("curse_altar", CurseAltarMenu::new);
    public static final Supplier<MenuType<FletchingTableMenu>> FLETCHING_TABLE = register("fletching_table", FletchingTableMenu::new);
    public static final Supplier<MenuType<MonsterBakeryMenu>> MONSTER_BAKERY = register("monster_bakery", MonsterBakeryMenu::new);

    private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, VectorRegistryIntf.MenuData<T> data)
    {
        return VectorLib.REGISTRY.registerMenu(Frontiers.MOD_ID, id, data);
    }

    public static void registerScreens()
    {

    }
}
