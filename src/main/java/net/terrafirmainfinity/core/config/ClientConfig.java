package net.terrafirmainfinity.core.config;

import net.dries007.tfc.config.BaseConfig;
import net.dries007.tfc.config.ConfigBuilder;

import java.util.function.Supplier;

public class ClientConfig extends BaseConfig {
    public final Supplier<Integer> preciseOreProspectorParticleChance;

    ClientConfig(ConfigBuilder builder) {
        builder.push("propick_vein_rendering");
        preciseOreProspectorParticleChance = builder
                .comment("1 in N chance for the precise x-ray ore prospector particles to appear per block. Set to 0 to disable.")
                .define("preciseOreProspectorParticleChance", 5, 0, 1000);
        builder.pop();
    }
}
