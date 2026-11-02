package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;

import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;
import static net.terrafirmainfinity.core.common.data.material.InfinityMaterials.*;

public class InfinityElementMaterials {
    public static void register() {
        Unknown = InfinityRegistrate.material("unknown", builder -> builder
                .color(0xFFFFFF)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION).formula("?")
        );
    }
}
