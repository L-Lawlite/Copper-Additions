package net.lawliet.copper_additions.datagen.tags;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CopperAdditionsTags {

    public static class Blocks {
        private static TagKey<Block> copperAdditionsTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, name));
        }

        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = copperAdditionsTag("incorrect_for_copper_tool");
        public static final TagKey<Block> COPPER = copperAdditionsTag("copper");
    }
    
    public static class Items {
        public static final TagKey<Item> COPPER_TOOL_MATERIALS = copperAdditionsTag("copper_tool_materials");
        public static final TagKey<Item> REPAIRS_COPPER_ARMOR = copperAdditionsTag("repairs_copper_armor");
        public static final TagKey<Item> COPPER = copperAdditionsTag("copper");

        private static TagKey<Item> copperAdditionsTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, name));
        }
    }

    public static class EntityTypes {
        private static TagKey<EntityType<?>> copperAdditionsTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE,ResourceLocation.fromNamespaceAndPath(CopperAdditions.MODID, name));
        }
        public static final TagKey<EntityType<?>> CAN_SPAWN_WITH_COPPER_ARMOR = copperAdditionsTag("can_spawn_with_copper_armor");
    }




}
