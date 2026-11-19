package net.terrafirmainfinity.core.util;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.registry.registrate.entry.MaterialRegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.terrafirmainfinity.core.common.data.InfinityMaps;

public class InfinityKJSHelper {
    public static ItemStack get(TagPrefix tagPrefix, Material material, int amount) {
        return ChemicalHelper.get(tagPrefix, material, amount);
    }

    public static ItemStack get(TagPrefix tagPrefix, Material material) {
        return get(tagPrefix, material, 1);
    }

    public static void addCustomDimensionAir(String dimension, MaterialRegistryEntry material) {
        addCustomDimensionAir(dimension, material, 0);
    }

    public static void addCustomDimensionAir(String dimension, MaterialRegistryEntry material, int dimTier) {
        ResourceKey<Level> dimensionKey = ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse(dimension));

        InfinityMaps.CUSTOM_DIMENSION_AIR_MAP.put(dimensionKey, new InfinityMaps.DimensionAir(material, dimTier));
    }
}
