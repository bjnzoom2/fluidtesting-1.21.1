package net.bjnzoom2.fluidtesting.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class RedWaterSplashParticle extends RedRainSplashParticle {
    RedWaterSplashParticle(ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z);
        this.gravityStrength = 0.04F;
        if (ySpeed == 0.0 && (xSpeed != 0.0 || zSpeed != 0.0)) {
            this.velocityX = xSpeed;
            this.velocityY = 0.1;
            this.velocityZ = zSpeed;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            RedWaterSplashParticle particle = new RedWaterSplashParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
