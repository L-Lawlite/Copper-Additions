package net.lawliet.copper_additions.copperAdditionsRegistration;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CreativeModeTabs {
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COPPER_ADDITIONS_TAB;

    static {
        COPPER_ADDITIONS_TAB = CopperAdditions.CREATIVE_MODE_TABS.register("chrono_circuits_tab", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.copper_additions"))
                .icon(net.minecraft.world.item.Items.COPPER_INGOT::getDefaultInstance)
                .displayItems(CreativeModeTabs::addCreative)
                .build()
        );
    }

    public static void init() {}

    public static void addCreative(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        Items.addCreative(parameters, output);
    }
}
