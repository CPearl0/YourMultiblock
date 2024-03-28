package com.cpearl.yourmultiblock;

import com.cpearl.yourmultiblock.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(YourMultiblock.MODID)
public class YourMultiblock
{
    public static final String MODID = "yourmultiblock";
    public static final Logger LOGGER = LogUtils.getLogger();

    public YourMultiblock() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // register registries
        YMBBlocks.register(modEventBus);
        YMBItems.register(modEventBus);
        YMBBlockEntityTypes.register(modEventBus);
        YMBRecipeTypes.register(modEventBus);
        YMBRecipeSerializer.register(modEventBus);
    }
}
