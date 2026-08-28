package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;

public class InfinityMaterialIconSet {

    public static final MaterialIconSet HALLOWED = new MaterialIconSet(GTCEu.id("hallowed"), SHINY);
    public static final MaterialIconSet SOULSTAINED = new MaterialIconSet(GTCEu.id("soulstained"), BRIGHT);
    public static final MaterialIconSet MALIGNANT = new MaterialIconSet(GTCEu.id("malignant"), METALLIC);

    public static void init() {}
}