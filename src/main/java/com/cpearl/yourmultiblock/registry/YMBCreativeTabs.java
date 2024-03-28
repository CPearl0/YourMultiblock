package com.cpearl.yourmultiblock.registry;

import com.cpearl.yourmultiblock.YourMultiblock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class YMBCreativeTabs {
    public static final CreativeModeTab YMBCREATIVETAB = new CreativeModeTab(YourMultiblock.MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(YMBItems.CONTROLLER.get());
        }
    };
}
