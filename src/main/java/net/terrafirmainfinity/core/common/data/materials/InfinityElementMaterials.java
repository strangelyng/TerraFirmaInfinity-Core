package net.terrafirmainfinity.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static net.terrafirmainfinity.core.common.data.materials.InfinityMaterials.*;

public class InfinityElementMaterials {
    public static void register() {
        Ember = new Material.Builder(InfinityCore.id("ember"))
                .gem()
                .gas(1300)
                .color(0xff7327).secondaryColor(0xe60000).iconSet(MaterialIconSet.RUBY) // TODO: Custom Material Set
                .flags(PHOSPHORESCENT)
                .element(InfinityElements.Ember)
                .buildAndRegister();

        Hallow = new Material.Builder(InfinityCore.id("hallow"))
                .element(InfinityElements.Hallow)
                .buildAndRegister();

        Malice = new Material.Builder(InfinityCore.id("malice"))
                .element(InfinityElements.Malice)
                .buildAndRegister();

        Soul = new Material.Builder(InfinityCore.id("soul"))
                .element(InfinityElements.Soul)
                .buildAndRegister();

        Unknown = new Material.Builder(InfinityCore.id("unknown"))
                .element(InfinityElements.Unknown)
                .buildAndRegister();
    }
}
