package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.material.InfinityMaterials.*;

public class InfinityOreMaterials {
    public static void register() {
        Acanthite = new Material.Builder(InfinityCore.id("acanthite"))
                .dust().ore()
                .color(0x747a8d)
                .components(Silver, 2, Sulfur, 1)
                .oreSmeltInto(Silver)
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
                .color(0x32c880).secondaryColor(0x344028).iconSet(MaterialIconSet.METALLIC)
                .components(Bismuth, 2, Sulfur, 3)
                .addOreByproducts(Sulfur, Pyrite, Bismuth)
                .oreSmeltInto(Bismuth)
                .buildAndRegister();

        Columbite = new Material.Builder(InfinityCore.id("columbite"))
                .dust().ore()
                .color(0x575959)
                .components(Manganese, 1, Niobium, 2, Oxygen, 6)
                .buildAndRegister();

        Cryolite = new Material.Builder(InfinityCore.id("cryolite"))
                .gem().ore()
                .liquid(new FluidBuilder().temperature(1285))
                .color(0xdedccd).secondaryColor(0xd3ccd1).iconSet(MaterialIconSet.EMERALD)
                .flags(NO_ORE_SMELTING, NO_ORE_SMELTING)
                .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
                .buildAndRegister();

        Fluorite = new Material.Builder(InfinityCore.id("fluorite"))
                .gem().ore(2, 1)
                .color(0x48d5cc).secondaryColor(0x9034b2).iconSet(MaterialIconSet.LAPIS)
                .flags(NO_SMASHING, NO_SMELTING, CRYSTALLIZABLE, DISABLE_DECOMPOSITION)
                .components(Calcium, 1, Fluorine, 2)
                .buildAndRegister();

        Hafnon = new Material.Builder(InfinityCore.id("hafnon"))
                .dust().ore()
                .color(0x523322)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
                .components(Hafnium, 1, Silicon, 1, Oxygen, 4)
                .buildAndRegister();

        Millerite = new Material.Builder(InfinityCore.id("millerite"))
                .dust().ore()
                .color(0x928771)
                .components(Nickel, 1, Sulfur, 1)
                .oreSmeltInto(Nickel)
                .buildAndRegister();

        Petalite = new Material.Builder(InfinityCore.id("petalite"))
                .dust().ore()
                .color(0xFFBCBC).secondaryColor(0x9f558d) // 0xfff1de, 0xf38d8d
                .components(Lithium, 1, Aluminium, 1, Silicon, 4, Oxygen, 10)
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
    }
}
