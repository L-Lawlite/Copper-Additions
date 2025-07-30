package net.lawliet.copper_additions.datagen.model;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.utility.EquipmentAssetsPath;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.function.BiConsumer;

public class EquipmentRenderGenerator extends EquipmentAssetProvider {
    public EquipmentRenderGenerator(PackOutput output) {
        super(output);
    }

    @Override
    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        output.accept(EquipmentAssetsPath.COPPER, humanoidAndHorse(CopperAdditions.MODID +":copper"));
    }
}
