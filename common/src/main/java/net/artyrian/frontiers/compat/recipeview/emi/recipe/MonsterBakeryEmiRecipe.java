package net.artyrian.frontiers.compat.recipeview.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.recipeview.FRRecViewCom;
import net.artyrian.frontiers.compat.recipeview.emi.FrontiersEMI;
import net.artyrian.frontiers.compat.recipeview.emi.custom.BakeryWidget;
import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MonsterBakeryEmiRecipe implements EmiRecipe
{
    private final EmiIngredient base;
    private final EmiIngredient entity_egg;
    private final int percent;
    private final ResourceLocation id;
    private final String entity_name;

    public static final int TEX_W = 82;
    public static final int TEX_H = 38;

    public <T extends LivingEntity> MonsterBakeryEmiRecipe(Item base, EntityType<T> entity, int percent)
    {
        this.base = EmiIngredient.of(Ingredient.of(base));
        this.entity_egg = EmiIngredient.of(Ingredient.of(MonsterBakeryBlockEntity.getSpawnEggItem(entity)));
        this.percent = percent;
        this.entity_name = entity.getDescriptionId();

        ResourceLocation resc = BuiltInRegistries.ENTITY_TYPE.getKey(entity);
        this.id = Frontiers.id(resc.getNamespace(), "/" + resc.getPath() + "_monsterbakery");
    }

    @Override public EmiRecipeCategory getCategory() { return FrontiersEMI.MONSTER_BAKERY; }
    @Override @Nullable public ResourceLocation getId() { return this.id; }
    @Override public List<EmiIngredient> getInputs() { return List.of(base); }
    @Override public List<EmiStack> getOutputs() { return List.of(); }
    @Override public int getDisplayWidth() { return TEX_W; }
    @Override public int getDisplayHeight() { return TEX_H; }
    @Override public boolean supportsRecipeTree() { return false; }

    @Override
    public void addWidgets(WidgetHolder widg)
    {
        widg.addTexture(FrontiersEMI.BAKER_ARROW_EMPTY, 24, 5);
        widg.addAnimatedTexture(FrontiersEMI.BAKER_ARROW_FULL, 24, 5, 10 * MonsterBakeryBlockEntity.MAX_INCUBATE_TIME, true, false, false);

        widg.addTexture(EmiTexture.EMPTY_FLAME, 1, 24);
        widg.addAnimatedTexture((Frontiers.DUNGEONS_DELIGHT_LOADED) ? FrontiersEMI.DD_FIRE : FrontiersEMI.FIRE,
                1, 24, MonsterBakeryBlockEntity.MAX_INCUBATE_TIME * 4, false, true, true);

        widg.addText(Component.translatable("emi.fuel_time.frontiers.bakery_chance", this.percent), 20, 28, -1, true);

        widg.addSlot(base, 0, 4);
        BakeryWidget outputWidget = new BakeryWidget(entity_egg, 56, 0);
        outputWidget.appendTooltip(FRRecViewCom.bakeryEntityText(this.entity_name));
        widg.add(outputWidget)
                .large(true)
                .customBackground(FRRecViewCom.WIDGET_SHEET, FRRecViewCom.CAGE_OUTPUT_XY[0], FRRecViewCom.CAGE_OUTPUT_XY[1], FRRecViewCom.CAGE_OUTPUT_DIM[0], FRRecViewCom.CAGE_OUTPUT_DIM[1])
                .recipeContext(this);
    }
}