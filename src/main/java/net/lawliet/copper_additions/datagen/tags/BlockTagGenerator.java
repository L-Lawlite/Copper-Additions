package net.lawliet.copper_additions.datagen.tags;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider{

    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, CopperAdditions.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
