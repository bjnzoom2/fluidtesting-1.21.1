package net.bjnzoom2.fluidtesting.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public abstract class RedWaterFluid extends FlowableFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_RED_WATER;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_RED_WATER;
    }

    @Override
    protected boolean isInfinite(World world) {
        return true;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    public Item getBucketItem() {
        return ModFluids.RED_WATER_BUCKET;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    @Override
    protected float getBlastResistance() {
        return 100f;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModFluids.RED_WATER_BLOCK.getDefaultState()
                .with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    public static class Flowing extends RedWaterFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        protected int getMaxFlowDistance(WorldView world) {
            return 4;
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }
    }

    public static class Still extends RedWaterFluid {
        @Override
        protected int getMaxFlowDistance(WorldView world) {
            return 4;
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
