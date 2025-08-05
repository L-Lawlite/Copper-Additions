package net.lawliet.copper_additions.datagen.lang;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LanguageGenerator extends LanguageProvider {
    public LanguageGenerator(PackOutput packOutput, String lang) {
        super(packOutput, CopperAdditions.MODID, lang);
    }

    @Override
    protected void addTranslations() {
        this.addItem(Items.COPPER_NUGGET,"Copper Nugget");
        this.addItem(Items.COPPER_SWORD, "Copper Sword");
        this.addItem(Items.COPPER_SHOVEL, "Copper Shovel");
        this.addItem(Items.COPPER_PICKAXE, "Copper Pickaxe");
        this.addItem(Items.COPPER_AXE, "Copper Axe");
        this.addItem(Items.COPPER_HOE, "Copper Hoe");
        this.addItem(Items.COPPER_HELMET, "Copper Helmet");
        this.addItem(Items.COPPER_CHESTPLATE, "Copper Chestplate");
        this.addItem(Items.COPPER_LEGGINGS, "Copper Leggings");
        this.addItem(Items.COPPER_BOOTS, "Copper Boots");
        this.addItem(Items.COPPER_HORSE_ARMOR, "Copper Horse Armor");

    }
}
