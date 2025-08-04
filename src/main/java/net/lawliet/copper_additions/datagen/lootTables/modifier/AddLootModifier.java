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

import java.util.List;
import java.util.function.Supplier;

public class AddLootModifier extends LootModifier {

    public static Supplier<MapCodec<AddLootModifier>> CODEC_SUPPLIER = Suppliers.memoize(() -> RecordCodecBuilder
            .mapCodec(instance -> AddLootModifier.codecStart(instance)
                    .and(Codec.list(ItemWithChance.CODEC).fieldOf("items_with_chances")
                            .forGetter(e -> e.itemsWithChances))
                    .apply(instance, AddLootModifier::new)));

    private final List<ItemWithChance> itemsWithChances;
    public AddLootModifier(LootItemCondition[] conditionsIn, List<ItemWithChance> itemsWithChances) {
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

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC_SUPPLIER.get();
    }


}
