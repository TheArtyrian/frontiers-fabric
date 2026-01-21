package net.artyrian.frontiers.definition.recipe.fletching;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FletchingRecipeBuilder implements RecipeBuilder
{
    private final Ingredient headIngredient;
    private final Ingredient stickIngredient;
    private final Ingredient featherIngredient;
    private final Item output;
    private final int count;
    private final ResourceLocation arrowTex;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private final ArrowFletchingRecipe.RecipeFactory<?> recipeFactory;

    public FletchingRecipeBuilder(
            ItemLike headIngredient,
            ItemLike stickIngredient,
            ItemLike featherIngredient,
            ItemLike output,
            int count,
            ResourceLocation arrowTex,
            ArrowFletchingRecipe.RecipeFactory<?> recipeFactory
    )
    {
        this.headIngredient = Ingredient.of(headIngredient);
        this.stickIngredient = Ingredient.of(stickIngredient);
        this.featherIngredient = Ingredient.of(featherIngredient);
        this.output = output.asItem();
        this.count = count;
        this.arrowTex = arrowTex;
        this.recipeFactory = recipeFactory;
    }


    /** The root recipe builder for arrow fletching. Not recommended to use unless you want full control over the recipe creation.
     * <p>Intakes three ingredients, an output stack + coun, and a texture for the displayed arrow model.</p>*/
    public static <T extends ArrowFletchingRecipe> FletchingRecipeBuilder create(Item head, Item stick, Item feather, ItemLike output, int count, ResourceLocation arrowtex)
    {
        return new FletchingRecipeBuilder(head, stick, feather, output, count, arrowtex, ArrowFletchingRecipe::new);
    }

    /** <b>THE RECOMMENDED RECIPE FACTORY FOR FLETCHING RECIPE MAKING!</b>
     * <p>Takes an arrowhead ingredient, an output stack, and arrow texture. This recipe will make 6 of the requested item.</p>*/
    public static <T extends ArrowFletchingRecipe> FletchingRecipeBuilder create(Item head, ItemLike output, ResourceLocation arrowtex)
    {
        return create(head, Items.STICK, Items.FEATHER, output, 6, arrowtex);
    }

    /** <b>Similar to the recommended factory, but takes an input count as well.</b>
     * <p>Takes an arrowhead ingredient, an output stack + count, and arrow texture. This recipe will make 6 of the requested item.</p>*/
    public static <T extends ArrowFletchingRecipe> FletchingRecipeBuilder create(Item head, ItemLike output, int count, ResourceLocation arrowtex)
    {
        return create(head, Items.STICK, Items.FEATHER, output, count, arrowtex);
    }

    public FletchingRecipeBuilder unlockedBy(String string, Criterion<?> advancementCriterion)
    {
        this.criteria.put(string, advancementCriterion);
        return this;
    }

    /** Fym groups, you should literally only be crafting arrows on this thing dummy */
    @Override
    public RecipeBuilder group(@Nullable String group) { return this; }

    @Override
    public Item getResult() {
        return output;
    }

    @Override
    public void save(RecipeOutput exporter, ResourceLocation recipeId) {
        Advancement.Builder builder = exporter.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(builder);

        this.criteria.forEach(builder::addCriterion);
        Objects.requireNonNull(builder);

        ArrowFletchingRecipe arrowRec = this.recipeFactory.create(
                this.headIngredient,
                this.stickIngredient,
                this.featherIngredient,
                new ItemStack(this.output),
                this.count,
                this.arrowTex
        );

        exporter.accept(recipeId, arrowRec, builder.build(recipeId.withPrefix("recipes/")));
    }

    @Override
    public void save(RecipeOutput exporter)
    {
        this.save(exporter, Frontiers.MOD_ID + ":arrow_fletching/" + BuiltInRegistries.ITEM.getKey(getResult()).getPath() + "_fletching");
    }
}
