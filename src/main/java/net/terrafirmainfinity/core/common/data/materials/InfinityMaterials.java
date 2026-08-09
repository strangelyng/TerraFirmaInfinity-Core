package net.terrafirmainfinity.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public final class InfinityMaterials {
    /**
     * Element Metals
     */
    public static Material Unobtanium;

    /**
     * Alloys
     */
    public static Material Arsenic_Bronze;

    /**
     * Fantasy Alloys
     */
    public static Material Soulstained_Steel;

    /**
     * Misc
     */
    public static Material Andesite_Alloy; // TODO: To Remove
    public static Material Cryolite;

    public static void register() {
        Arsenic_Bronze = new Material.Builder(InfinityCore.id("arsenic_bronze"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1357))
                .components(Copper, 8, Tin, 1, Arsenic, 1)
                .color(0xFFB370).secondaryColor(0xD7371B).iconSet(MaterialIconSet.METALLIC)
                .toolStats(ToolProperty.Builder.of(3.0F, 2.0F, 192, 2)
                        .enchantability(18).build())
                .buildAndRegister();

        // TODO: TO REMOVE
        Andesite_Alloy = new Material.Builder(InfinityCore.id("andesite_alloy"))
                .ingot()
                .components(Andesite, 1, Iron, 1)
                .color(0xC7C8B8).secondaryColor(0x839689).iconSet(MaterialIconSet.DULL)
                .flags(GENERATE_PLATE, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister();

        Cryolite = new Material.Builder(InfinityCore.id("cryolite"))
                .gem()
                .liquid(new FluidBuilder().temperature(1285))
                .color(0xDEDCCD).secondaryColor(0xD3CCD1).iconSet(MaterialIconSet.EMERALD)
                .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
                .buildAndRegister();
    }

    public static void postInit() {
        ingot.setIgnored(Andesite_Alloy, () -> AllItems.ANDESITE_ALLOY); // TODO: TO REMOVE
        ingot.setIgnored(Brass, () -> AllItems.BRASS_INGOT);
        ingot.setIgnored(Zinc, () -> AllItems.ZINC_INGOT);

        ingot.setIgnored(BlackSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.BLACK_STEEL).get(Metal.ItemType.INGOT).get());
        ingot.setIgnored(BlueSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.BLUE_STEEL).get(Metal.ItemType.INGOT).get());
        ingot.setIgnored(RedSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.RED_STEEL).get(Metal.ItemType.INGOT).get());

        nugget.setIgnored(Brass, () -> AllItems.BRASS_NUGGET);
        nugget.setIgnored(Copper, () -> AllItems.COPPER_NUGGET);
        nugget.setIgnored(Zinc, () -> AllItems.ZINC_INGOT);

        plate.setIgnored(Brass, () -> AllItems.BRASS_SHEET);
        plate.setIgnored(Copper, () -> AllItems.COPPER_SHEET);
        plate.setIgnored(Gold, () -> AllItems.GOLDEN_SHEET);

        block.setIgnored(Brass, () -> AllBlocks.BRASS_BLOCK);
        block.setIgnored(Zinc, () -> AllBlocks.ZINC_BLOCK);
    }
}
