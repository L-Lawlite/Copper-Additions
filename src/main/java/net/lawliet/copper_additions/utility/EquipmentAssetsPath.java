package net.lawliet.copper_additions.utility;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;

public interface EquipmentAssetsPath {
    ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, "equipment_asset"));

    ResourceKey<EquipmentAsset> COPPER = ResourceKey.create(ROOT_ID, ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, "copper"));

}
