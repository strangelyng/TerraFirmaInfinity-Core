package net.terrafirmainfinity.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.materials.InfinityMaterials.*;

public class InfinityThirdDegreeMaterials {
    public static void register() {
        AndesiteAlloy = new Material.Builder(InfinityCore.id("andesite_alloy")) // TODO: Consider Removing
                .ingot()
                .color(0xd8dcc9).secondaryColor(0x7f9f9c).iconSet(MaterialIconSet.DULL) // 0xC7C8B8, 0x839689
                .flags(GENERATE_PLATE, GENERATE_GEAR, GENERATE_SMALL_GEAR, DECOMPOSITION_BY_CENTRIFUGING)
                .components(Andesite, 1, Iron, 1)
                .buildAndRegister();

        SoulstainedSteel = new Material.Builder(InfinityCore.id("soulstained_steel"))
                .ingot()
                .color(0xe98cff).secondaryColor(0x7b3bd3).iconSet(MaterialIconSet.BRIGHT) // 0xf28fff. 0xaa54ff, METALLIC
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_ROTOR, DISABLE_DECOMPOSITION)
                .components(BlackSteel, 1, Soul, 1)
                .buildAndRegister();
    }
}
