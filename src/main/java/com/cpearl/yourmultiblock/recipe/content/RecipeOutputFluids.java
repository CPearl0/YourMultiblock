package com.cpearl.yourmultiblock.recipe.content;

import com.google.gson.JsonElement;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;

public class RecipeOutputFluids {
    public final FluidStack fluidStack;
    public final float probability;

    public RecipeOutputFluids(FluidStack fluidStack, float probability) {
        this.fluidStack = fluidStack;
        this.probability = probability;
    }

    public RecipeOutputFluids(FluidStack fluidStack) {
        this(fluidStack, 1.0f);
    }

    public static RecipeOutputFluids fromJson(JsonElement element) {
        var jsonObject = element.getAsJsonObject();
        float probability = 1.0f;
        if (jsonObject.has("probability"))
            probability = jsonObject.get("probability").getAsFloat();
        return new RecipeOutputFluids(FluidStack.loadFluidStackFromNBT(
                CraftingHelper.getNBT(jsonObject.get("fluid"))), probability);
    }
}
