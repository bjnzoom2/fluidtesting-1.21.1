package net.bjnzoom2.fluidtesting.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

@Environment(EnvType.CLIENT)
public class RedRainSplashParticle extends SpriteBillboardParticle {
    protected RedRainSplashParticle(ClientWorld clientWorld, double x, double y, double z) {
        super(clientWorld, x, y, z, 0.0, 0.0, 0.0);
        this.velocityX *= 0.3F;
        this.velocityY = Math.random() * 0.2F + 0.1F;
        this.velocityZ *= 0.3F;
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.gravityStrength = 0.06F;
        this.maxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.maxAge-- <= 0) {
            this.markDead();
        } else {
            this.velocityY = this.velocityY - this.gravityStrength;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            this.velocityX *= 0.98F;
            this.velocityY *= 0.98F;
            this.velocityZ *= 0.98F;
            if (this.onGround) {
                if (Math.random() < 0.5) {
                    this.markDead();
                }
                this.velocityX *= 0.7F;
                this.velocityZ *= 0.7F;
            }

            BlockPos blockPos = BlockPos.ofFloored(this.x, this.y, this.z);
            double d = Math.max(
                    this.world.getBlockState(blockPos).getCollisionShape(this.world, blockPos)
                            .getEndingCoord(Direction.Axis.Y, this.x - blockPos.getX(), this.z - blockPos.getZ()),
                    this.world.getFluidState(blockPos).getHeight(this.world, blockPos)
            );
            if (d > 0.0 && this.y < blockPos.getY() + d) {
                this.markDead();
            }
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
            RedRainSplashParticle particle = new RedRainSplashParticle(world, x, y, z);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}

