package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.registry.registrate.entry.MaterialRegistryEntry;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class InfinityMaps {
    public record DimensionAir(MaterialRegistryEntry material, int tier) {}

    public static final int[] TIER_AMOUNT = {100, 50, 25, 10, 5};

    public static final Map<ResourceKey<Level>, DimensionAir> DIMENSION_AIR_MAP = new HashMap<>();

    public static final Map<ResourceKey<Level>, DimensionAir> CUSTOM_DIMENSION_AIR_MAP = new HashMap<>();

    public static Fluid getAirInDimension(ResourceKey<Level> dimension) {
        return DIMENSION_AIR_MAP.containsKey(dimension) ? DIMENSION_AIR_MAP.get(dimension).material().getFluid() : Fluids.EMPTY;
    }

    public static FluidStack getAirStackInDimension(ResourceKey<Level> dimension) {
        @Nullable DimensionAir airInfo = DIMENSION_AIR_MAP.get(dimension);

        if (airInfo != null) {
            return airInfo.material().getFluid(TIER_AMOUNT[airInfo.tier()]);
        } else return FluidStack.EMPTY;
    }

    public static void init() {
        DIMENSION_AIR_MAP.clear();

        DIMENSION_AIR_MAP.put(Level.OVERWORLD, new DimensionAir(GTMaterials.Air, 0));
        DIMENSION_AIR_MAP.put(Level.NETHER, new DimensionAir(GTMaterials.NetherAir, 1));
        DIMENSION_AIR_MAP.put(Level.END, new DimensionAir(GTMaterials.EnderAir, 2));

        DIMENSION_AIR_MAP.putAll(CUSTOM_DIMENSION_AIR_MAP);
    }
}
