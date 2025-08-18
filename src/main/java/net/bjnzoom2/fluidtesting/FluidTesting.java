package net.bjnzoom2.fluidtesting;

import net.bjnzoom2.fluidtesting.fluid.ModFluids;
import net.bjnzoom2.fluidtesting.item.ModItemGroups;
import net.bjnzoom2.fluidtesting.particle.ModParticles;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FluidTesting implements ModInitializer {
	public static final String MOD_ID = "fluidtesting";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModFluids.registerFluids();
		ModItemGroups.registerItemGroups();
		ModParticles.registerParticles();
	}
}