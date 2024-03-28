package com.cpearl.yourmultiblock.recipe.content;

import net.minecraft.world.item.crafting.Ingredient;

public class RecipeInputIngredient {
    public final Ingredient ingredient;
    public final float possibility;

    public RecipeInputIngredient(Ingredient ingredient, float possibility) {
        this.ingredient = ingredient;
        this.possibility = possibility;
    }
}
