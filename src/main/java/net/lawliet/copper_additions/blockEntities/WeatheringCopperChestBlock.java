package net.lawliet.copper_additions.blockEntities;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

public class WeatheringCopperChestBlock extends CopperChestBlock implements WeatheringCopper {
    public static final MapCodec<WeatheringCopperChestBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(WeatheringCopperChestBlock::getState),
                            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("open_sound").forGetter(WeatheringCopperChestBlock::getOpenChestSound),
                            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("close_sound").forGetter(WeatheringCopperChestBlock::getCloseChestSound),
                            propertiesCodec()
                    )
                    .apply(instance, WeatheringCopperChestBlock::new)
    );

    @Override
    public MapCodec<? extends WeatheringCopperChestBlock> codec() {
        return CODEC;
    }

    public WeatheringCopperChestBlock(WeatherState weatherState, SoundEvent openSound, SoundEvent closeSound, Properties properties) {
        super(weatherState, openSound, closeSound, properties);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return WeatheringCopper.getNext(state.getBlock()).isPresent();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(!state.getValue(CustomChestBlock.TYPE).equals(ChestType.RIGHT)
                && level.getBlockEntity(pos) instanceof ChestBlockEntity chestBlockEntity
                && this.getEntitiesWithContainerOpen(chestBlockEntity).isEmpty()
        ) {
            this.changeOverTime(state, level, pos, random);

        }
    }

    @Override
    public WeatherState getAge() {
        return this.getState();
    }
}
