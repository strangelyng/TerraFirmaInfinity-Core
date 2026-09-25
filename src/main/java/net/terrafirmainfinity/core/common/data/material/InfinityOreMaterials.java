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
                .color(0x6B7484).secondaryColor(0x1E234B)
                .components(Silver, 2, Sulfur, 1)
                .oreSmeltInto(Silver)
                .buildAndRegister();

        Arsenopyrite = new Material.Builder(InfinityCore.id("arsenopyrite"))
                .dust().ore()
                .color(0xA99543).secondaryColor(0x84611A)
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
                .color(0x504C59).secondaryColor(0x2C0E2D)
                .components(Manganese, 1, Niobium, 2, Oxygen, 6)
                .buildAndRegister();

        Cryolite = new Material.Builder(InfinityCore.id("cryolite"))
                .gem().ore()
                .liquid(new FluidBuilder().temperature(1285))
                .color(0xdedccd).secondaryColor(0xd3ccd1).iconSet(MaterialIconSet.EMERALD)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
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
                .color(0x523322).secondaryColor(0x3F1208)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hafnium, 1, Silicon, 1, Oxygen, 4)
                .buildAndRegister();

        Millerite = new Material.Builder(InfinityCore.id("millerite"))
                .dust().ore()
                .color(0xBCAA86).secondaryColor(0x3D2D24)
                .components(Nickel, 1, Sulfur, 1)
                .oreSmeltInto(Nickel)
                .buildAndRegister();

        Petalite = new Material.Builder(InfinityCore.id("petalite"))
                .dust().ore()
                .color(0xFFBCBC).secondaryColor(0x9f558d) // 0xfff1de, 0xf38d8d
                .flags(DISABLE_DECOMPOSITION)
                .components(Lithium, 1, Aluminium, 1, Silicon, 4, Oxygen, 10)
                .buildAndRegister();

        Sperrylite = new Material.Builder(InfinityCore.id("sperrylite"))
                .dust().ore()
                .color(0xBBB7EF).secondaryColor(0x7F7DA3).iconSet(MaterialIconSet.METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Platinum, 1, Arsenic, 2)
                .buildAndRegister();

        Thorianite = new Material.Builder(InfinityCore.id("thorianite"))
                .dust().ore()
                .color(0x8A5223).secondaryColor(0x2E2823)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Oxygen, 2)
                .buildAndRegister();

        Thorite = new Material.Builder(InfinityCore.id("thorite"))
                .dust().ore()
                .color(0x985c39).secondaryColor(0x581F18)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Uranium238, 1, Silicon, 1, Oxygen, 4)
                .radioactiveHazard(1.0f)
                .buildAndRegister()
                .setFormula("(Th,U)SiO4", true);

        Wolframite = new Material.Builder(InfinityCore.id("wolframite"))
                .dust().ore()
                .color(0x3A3141).secondaryColor(0x2E2227)
                .flags(DISABLE_DECOMPOSITION)
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
