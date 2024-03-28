package com.cpearl.yourmultiblock.data.language;

import com.cpearl.yourmultiblock.YourMultiblock;
import com.cpearl.yourmultiblock.registry.YMBBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ChineseProvider extends LanguageProvider {
    public ChineseProvider(DataGenerator gen) {
        super(gen, YourMultiblock.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(YMBBlocks.CONTROLLER.get(), "控制器");

        add("itemGroup." + YourMultiblock.MODID, "你的多方块");
    }
}
