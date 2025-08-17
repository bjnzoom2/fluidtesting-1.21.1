package net.bjnzoom2.fluidtesting.fluid;

import net.bjnzoom2.fluidtesting.FluidTesting;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid STILL_RED_WATER = Registry.register(Registries.FLUID,
            Identifier.of(FluidTesting.MOD_ID, "red_water"), new RedWaterFluid.Still());
    public static final FlowableFluid FLOWING_RED_WATER = Registry.register(Registries.FLUID,
            Identifier.of(FluidTesting.MOD_ID, "flowing_red_water"), new RedWaterFluid.Flowing());

    public static final Block RED_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of(FluidTesting.MOD_ID,
            "red_water_block"), new FluidBlock(ModFluids.STILL_RED_WATER, Block.Settings.copy(Blocks.WATER)
            .replaceable().liquid()));
    public static final Item RED_WATER_BUCKET = Registry.register(Registries.ITEM, Identifier.of(FluidTesting.MOD_ID,
            "red_water_bucket"), new BucketItem(ModFluids.STILL_RED_WATER,
            new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static void registerFluids() {
        FluidTesting.LOGGER.info("Registering Custom Fluids for " + FluidTesting.MOD_ID);
    }
}
