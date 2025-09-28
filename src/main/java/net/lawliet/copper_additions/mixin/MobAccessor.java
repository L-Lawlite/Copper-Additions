package net.lawliet.copper_additions.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Mob.class)
public interface MobAccessor {

    @Invoker(value = "populateDefaultEquipmentEnchantments")
    void copper_additions$populateEnchantment(ServerLevelAccessor level, RandomSource random, DifficultyInstance difficulty);
}
