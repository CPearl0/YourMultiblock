package com.cpearl.yourmultiblock.registry;

import com.cpearl.yourmultiblock.YourMultiblock;
import com.cpearl.yourmultiblock.block.ControllerBlockEntity;
import com.cpearl.yourmultiblock.recipe.MultiblockRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class YMBRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, YourMultiblock.MODID);

    public static final RegistryObject<RecipeSerializer<?>> MULTIBLOCK = RECIPE_SERIALIZERS.register("multiblock", () -> MultiblockRecipeSerializer.SERIALIZER);

    public static void register(IEventBus bus) {
        RECIPE_SERIALIZERS.register(bus);
    }
}
