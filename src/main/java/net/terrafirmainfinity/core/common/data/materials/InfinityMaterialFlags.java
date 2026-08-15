package net.terrafirmainfinity.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;

public class InfinityMaterialFlags {
    public static final MaterialFlag GENERATE_DOUBLE_INGOT = new MaterialFlag.Builder("generate_double_ingot")
            .build();

    public static final MaterialFlag GENERATE_CRYSTAL = new MaterialFlag.Builder("generate_crystal")
            .requireProps(PropertyKey.INGOT)
            .build();

    public static final MaterialFlag GENERATE_POWDER_COMPACTS = new MaterialFlag.Builder("generate_powder_compacts")
            .requireProps(PropertyKey.DUST)
            .build();

    /* Thanks to TFG for this workaround */
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
