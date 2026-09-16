package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.material.InfinityMaterials.*;

public class InfinityCompoundMaterials {
    public static void register() {
        Alumina = new Material.Builder(InfinityCore.id("alumina"))
                .dust()
                .color(0xEBF1F5)
                .secondaryColor(0xC5D1DA)
                .components(Aluminium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        ChromiumOxide = new Material.Builder(InfinityCore.id("chromium_oxide"))
                .dust()
//                .color(0x5F853B)
//                .secondaryColor(0x354733)
                .colorAverage()
                .components(Chromium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        HydrogenChloride = new Material.Builder(InfinityCore.id("hydrogen_chloride")) // TODO: Is this stupid?
                .gas()
                .components(Hydrogen, 1, Chlorine, 1)
                .colorAverage()
                // TODO Hazard?
                .colorAverage()
                .buildAndRegister();

        HydrogenFluoride = new Material.Builder(InfinityCore.id("hydrogen_fluoride")) // TODO: Is this stupid?
                .gas()
                .components(Hydrogen, 1, Fluorine, 1)
                .colorAverage()
                // TODO Hazard?
                .buildAndRegister();
    }
}
