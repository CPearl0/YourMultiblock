package com.cpearl.yourmultiblock.recipe.content;

import net.minecraft.world.item.ItemStack;

public class RecipeOutputItems {
    public final ItemStack itemStack;
    public final float possibility;

    public RecipeOutputItems(ItemStack itemStack, float possibility) {
        this.itemStack = itemStack;
        this.possibility = possibility;
    }
}
