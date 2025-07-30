package net.lawliet.copper_additions.datagen.lootTables;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class CopperAdditionsGlobalLootModifier extends GlobalLootModifierProvider {
    public CopperAdditionsGlobalLootModifier(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, CopperAdditions.MODID);
    }

    @Override
    protected void start() {
//        this.add(
//                "copper_horse_armor_in_simple_dungeon",
//
//        );
    }
}
