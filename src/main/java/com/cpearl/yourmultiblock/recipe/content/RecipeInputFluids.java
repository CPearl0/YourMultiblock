package com.cpearl.yourmultiblock.recipe.content;

import com.google.gson.JsonElement;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;

public class RecipeInputFluids {
    public final FluidStack fluidStack;
    public final float probability;

    public RecipeInputFluids(FluidStack fluidStack, float probability) {
        this.fluidStack = fluidStack;
        this.probability = probability;
    }

    public RecipeInputFluids(FluidStack fluidStack) {
        this(fluidStack, 1.0f);
    }

    public static RecipeInputFluids fromJson(JsonElement element) {
        var jsonObject = element.getAsJsonObject();
        float probability = 1.0f;
        if (jsonObject.has("probability"))
            probability = jsonObject.get("probability").getAsFloat();
        return new RecipeInputFluids(FluidStack.loadFluidStackFromNBT(
                CraftingHelper.getNBT(jsonObject.get("fluid"))), probability);
    }
}
