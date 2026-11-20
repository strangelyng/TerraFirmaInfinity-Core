package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
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

        SiliconCarbide = InfinityRegistrate.material("silicon_carbide", builder -> builder
                .dust()
                .ingot()
                .blastTemp(2000, BlastProperty.GasTier.LOW, VA[MV])
                .color(0x716e79).secondaryColor(0x3d3a41).iconSet(MaterialIconSet.DULL)
                .components(Silicon, 1, Carbon, 1));

        WReAlloy = InfinityRegistrate.material("wre_alloy", builder -> builder
                .lang("WRe Alloy")
                .ingot().fluid()
                .blast(b -> b.temp(3500, BlastProperty.GasTier.MID)
                        .blastStats(VA[EV], 1400)
                        .vacuumStats(VA[HV], 250))
                .color(0x95a3c5).secondaryColor(0x363f56).iconSet(MaterialIconSet.SHINY)
                .components(Tungsten, 4, Rhenium, 1)
                .flags(GENERATE_PLATE));
    }
}
