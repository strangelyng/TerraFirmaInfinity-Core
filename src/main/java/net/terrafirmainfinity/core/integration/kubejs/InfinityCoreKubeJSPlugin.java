package net.terrafirmainfinity.core.integration.kubejs;

import dev.latvian.mods.kubejs.plugin.ClassFilter;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.terrafirmainfinity.core.common.data.*;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterialFlags;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterials;

public class InfinityCoreKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void registerClasses(ClassFilter filter) {
        filter.allow("net.terrafirmainfinity.core");
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("InfinityMaterials", InfinityMaterials.class);
//        bindings.add("InfinityElements", InfinityElements.class);
        bindings.add("InfinityMachines", InfinityMachines.class);
        bindings.add("InfinityRecipeTypes", InfinityRecipeTypes.class);
        bindings.add("InfinityPropertyKey", InfinityPropertyKeys.class);
        bindings.add("InfinityMaterialFlags", InfinityMaterialFlags.class);
        bindings.add("InfinityMaterialIconType", InfinityMaterialIconType.class);
        bindings.add("InfinityTagPrefix", InfinityTagPrefix.class);
    }
}
