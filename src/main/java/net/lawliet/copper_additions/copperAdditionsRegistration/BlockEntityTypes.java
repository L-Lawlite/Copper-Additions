package net.lawliet.copper_additions.copperAdditionsRegistration;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.blockEntities.CopperChestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class BlockEntityTypes {
    public static final Supplier<BlockEntityType<CopperChestBlockEntity>> COPPER_CHEST_BLOCK_ENTITY;


    static {
        COPPER_CHEST_BLOCK_ENTITY = CopperAdditions.BLOCK_ENTITY_TYPES.register("copper_block_entity", () -> new BlockEntityType<>(
                CopperChestBlockEntity::new,
                false,
                Blocks.COPPER_CHEST.get()
        ));
    }


    public static void init() {}
}
