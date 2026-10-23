package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.material.InfinityMaterials.*;

public class InfinityAlloyMaterials {
    public static void register() {
        Ferrosilicon = new Material.Builder(InfinityCore.id("ferrosilicon"))
                .dust()
                .ingot()
                .liquid(new FluidBuilder().temperature(1330))
                .color(0x979797).secondaryColor(0x10293b).iconSet(MaterialIconSet.METALLIC)
                .components(Iron, 1, Silicon, 3)
                .buildAndRegister();

        Ferrovanadium = new Material.Builder(InfinityCore.id("ferrovanadium"))
                .dust()
                .ingot()
                .liquid(new FluidBuilder().temperature(1480))
                .color(0x979797).secondaryColor(0x240808).iconSet(MaterialIconSet.SHINY)
                .components(Vanadium, 4, Iron, 1)
                .buildAndRegister();
    }
}
