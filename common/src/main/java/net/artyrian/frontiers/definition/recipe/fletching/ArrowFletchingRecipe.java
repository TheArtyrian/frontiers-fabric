package net.artyrian.frontiers.definition.recipe.fletching;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.property.FRRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class ArrowFletchingRecipe implements Recipe<RecipeInput>
{
    private final ResourceLocation id;
    private final ResourceLocation arrow_texture;
    private final Ingredient headIngredient;
    private final Ingredient stickIngredient;
    private final Ingredient featherIngredient;
    private final ItemStack output;

    public ArrowFletchingRecipe(
            Ingredient headIngredient,
            Ingredient stickIngredient,
            Ingredient featherIngredient,
            ItemStack itemStack,
            int outputCount,
            ResourceLocation arrowTex)
    {
        this.id = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "arrow_fletching");
        this.headIngredient = headIngredient;
        this.stickIngredient = stickIngredient; // Ingredient.ofItems(Items.STICK)
        this.featherIngredient = featherIngredient; // Ingredient.ofItems(Items.FEATHER)
        this.output = new ItemStack(itemStack.getItem(), outputCount);
        this.arrow_texture = arrowTex;
    }

    /** <b>Similar to the recommended factory, but takes an input count as well.</b>
     * <p>Takes an arrowhead ingredient, an output stack + count, and arrow texture. This recipe will make 6 of the requested item.</p>*/
    public ArrowFletchingRecipe(Ingredient ingredient, ItemStack itemStack, int count, ResourceLocation arrow_texture)
    {
        this(
                ingredient,
                Ingredient.of(Items.STICK),
                Ingredient.of(Items.FEATHER),
                itemStack,
                count,
                arrow_texture
        );
    }

    @Override
    public boolean matches(RecipeInput input, Level world)
    {
        return (input instanceof ArrowFletchingRecipeInput input2 && this.matches(input2, world));
    }

    public boolean matches(ArrowFletchingRecipeInput recipe, Level world)
    {
        return (
                this.headIngredient.test(recipe.head()) &&
                this.stickIngredient.test(recipe.stick()) &&
                this.featherIngredient.test(recipe.feather()
                )
        );
    }

    @Override public ItemStack assemble(RecipeInput input, HolderLookup.Provider lookup) {
        return output.copy();
    }

    @Override public boolean canCraftInDimensions(int width, int height)
    {
        return width >= 1 && height >= 3;
    }

    @Override public ItemStack getResultItem(HolderLookup.Provider registriesLookup) {
        return output.copy();
    }

    @Override public ItemStack getToastSymbol() {
        return new ItemStack(Blocks.FLETCHING_TABLE);
    }

    @Override public RecipeType<?> getType() {
        return FRRecipes.ARROW_FLETCHING.get();
    }
    @Override public RecipeSerializer<?> getSerializer() {
        return FRRecipes.ARROW_FLETCHING_SERIALIZER.get();
    }

    /** Returns the arrow tex for this recipe. */
    @Nullable public ResourceLocation getArrowTex() { return arrow_texture; }

    public Ingredient getHead() { return headIngredient; }
    public Ingredient getStick() { return stickIngredient; }
    public Ingredient getFeather() { return featherIngredient; }
    public ItemStack getOutput() { return output; }

    // Used in recipe factory creation via datagen
    public interface RecipeFactory<T extends ArrowFletchingRecipe>
    {
        T create(
                Ingredient headIngredient,
                Ingredient stickIngredient,
                Ingredient featherIngredient,
                ItemStack itemStack,
                int outputCount,
                ResourceLocation arrowTex
        );
    }

    public static class Serializer implements RecipeSerializer<ArrowFletchingRecipe>
    {
        private final ArrowFletchingRecipe.RecipeFactory<ArrowFletchingRecipe> recipeFactory;
        public final MapCodec<ArrowFletchingRecipe> CODEC;
        public final StreamCodec<RegistryFriendlyByteBuf, ArrowFletchingRecipe> PACKET_CODEC;

        public ArrowFletchingRecipe create(
                Ingredient headIngredient,
                Ingredient stickIngredient,
                Ingredient featherIngredient,
                ItemStack output,
                int outputCount,
                ResourceLocation arrowTex
        )
        {
            return this.recipeFactory.create(headIngredient, stickIngredient, featherIngredient, output, outputCount, arrowTex);
        }

        public Serializer(ArrowFletchingRecipe.RecipeFactory<ArrowFletchingRecipe> recipeFactory)
        {
            this.CODEC = RecordCodecBuilder.mapCodec((instance) ->
                    instance.group(
                                    Ingredient.CODEC_NONEMPTY.fieldOf("head_ingredient")
                                            .forGetter((recipe) -> recipe.headIngredient),
                                    Ingredient.CODEC_NONEMPTY.fieldOf("stick_ingredient")
                                            .forGetter((recipe) -> recipe.stickIngredient),
                                    Ingredient.CODEC_NONEMPTY.fieldOf("feather_ingredient")
                                            .forGetter((recipe) -> recipe.featherIngredient),
                                    ItemStack.STRICT_SINGLE_ITEM_CODEC.fieldOf("output")
                                            .forGetter((recipe) -> recipe.output),
                                    ExtraCodecs.intRange(1, 99).fieldOf("output_count")
                                            .forGetter((recipe) -> recipe.output.getCount()),
                                    ResourceLocation.CODEC.fieldOf("arrow_texture")
                                            .forGetter((recipe) -> recipe.arrow_texture)
                            )
                            .apply(instance, recipeFactory::create));
            this.PACKET_CODEC = StreamCodec.of(this::write, this::read);
            this.recipeFactory = recipeFactory;
        }

        public ArrowFletchingRecipe read(RegistryFriendlyByteBuf buf)
        {
            Ingredient headIngredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            Ingredient stickIngredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            Ingredient featherIngredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
            int outputCount = ByteBufCodecs.INT.decode(buf);
            ResourceLocation arrowTex = ResourceLocation.STREAM_CODEC.decode(buf);

            return this.recipeFactory.create(headIngredient, stickIngredient, featherIngredient, output, outputCount, arrowTex);
        }

        public void write(RegistryFriendlyByteBuf buf, ArrowFletchingRecipe recipe)
        {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.headIngredient);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.stickIngredient);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.featherIngredient);
            ItemStack.STREAM_CODEC.encode(buf, recipe.output);
            ByteBufCodecs.INT.encode(buf, recipe.output.getCount());
            ResourceLocation.STREAM_CODEC.encode(buf, recipe.arrow_texture);
        }

        @Override
        public MapCodec<ArrowFletchingRecipe> codec() {
            return CODEC;
        }
        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ArrowFletchingRecipe> streamCodec() {
            return PACKET_CODEC;
        }
    }
}
