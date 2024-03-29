package com.cpearl.yourmultiblock.recipe;

import com.cpearl.yourmultiblock.recipe.content.RecipeInputFluids;
import com.cpearl.yourmultiblock.recipe.content.RecipeInputIngredient;
import com.cpearl.yourmultiblock.recipe.content.RecipeOutputFluids;
import com.cpearl.yourmultiblock.recipe.content.RecipeOutputItems;
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
        List<RecipeInputIngredient> inputIngredients = new ArrayList<>();
        for (var ingredient: inputIngredientsArray)
            inputIngredients.add(RecipeInputIngredient.fromJson(ingredient));

        var inputFluidsArray = json.getAsJsonArray("inputFluids");
        List<RecipeInputFluids> inputFluids = new ArrayList<>();
        for (var fluid: inputFluidsArray)
            inputFluids.add(RecipeInputFluids.fromJson(fluid));

        var outputItemsArray = json.getAsJsonArray("outputItems");
        List<RecipeOutputItems> outputItems = new ArrayList<>();
        for (var item: outputItemsArray)
            outputItems.add(RecipeOutputItems.fromJson(item));

        var outputFluidsArray = json.getAsJsonArray("outputFluids");
        List<RecipeOutputFluids> outputFluids = new ArrayList<>();
        for (var fluid: outputFluidsArray)
            outputFluids.add(RecipeOutputFluids.fromJson(fluid));

        return new MultiblockRecipe(id, MultiblockRecipe.TEST_TYPE, inputIngredients, inputFluids, outputItems, outputFluids, 20);
    }

    @Override
    public @Nullable MultiblockRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        return null;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, MultiblockRecipe recipe) {

    }
}
