package net.lawliet.copper_additions.blockEntities;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.lawliet.copper_additions.copperAdditionsRegistration.BlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Supplier;

public class CustomChestBlock extends ChestBlock {
    public static final MapCodec<CustomChestBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("open_sound").forGetter(CustomChestBlock::getOpenChestSound),
                            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("close_sound").forGetter(CustomChestBlock::getCloseChestSound),
                            propertiesCodec()
                    )
                    .apply(instance, (soundEvent1, soundEvent2, properties) -> new CustomChestBlock(BlockEntityTypes.COPPER_CHEST_BLOCK_ENTITY::get, soundEvent1, soundEvent2, properties))
    );

    private final SoundEvent openSound;
    private final SoundEvent closeSound;

    protected CustomChestBlock(Supplier<BlockEntityType<? extends ChestBlockEntity>> chest, SoundEvent openSound, SoundEvent closeSound, BlockBehaviour.Properties properties) {
        super(chest, properties);
        this.openSound = openSound;
        this.closeSound = closeSound;
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, false));
    }

    public SoundEvent getCloseChestSound() {
        return this.closeSound;
    }

    public SoundEvent getOpenChestSound() {
        return this.openSound;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if(state.getValue(WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if(this.chestCanConnectTo(neighborState) && direction.getAxis().isHorizontal()) {
            ChestType chestType = neighborState.getValue(TYPE);
            if (state.getValue(TYPE) == ChestType.SINGLE
                    && chestType != ChestType.SINGLE
                    && state.getValue(FACING) == neighborState.getValue(FACING)
                    && getConnectedDirection(neighborState) == direction.getOpposite()
            ) {
                return state.setValue(TYPE, chestType.getOpposite());
            } else if (getConnectedDirection(state) == direction) {
                return state.setValue(TYPE, ChestType.SINGLE);
            }
        }

        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    public boolean chestCanConnectTo(BlockState neighborState) {
        return neighborState.is(this);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CopperChestBlockEntity(pos, state);
    }
}
