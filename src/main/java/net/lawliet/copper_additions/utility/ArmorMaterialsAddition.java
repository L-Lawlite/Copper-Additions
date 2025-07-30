package net.lawliet.copper_additions.utility;

import com.google.common.collect.Maps;
import net.lawliet.copper_additions.datagen.tags.CopperAdditionsTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class ArmorMaterialsAddition {

    public static ArmorMaterial LEATHER = new ArmorMaterial(
            5, makeDefense(1, 2, 3, 1, 3), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAssets.LEATHER
    );
    public static ArmorMaterial COPPER = new ArmorMaterial(
            11, makeDefense(1, 3, 4, 2, 4), 8, net.lawliet.copper_additions.copperAdditionsRegistration.SoundEvents.ARMOR_EQUIP_COPPER, 0.0F, 0.0F, CopperAdditionsTags.Items.REPAIRS_COPPER_ARMOR, EquipmentAssetsPath.COPPER
    );
    public static ArmorMaterial CHAINMAIL = new ArmorMaterial(
            15, makeDefense(1, 4, 5, 2, 4), 12, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, ItemTags.REPAIRS_CHAIN_ARMOR, EquipmentAssets.CHAINMAIL
    );
    public static ArmorMaterial IRON = new ArmorMaterial(
            15, makeDefense(2, 5, 6, 2, 5), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, EquipmentAssets.IRON
    );
    public static ArmorMaterial GOLD = new ArmorMaterial(
            7, makeDefense(1, 3, 5, 2, 7), 25, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, ItemTags.REPAIRS_GOLD_ARMOR, EquipmentAssets.GOLD
    );
    public static ArmorMaterial DIAMOND = new ArmorMaterial(
            33, makeDefense(3, 6, 8, 3, 11), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, ItemTags.REPAIRS_DIAMOND_ARMOR, EquipmentAssets.DIAMOND
    );
    public static ArmorMaterial TURTLE_SCUTE = new ArmorMaterial(
            25, makeDefense(2, 5, 6, 2, 5), 9, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, 0.0F, ItemTags.REPAIRS_TURTLE_HELMET, EquipmentAssets.TURTLE_SCUTE
    );
    public static ArmorMaterial NETHERITE = new ArmorMaterial(
            37, makeDefense(3, 6, 8, 3, 11), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, ItemTags.REPAIRS_NETHERITE_ARMOR, EquipmentAssets.NETHERITE
    );
    public static ArmorMaterial ARMADILLO_SCUTE = new ArmorMaterial(
            4, makeDefense(3, 6, 8, 3, 11), 10, SoundEvents.ARMOR_EQUIP_WOLF, 0.0F, 0.0F, ItemTags.REPAIRS_WOLF_ARMOR, EquipmentAssets.ARMADILLO_SCUTE
    );

    private static Map<ArmorType, Integer> makeDefense(int bootDefense, int leggingDefense, int chestDefense, int helmetDefense, int bodyDefense) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, bootDefense, ArmorType.LEGGINGS, leggingDefense, ArmorType.CHESTPLATE, chestDefense, ArmorType.HELMET, helmetDefense, ArmorType.BODY, bodyDefense));
    }
}
