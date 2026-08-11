package net.terrafirmainfinity.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.common.data.GTElements;
import net.terrafirmainfinity.core.InfinityCore;

public class InfinityElements {
    public static final Element Malice = GTElements.createAndRegister(InfinityCore.id("malice"), 1, 0, -1, null,
            "Malice", "Ml", false);
    public static final Element Hallow = GTElements.createAndRegister(InfinityCore.id("hallow"), 1, 0, -1, null,
            "Hallow", "Ha", false);
    public static final Element Soul = GTElements.createAndRegister(InfinityCore.id("soul"), 1, 0, -1, null,
            "Soul", "So", false);

    public static final Element Ember = GTElements.createAndRegister(InfinityCore.id("ember"), 1, 0, -1, null,
            "Ember", "Em", false);

    public static final Element Unknown = GTElements.createAndRegister(InfinityCore.id("unknown"), 1, 0, -1, null,
            "Unknown", "?", false);

    public static void init() {}
}
