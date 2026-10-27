package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import net.minecraft.core.Holder;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityMaterialIconSet {

    public static final Holder<MaterialIconSet> HALLOWED = InfinityRegistrate.materialIconSet("hallowed", SHINY);
    public static final Holder<MaterialIconSet> SOULSTAINED = InfinityRegistrate.materialIconSet("soulstained", BRIGHT);
    public static final Holder<MaterialIconSet> MALIGNANT = InfinityRegistrate.materialIconSet("malignant", METALLIC);

    public static final Holder<MaterialIconSet> BOTRYOIDAL = InfinityRegistrate.materialIconSet("botryoidal", DULL);
    public static final Holder<MaterialIconSet> BOTRYOIDAL_METALLIC = InfinityRegistrate.materialIconSet("botryoidal_metallic", METALLIC);
    public static final Holder<MaterialIconSet> CUBIC = InfinityRegistrate.materialIconSet("cubic", DULL);
    public static final Holder<MaterialIconSet> CUBIC_METALLIC = InfinityRegistrate.materialIconSet("cubic_metallic", METALLIC);
    public static final Holder<MaterialIconSet> CUBIC_RUBY = InfinityRegistrate.materialIconSet("cubic_ruby", RUBY);
    public static final Holder<MaterialIconSet> CUBIC_SHINY = InfinityRegistrate.materialIconSet("cubic_shiny", SHINY);
    public static final Holder<MaterialIconSet> OCTAHEDRAL_METALLIC = InfinityRegistrate.materialIconSet("octahedral_metallic", METALLIC);
    public static final Holder<MaterialIconSet> OCTAHEDRAL_SHINY = InfinityRegistrate.materialIconSet("octahedral_shiny", SHINY);
    public static final Holder<MaterialIconSet> TABULAR = InfinityRegistrate.materialIconSet("tabular", DULL);
    public static final Holder<MaterialIconSet> TABULAR_FLINT = InfinityRegistrate.materialIconSet("tabular_flint", FLINT);
    public static final Holder<MaterialIconSet> TABULAR_FINE = InfinityRegistrate.materialIconSet("tabular_fine", FINE);

    public static void init() {}
}