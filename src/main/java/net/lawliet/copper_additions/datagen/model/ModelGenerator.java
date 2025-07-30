package net.lawliet.copper_additions.datagen.model;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Items;
import net.lawliet.copper_additions.utility.EquipmentAssetsPath;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModelGenerator extends ModelProvider {
    public ModelGenerator(PackOutput output) {
        super(output, CopperAdditions.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(Items.COPPER_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(Items.COPPER_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(Items.COPPER_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(Items.COPPER_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(Items.COPPER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(Items.COPPER_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateTrimmableItem(Items.COPPER_HELMET.get(), EquipmentAssetsPath.COPPER, "helmet", false);
        itemModels.generateTrimmableItem(Items.COPPER_CHESTPLATE.get(), EquipmentAssetsPath.COPPER, "chestplate", false);
        itemModels.generateTrimmableItem(Items.COPPER_LEGGINGS.get(), EquipmentAssetsPath.COPPER, "leggings", false);
        itemModels.generateTrimmableItem(Items.COPPER_BOOTS.get(), EquipmentAssetsPath.COPPER, "boots", false);

    }

}
