package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttributes;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;
import static net.terrafirmainfinity.core.common.data.material.InfinityMaterials.*;

public class InfinityCompoundMaterials {
    public static void register() {
        Alumina = InfinityRegistrate.material("alumina", builder -> builder
                .dust()
                .color(0xEBF1F5)
                .secondaryColor(0xC5D1DA)
                .components(Aluminium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION));

        Beryllia = InfinityRegistrate.material("beryllia", builder -> builder // Beryllium Oxide
                .dust()
                .color(0x4cba56)
                .secondaryColor(0x5e8562)
                .components(Beryllium, 1, Oxygen, 1)
                .flags(DISABLE_DECOMPOSITION));

        BismuthTrioxide = InfinityRegistrate.material("bismuth_trioxide", builder -> builder
                .dust()
                .colorAverage()
                .components(Bismuth, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION));

        ChromiumOxide = InfinityRegistrate.material("chromium_oxide", builder -> builder
                .dust()
//                .color(0x5F853B)
//                .secondaryColor(0x354733)
                .colorAverage()
                .components(Chromium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION));

        HydrogenChloride = InfinityRegistrate.material("hydrogen_chloride", builder -> builder // TODO: Is this stupid?
                .gas(new FluidBuilder().attribute(FluidAttributes.ACID))
                .components(Hydrogen, 1, Chlorine, 1)
                .colorAverage());
                // TODO Hazard?

        HydrogenFluoride = InfinityRegistrate.material("hydrogen_fluoride", builder -> builder // TODO: Is this stupid?
                .gas(new FluidBuilder().attribute(FluidAttributes.ACID))
                .components(Hydrogen, 1, Fluorine, 1)
                .colorAverage());
                // TODO Hazard?);

        // Infinity Organic Chemistry Materials
        Bakelite = InfinityRegistrate.material("bakelite", builder -> builder
                .polymer(1)
                .liquid(new FluidBuilder().temperature(293))
                .color(0xc7622b)
                .flags(GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Phenol, 1, Formaldehyde, 1));;

        PhenolicResin = InfinityRegistrate.material("phenolic_resin", builder -> builder
                .liquid(new FluidBuilder().temperature(293))
                .color(0xe09243)
                .flags(DISABLE_DECOMPOSITION)
                .components(Phenol, 1, Formaldehyde, 1));

        Syngas = InfinityRegistrate.material("syngas", builder -> builder
                .gas(new FluidBuilder()
                        .state(FluidState.GAS)
                        .temperature(293))
                .color(0x80400)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 1, CarbonMonoxide, 1));
    }
}
