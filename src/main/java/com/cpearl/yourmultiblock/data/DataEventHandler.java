package com.cpearl.yourmultiblock.data;

import com.cpearl.yourmultiblock.YourMultiblock;
import com.cpearl.yourmultiblock.data.language.ChineseProvider;
import com.cpearl.yourmultiblock.data.language.EnglishProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = YourMultiblock.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataEventHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new EnglishProvider(generator));
        generator.addProvider(event.includeClient(), new ChineseProvider(generator));
    }
}
