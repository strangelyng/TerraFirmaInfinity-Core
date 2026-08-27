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
        Bismuthinite = new Material.Builder(InfinityCore.id("bismuthinite"))
                .dust().ore()
                .color(0x32c880).secondaryColor(0x344028).iconSet(MaterialIconSet.METALLIC)
                .components(Bismuth, 2, Sulfur, 3)
                .addOreByproducts(Sulfur, Pyrite, Bismuth)
                .oreSmeltInto(Bismuth)
                .buildAndRegister();

        Cryolite = new Material.Builder(InfinityCore.id("cryolite"))
                .gem().ore()
                .liquid(new FluidBuilder().temperature(1285))
                .color(0xdedccd).secondaryColor(0xd3ccd1).iconSet(MaterialIconSet.EMERALD)
                .flags(NO_ORE_SMELTING, NO_ORE_SMELTING)
                .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
                .buildAndRegister();
    }
}
