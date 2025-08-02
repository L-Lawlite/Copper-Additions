package net.lawliet.copper_additions.copperAdditionsRegistration;

import com.mojang.serialization.MapCodec;
import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.datagen.lootTables.modifier.AddItemModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;

import java.util.function.Supplier;

public class LootModifiers {
    public static void init() {}

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM;

    static {
        ADD_ITEM = CopperAdditions.LOOT_MODIFIER_SERIALIZER.register("add_item", () -> AddItemModifier.CODEC);
    }
}
