package net.lawliet.copper_additions.event;

import com.mojang.logging.LogUtils;
import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Set;

@EventBusSubscriber(modid = CopperAdditions.MODID)
public class LootTableModifyOnLoad {
    public static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void ModifyLootTable(LootTableLoadEvent event) {
        Set<String> poolsToModify = Set.of(
                "chests/simple_dungeon",
                "chests/desert_pyramid",
                "chests/village/village_weaponsmith",
                "chests/end_city_treasure",
                "chests/stronghold_corridor",
                "chests/jungle_temple",
                "chests/nether_bridge"
        );
        LootTable lootTable = event.getTable();
        ResourceLocation tableName = event.getName();
        if (!poolsToModify.contains(tableName.getPath())) {
            return;
        }
        String poolName = "pool0";
        LootPool modificationPool = lootTable.getPool(poolName);
        if (modificationPool != null) {
            LootPool.Builder newLootPool = copyLootTable(modificationPool);
            
            if(tableName.equals(VanillaLootTableName("chests/simple_dungeon")) ||
                    tableName.equals(VanillaLootTableName("chests/desert_pyramid"))
            ) {
                newLootPool.add(LootItem.lootTableItem(Items.COPPER_HORSE_ARMOR).setWeight(15));
            } else if (tableName.equals(VanillaLootTableName("chests/village/village_weaponsmith")) ||
                    tableName.equals(VanillaLootTableName("chests/end_city_treasure")) ||
                    tableName.equals(VanillaLootTableName("chests/stronghold_corridor")) ||
                    tableName.equals(VanillaLootTableName("chests/jungle_temple"))
            ) {
                newLootPool.add(LootItem.lootTableItem(Items.COPPER_HORSE_ARMOR));
            } else if (tableName.equals(VanillaLootTableName("chests/nether_bridge"))) {
                newLootPool.add(LootItem.lootTableItem(Items.COPPER_HORSE_ARMOR).setWeight(5));
            }

            LOGGER.info("Modifying loot table {}", tableName);
            lootTable.removePool(poolName);
            lootTable.addPool(newLootPool.build());
        }
    }

    @SuppressWarnings({"rawtypes","ConstantConditions"})
    private static LootPool.Builder copyLootTable(LootPool oldLootPool) {
        LootPool.Builder newLootPoolBuilder = LootPool.lootPool()
                .setRolls(oldLootPool.getRolls())
                .setBonusRolls(oldLootPool.getBonusRolls())
                .name(oldLootPool.getName());
        oldLootPool.entries.forEach(entry -> newLootPoolBuilder.add(new LootPoolEntryContainer.Builder() {
            @Override
            protected @NotNull LootPoolEntryContainer.Builder getThis() {
                return this;
            }

            @Override
            public @NotNull LootPoolEntryContainer build() {
                return entry;
            }
        }));
        return newLootPoolBuilder;
    }

    private static ResourceLocation VanillaLootTableName(String location) {
        return ResourceLocation.withDefaultNamespace(location);
    }
}
