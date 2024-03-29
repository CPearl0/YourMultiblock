package com.cpearl.yourmultiblock.recipe.content;

import com.google.gson.JsonElement;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;

public class RecipeOutputItems {
    public final ItemStack itemStack;
    public final float probability;

    public RecipeOutputItems(ItemStack itemStack, float probability) {
        this.itemStack = itemStack;
        this.probability = probability;
    }

    public RecipeOutputItems(ItemStack itemStack) {
        this(itemStack, 1.0f);
    }

    public static RecipeOutputItems fromJson(JsonElement element) {
        var jsonObject = element.getAsJsonObject();
        float probability = 1.0f;
        if (jsonObject.has("probability"))
            probability = jsonObject.get("probability").getAsFloat();
        return new RecipeOutputItems(CraftingHelper.getItemStack(
                jsonObject.get("item").getAsJsonObject(), true), probability);
    }
}
