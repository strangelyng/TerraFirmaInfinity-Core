package net.terrafirmainfinity.core.common;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.terrafirmainfinity.core.InfinityCore;

public class InfinityParticles {
    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, InfinityCore.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ORE_PROSPECTOR = PARTICLE_TYPES
            .register("ore_prospector", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ORE_PROSPECTOR_VEIN = PARTICLE_TYPES
            .register("ore_prospector_vein", () -> new SimpleParticleType(true));

    public static void init(IEventBus modBus) {
        PARTICLE_TYPES.register(modBus);
    }
}
