package net.lawliet.copper_additions.datagen.sounds;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.CopperAdditionSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class SoundGenerator extends SoundDefinitionsProvider {
    public SoundGenerator(PackOutput output) {
        super(output, CopperAdditions.MODID);
    }

    @Override
    public void registerSounds() {
        add(CopperAdditionSounds.ARMOR_EQUIP_COPPER::value, SoundDefinition.definition()
                .with(
                        sound(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID,"item/armor/equip_copper1")),
                        sound(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID,"item/armor/equip_copper2")),
                        sound(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID,"item/armor/equip_copper3")),
                        sound(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID,"item/armor/equip_copper4")),
                        sound(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID,"item/armor/equip_copper5")),
                        sound(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID,"item/armor/equip_copper6"))
                )
                        .subtitle("subtitles.item.armor.equip_copper")
                );
    }
}
