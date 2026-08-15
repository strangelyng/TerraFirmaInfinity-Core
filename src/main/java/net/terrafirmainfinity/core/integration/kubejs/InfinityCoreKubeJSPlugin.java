package net.terrafirmainfinity.core.integration.kubejs;

import dev.latvian.mods.kubejs.plugin.ClassFilter;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;

public class InfinityCoreKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void registerClasses(ClassFilter filter) {
        filter.allow("net.terrafirmainfinity.core");
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
//        bindings.add("InfinityMaterials", InfinityMaterials.class);
//        bindings.add("InfinityElements", InfinityElements.class);
//        bindings.add("InfinityMachines", InfinityMachines.class);
//        bindings.add("InfinityRecipeTypes", InfinityRecipeTypes.class);
//        bindings.add("InfinityMaterialFlags", InfinityMaterialFlags.class);
//        bindings.add("InfinityMaterialIconTypes", InfinityMaterialIconType.class);
//        bindings.add("InfinityTagPrefixes", InfinityTagPrefix.class);
    }
}
