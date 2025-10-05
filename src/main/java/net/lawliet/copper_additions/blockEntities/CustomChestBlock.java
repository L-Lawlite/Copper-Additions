package net.lawliet.copper_additions.blockEntities;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.lawliet.copper_additions.copperAdditionsRegistration.BlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class CustomChestBlock extends AbstractChestBlock<CopperChestBlockEntity> implements SimpleWaterloggedBlock {
    public static final MapCodec<CustomChestBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("open_sound").forGetter(CustomChestBlock::getOpenChestSound),
                    BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("close_sound").forGetter(CustomChestBlock::getCloseChestSound),
                    propertiesCodec()
            )
                    .apply(instance, (soundEvent1, soundEvent2, properties) -> new CustomChestBlock(BlockEntityTypes.COPPER_CHEST_BLOCK_ENTITY::get, soundEvent1, soundEvent2, properties))
    );
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final SoundEvent openSound;
    private final SoundEvent closeSound;
    private static final DoubleBlockCombiner.Combiner<CopperChestBlockEntity, Optional<Container>> CHEST_COMBINER = new DoubleBlockCombiner.Combiner<CopperChestBlockEntity, Optional<Container>>() {
        public Optional<Container> acceptDouble(CopperChestBlockEntity chestBlockEntity, CopperChestBlockEntity chestBlockEntity1) {
            return Optional.of(new CompoundContainer(chestBlockEntity1, chestBlockEntity1));
        }

        public Optional<Container> acceptSingle(CopperChestBlockEntity chestblockentity) {
            return Optional.of(chestblockentity);
        }

        public Optional<Container> acceptNone() {
            return Optional.empty();
        }
    };
    private static final DoubleBlockCombiner.Combiner<CopperChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<CopperChestBlockEntity, Optional<MenuProvider>>() {
        public Optional<MenuProvider> acceptDouble(final CopperChestBlockEntity chestblockentity, final CopperChestBlockEntity chestBlockEntity1) {
            final Container container = new CompoundContainer(chestblockentity, chestBlockEntity1);
            return Optional.of(
                    new MenuProvider() {
                        @javax.annotation.Nullable
                        @Override
                        public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                            if (chestblockentity.canOpen(player) && chestBlockEntity1.canOpen(player)) {
                                chestblockentity.unpackLootTable(inventory.player);
                                chestBlockEntity1.unpackLootTable(inventory.player);
                                return ChestMenu.sixRows(i, inventory, container);
                            } else {
                                return null;
                            }
                        }

                        @Override
                        public Component getDisplayName() {
                            if (chestblockentity.hasCustomName()) {
                                return chestblockentity.getDisplayName();
                            } else {
                                return chestBlockEntity1.hasCustomName()
                                        ? chestBlockEntity1.getDisplayName()
                                        : Component.translatable("container.chestDouble");
                            }
                        }
                    }
            );
        }

        public Optional<MenuProvider> acceptSingle(CopperChestBlockEntity chestblockentity) {
            return Optional.of(chestblockentity);
        }

        public Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }
    };

    public MapCodec<? extends CustomChestBlock> codec() {
        return CODEC;
    }

    protected CustomChestBlock(Supplier<BlockEntityType<? extends CopperChestBlockEntity>> chest, SoundEvent openSound, SoundEvent closeSound, BlockBehaviour.Properties properties) {
        super(properties,chest);
        this.openSound = openSound;
        this.closeSound = closeSound;
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, false));
    }

    public static DoubleBlockCombiner.BlockType getBlockType(BlockState state) {
        ChestType chestType = state.getValue(TYPE);
        if (chestType == ChestType.SINGLE) {
            return DoubleBlockCombiner.BlockType.SINGLE;
        }
        return chestType == ChestType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
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

    public static Direction getConnectedDirection(BlockState neighborState) {
        Direction direction = neighborState.getValue(FACING);
        return neighborState.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    public static BlockPos getConnectedBlockPos(BlockPos blockpos, BlockState blockstate) {
        Direction direction = getConnectedDirection(blockstate);
        return blockpos.relative(direction);
    }

    public boolean chestCanConnectTo(BlockState neighborState) {
        return neighborState.is(this);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockplacecontext) {
        ChestType chesttype = ChestType.SINGLE;
        Direction direction = blockplacecontext.getHorizontalDirection().getOpposite();
        FluidState fluidstate = blockplacecontext.getLevel().getFluidState(blockplacecontext.getClickedPos());
        boolean flag = blockplacecontext.isSecondaryUseActive();
        Direction direction1 = blockplacecontext.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.candidatePartnerFacing(blockplacecontext.getLevel(), blockplacecontext.getClickedPos(), direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                chesttype = direction2.getCounterClockWise() == direction1.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
            }
        }

        if (chesttype == ChestType.SINGLE && !flag) {
            chesttype = this.getChestType(blockplacecontext.getLevel(), blockplacecontext.getClickedPos(), direction);
        }

        return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, chesttype).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    protected ChestType getChestType(Level level, BlockPos blockpos, Direction direction) {
        if (direction == this.candidatePartnerFacing(level, blockpos, direction.getClockWise())) {
            return ChestType.LEFT;
        } else {
            return direction == this.candidatePartnerFacing(level, blockpos, direction.getCounterClockWise()) ? ChestType.RIGHT : ChestType.SINGLE;
        }
    }

    @Override
    protected FluidState getFluidState(BlockState blockstate) {
        return blockstate.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockstate);
    }

    @javax.annotation.Nullable
    private Direction candidatePartnerFacing(Level level, BlockPos blockpos, Direction direction) {
        BlockState blockstate = level.getBlockState(blockpos.relative(direction));
        return this.chestCanConnectTo(blockstate) && blockstate.getValue(TYPE) == ChestType.SINGLE ? blockstate.getValue(FACING) : null;
    }

    protected void affectNeighborsAfterRemoval(BlockState blockstate, ServerLevel serverlevel, BlockPos blockpos, boolean flag) {
        serverlevel.updateNeighbourForOutputSignal(blockpos, blockstate.getBlock());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockstate, Level level, BlockPos blockpos, Player player, BlockHitResult blockhitresult) {
        if (level instanceof ServerLevel serverlevel) {
            MenuProvider menuprovider = this.getMenuProvider(blockstate, level, blockpos);
            if (menuprovider != null) {
                player.openMenu(menuprovider);
                player.awardStat(this.getOpenChestStat());
                PiglinAi.angerNearbyPiglins(serverlevel, player, true);
            }
        }

        return InteractionResult.SUCCESS;
    }

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    public BlockEntityType<? extends CopperChestBlockEntity> blockEntityType() {
        return this.blockEntityType.get();
    }

    @javax.annotation.Nullable
    public static Container getContainer(CustomChestBlock chestBlock, BlockState blockstate, Level level, BlockPos blockpos, boolean flag) {
        return chestBlock.combine(blockstate, level, blockpos, flag).apply(CHEST_COMBINER).orElse(null);
    }

    @Override
    public DoubleBlockCombiner.NeighborCombineResult<? extends CopperChestBlockEntity> combine(BlockState blockstate, Level level, BlockPos blockpos, boolean flag) {
        BiPredicate<LevelAccessor, BlockPos> bipredicate;
        if (flag) {
            bipredicate = (levelaccessor, pos1) -> false;
        } else {
            bipredicate = ChestBlock::isChestBlockedAt;
        }

        return DoubleBlockCombiner.combineWithNeigbour(
                this.blockEntityType.get(), ChestBlock::getBlockType, ChestBlock::getConnectedDirection, FACING, blockstate, level, blockpos, bipredicate
        );
    }

    @javax.annotation.Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState blockstate, Level level, BlockPos blockpos) {
        return this.combine(blockstate, level, blockpos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    public static DoubleBlockCombiner.Combiner<CopperChestBlockEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity lidblockentity) {
        return new DoubleBlockCombiner.Combiner<CopperChestBlockEntity, Float2FloatFunction>() {
            public Float2FloatFunction acceptDouble(CopperChestBlockEntity chestblockentity, CopperChestBlockEntity chestBlockEntity1) {
                return f -> Math.max(chestblockentity.getOpenNess(f), chestBlockEntity1.getOpenNess(f));
            }

            public Float2FloatFunction acceptSingle(CopperChestBlockEntity chestblockentity) {
                return chestblockentity::getOpenNess;
            }

            public Float2FloatFunction acceptNone() {
                return lidblockentity::getOpenNess;
            }
        };
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockpos, BlockState blockstate) {
        return new CopperChestBlockEntity(blockpos, blockstate);
    }

    @javax.annotation.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockstate, BlockEntityType<T> blockentitytype) {
        return level.isClientSide() ? createTickerHelper(blockentitytype, this.blockEntityType(), CopperChestBlockEntity::lidAnimateTick) : null;
    }

    public static boolean isChestBlockedAt(LevelAccessor levelaccessor, BlockPos blockpos) {
        return isBlockedChestByBlock(levelaccessor, blockpos) || isCatSittingOnChest(levelaccessor, blockpos);
    }

    private static boolean isBlockedChestByBlock(BlockGetter blockgetter, BlockPos blockpos) {
        BlockPos pos1 = blockpos.above();
        return blockgetter.getBlockState(pos1).isRedstoneConductor(blockgetter, pos1);
    }

    private static boolean isCatSittingOnChest(LevelAccessor levelaccessor, BlockPos blockpos) {
        List<Cat> list = levelaccessor.getEntitiesOfClass(
                Cat.class, new AABB(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ(), blockpos.getX() + 1, blockpos.getY() + 2, blockpos.getZ() + 1)
        );
        if (!list.isEmpty()) {
            for (Cat cat : list) {
                if (cat.isInSittingPose()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState blockstate) {
        return true;
    }

    protected int getAnalogOutputSignal(BlockState blockstate, Level level, BlockPos blockpos, Direction direction) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, blockstate, level, blockpos, false));
    }

    @Override
    protected BlockState rotate(BlockState blockstate, Rotation rotation) {
        return blockstate.setValue(FACING, rotation.rotate(blockstate.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState blockstate, Mirror mirror) {
        return blockstate.rotate(mirror.getRotation(blockstate.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    protected boolean isPathfindable(BlockState blockstate, PathComputationType pathcomputationtype) {
        return false;
    }

    @Override
    protected void tick(BlockState blockstate, ServerLevel serverlevel, BlockPos blockpos, RandomSource randomsource) {
        BlockEntity blockentity = serverlevel.getBlockEntity(blockpos);
        if (blockentity instanceof CopperChestBlockEntity) {
            ((CopperChestBlockEntity)blockentity).recheckOpen();
        }
    }

    public SoundEvent getCloseChestSound() {
        return this.closeSound;
    }

    public SoundEvent getOpenChestSound() {
        return this.openSound;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(TYPE) == ChestType.SINGLE) {
            return AABB;
        } else {
            return switch (getConnectedDirection(state)) {
                case SOUTH -> SOUTH_AABB;
                case WEST -> WEST_AABB;
                case EAST -> EAST_AABB;
                default -> NORTH_AABB;
            };
        }
    }

    protected static final VoxelShape NORTH_AABB = Block.box(1.0, 0.0, 0.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 16.0);
    protected static final VoxelShape WEST_AABB = Block.box(0.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape EAST_AABB = Block.box(1.0, 0.0, 1.0, 16.0, 14.0, 15.0);
    protected static final VoxelShape AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);

}
