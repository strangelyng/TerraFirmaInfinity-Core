package net.terrafirmainfinity.core.util;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.minecraft.world.item.ItemStack;

public class InfinityKJSHelper {
    public static ItemStack get(TagPrefix tagPrefix, Material material, int amount) {
        return ChemicalHelper.get(tagPrefix, material, amount);
    }

    public static ItemStack get(TagPrefix tagPrefix, Material material) {
        return get(tagPrefix, material, 1);
    }
}
