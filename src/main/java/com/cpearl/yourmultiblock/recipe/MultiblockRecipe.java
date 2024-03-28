package com.cpearl.yourmultiblock.recipe;

import com.cpearl.yourmultiblock.YourMultiblock;
import com.cpearl.yourmultiblock.registry.YMBRecipeTypes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MultiblockRecipe implements Recipe<Container> {
    public static final RecipeType<MultiblockRecipe> TEST_TYPE = YMBRecipeTypes.MULTIBLOCK.get();
    public static final MultiblockRecipe TEST1 = new MultiblockRecipe(
            new ResourceLocation(YourMultiblock.MODID, "test1"),
            TEST_TYPE,
            List.of(Ingredient.of(new ItemStack(Items.APPLE))),
            new ArrayList<>(),
            List.of(new ItemStack(Items.DIAMOND)),
            new ArrayList<>(),
            40
    );

    public final ResourceLocation id;

    public final RecipeType<? extends MultiblockRecipe> recipeType;

    public final List<Ingredient> recipeIngredients;
    public final List<FluidStack> recipeFluids;
    public final List<ItemStack> resultItems;
    public final List<FluidStack> resultFluids;

    public final int processTime;

    public MultiblockRecipe(ResourceLocation id, RecipeType<? extends MultiblockRecipe> recipeType, List<Ingredient> recipeIngredients, List<FluidStack> recipeFluids, List<ItemStack> resultItems, List<FluidStack> resultFluids, int processTime) {
        this.id = id;
        this.recipeType = recipeType;
        this.recipeIngredients = recipeIngredients;
        this.recipeFluids = recipeFluids;
        this.resultItems = resultItems;
        this.resultFluids = resultFluids;
        this.processTime = processTime;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(Container container) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int gridWidth, int gridHeight) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MultiblockRecipeSerializer.SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return recipeType;
    }
}
