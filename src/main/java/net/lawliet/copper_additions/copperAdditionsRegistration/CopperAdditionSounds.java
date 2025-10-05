package net.lawliet.copper_additions.copperAdditionsRegistration;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class CopperAdditionSounds {

    public static void init() {}

    public static final Holder<SoundEvent> ARMOR_EQUIP_COPPER;
    public static final Holder<SoundEvent> COPPER_CHEST_OPEN;
    public static final Holder<SoundEvent> COPPER_CHEST_CLOSE;


    static {
        ARMOR_EQUIP_COPPER = CopperAdditions.SOUND_EVENT.register("armor_equip_copper",makeDefaultSoundEvent("item.armor.equip_copper"));
        COPPER_CHEST_OPEN = CopperAdditions.SOUND_EVENT.register("copper_chest_open", makeDefaultSoundEvent("block.copper_chest.open"));
        COPPER_CHEST_CLOSE = CopperAdditions.SOUND_EVENT.register("copper_chest_close", makeDefaultSoundEvent("block.copper_chest.close"));
    }

    private static Supplier<SoundEvent> makeDefaultSoundEvent(String path) {
        return () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, path));
    }
}
