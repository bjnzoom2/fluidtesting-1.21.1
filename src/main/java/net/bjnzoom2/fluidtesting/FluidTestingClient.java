package net.bjnzoom2.fluidtesting;

import net.bjnzoom2.fluidtesting.fluid.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;

public class FluidTestingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_RED_WATER, ModFluids.FLOWING_RED_WATER,
                SimpleFluidRenderHandler.coloredWater(0xFFCC2323));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_RED_WATER, ModFluids.FLOWING_RED_WATER);
    }
}
