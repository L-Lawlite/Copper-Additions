package net.lawliet.copper_additions.copperAdditionsRegistration;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class SoundEvents {
    public static void init() {}

    public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_COPPER;

    static {
        //ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, "item.armor.equip_copper")
        ARMOR_EQUIP_COPPER = CopperAdditions.SOUND_EVENT.register("armor_equip_copper",() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, "item.armor.equip_copper")));
    }
}
