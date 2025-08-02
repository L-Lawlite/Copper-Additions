package net.lawliet.copper_additions.datagen.lootTables.modifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.lawliet.copper_additions.datagen.lootTables.records.ItemWithChance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class ReplaceLootModifier extends LootModifier {

    public static Supplier<MapCodec<ReplaceLootModifier>> CODEC_SUPPLIER = Suppliers.memoize(() -> RecordCodecBuilder
            .mapCodec(instance -> ReplaceLootModifier.codecStart(instance)
                    .and(Codec.list(ItemWithChance.CODEC).fieldOf("items_with_chances")
                            .forGetter(replaceLootModifier -> replaceLootModifier.itemsWithChances))
                    .apply(instance, ReplaceLootModifier::new)));
    private final List<ItemWithChance> itemsWithChances;

    public ReplaceLootModifier(LootItemCondition[] conditionsIn, List<ItemWithChance> itemsWithChances) {
        super(conditionsIn);
        this.itemsWithChances = itemsWithChances;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {


        // Clear existing loot
        generatedLoot.clear();

        for (ItemWithChance itemWithChance : itemsWithChances) {
            // Generate a random float and compare with the item chance
            if (context.getRandom().nextFloat() < itemWithChance.chance()) {
                int totalCount = context.getRandom().nextInt(itemWithChance.maxCount() - itemWithChance.minCount() + 1)
                        + itemWithChance.minCount();
                int remainingCount = totalCount;

                while (remainingCount > 0) {
                    // Get the maximum stack size for the current item
                    int maxStackSize = itemWithChance.item().getMaxStackSize(new ItemStack(itemWithChance.item()));
                    int stackCount = Math.min(remainingCount, maxStackSize); // Limit to the item's max stack size
                    generatedLoot.add(new ItemStack(itemWithChance.item(), stackCount));
                    remainingCount -= stackCount; // Decrease the remaining count
                }
            }
        }

        return generatedLoot;
    }

    @Nullable
    private ItemWithChance weightedItemSelection(LootContext context) {
        float totalWeight = itemsWithChances.stream()
                .map(ItemWithChance::chance)
                .reduce(0f, Float::sum);

        float randomValue = context.getRandom().nextFloat() * totalWeight;
        float cumulativeWeight = 0;

        // Create a shuffled copy of the list to remove order bias
        List<ItemWithChance> shuffledItems = new ArrayList<>(itemsWithChances);
        Collections.shuffle(shuffledItems);

        for (ItemWithChance itemWithChance : shuffledItems) {
            cumulativeWeight += itemWithChance.chance();
            if (randomValue <= cumulativeWeight) {
                return itemWithChance;
            }
        }

        return null; // In case something goes wrong
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC_SUPPLIER.get();
    }


}