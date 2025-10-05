package net.lawliet.copper_additions.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.lawliet.copper_additions.blockEntities.CustomChestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net/minecraft/world/level/block/entity/ChestBlockEntity$1")
public class ChestBlockEntityOpenerCounterMixin {

    @Definition(id = "CHEST_OPEN", field = "Lnet/minecraft/sounds/SoundEvents;CHEST_OPEN:Lnet/minecraft/sounds/SoundEvent;")
    @Expression("CHEST_OPEN")
    @ModifyExpressionValue(method = "onOpen", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    public SoundEvent updateOpenSound(SoundEvent original, Level level, BlockPos pos, BlockState state) {
        if (state.getBlock() instanceof CustomChestBlock chestBlock) {
            return chestBlock.getOpenChestSound();
        }
        return original;
    }

    @Definition(id = "CHEST_CLOSE", field = "Lnet/minecraft/sounds/SoundEvents;CHEST_CLOSE:Lnet/minecraft/sounds/SoundEvent;")
    @Expression("CHEST_CLOSE")
    @ModifyExpressionValue(method = "onClose", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    public SoundEvent updateCloseSound(SoundEvent original, Level level, BlockPos pos, BlockState state) {
        if (state.getBlock() instanceof CustomChestBlock chestBlock) {
            return chestBlock.getCloseChestSound();
        }
        return original;
    }
}
