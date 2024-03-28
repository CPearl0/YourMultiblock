package com.cpearl.yourmultiblock.registry;

import com.cpearl.yourmultiblock.YourMultiblock;
import com.cpearl.yourmultiblock.recipe.MultiblockRecipe;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class YMBRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, YourMultiblock.MODID);

    public static final RegistryObject<RecipeType<MultiblockRecipe>> MULTIBLOCK = RECIPE_TYPES.register("multiblock", () -> RecipeType.simple(YMBRecipeTypes.MULTIBLOCK.getId()));

    public static void register(IEventBus bus) {
        RECIPE_TYPES.register(bus);
    }
}
