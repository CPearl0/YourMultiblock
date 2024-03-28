package com.cpearl.yourmultiblock.registry;

import com.cpearl.yourmultiblock.YourMultiblock;
import com.cpearl.yourmultiblock.block.ControllerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class YMBBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, YourMultiblock.MODID);

    public static final RegistryObject<Block> CONTROLLER = BLOCKS.register("controller", ControllerBlock::new);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
