package net.lawliet.copper_additions.copperAdditionsRegistration;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.utility.ArmorMaterialsAddition;
import net.lawliet.copper_additions.utility.ToolMaterialAddition;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;

public class Items {


    public static final DeferredItem<Item> COPPER_NUGGET;
    public static final DeferredItem<Item> COPPER_SWORD;
    public static final DeferredItem<Item> COPPER_SHOVEL;
    public static final DeferredItem<Item> COPPER_PICKAXE;
    public static final DeferredItem<Item> COPPER_AXE;
    public static final DeferredItem<Item> COPPER_HOE;

    public static final DeferredItem<Item> COPPER_HELMET;
    public static final DeferredItem<Item> COPPER_CHESTPLATE;
    public static final DeferredItem<Item> COPPER_LEGGINGS;
    public static final DeferredItem<Item> COPPER_BOOTS;
    public static final DeferredItem<Item> COPPER_HORSE_ARMOR;

    static {
        COPPER_NUGGET = CopperAdditions.ITEMS.registerSimpleItem("copper_nugget");
        COPPER_SWORD = CopperAdditions.ITEMS.registerItem("copper_sword",properties ->  new SwordItem(ToolMaterialAddition.COPPER, 3.0F, -2.4F, properties));
        COPPER_SHOVEL = CopperAdditions.ITEMS.registerItem("copper_shovel", properties -> new ShovelItem(ToolMaterialAddition.COPPER, 1.5F, -3.0F, properties));
        COPPER_PICKAXE = CopperAdditions.ITEMS.registerItem("copper_pickaxe",properties ->  new PickaxeItem(ToolMaterialAddition.COPPER, 1.0F, -2.8F, properties));
        COPPER_AXE = CopperAdditions.ITEMS.registerItem("copper_axe", properties -> new AxeItem(ToolMaterialAddition.COPPER, 7.0F, -3.2F, properties));
        COPPER_HOE = CopperAdditions.ITEMS.registerItem("copper_hoe", properties -> new HoeItem(ToolMaterialAddition.COPPER, -1.0F, -2.0F, properties));

        COPPER_HELMET = CopperAdditions.ITEMS.registerItem("copper_helmet", properties -> new ArmorItem(ArmorMaterialsAddition.COPPER, ArmorType.HELMET, properties));
        COPPER_CHESTPLATE = CopperAdditions.ITEMS.registerItem("copper_chestplate", properties -> new ArmorItem(ArmorMaterialsAddition.COPPER, ArmorType.CHESTPLATE, properties));
        COPPER_LEGGINGS = CopperAdditions.ITEMS.registerItem("copper_leggings", properties -> new ArmorItem(ArmorMaterialsAddition.COPPER, ArmorType.LEGGINGS, properties));
        COPPER_BOOTS = CopperAdditions.ITEMS.registerItem("copper_boots", properties -> new ArmorItem(ArmorMaterialsAddition.COPPER, ArmorType.BOOTS, properties));
        COPPER_HORSE_ARMOR = CopperAdditions.ITEMS.registerItem("copper_horse_armor", properties -> new AnimalArmorItem(ArmorMaterialsAddition.COPPER, AnimalArmorItem.BodyType.EQUESTRIAN, SoundEvents.HORSE_ARMOR, false, properties), new Item.Properties().stacksTo(1));
    }

    public static void init() {
        ToolMaterialAddition.init();
    }

    @SuppressWarnings("unused")
    public static void addCreative(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        CopperAdditions.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
    }
}
