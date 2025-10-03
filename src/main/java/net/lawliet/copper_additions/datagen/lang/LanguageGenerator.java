package net.lawliet.copper_additions.datagen.lang;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Blocks;
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
        this.addItem(Items.COPPER_TORCH, "Copper Torch");

        this.addBlock(Blocks.COPPER_BARS, "Copper Bars");
        this.addBlock(Blocks.COPPER_CHAIN, "Copper Chain");
        this.addBlock(Blocks.COPPER_LANTERN, "Copper Lantern");
        this.addBlock(Blocks.COPPER_TORCH, "Copper Torch");
        this.addBlock(Blocks.EXPOSED_COPPER_BARS, "Exposed Copper Bars");
        this.addBlock(Blocks.EXPOSED_COPPER_CHAIN, "Exposed Copper Chain");
        this.addBlock(Blocks.EXPOSED_COPPER_LANTERN, "Exposed Copper Lantern");
        this.addBlock(Blocks.OXIDIZED_COPPER_BARS, "Oxidized Copper Bars");
        this.addBlock(Blocks.OXIDIZED_COPPER_CHAIN, "Oxidized Copper Chain");
        this.addBlock(Blocks.OXIDIZED_COPPER_LANTERN, "Oxidized Copper Lantern");
        this.addBlock(Blocks.WAXED_COPPER_BARS, "Waxed Copper Bars");
        this.addBlock(Blocks.WAXED_COPPER_CHAIN, "Waxed Copper Chain");
        this.addBlock(Blocks.WAXED_COPPER_LANTERN, "Waxed Copper Lantern");
        this.addBlock(Blocks.WAXED_EXPOSED_COPPER_BARS, "Waxed Exposed Copper Bars");
        this.addBlock(Blocks.WAXED_EXPOSED_COPPER_CHAIN, "Waxed Exposed Copper Chain");
        this.addBlock(Blocks.WAXED_EXPOSED_COPPER_LANTERN, "Waxed Exposed Copper Lantern");
        this.addBlock(Blocks.WAXED_OXIDIZED_COPPER_BARS, "Waxed Oxidized Copper Bars");
        this.addBlock(Blocks.WAXED_OXIDIZED_COPPER_CHAIN, "Waxed Oxidized Copper Chain");
        this.addBlock(Blocks.WAXED_OXIDIZED_COPPER_LANTERN, "Waxed Oxidized Copper Lantern");
        this.addBlock(Blocks.WAXED_WEATHERED_COPPER_BARS, "Waxed Weathered Copper Bars");
        this.addBlock(Blocks.WAXED_WEATHERED_COPPER_CHAIN, "Waxed Weathered Copper Chain");
        this.addBlock(Blocks.WAXED_WEATHERED_COPPER_LANTERN, "Waxed Weathered Copper Lantern");
        this.addBlock(Blocks.WEATHERED_COPPER_BARS, "Weathered Copper Bars");
        this.addBlock(Blocks.WEATHERED_COPPER_CHAIN, "Weathered Copper Chain");
        this.addBlock(Blocks.WEATHERED_COPPER_LANTERN, "Weathered Copper Lantern");
        this.addBlock(() -> net.minecraft.world.level.block.Blocks.CHAIN, "Iron Chain");

        this.add("subtitles.item.armor.equip_copper", "Copper armor clonks");
        this.add("itemGroup.copper_additions","Copper Additions");
    }
}
