package net.lawliet.copper_additions.datagen.lootTables.records;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public record ItemWithChance(Item item, float chance, int minCount, int maxCount) {
    public static final Codec<ItemWithChance> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(itemWithChance -> itemWithChance.item),
                    Codec.floatRange(0.0f, 1.0f).fieldOf("chance").forGetter(itemWithChance -> itemWithChance.chance),
                    Codec.INT.fieldOf("min_count").forGetter(itemWithChance -> itemWithChance.minCount),
                    Codec.INT.fieldOf("max_count").forGetter(itemWithChance -> itemWithChance.maxCount)
            ).apply(instance, ItemWithChance::new));

}
