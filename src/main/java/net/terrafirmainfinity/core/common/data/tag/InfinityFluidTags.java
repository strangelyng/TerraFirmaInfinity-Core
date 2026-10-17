package net.terrafirmainfinity.core.common.data.tag;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class InfinityFluidTags {
    public static final TagKey<Fluid> MOLTEN = TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath("c", "molten"));

    public static void init(RegistrateTagsProvider<Fluid> provider) {

    }
}
