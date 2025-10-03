package net.lawliet.copper_additions.utility;

import com.google.common.collect.Maps;
import net.lawliet.copper_additions.copperAdditionsRegistration.CopperAdditionSounds;
import net.lawliet.copper_additions.datagen.tags.CopperAdditionsTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public class ArmorMaterialsAddition {

    public static ArmorMaterial COPPER = new ArmorMaterial(
            11, makeDefense(1, 3, 4, 2, 4), 8, CopperAdditionSounds.ARMOR_EQUIP_COPPER, 0.0F, 0.0F, CopperAdditionsTags.Items.REPAIRS_COPPER_ARMOR, EquipmentAssetsPath.COPPER
    );


    @SuppressWarnings("SameParameterValue")
    private static Map<ArmorType, Integer> makeDefense(int bootDefense, int leggingDefense, int chestDefense, int helmetDefense, int bodyDefense) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, bootDefense, ArmorType.LEGGINGS, leggingDefense, ArmorType.CHESTPLATE, chestDefense, ArmorType.HELMET, helmetDefense, ArmorType.BODY, bodyDefense));
    }
}
