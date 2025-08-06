package net.lawliet.copper_additions.datagen.lootTables;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Blocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;


public class LootTableGenerator extends BlockLootSubProvider {
    public LootTableGenerator(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, provider);
    }

    @Override
    protected void generate() {
        this.dropSelf(Blocks.COPPER_TORCH.get());
        this.dropSelf(Blocks.COPPER_BARS.get());
        this.dropSelf(Blocks.EXPOSED_COPPER_BARS.get());
        this.dropSelf(Blocks.WEATHERED_COPPER_BARS.get());
        this.dropSelf(Blocks.OXIDIZED_COPPER_BARS.get());
        this.dropSelf(Blocks.COPPER_CHAIN.get());
        this.dropSelf(Blocks.EXPOSED_COPPER_CHAIN.get());
        this.dropSelf(Blocks.WEATHERED_COPPER_CHAIN.get());
        this.dropSelf(Blocks.OXIDIZED_COPPER_CHAIN.get());
        this.dropSelf(Blocks.COPPER_LANTERN.get());
        this.dropSelf(Blocks.EXPOSED_COPPER_LANTERN.get());
        this.dropSelf(Blocks.WEATHERED_COPPER_LANTERN.get());
        this.dropSelf(Blocks.OXIDIZED_COPPER_LANTERN.get());
        this.dropSelf(Blocks.WAXED_COPPER_BARS.get());
        this.dropSelf(Blocks.WAXED_EXPOSED_COPPER_BARS.get());
        this.dropSelf(Blocks.WAXED_WEATHERED_COPPER_BARS.get());
        this.dropSelf(Blocks.WAXED_OXIDIZED_COPPER_BARS.get());
        this.dropSelf(Blocks.WAXED_COPPER_CHAIN.get());
        this.dropSelf(Blocks.WAXED_EXPOSED_COPPER_CHAIN.get());
        this.dropSelf(Blocks.WAXED_WEATHERED_COPPER_CHAIN.get());
        this.dropSelf(Blocks.WAXED_OXIDIZED_COPPER_CHAIN.get());
        this.dropSelf(Blocks.WAXED_COPPER_LANTERN.get());
        this.dropSelf(Blocks.WAXED_EXPOSED_COPPER_LANTERN.get());
        this.dropSelf(Blocks.WAXED_WEATHERED_COPPER_LANTERN.get());
        this.dropSelf(Blocks.WAXED_OXIDIZED_COPPER_LANTERN.get());

    }

    @NotNull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return CopperAdditions.BLOCKS.getEntries().stream().map(e -> (Block) e.value()).toList();
    }
}
