package net.artyrian.frontiers.recipe.fletching;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.recipe.ModRecipes;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ArrowFletchingRecipe implements Recipe<RecipeInput>
{
    private final Identifier id;
    private final Identifier arrow_texture;
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
            Identifier arrowTex)
    {
        this.id = Identifier.of(Frontiers.MOD_ID, "arrow_fletching");
        this.headIngredient = headIngredient;
        this.stickIngredient = stickIngredient; // Ingredient.ofItems(Items.STICK)
        this.featherIngredient = featherIngredient; // Ingredient.ofItems(Items.FEATHER)
        this.output = new ItemStack(itemStack.getItem(), outputCount);
        this.arrow_texture = arrowTex;
    }

    /** <b>Similar to the recommended factory, but takes an input count as well.</b>
     * <p>Takes an arrowhead ingredient, an output stack + count, and arrow texture. This recipe will make 6 of the requested item.</p>*/
    public ArrowFletchingRecipe(Ingredient ingredient, ItemStack itemStack, int count, Identifier arrow_texture)
    {
        this(
                ingredient,
                Ingredient.ofItems(Items.STICK),
                Ingredient.ofItems(Items.FEATHER),
                itemStack,
                count,
                arrow_texture
        );
    }

    @Override
    public boolean matches(RecipeInput input, World world)
    {
        return (input instanceof ArrowFletchingRecipeInput input2 && this.matches(input2, world));
    }

    public boolean matches(ArrowFletchingRecipeInput recipe, World world)
    {
        return (
                this.headIngredient.test(recipe.head()) &&
                this.stickIngredient.test(recipe.stick()) &&
                this.featherIngredient.test(recipe.feather()
                )
        );
    }

    @Override
    public ItemStack craft(RecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height)
    {
        return width >= 1 && height >= 3;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output.copy();
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.FLETCHING_TABLE);
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ARROW_FLETCHING;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ARROW_FLETCHING_SERIALIZER;
    }

    /** Returns the arrow tex for this recipe. */
    @Nullable
    public Identifier getArrowTex() { return arrow_texture; }

    // Used in recipe factory creation via datagen
    public interface RecipeFactory<T extends ArrowFletchingRecipe>
    {
        T create(
                Ingredient headIngredient,
                Ingredient stickIngredient,
                Ingredient featherIngredient,
                ItemStack itemStack,
                int outputCount,
                Identifier arrowTex
        );
    }

    public static class Serializer implements RecipeSerializer<ArrowFletchingRecipe>
    {
        private final ArrowFletchingRecipe.RecipeFactory<ArrowFletchingRecipe> recipeFactory;
        public final MapCodec<ArrowFletchingRecipe> CODEC;
        public final PacketCodec<RegistryByteBuf, ArrowFletchingRecipe> PACKET_CODEC;

        public ArrowFletchingRecipe create(
                Ingredient headIngredient,
                Ingredient stickIngredient,
                Ingredient featherIngredient,
                ItemStack output,
                int outputCount,
                Identifier arrowTex
        )
        {
            return this.recipeFactory.create(headIngredient, stickIngredient, featherIngredient, output, outputCount, arrowTex);
        }

        public Serializer(ArrowFletchingRecipe.RecipeFactory<ArrowFletchingRecipe> recipeFactory)
        {
            this.CODEC = RecordCodecBuilder.mapCodec((instance) ->
                    instance.group(
                                    Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("head_ingredient")
                                            .forGetter((recipe) -> recipe.headIngredient),
                                    Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("stick_ingredient")
                                            .forGetter((recipe) -> recipe.stickIngredient),
                                    Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("feather_ingredient")
                                            .forGetter((recipe) -> recipe.featherIngredient),
                                    ItemStack.VALIDATED_UNCOUNTED_CODEC.fieldOf("output")
                                            .forGetter((recipe) -> recipe.output),
                                    Codecs.rangedInt(1, 99).fieldOf("output_count")
                                            .forGetter((recipe) -> recipe.output.getCount()),
                                    Identifier.CODEC.fieldOf("arrow_texture")
                                            .forGetter((recipe) -> recipe.arrow_texture)
                            )
                            .apply(instance, recipeFactory::create));
            this.PACKET_CODEC = PacketCodec.ofStatic(this::write, this::read);
            this.recipeFactory = recipeFactory;
        }

        public ArrowFletchingRecipe read(RegistryByteBuf buf)
        {
            Ingredient headIngredient = Ingredient.PACKET_CODEC.decode(buf);
            Ingredient stickIngredient = Ingredient.PACKET_CODEC.decode(buf);
            Ingredient featherIngredient = Ingredient.PACKET_CODEC.decode(buf);
            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            int outputCount = PacketCodecs.INTEGER.decode(buf);
            Identifier arrowTex = Identifier.PACKET_CODEC.decode(buf);

            return this.recipeFactory.create(headIngredient, stickIngredient, featherIngredient, output, outputCount, arrowTex);
        }

        public void write(RegistryByteBuf buf, ArrowFletchingRecipe recipe)
        {
            Ingredient.PACKET_CODEC.encode(buf, recipe.headIngredient);
            Ingredient.PACKET_CODEC.encode(buf, recipe.stickIngredient);
            Ingredient.PACKET_CODEC.encode(buf, recipe.featherIngredient);
            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
            PacketCodecs.INTEGER.encode(buf, recipe.output.getCount());
            Identifier.PACKET_CODEC.encode(buf, recipe.arrow_texture);
        }

        @Override
        public MapCodec<ArrowFletchingRecipe> codec() {
            return CODEC;
        }
        @Override
        public PacketCodec<RegistryByteBuf, ArrowFletchingRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}
