package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;
import static net.terrafirmainfinity.core.common.data.material.InfinityMaterials.*;

public class InfinityOreMaterials {
    public static void register() {
        Acanthite = InfinityRegistrate.material("acanthite", builder -> builder
                .dust().ore()
                .color(0x6B7484).secondaryColor(0x1E234B)
                .components(Silver, 2, Sulfur, 1)
                .oreSmeltInto(Silver)
                .oreByproducts(Sulfur, Galena, Silver));

        Anthracite = InfinityRegistrate.material("anthracite", builder -> builder
                .gem()
                .color(0x241d29).iconSet(MaterialIconSet.METALLIC)
                .flags(FLAMMABLE, DISABLE_MATERIAL_RECIPES)
                .components(Carbon, 1));

        Arsenopyrite = InfinityRegistrate.material("arsenopyrite", builder -> builder
                .dust().ore()
                .color(0xA99543).secondaryColor(0x84611A)
                .components(Iron, 1, Arsenic, 1, Sulfur, 1)
                .separatedInto(Iron)
                .oreSmeltInto(Iron)
                .oreByproducts(Sulfur, Pyrite, Arsenic));

        Bismuthinite = InfinityRegistrate.material("bismuthinite", builder -> builder
                .dust().ore()
                .color(0x32c880).secondaryColor(0x344028).iconSet(MaterialIconSet.METALLIC)
                .components(Bismuth, 2, Sulfur, 3)
                .oreByproducts(Sulfur, Pyrite, Bismuth)
                .oreSmeltInto(Bismuth));

        Columbite = InfinityRegistrate.material("columbite", builder -> builder
                .dust().ore()
                .color(0x504C59).secondaryColor(0x2C0E2D)
                .components(Manganese, 1, Niobium, 2, Oxygen, 6)
                .oreByproducts(Manganese, Tantalite, Thorite));

        Cryolite = InfinityRegistrate.material("cryolite", builder -> builder
                .gem().ore()
                .liquid(new FluidBuilder().temperature(1285))
                .color(0xdedccd).secondaryColor(0xd3ccd1).iconSet(MaterialIconSet.EMERALD)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
                .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
                .oreByproducts(Cryolite, Sodium, Pyrite));

        Fluorite = InfinityRegistrate.material("fluorite", builder -> builder
                .gem().ore(2, 1)
                .color(0x48d5cc).secondaryColor(0x9034b2).iconSet(MaterialIconSet.LAPIS)
                .flags(NO_SMASHING, NO_SMELTING, CRYSTALLIZABLE, DISABLE_DECOMPOSITION)
                .components(Calcium, 1, Fluorine, 2)
                .oreByproducts(Fluorite, Calcium, Apatite));

        Hafnon = InfinityRegistrate.material("hafnon", builder -> builder
                .dust().ore()
                .color(0x523322).secondaryColor(0x3F1208)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hafnium, 1, Silicon, 1, Oxygen, 4)
                .oreByproducts(Zircon, Uraninite, RareEarth));

        Lignite = InfinityRegistrate.material("lignite", builder -> builder
                .gem()
                .color(0x2d2725).iconSet(MaterialIconSet.DULL)
                .flags(FLAMMABLE, DISABLE_MATERIAL_RECIPES)
                .components(Carbon, 1));

        Millerite = InfinityRegistrate.material("millerite", builder -> builder
                .dust().ore()
                .color(0xBCAA86).secondaryColor(0x3D2D24)
                .components(Nickel, 1, Sulfur, 1)
                .oreSmeltInto(Nickel)
                .oreByproducts(Pentlandite, Nickel, Sulfur));

        Petalite = InfinityRegistrate.material("petalite", builder -> builder
                .dust().ore()
                .color(0xFFBCBC).secondaryColor(0x9f558d)
                .flags(DISABLE_DECOMPOSITION)
                .components(Lithium, 1, Aluminium, 1, Silicon, 4, Oxygen, 10)
                .oreByproducts(Silicon, Petalite, Spodumene));

        Sperrylite = InfinityRegistrate.material("sperrylite", builder -> builder
                .dust().ore()
                .color(0xBBB7EF).secondaryColor(0x7F7DA3).iconSet(MaterialIconSet.METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(Platinum, 1, Arsenic, 2)
                .washedIn(Mercury)
                .oreByproducts(Nickel, Nickel, Pyrite, Platinum));

        Thorianite = InfinityRegistrate.material("thorianite", builder -> builder
                .dust().ore()
                .color(0x8A5223).secondaryColor(0x2E2823)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Oxygen, 2)
                .oreByproducts(Thorianite, Neodymium, RareEarth)
                .separatedInto(Neodymium));

        Thorite = InfinityRegistrate.material("thorite", builder -> builder
                .dust().ore()
                .color(0x985c39).secondaryColor(0x581F18)
                .flags(DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Uranium238, 1, Silicon, 1, Oxygen, 4)
                .oreByproducts(Uraninite, Zircon, RareEarth)
                .radioactiveHazard(1.0f)
                .formula("(Th,U)SiO4", true));

        Vanadinite = InfinityRegistrate.material("vanadinite", builder -> builder
                .dust().ore()
                .color(0xff7242).secondaryColor(0xff2d1a).iconSet(InfinityMaterialIconSet.CUBIC_RUBY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Lead, 5, Vanadium, 3, Oxygen, 12, Chlorine, 1)
                .oreByproducts(Galena, Wulfenite, Vanadium)
                .formula("Pb5(VO4)3Cl", true));

        Wolframite = InfinityRegistrate.material("wolframite", builder -> builder
                .dust().ore()
                .color(0x3A3141).secondaryColor(0x2E2227)
                .flags(DISABLE_DECOMPOSITION)
                .components(Iron, 1, Manganese, 1, Tungsten, 1, Oxygen, 4)
                .separatedInto(Iron)
                .oreByproducts(Iron, Manganese, Wolframite)
                .formula("(Fe,Mn)WO4", true));

        Zircon = InfinityRegistrate.material("zircon", builder -> builder
                .gem().ore()
                .color(0x6a3720).secondaryColor(0x3d1714).iconSet(MaterialIconSet.EMERALD)
                .flags(NO_SMELTING, DISABLE_DECOMPOSITION)
                .components(Zirconium, 1, Silicon, 1, Oxygen, 4)
                .oreByproducts(Zircon, Hafnon, Thorite));
    }
}
