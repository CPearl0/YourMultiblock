package com.cpearl.yourmultiblock.registry;

import com.cpearl.yourmultiblock.YourMultiblock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class YMBItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, YourMultiblock.MODID);

    public static final RegistryObject<Item> CONTROLLER = ITEMS.register("controller", () -> new BlockItem(YMBBlocks.CONTROLLER.get(), new Item.Properties().tab(YMBCreativeTabs.YMBCREATIVETAB)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
