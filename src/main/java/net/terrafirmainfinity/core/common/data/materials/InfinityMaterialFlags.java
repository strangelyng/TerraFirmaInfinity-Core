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

    public static void init() {}
}
