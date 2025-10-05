//package net.lawliet.copper_additions.blockEntities;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.sounds.SoundEvent;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.CompoundContainer;
//import net.minecraft.world.Container;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.inventory.ChestMenu;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.ChestBlock;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.entity.ChestBlockEntity;
//import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.properties.ChestType;
//
//public class CustomChestBlockEntity extends ChestBlockEntity {
//    private final ContainerOpenersCounter openersCounter;
//
//    public CustomChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
//        super(blockEntityType, pos, state);
//        this.openersCounter = new ContainerOpenersCounter() {
//            protected void onOpen(Level level, BlockPos pos1, BlockState state1) {
//                if (state1.getBlock() instanceof CustomChestBlock chestBlock) {
//                    CustomChestBlockEntity.playSound(level, pos1, state1, chestBlock.getOpenChestSound());
//                }
//            }
//
//            protected void onClose(Level level, BlockPos pos1, BlockState state1) {
//                if (state1.getBlock() instanceof CustomChestBlock chestBlock) {
//                    CustomChestBlockEntity.playSound(level, pos1, state1, chestBlock.getCloseChestSound());
//                }
//            }
//
//            protected void openerCountChanged(Level level, BlockPos pos1, BlockState state1, int i, int j) {
//                CustomChestBlockEntity.this.signalOpenCount(level, pos1, state1, i, j);
//            }
//
//            protected boolean isOwnContainer(Player player) {
//                if (!(player.containerMenu instanceof ChestMenu)) {
//                    return false;
//                } else {
//                    Container container = ((ChestMenu)player.containerMenu).getContainer();
//                    return container == CustomChestBlockEntity.this || container instanceof CompoundContainer && ((CompoundContainer)container).contains(CustomChestBlockEntity.this);
//                }
//            }
//        };
//    }
//
//    public CustomChestBlockEntity(BlockPos blockpos, BlockState blockstate) {
//        this(BlockEntityType.CHEST, blockpos, blockstate);
//    }
//
//    static void playSound(Level level, BlockPos pos, BlockState state, SoundEvent sound) {
//        ChestType chesttype = (ChestType)state.getValue(ChestBlock.TYPE);
//        if (chesttype != ChestType.LEFT) {
//            double d0 = (double)pos.getX() + (double)0.5F;
//            double d1 = (double)pos.getY() + (double)0.5F;
//            double d2 = (double)pos.getZ() + (double)0.5F;
//            if (chesttype == ChestType.RIGHT) {
//                Direction direction = ChestBlock.getConnectedDirection(state);
//                d0 += (double)direction.getStepX() * (double)0.5F;
//                d2 += (double)direction.getStepZ() * (double)0.5F;
//            }
//
//            level.playSound((Player)null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
//        }
//
//    }
//
//    @Override
//    public void stopOpen(Player player) {
//        if(!this.remove && !player.isSpectator()) {
//            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
//        }
//    }
//
//    @Override
//    public void startOpen(Player player) {
//        if(!this.remove && !player.isSpectator()) {
//            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
//        }
//    }
//
//    public ContainerOpenersCounter getOpenersCounter() {
//        return this.openersCounter;
//    }
//
//    public static int getOpenCount(BlockGetter level, BlockPos pos) {
//        BlockState blockstate = level.getBlockState(pos);
//        if (blockstate.hasBlockEntity()) {
//            BlockEntity blockentity = level.getBlockEntity(pos);
//            if (blockentity instanceof CustomChestBlockEntity) {
//                return ((CustomChestBlockEntity)blockentity).openersCounter.getOpenerCount();
//            }
//        }
//
//        return 0;
//    }
//
//    @Override
//    public void recheckOpen() {
//        if (!this.remove) {
//            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
//        }
//    }
//}
