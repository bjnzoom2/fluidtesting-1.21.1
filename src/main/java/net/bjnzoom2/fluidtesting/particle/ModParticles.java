package net.bjnzoom2.fluidtesting.particle;

import net.bjnzoom2.fluidtesting.FluidTesting;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType RED_SPLASH =
            registerParticle("red_splash_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType RED_BUBBLE =
            registerParticle("red_bubble_particle", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(FluidTesting.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        FluidTesting.LOGGER.info("Registering Particles for " + FluidTesting.MOD_ID);
    }
}