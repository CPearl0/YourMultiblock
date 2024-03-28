package com.cpearl.yourmultiblock.data.language;

import com.cpearl.yourmultiblock.YourMultiblock;
import com.cpearl.yourmultiblock.registry.YMBBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class EnglishProvider extends LanguageProvider {
    public EnglishProvider(DataGenerator gen) {
        super(gen, YourMultiblock.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(YMBBlocks.CONTROLLER.get(), "Controller");

        add("itemGroup." + YourMultiblock.MODID, "Your Multiblock");
    }
}
