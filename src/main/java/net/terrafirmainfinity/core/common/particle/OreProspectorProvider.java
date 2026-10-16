/*
 * This class is adapted from TerraFirmaGreg-Core - Modern, which is licensed under LGPL-3.0
 * A copy of the license can be found here:
 * https://www.gnu.org/licenses/lgpl-3.0.en.html
 */

package net.terrafirmainfinity.core.common.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class OreProspectorProvider implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet spriteSet;

    public OreProspectorProvider(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }

    @Override
    public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        OreProspector particle = new OreProspector(level, x, y, z);
        particle.pickSprite(spriteSet);
        return particle;
    }
}
