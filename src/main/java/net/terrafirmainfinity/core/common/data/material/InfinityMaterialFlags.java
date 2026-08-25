package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;

public class InfinityMaterialFlags {
    // For Materials that can be cast into tools (does not affect ingot casting, all metals with a TFCProperty can be cast into ingots)
    public static final MaterialFlag TFC_CASTABLE = new MaterialFlag.Builder("tfc_castable")
            .build();

    public static final MaterialFlag HAS_TFC_OXIDATION = new MaterialFlag.Builder("has_tfc_oxidation")
            .build();

    public static final MaterialFlag GENERATE_DOUBLE_INGOT = new MaterialFlag.Builder("generate_double_ingot")
            .build();

    /* Allow Generation of Electric Tool Heads without requiring the material to have the related ToolProperty */
    public static final MaterialFlag GENERATE_BUZZSAW_BLADE = new MaterialFlag.Builder("generate_buzzsaw_blade")
            .build();
    public static final MaterialFlag GENERATE_SCREWDRIVER_HEAD = new MaterialFlag.Builder("generate_screwdriver_head")
            .build();
    public static final MaterialFlag GENERATE_DRILL_HEAD = new MaterialFlag.Builder("generate_drill_head")
            .build();
    public static final MaterialFlag GENERATE_CHAINSAW_HEAD = new MaterialFlag.Builder("generate_chainsaw_head")
            .build();
    public static final MaterialFlag GENERATE_WRENCH_HEAD = new MaterialFlag.Builder("generate_wrench_head")
            .build();
    public static final MaterialFlag GENERATE_WIRE_CUTTER_HEAD = new MaterialFlag.Builder("generate_wire_cutter_head")
            .build();

    public static void init() {}
}
