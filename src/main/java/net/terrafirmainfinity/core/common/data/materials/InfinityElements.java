package net.terrafirmainfinity.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import net.terrafirmainfinity.core.InfinityCore;

import java.util.Locale;

public class InfinityElements {
    public static final Element Sl = createAndRegister(1, 0, -1, null, "Soul", "Sl", false);
    public static final Element Ub = createAndRegister(121, 242, -1, null, "Unobtanium", "Ub", false);

    public static Element createAndRegister(long protons, long neutrons, long halfLifeSeconds, String decayTo,
                                            String name, String symbol, boolean isIsotope) {
        Element element = new Element(protons, neutrons, halfLifeSeconds, decayTo, name, symbol, isIsotope);
        GTRegistries.register(GTRegistries.ELEMENTS, InfinityCore.id(name.toLowerCase(Locale.ROOT)), element);
        return element;
    }

    public static void init() {};
}
