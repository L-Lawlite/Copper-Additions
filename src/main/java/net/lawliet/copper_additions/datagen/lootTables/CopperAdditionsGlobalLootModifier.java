package net.lawliet.copper_additions.datagen.lootTables;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Items;
import net.lawliet.copper_additions.datagen.lootTables.modifier.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class CopperAdditionsGlobalLootModifier extends GlobalLootModifierProvider {
    public CopperAdditionsGlobalLootModifier(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, CopperAdditions.MODID);
    }

    @Override
    protected void start() {
        this.add("copper_horse_armor_in_simple_dungeon",
                new AddItemModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/simple_dungeon")).build()
                }, Items.COPPER_HORSE_ARMOR.get())
                );
    }
}
