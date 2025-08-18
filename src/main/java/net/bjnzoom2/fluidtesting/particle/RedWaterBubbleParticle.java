package net.bjnzoom2.fluidtesting.particle;

import net.bjnzoom2.fluidtesting.fluid.ModFluids;
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

@Environment(EnvType.CLIENT)
public class RedWaterBubbleParticle extends SpriteBillboardParticle {
    RedWaterBubbleParticle(ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z);
        this.setBoundingBoxSpacing(0.02F, 0.02F);
        this.scale = this.scale * (this.random.nextFloat() * 0.6F + 0.2F);
        this.velocityX = xSpeed * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
        this.velocityY = ySpeed * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
        this.velocityZ = zSpeed * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
        this.maxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.maxAge-- <= 0) {
            this.markDead();
        } else {
            this.velocityY += 0.002;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            this.velocityX *= 0.85F;
            this.velocityY *= 0.85F;
            this.velocityZ *= 0.85F;

            if (!this.world.getFluidState(BlockPos.ofFloored(this.x, this.y, this.z))
                    .isOf(ModFluids.STILL_RED_WATER) &&
                    !this.world.getFluidState(BlockPos.ofFloored(this.x, this.y, this.z))
                            .isOf(ModFluids.FLOWING_RED_WATER)) {
                this.markDead();
            }
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            RedWaterBubbleParticle particle = new RedWaterBubbleParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}

