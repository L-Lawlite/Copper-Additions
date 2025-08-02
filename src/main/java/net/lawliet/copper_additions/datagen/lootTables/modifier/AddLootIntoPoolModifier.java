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
import java.util.Random;
import java.util.function.Supplier;

public class AddLootIntoPoolModifier extends LootModifier {

    public static Supplier<MapCodec<AddLootIntoPoolModifier>> CODEC_SUPPLIER = Suppliers.memoize(() -> RecordCodecBuilder
            .mapCodec(instance -> AddLootIntoPoolModifier.codecStart(instance)
                    .and(Codec.list(ItemWithChance.CODEC).fieldOf("items_with_chances")
                            .forGetter(e -> e.itemsWithChances))
                    .apply(instance, AddLootIntoPoolModifier::new)));

    private final List<ItemWithChance> itemsWithChances;
    public AddLootIntoPoolModifier(LootItemCondition[] conditionsIn, List<ItemWithChance> itemsWithChances) {
        super(conditionsIn);
        this.itemsWithChances = itemsWithChances;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (ItemWithChance ItemWithChance : itemsWithChances) {
            if (context.getRandom().nextFloat() < ItemWithChance.chance()) {
                int remainingCount =
                        context.getRandom().nextInt(ItemWithChance.maxCount() - ItemWithChance.minCount() + 1)
                                + ItemWithChance.minCount();

                while (remainingCount > 0) {
                    // Get the maximum stack size for the current item
                    int maxStackSize = ItemWithChance.item().getMaxStackSize(new ItemStack(ItemWithChance.item()));
                    int stackCount = Math.min(remainingCount, maxStackSize);
                    generatedLoot.add(new ItemStack(ItemWithChance.item(), stackCount));
                    remainingCount -= stackCount;
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
        ArrayList<ItemWithChance> shuffledItems = new ArrayList<>(itemsWithChances);
        Collections.shuffle(shuffledItems, new Random(context.getRandom().nextLong()));

        for (ItemWithChance ItemWithChance : shuffledItems) {
            cumulativeWeight += ItemWithChance.chance();
            if (randomValue <= cumulativeWeight) {
                return ItemWithChance;
            }
        }

        return null;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC_SUPPLIER.get();
    }


}
