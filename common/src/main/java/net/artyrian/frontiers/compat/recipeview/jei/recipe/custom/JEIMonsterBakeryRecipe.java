package net.artyrian.frontiers.compat.recipeview.jei.recipe.custom;

import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class JEIMonsterBakeryRecipe
{
    private final Ingredient item;
    private final Item egg;
    private final int percent;
    private final String entity_name;

    public <T extends LivingEntity> JEIMonsterBakeryRecipe(Item base, EntityType<T> entity, int percent)
    {
        this.item = Ingredient.of(base);
        this.egg = MonsterBakeryBlockEntity.getSpawnEggItem(entity);
        this.percent = percent;
        this.entity_name = entity.getDescriptionId();
    }

    public Ingredient getItem() { return this.item; }
    public Item getEgg() { return this.egg; }
    public int getPercent() { return this.percent; }
    public int getCookTime() { return MonsterBakeryBlockEntity.MAX_INCUBATE_TIME; }
    public String getEntityName() { return this.entity_name; }
}
