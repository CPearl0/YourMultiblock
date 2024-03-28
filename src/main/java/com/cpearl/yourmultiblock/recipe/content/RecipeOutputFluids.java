package com.cpearl.yourmultiblock.recipe.content;

import net.minecraftforge.fluids.FluidStack;

public class RecipeOutputFluids {
    public final FluidStack fluidStack;
    public final float possibility;

    public RecipeOutputFluids(FluidStack fluidStack, float possibility) {
        this.fluidStack = fluidStack;
        this.possibility = possibility;
    }
}
