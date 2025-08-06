package net.lawliet.copper_additions.datagen.datamaps;

import net.lawliet.copper_additions.copperAdditionsRegistration.Blocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Oxidizable;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;

import java.util.concurrent.CompletableFuture;

public class DataMapGenerator extends DataMapProvider {
    public DataMapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.OXIDIZABLES)
                .add(Blocks.COPPER_BARS.getDelegate(), new Oxidizable(Blocks.EXPOSED_COPPER_BARS.get()), false)
                .add(Blocks.EXPOSED_COPPER_BARS.getDelegate(), new Oxidizable(Blocks.WEATHERED_COPPER_BARS.get()), false)
                .add(Blocks.WEATHERED_COPPER_BARS.getDelegate(), new Oxidizable(Blocks.OXIDIZED_COPPER_BARS.get()), false)
                .add(Blocks.COPPER_CHAIN.getDelegate(), new Oxidizable(Blocks.EXPOSED_COPPER_CHAIN.get()), false)
                .add(Blocks.EXPOSED_COPPER_CHAIN.getDelegate(), new Oxidizable(Blocks.WEATHERED_COPPER_CHAIN.get()), false)
                .add(Blocks.WEATHERED_COPPER_CHAIN.getDelegate(), new Oxidizable(Blocks.OXIDIZED_COPPER_CHAIN.get()), false)
                .add(Blocks.COPPER_LANTERN.getDelegate(), new Oxidizable(Blocks.EXPOSED_COPPER_LANTERN.get()), false)
                .add(Blocks.EXPOSED_COPPER_LANTERN.getDelegate(), new Oxidizable(Blocks.WEATHERED_COPPER_LANTERN.get()), false)
                .add(Blocks.WEATHERED_COPPER_LANTERN.getDelegate(), new Oxidizable(Blocks.OXIDIZED_COPPER_LANTERN.get()), false);

        this.builder(NeoForgeDataMaps.WAXABLES)
                .add(Blocks.COPPER_BARS.getDelegate(), new Waxable(Blocks.WAXED_COPPER_BARS.get()), false)
                .add(Blocks.EXPOSED_COPPER_BARS.getDelegate(), new Waxable(Blocks.WAXED_EXPOSED_COPPER_BARS.get()), false)
                .add(Blocks.WEATHERED_COPPER_BARS.getDelegate(), new Waxable(Blocks.WAXED_WEATHERED_COPPER_BARS.get()), false)
                .add(Blocks.OXIDIZED_COPPER_BARS.getDelegate(), new Waxable(Blocks.WAXED_OXIDIZED_COPPER_BARS.get()), false)
                .add(Blocks.COPPER_CHAIN.getDelegate(), new Waxable(Blocks.WAXED_COPPER_CHAIN.get()), false)
                .add(Blocks.EXPOSED_COPPER_CHAIN.getDelegate(), new Waxable(Blocks.WAXED_EXPOSED_COPPER_CHAIN.get()), false)
                .add(Blocks.WEATHERED_COPPER_CHAIN.getDelegate(), new Waxable(Blocks.WAXED_WEATHERED_COPPER_CHAIN.get()), false)
                .add(Blocks.OXIDIZED_COPPER_CHAIN.getDelegate(), new Waxable(Blocks.WAXED_OXIDIZED_COPPER_CHAIN.get()), false)
                .add(Blocks.COPPER_LANTERN.getDelegate(), new Waxable(Blocks.WAXED_COPPER_LANTERN.get()), false)
                .add(Blocks.EXPOSED_COPPER_LANTERN.getDelegate(), new Waxable(Blocks.WAXED_EXPOSED_COPPER_LANTERN.get()), false)
                .add(Blocks.WEATHERED_COPPER_LANTERN.getDelegate(), new Waxable(Blocks.WAXED_WEATHERED_COPPER_LANTERN.get()), false)
                .add(Blocks.OXIDIZED_COPPER_LANTERN.getDelegate(), new Waxable(Blocks.WAXED_OXIDIZED_COPPER_LANTERN.get()), false);









    }
}
