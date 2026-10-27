package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;
import static net.terrafirmainfinity.core.common.data.material.InfinityMaterials.*;

public class InfinityAlloyMaterials {
    public static void register() {
        Ferrosilicon = InfinityRegistrate.material("ferrosilicon", builder -> builder
                .dust()
                .ingot()
                .liquid(new FluidBuilder().temperature(1330))
                .color(0x979797).secondaryColor(0x10293b).iconSet(MaterialIconSet.METALLIC)
                .components(Iron, 1, Silicon, 3));

        Ferrovanadium = InfinityRegistrate.material("ferrovanadium", builder -> builder
                .dust()
                .ingot()
                .liquid(new FluidBuilder().temperature(1480))
                .color(0x979797).secondaryColor(0x240808).iconSet(MaterialIconSet.SHINY)
                .components(Vanadium, 4, Iron, 1));
    }
}
