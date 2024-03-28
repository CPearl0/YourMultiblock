package com.cpearl.yourmultiblock.recipe.content;

import net.minecraftforge.fluids.FluidStack;

public class RecipeInputFluids {
    public final FluidStack fluidStack;
    public final float possibility;

    public RecipeInputFluids(FluidStack fluidStack, float possibility) {
        this.fluidStack = fluidStack;
        this.possibility = possibility;
    }
}
