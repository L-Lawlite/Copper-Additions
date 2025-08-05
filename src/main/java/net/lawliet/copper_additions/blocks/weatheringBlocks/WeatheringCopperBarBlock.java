package net.lawliet.copper_additions.blocks.weatheringBlocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

public class WeatheringCopperBarBlock extends IronBarsBlock implements WeatheringCopper {
    public static final MapCodec<WeatheringCopperBarBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(WeatherState.CODEC.fieldOf("weathering_state").forGetter(WeatheringCopperBarBlock::getAge), propertiesCodec()).apply(instance, WeatheringCopperBarBlock::new));


    private final WeatheringCopper.WeatherState weatherState;

    @Override
    public MapCodec<WeatheringCopperBarBlock> codec() {
        return CODEC;
    }

    public WeatheringCopperBarBlock(WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.changeOverTime(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return WeatheringCopper.getNext(state.getBlock()).isPresent();
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }
}
