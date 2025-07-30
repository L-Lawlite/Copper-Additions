package net.lawliet.copper_additions.datagen.lang;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LanguageGenerator extends LanguageProvider {
    public LanguageGenerator(PackOutput packOutput, String lang) {
        super(packOutput, CopperAdditions.MODID, lang);
    }

    @Override
    protected void addTranslations() {

    }
}
