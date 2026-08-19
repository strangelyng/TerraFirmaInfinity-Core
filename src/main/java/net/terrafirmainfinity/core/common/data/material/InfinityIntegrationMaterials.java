package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.HazardProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.common.data.GTMedicalConditions;
import net.terrafirmainfinity.core.InfinityCore;

import static net.terrafirmainfinity.core.common.data.material.InfinityMaterials.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class InfinityIntegrationMaterials {
    public static void register() {
        // Embers
        Dawnstone = new Material.Builder(InfinityCore.id("dawnstone"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1100))
                .color(0xffba54).secondaryColor(0xe46d0e).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_RING)
                .components(Gold, 1, Copper, 1)
                .buildAndRegister();

        // Eidolon Repraised
        Pewter = new Material.Builder(InfinityCore.id("pewter"))
                .ingot()
                .liquid(new FluidBuilder().temperature(500))
                .color(0xD6D6D4).secondaryColor(0xA6A48B).iconSet(MaterialIconSet.METALLIC)
                .components(Tin, 3, Lead, 2)
                .hazard(HazardProperty.HazardTrigger.INHALATION, GTMedicalConditions.WEAK_POISON)
                .buildAndRegister();

        // Fluix
        Fluix = new Material.Builder(InfinityCore.id("fluix"))
                .gem()
                .dust()
                .liquid()
                .color(0x7e4aa8).secondaryColor(0x704ca5).iconSet(MaterialIconSet.CERTUS)
                .flags(NO_SMELTING, CRYSTALLIZABLE, DISABLE_DECOMPOSITION)
                .components(Ruby, 1, CertusQuartz, 1)
                .buildAndRegister().setFormula("?(Cr(Al2O3))(SiO2)", true);

        // TFC
        WeakSteel = new Material.Builder(InfinityCore.id("weak_steel"))
                .ingot()
                .liquid(1485)
                .components(Nickel, 1, BlackBronze, 1, Steel, 2)
                .color(0x8a8a8a).secondaryColor(0x5c5c5c).iconSet(MaterialIconSet.METALLIC)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION, DISABLE_ALLOY_BLAST)
                .buildAndRegister();

        WeakBlueSteel = new Material.Builder(InfinityCore.id("weak_blue_steel"))
                .dust()
                .ingot()
                .liquid(1540)
                .components(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4)
                .color(0x697fbd).secondaryColor(0x384b82).iconSet(MaterialIconSet.METALLIC)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION, DISABLE_ALLOY_BLAST)
                .buildAndRegister();

        WeakRedSteel = new Material.Builder(InfinityCore.id("weak_red_steel"))
                .dust()
                .ingot()
                .liquid(1540)
                .components(Brass, 1, RoseGold, 1, Steel, 2, BlackSteel, 4)
                .color(0xb55c5c).secondaryColor(0x7d3232).iconSet(MaterialIconSet.METALLIC)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION, DISABLE_ALLOY_BLAST)
                .buildAndRegister();
    }
}
