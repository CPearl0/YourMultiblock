package com.cpearl.yourmultiblock.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@ParametersAreNonnullByDefault
public class MultiblockRecipeSerializer implements RecipeSerializer<MultiblockRecipe> {
    public static final MultiblockRecipeSerializer SERIALIZER = new MultiblockRecipeSerializer();

    @Override
    public @NotNull MultiblockRecipe fromJson(ResourceLocation id, JsonObject json) {
        var inputIngredientsArray = json.getAsJsonArray("inputIngredients");
        List<Ingredient> inputIngredients = new ArrayList<>();
        for (var ingredient: inputIngredientsArray)
            inputIngredients.add(CraftingHelper.getIngredient(ingredient));

        var inputFluidsArray = json.getAsJsonArray("inputFluids");
        List<FluidStack> inputFluids = new ArrayList<>();
        for (var fluid: inputFluidsArray)
            inputFluids.add(FluidStack.loadFluidStackFromNBT(CraftingHelper.getNBT(fluid)));

        return null;
    }

    @Override
    public @Nullable MultiblockRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        return null;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, MultiblockRecipe recipe) {

    }
}
