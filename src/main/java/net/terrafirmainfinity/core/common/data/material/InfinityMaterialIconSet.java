package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;

public class InfinityMaterialIconSet {

    public static final MaterialIconSet HALLOWED = new MaterialIconSet(GTCEu.id("hallowed"), SHINY);
    public static final MaterialIconSet SOULSTAINED = new MaterialIconSet(GTCEu.id("soulstained"), BRIGHT);
    public static final MaterialIconSet MALIGNANT = new MaterialIconSet(GTCEu.id("malignant"), METALLIC);

    public static final MaterialIconSet BOTRYOIDAL = new MaterialIconSet(GTCEu.id("botryoidal"), DULL);
    public static final MaterialIconSet BOTRYOIDAL_METALLIC = new MaterialIconSet(GTCEu.id("botryoidal_metallic"), METALLIC);
    public static final MaterialIconSet CUBIC = new MaterialIconSet(GTCEu.id("cubic"), DULL);
    public static final MaterialIconSet CUBIC_METALLIC = new MaterialIconSet(GTCEu.id("cubic_metallic"), METALLIC);
    public static final MaterialIconSet CUBIC_RUBY = new MaterialIconSet(GTCEu.id("cubic_ruby"), RUBY);
    public static final MaterialIconSet CUBIC_SHINY = new MaterialIconSet(GTCEu.id("cubic_shiny"), SHINY);
    public static final MaterialIconSet OCTAHEDRAL_METALLIC = new MaterialIconSet(GTCEu.id("octahedral_metallic"), METALLIC);
    public static final MaterialIconSet TABULAR = new MaterialIconSet(GTCEu.id("tabular"), DULL);
    public static final MaterialIconSet TABULAR_FLINT = new MaterialIconSet(GTCEu.id("tabular_flint"), FLINT);
    public static final MaterialIconSet TABULAR_FINE = new MaterialIconSet(GTCEu.id("tabular_fine"), FINE);

    public static void init() {}
}