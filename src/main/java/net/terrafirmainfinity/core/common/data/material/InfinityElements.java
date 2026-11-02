package net.terrafirmainfinity.core.common.data.material;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.tterrag.registrate.util.entry.RegistryEntry;

import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityElements {
    public static final RegistryEntry<Element, Element> Em = InfinityRegistrate.element("ember", 1, 0, "Ember", "Em");

    public static final RegistryEntry<Element, Element> Unknown = InfinityRegistrate.element("unknown", 75, 75, "Unknown", "?");

    public static void init() {}
}
