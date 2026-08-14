package net.terrafirmainfinity.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.HazardProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.common.data.GTMedicalConditions;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.materials.InfinityMaterialFlags.*;
import static net.terrafirmainfinity.core.common.data.materials.InfinityMaterials.*;

public class InfinityFirstDegreeMaterials {
    public static void register() {
        Acanthite = new Material.Builder(InfinityCore.id("acanthite"))
                .dust().ore()
                .color(0x747a8d)
                .components(Silver, 2, Sulfur, 1)
                .oreSmeltInto(Silver)
                .buildAndRegister();

        Alumina = new Material.Builder(InfinityCore.id("alumina"))
                .dust()
                .color(0xffffff).iconSet(MaterialIconSet.ROUGH)
                .components(Aluminium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION, EXCLUDE_PLATE_COMPRESSOR_RECIPE, GENERATE_PLATE)
                .blast(b -> b.temp(1873))
                .buildAndRegister();

        Anatase = new Material.Builder(InfinityCore.id("anatase"))
                .dust().ore()
                .color(0x35404d)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
                .components(Titanium, 1, Oxygen, 2)
                .buildAndRegister();

        Arsenopyrite = new Material.Builder(InfinityCore.id("arsenopyrite"))
                .dust().ore()
                .color(0x8f8259)
                .components(Iron, 1, Arsenic, 1, Sulfur, 1)
                .separatedInto(Iron)
                .oreSmeltInto(Iron)
                .buildAndRegister();

        Bismuthinite = new Material.Builder(InfinityCore.id("bismuthinite"))
                .dust().ore()
                .color(0x44583b).secondaryColor(0x1c3d32).iconSet(MaterialIconSet.METALLIC)
                .components(Bismuth, 2, Sulfur, 3)
                .oreSmeltInto(Bismuth)
                .buildAndRegister();

        BoricAcid = new Material.Builder(InfinityCore.id("boric_acid"))
                .liquid()
                .color(0x5fad4b)
                .components(Boron, 1, Hydrogen, 3, Oxygen, 3)
                .buildAndRegister();

        BoronCarbide = new Material.Builder(InfinityCore.id("boron_carbide"))
                .ingot()
                .color(0x2f3e2f).secondaryColor(0x2d2b2b).iconSet(MaterialIconSet.DULL)
                .flags(GENERATE_PLATE)
                .components(Boron, 4, Carbon, 1)
                .blast(b -> b.temp(2620, BlastProperty.GasTier.MID)
                        .blastStats(VA[MV], 1600)
                        .vacuumStats(VA[MV], 200))
                .buildAndRegister();

        BoronTrioxide = new Material.Builder(InfinityCore.id("boron_trioxide"))
                .dust()
                .colorAverage()
                .components(Boron, 2, Oxygen, 3)
                .buildAndRegister();

        Cryolite = new Material.Builder(InfinityCore.id("cryolite"))
                .gem().ore(2, 1)
                .liquid(new FluidBuilder().temperature(1285))
                .color(0xDEDCCD).secondaryColor(0xD3CCD1).iconSet(MaterialIconSet.EMERALD)
                .flags(NO_SMELTING)
                .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
                .buildAndRegister();

        Dawnstone = new Material.Builder(InfinityCore.id("dawnstone"))
                .ingot()
                .color(0xffba54).secondaryColor(0xe46d0e).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_RING)
                .components(Gold, 1, Copper, 1)
                .buildAndRegister();

        Fluorite = new Material.Builder(InfinityCore.id("fluorite"))
                .gem(1).ore(2, 1)
                .color(0x48d5cc).secondaryColor(0x9034b2).iconSet(MaterialIconSet.LAPIS)
                .flags(NO_SMASHING, NO_SMELTING, CRYSTALLIZABLE)
                .components(Calcium, 1, Fluorine, 2)
                .buildAndRegister();

        Hafnon = new Material.Builder(InfinityCore.id("hafnon"))
                .dust().ore()
                .color(0x523322)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
                .components(Hafnium, 1, Silicon, 1, Oxygen, 4)
                .buildAndRegister();

