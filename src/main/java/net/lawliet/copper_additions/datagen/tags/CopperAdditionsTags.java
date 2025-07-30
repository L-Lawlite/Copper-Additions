package net.lawliet.copper_additions.datagen.tags;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CopperAdditionsTags {
    public static class Blocks {
        private static TagKey<Block> copperAdditionsTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, name));
        }

        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = copperAdditionsTag("incorrect_for_copper_tool");
    }
    
    public static class Items {
        public static final TagKey<Item> COPPER_TOOL_MATERIALS = copperAdditionsTag("copper_tool_materials");
        public static final TagKey<Item> REPAIRS_COPPER_ARMOR = copperAdditionsTag("repairs_copper_armor");

        private static TagKey<Item> copperAdditionsTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, name));
        }
    }




}
