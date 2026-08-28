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
                .components(Gold, 1, Copper, 4)
                .buildAndRegister();

        Ember = new Material.Builder(InfinityCore.id("ember"))
                .gem().ore(1, 1, true)
                .gas(1300)
                .color(0xff7327).secondaryColor(0xe60000).iconSet(MaterialIconSet.DIAMOND) // TODO: Custom Material Set
                .flags(PHOSPHORESCENT, NO_ORE_SMELTING)
                .element(InfinityElements.Ember)
                .buildAndRegister();

        // Eidolon Repraised
        Pewter = new Material.Builder(InfinityCore.id("pewter"))
                .ingot()
                .liquid(new FluidBuilder().temperature(500))
                .color(0xD6D6D4).secondaryColor(0xA6A48B).iconSet(MaterialIconSet.METALLIC)
                .components(Tin, 3, Lead, 2)
                .hazard(HazardProperty.HazardTrigger.INHALATION, GTMedicalConditions.WEAK_POISON)
                .buildAndRegister();

        // AE2
        Fluix = new Material.Builder(InfinityCore.id("fluix"))
                .gem()
                .dust()
                .liquid()
                .color(0x7e4aa8).secondaryColor(0x704ca5).iconSet(MaterialIconSet.CERTUS)
                .flags(NO_SMELTING, CRYSTALLIZABLE, DISABLE_DECOMPOSITION)
                .components(Ruby, 1, CertusQuartz, 1)
                .buildAndRegister().setFormula("?(Cr(Al2O3))(SiO2)", true);

        // Malum
        HallowedGold = new Material.Builder(InfinityCore.id("hallowed_gold"))
                .ingot()
                .color(0xffd659).secondaryColor(0xca0023).iconSet(InfinityMaterialIconSet.HALLOWED)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FINE_WIRE, GENERATE_RING, DISABLE_DECOMPOSITION)
                .buildAndRegister();

        MalignantPewter = new Material.Builder(InfinityCore.id("malignant_pewter"))
                .ingot()
                .color(0xf2d4ff).secondaryColor(0xba84c6).iconSet(InfinityMaterialIconSet.MALIGNANT)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .hazard(HazardProperty.HazardTrigger.INHALATION, GTMedicalConditions.POISON)
                .buildAndRegister();

        SoulstainedSteel = new Material.Builder(InfinityCore.id("soulstained_steel"))
                .ingot()
                .color(0xe98cff).secondaryColor(0x7b3bd3).iconSet(InfinityMaterialIconSet.SOULSTAINED)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_ROTOR, DISABLE_DECOMPOSITION)
                .buildAndRegister();

        // TFC
        Kaolinite = new Material.Builder(InfinityCore.id("kaolinite"))
                .dust()
                .components(Aluminium, 2, Hydrogen, 4, Oxygen, 9, Silicon, 2)
                .color(0xffe9e9).secondaryColor(0xffa887).iconSet(MaterialIconSet.ROUGH)
                .flags(NO_SMASHING, NO_SMELTING, DISABLE_DECOMPOSITION, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES)
                .buildAndRegister().setFormula("Al2Si2O5(OH)4", true);

        PigIron = new Material.Builder(InfinityCore.id("pig_iron"))
                .dust()
                .ingot()
                .liquid(1775)
                .components(Iron, 1, Carbon, 1)
                .color(0xb5adae).secondaryColor(0x625457).iconSet(MaterialIconSet.DULL)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION, DISABLE_ALLOY_BLAST)
                .buildAndRegister();

        WeakSteel = new Material.Builder(InfinityCore.id("weak_steel"))
                .ingot()
                .liquid(1750)
                .components(Nickel, 1, BlackBronze, 1, Steel, 2)
                .color(0x8a8a8a).secondaryColor(0x5c5c5c).iconSet(MaterialIconSet.METALLIC)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION, DISABLE_ALLOY_BLAST)
                .buildAndRegister();

        WeakBlueSteel = new Material.Builder(InfinityCore.id("weak_blue_steel"))
                .dust()
                .ingot()
                .liquid(1800)
                .components(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4)
                .color(0x697fbd).secondaryColor(0x384b82).iconSet(MaterialIconSet.METALLIC)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION, DISABLE_ALLOY_BLAST)
                .buildAndRegister();

        WeakRedSteel = new Material.Builder(InfinityCore.id("weak_red_steel"))
                .dust()
                .ingot()
                .liquid(1800)
                .components(Brass, 1, RoseGold, 1, Steel, 2, BlackSteel, 4)
                .color(0xb55c5c).secondaryColor(0x7d3232).iconSet(MaterialIconSet.METALLIC)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION, DISABLE_ALLOY_BLAST)
                .buildAndRegister();
    }
}
