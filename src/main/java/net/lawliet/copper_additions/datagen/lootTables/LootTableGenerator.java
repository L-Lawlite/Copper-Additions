package net.lawliet.copper_additions.datagen.lootTables;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

import java.util.Set;

public class LootTableGenerator extends BlockLootSubProvider {
    public LootTableGenerator(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, provider);
    }

    @Override
    protected void generate() {

    }
}
