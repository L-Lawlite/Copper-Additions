package net.lawliet.copper_additions.blockEntities;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.lawliet.copper_additions.copperAdditionsRegistration.BlockEntityTypes;
import net.lawliet.copper_additions.datagen.tags.CopperAdditionsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.neoforged.neoforge.common.DataMapHooks;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CopperChestBlock extends CustomChestBlock {

    public static final MapCodec<CopperChestBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(CopperChestBlock::getState),
                            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("open_sound").forGetter(CopperChestBlock::getOpenChestSound),
                            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("close_sound").forGetter(CopperChestBlock::getCloseChestSound),
                            propertiesCodec()
                    )
                    .apply(instance, CopperChestBlock::new)
    );



    private final WeatheringCopper.WeatherState weatherState;

    @Override
    public MapCodec<? extends CopperChestBlock> codec() {
        return CODEC;
    }

    public CopperChestBlock(WeatheringCopper.WeatherState weatherState, SoundEvent openSound, SoundEvent closeSound, Properties properties) {
        super(BlockEntityTypes.COPPER_CHEST_BLOCK_ENTITY::get, openSound, closeSound, properties);
        this.weatherState = weatherState;
    }

    public WeatheringCopper.WeatherState getState() {
        return this.weatherState;
    }

    @Override
    public boolean chestCanConnectTo(BlockState neighborState) {
        return neighborState.is(CopperAdditionsTags.Blocks.COPPER_CHEST);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockplacecontext) {
        BlockState blockState = super.getStateForPlacement(blockplacecontext);
        return getLeastOxidizedChestOfConnectedBlocks(blockState, blockplacecontext.getLevel(), blockplacecontext.getClickedPos());
    }

    private BlockState getLeastOxidizedChestOfConnectedBlocks(BlockState blockState, Level level, BlockPos blockPos) {
        BlockState blockState1 = level.getBlockState(blockPos.relative(getConnectedDirection(blockState)));
        if (!blockState.getValue(CustomChestBlock.TYPE).equals(ChestType.SINGLE)
                && blockState.getBlock() instanceof CopperChestBlock copperChestBlock
                && blockState1.getBlock() instanceof CopperChestBlock neighghbourCopperChestBlock) {
            BlockState currentChest = blockState;
            BlockState neighbourChest = blockState1;
            if (copperChestBlock.isWaxed() != neighghbourCopperChestBlock.isWaxed()) {
                currentChest = unwaxBlock(copperChestBlock, blockState).orElse(blockState);
                neighbourChest = unwaxBlock(copperChestBlock, blockState1).orElse(blockState1);
            }

            Block block = copperChestBlock.weatherState.ordinal() <= neighghbourCopperChestBlock.weatherState.ordinal() ? currentChest.getBlock() : neighbourChest.getBlock();
            return block.withPropertiesOf(currentChest);
        }
        return blockState;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        BlockState blockState = super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
        if(this.chestCanConnectTo(neighborState)) {
            ChestType chestType = blockState.getValue(CustomChestBlock.TYPE);
            if (!chestType.equals(ChestType.SINGLE) && getConnectedDirection(blockState) == direction) {
                return neighborState.getBlock().withPropertiesOf(blockState);
            }
        }
        return blockState;
    }

    public List<Player> getEntitiesWithContainerOpen(ChestBlockEntity chestBlockEntity) {
        assert chestBlockEntity.getLevel() != null;
        return chestBlockEntity.openersCounter.getPlayersWithContainerOpen(chestBlockEntity.getLevel(), chestBlockEntity.getBlockPos());
    }

    private static Optional<BlockState> unwaxBlock(CopperChestBlock copperChestBlock, BlockState blockState) {
        return !copperChestBlock.isWaxed() ? Optional.of(blockState) : Optional.of(Objects.requireNonNull(DataMapHooks.getBlockUnwaxed(copperChestBlock)).withPropertiesOf(blockState));
    }

    public boolean isWaxed() {
        return DataMapHooks.getBlockUnwaxed(this) != null;
    }
}
