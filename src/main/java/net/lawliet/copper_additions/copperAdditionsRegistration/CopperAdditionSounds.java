package net.lawliet.copper_additions.copperAdditionsRegistration;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class CopperAdditionSounds {
    public static void init() {}

    public static final Holder<SoundEvent> ARMOR_EQUIP_COPPER;

    static {
        ARMOR_EQUIP_COPPER = CopperAdditions.SOUND_EVENT.register("armor_equip_copper",() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, "item.armor.equip_copper")));
    }
}
