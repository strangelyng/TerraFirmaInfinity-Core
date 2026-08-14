package net.terrafirmainfinity.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.HazardProperty;
import com.gregtechceu.gtceu.common.data.GTMedicalConditions;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.materials.InfinityMaterials.*;

public class InfinitySecondDegreeMaterials {
    public static void register() {
        Fluix = new Material.Builder(InfinityCore.id("fluix"))
                .gem(1)
                .dust()
                .liquid()
                .color(0x7e4aa8).secondaryColor(0x704ca5).iconSet(MaterialIconSet.CERTUS)
                .flags(NO_SMELTING, CRYSTALLIZABLE, DISABLE_DECOMPOSITION)
                .components(Unknown, 1, Ruby, 1, CertusQuartz, 1)
                .buildAndRegister();

        MalignantPewter = new Material.Builder(InfinityCore.id("malignant_pewter"))
                .ingot()
                .color(0xf2d4ff).secondaryColor(0xba84c6).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Pewter, 1, Malice, 1)
                .hazard(HazardProperty.HazardTrigger.INHALATION, GTMedicalConditions.POISON)
                .buildAndRegister();

        ZirconiumUHTCComposite = new Material.Builder(InfinityCore.id("zirconium_uhtc_composite"))
                .ingot()
                .color(0x383e2f).secondaryColor(0x2d2b2b).iconSet(MaterialIconSet.ROUGH)
                .flags(GENERATE_PLATE)
                .components(ZirconiumCarbide, 1, ZirconiumDiboride, 1)
                .blast(b -> b.temp(3473, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[EV], 1600)
                        .vacuumStats(VA[HV], 300))
                .buildAndRegister()
                .setFormula("ZrC-ZrB2", true);
    }
}
