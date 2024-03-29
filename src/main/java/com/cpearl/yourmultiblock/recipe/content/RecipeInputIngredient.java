package com.cpearl.yourmultiblock.recipe.content;

import com.google.gson.JsonElement;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;

public class RecipeInputIngredient {
    public final Ingredient ingredient;
    public final float probability;

    public RecipeInputIngredient(Ingredient ingredient, float probability) {
        this.ingredient = ingredient;
        this.probability = probability;
    }

    public RecipeInputIngredient(Ingredient ingredient) {
        this(ingredient, 1.0f);
    }

    public static RecipeInputIngredient fromJson(JsonElement element) {
        var jsonObject = element.getAsJsonObject();
        float probability = 1.0f;
        if (jsonObject.has("probability"))
            probability = jsonObject.get("probability").getAsFloat();
        return new RecipeInputIngredient(CraftingHelper.getIngredient(jsonObject.get("ingredient")), probability);
    }
}
