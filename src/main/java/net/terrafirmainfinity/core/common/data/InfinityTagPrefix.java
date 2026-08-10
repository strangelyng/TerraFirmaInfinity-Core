package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;
import net.terrafirmainfinity.core.common.data.materials.InfinityMaterialFlags;
import net.terrafirmainfinity.core.common.data.materials.InfinityMaterialIconType;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.Conditions.*;

public class InfinityTagPrefix {
    public static TagPrefix doubleIngot;

    public static TagPrefix poorRawOre;
    public static TagPrefix richRawOre;

    public static void init() {
        doubleIngot = new TagPrefix("doubleIngot")
                .defaultTagPath("double_ingots/%s")
                .unformattedTagPath("double_ingots")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 2)
                .materialIconType(MaterialIconType.ingotDouble)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(material -> material.hasFlag(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT));

        poorRawOre = new TagPrefix("poorRawOre")
                .idPattern("poor_raw_%s")
                .defaultTagPath("poor_raw_materials/%s")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .unformattedTagPath("poor_raw_materials")
                .materialIconType(InfinityMaterialIconType.poorRawOre)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasOreProperty);

        richRawOre = new TagPrefix("richRawOre")
                .idPattern("rich_raw_%s")
                .defaultTagPath("rich_raw_materials/%s")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .unformattedTagPath("rich_raw_materials")
                .materialIconType(InfinityMaterialIconType.richRawOre)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasOreProperty);
    }
}
