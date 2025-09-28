package net.lawliet.copper_additions.event;

import net.lawliet.copper_additions.Config;
import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Items;
import net.lawliet.copper_additions.datagen.tags.CopperAdditionsTags;
import net.lawliet.copper_additions.mixin.MobAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;

import javax.annotation.Nullable;
import java.util.List;

@EventBusSubscriber(modid = CopperAdditions.MODID)
public class onLivingEntitySpawn {
    private static final List<EquipmentSlot> EQUIPMENT_POPULATION_ORDER = List.of(
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    );

    @SubscribeEvent
    public static void EntitySpawn(FinalizeSpawnEvent event) {
        Mob entity = event.getEntity();
        if (entity.getType().is(CopperAdditionsTags.EntityTypes.CAN_SPAWN_WITH_COPPER_ARMOR)) {
            RandomSource random = event.getLevel().getRandom();
            DifficultyInstance difficulty = event.getDifficulty();
            boolean isHard = difficulty.getDifficulty() == Difficulty.HARD;
            float difficultyMultiplier = difficulty.getSpecialMultiplier();

            if (random.nextFloat() < Mob.MAX_WEARING_ARMOR_CHANCE * Config.copperArmorSpawnChance * difficultyMultiplier) {
                float f = isHard ? 0.1F : 0.25F;
                boolean flag = true;
                for (EquipmentSlot equipmentslot : EQUIPMENT_POPULATION_ORDER) {
                    ItemStack itemstack = entity.getItemBySlot(equipmentslot);
                    if (!flag && random.nextFloat() < f) {
                        break;
                    }

                    flag = false;
                    if (itemstack.isEmpty()) {
                        Item item = onLivingEntitySpawn.getEquipmentForSlot(equipmentslot);
                        if (item != null) {
                            entity.setItemSlot(equipmentslot, new ItemStack(item));
                        }
                    }
                }
            }

            ((MobAccessor) entity).copper_additions$populateEnchantment(event.getLevel(),random,difficulty);
        }
    }

    @Nullable
    private static Item getEquipmentForSlot(EquipmentSlot equipmentSlot) {
        return switch (equipmentSlot) {
            case HEAD -> Items.COPPER_HELMET.get();
            case CHEST -> Items.COPPER_CHESTPLATE.get();
            case LEGS -> Items.COPPER_LEGGINGS.get();
            case FEET -> Items.COPPER_BOOTS.get();
            default -> null;
        };
    }
}
