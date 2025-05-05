package net.artyrian.frontiers.datagen.recipe;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.recipe.fletching.ArrowFletchingRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FletchingRecipeBuilder implements CraftingRecipeJsonBuilder
{
    private final Ingredient headIngredient;
    private final Ingredient stickIngredient;
    private final Ingredient featherIngredient;
    private final Item output;
    private final int count;
    private final Identifier arrowTex;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    private final ArrowFletchingRecipe.RecipeFactory<?> recipeFactory;

    public FletchingRecipeBuilder(
            ItemConvertible headIngredient,
            ItemConvertible stickIngredient,
            ItemConvertible featherIngredient,
            ItemConvertible output,
            int count,
            Identifier arrowTex,
            ArrowFletchingRecipe.RecipeFactory<?> recipeFactory
    )
    {
        this.headIngredient = Ingredient.ofItems(headIngredient);
        this.stickIngredient = Ingredient.ofItems(stickIngredient);
        this.featherIngredient = Ingredient.ofItems(featherIngredient);
        this.output = output.asItem();
        this.count = count;
        this.arrowTex = arrowTex;
        this.recipeFactory = recipeFactory;
    }


    /** The root recipe builder for arrow fletching. Not recommended to use unless you want full control over the recipe creation.
     * <p>Intakes three ingredients, an output stack + coun, and a texture for the displayed arrow model.</p>*/
    public static <T extends ArrowFletchingRecipe> FletchingRecipeBuilder create(Item head, Item stick, Item feather, ItemConvertible output, int count, Identifier arrowtex)
    {
        return new FletchingRecipeBuilder(head, stick, feather, output, count, arrowtex, ArrowFletchingRecipe::new);
    }

    /** <b>THE RECOMMENDED RECIPE FACTORY FOR FLETCHING RECIPE MAKING!</b>
     * <p>Takes an arrowhead ingredient, an output stack, and arrow texture. This recipe will make 6 of the requested item.</p>*/
    public static <T extends ArrowFletchingRecipe> FletchingRecipeBuilder create(Item head, ItemConvertible output, Identifier arrowtex)
    {
        return create(head, Items.STICK, Items.FEATHER, output, 6, arrowtex);
    }

    /** <b>Similar to the recommended factory, but takes an input count as well.</b>
     * <p>Takes an arrowhead ingredient, an output stack + count, and arrow texture. This recipe will make 6 of the requested item.</p>*/
    public static <T extends ArrowFletchingRecipe> FletchingRecipeBuilder create(Item head, ItemConvertible output, int count, Identifier arrowtex)
    {
        return create(head, Items.STICK, Items.FEATHER, output, count, arrowtex);
    }

    public FletchingRecipeBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion)
    {
        this.criteria.put(string, advancementCriterion);
        return this;
    }

    /** Fym groups, you should literally only be crafting arrows on this thing dummy */
    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) { return this; }

    @Override
    public Item getOutputItem() {
        return output;
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        Objects.requireNonNull(builder);

        this.criteria.forEach(builder::criterion);
        Objects.requireNonNull(builder);

        ArrowFletchingRecipe arrowRec = this.recipeFactory.create(
                this.headIngredient,
                this.stickIngredient,
                this.featherIngredient,
                new ItemStack(this.output),
                this.count,
                this.arrowTex
        );

        exporter.accept(recipeId, arrowRec, builder.build(recipeId.withPrefixedPath("recipes/")));
    }

    @Override
    public void offerTo(RecipeExporter exporter)
    {
        this.offerTo(exporter, Frontiers.MOD_ID + ":arrow_fletching/" + Registries.ITEM.getId(getOutputItem()).getPath() + "_fletching");
    }
}
