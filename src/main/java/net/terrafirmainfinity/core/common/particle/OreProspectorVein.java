/*
 * This class is adapted from TerraFirmaGreg-Core - Modern, which is licensed under LGPL-3.0
 * A copy of the license can be found here:
 * https://www.gnu.org/licenses/lgpl-3.0.en.html
 */

package net.terrafirmainfinity.core.common.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OreProspectorVein extends TextureSheetParticle {

    protected OreProspectorVein(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
        this.lifetime = 120;
        this.gravity = 0;
        this.xd = this.yd = this.zd = 0;
        this.quadSize = 10.0f;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return OreProspector.PARTICLE_SHEET_TRANSLUCENT_NO_DEPTH;
    }

    @Override
    protected int getLightColor(float partialTick) {
        return 0x00F000F0;
    }
}
