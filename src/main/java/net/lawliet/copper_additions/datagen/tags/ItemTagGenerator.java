package net.lawliet.copper_additions.datagen.tags;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {


    public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, CopperAdditions.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS).addTag(Tags.Items.INGOTS_COPPER);
        this.tag(CopperAdditionsTags.Items.REPAIRS_COPPER_ARMOR).addTag(Tags.Items.INGOTS_COPPER);
        this.tag(ItemTags.PICKAXES).add(Items.COPPER_PICKAXE.asItem());
        this.tag(ItemTags.AXES).add(Items.COPPER_AXE.asItem());
        this.tag(ItemTags.HOES).add(Items.COPPER_HOE.asItem());
        this.tag(ItemTags.SHOVELS).add(Items.COPPER_SHOVEL.asItem());
        this.tag(ItemTags.SWORDS).add(Items.COPPER_SWORD.asItem());
        this.tag(ItemTags.HEAD_ARMOR).add(Items.COPPER_HELMET.asItem());
        this.tag(ItemTags.CHEST_ARMOR).add(Items.COPPER_CHESTPLATE.asItem());
        this.tag(ItemTags.LEG_ARMOR).add(Items.COPPER_LEGGINGS.asItem());
        this.tag(ItemTags.FOOT_ARMOR).add(Items.COPPER_BOOTS.asItem());
        this.copy(CopperAdditionsTags.Blocks.COPPER, CopperAdditionsTags.Items.COPPER);

    }
}
