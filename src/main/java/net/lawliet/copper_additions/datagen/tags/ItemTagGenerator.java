package net.lawliet.copper_additions.datagen.tags;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {


    public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, CopperAdditions.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