        HallowedGold = new Material.Builder(InfinityCore.id("hallowed_gold"))
                .ingot()
                .color(0xffd659).secondaryColor(0xca0023).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FINE_WIRE, GENERATE_RING, DISABLE_DECOMPOSITION)
                .components(Hallow, 1, Gold, 1)
                .buildAndRegister();

        Manganin = new Material.Builder(InfinityCore.id("manganin"))
                .ingot(1)
                .liquid(new FluidBuilder().temperature(1293))
                .color(0xf49764).secondaryColor(0x9f4c2c).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE, GENERATE_FINE_WIRE, GENERATE_FOIL)
                .components(Copper, 13, Nickel, 2, Manganese, 1)
                .cableProperties(V[MV], 1, 1)
                .buildAndRegister();

        Millerite = new Material.Builder(InfinityCore.id("millerite"))
                .dust().ore()
                .color(0x928771)
                .components(Nickel, 1, Sulfur, 1)
                .oreSmeltInto(Nickel)
                .buildAndRegister();

        Pewter = new Material.Builder(InfinityCore.id("pewter"))
                .ingot()
                .liquid(new FluidBuilder().temperature(500))
                .color(0xD6D6D4).secondaryColor(0xA6A48B).iconSet(MaterialIconSet.METALLIC)
                .components(Tin, 3, Lead, 2)
                .hazard(HazardProperty.HazardTrigger.INHALATION, GTMedicalConditions.WEAK_POISON)
                .buildAndRegister();

        Sylvite = new Material.Builder(InfinityCore.id("sylvite"))
                .gem(1).ore(2, 1)
                .color(0xff8b56).secondaryColor(0xd23d2d).iconSet(MaterialIconSet.FINE)
                .flags(NO_SMASHING, NO_SMELTING, CRYSTALLIZABLE)
                .components(Potassium, 1, Chlorine, 1)
                .buildAndRegister();

        Thorianite = new Material.Builder(InfinityCore.id("thorianite"))
                .dust().ore()
                .color(0x2e2823)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Oxygen, 2)
                .buildAndRegister();

        Thorite = new Material.Builder(InfinityCore.id("thorite"))
                .dust().ore()
                .color(0x985c39)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Uranium238, 1, Silicon, 1, Oxygen, 4)
                .radioactiveHazard(1.0f)
                .buildAndRegister()
                .setFormula("(Th,U)SiO4", true);

        Witherite = new Material.Builder(InfinityCore.id("witherite"))
                .gem().ore()
                .color(0xe2d8cf).iconSet(MaterialIconSet.QUARTZ)
                .flags(NO_SMELTING)
                .components(Barium, 1, Carbon, 1, Oxygen, 3)
                .buildAndRegister();

        Wolframite = new Material.Builder(InfinityCore.id("wolframite"))
                .dust().ore()
                .color(0x413138)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
                .components(Iron, 1, Manganese, 1, Tungsten, 1, Oxygen, 4)
                .separatedInto(Iron)
                .buildAndRegister()
                .setFormula("(Fe,Mn)WO4", true);

        Zircon = new Material.Builder(InfinityCore.id("zircon"))
                .gem().ore()
                .color(0x6a3720).secondaryColor(0x3d1714).iconSet(MaterialIconSet.EMERALD)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
                .components(Zirconium, 1, Silicon, 1, Oxygen, 4)
                .buildAndRegister();

        Zirconia = new Material.Builder(InfinityCore.id("zirconia"))
                .gem()
                .color(0xc8ffee).secondaryColor(0xc8ffff).iconSet(MaterialIconSet.DIAMOND)
                .components(Zirconium, 1, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();

        ZirconiumCarbide = new Material.Builder(InfinityCore.id("zirconium_carbide"))
                .ingot()
                .color(0x50603b).secondaryColor(0x2d2b2b).iconSet(MaterialIconSet.DULL)
                .flags(NO_WORKING, EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        GENERATE_PLATE, GENERATE_ROD, GENERATE_POWDER_COMPACTS)
                .components(Zirconium, 1, Carbon, 1)
                .blast(b -> b.temp(3805, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[EV], 1600)
                        .vacuumStats(VA[HV], 300))
                .buildAndRegister();

        ZirconiumDiboride = new Material.Builder(InfinityCore.id("zirconium_diboride"))
                .ingot()
                .color(0x748b55).secondaryColor(0x6d7058).iconSet(MaterialIconSet.DULL)
                .flags(NO_WORKING, EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        GENERATE_PLATE, GENERATE_FINE_WIRE)
                .components(Zirconium, 1, Boron, 2)
                .blast(b -> b.temp(3520, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[HV], 1600)
                        .vacuumStats(VA[HV], 300))
                .cableProperties(V[HV], 4, 1)
                .buildAndRegister();

        ZirconiumTetrachloride = new Material.Builder(InfinityCore.id("zirconium_tetrachloride"))
                .liquid(new FluidBuilder())
                .color(0xC6ED91).secondaryColor(0x4A691F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Zirconium, 1, Chlorine, 4)
                .buildAndRegister();
    }
}
