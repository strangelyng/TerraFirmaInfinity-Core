package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.common.data.GTElements;
import net.terrafirmainfinity.core.InfinityCore;

public class InfinityElements {
    public static final Element Ember = GTElements.createAndRegister(InfinityCore.id("ember"), 1, 0, -1, null,
            "Ember", "Em", false);

    public static void init() {}
}
