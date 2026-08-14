package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.mui.GTGuiTextures;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class InfinityRecipeTypes {
    public static final GTRecipeType HPHT_VACUUM_PRESS_RECIPE = GTRecipeTypes
            .register(InfinityCore.id("hpht_vacuum_press"), ELECTRIC)
            .setMaxIOSize(2, 1, 1, 1)
            .UI(builder -> builder.setProgressBar(GTGuiTextures.PROGRESS_ARROW));

    public static void init() {
        for (GTRecipeType type : new GTRecipeType[] {
                HPHT_VACUUM_PRESS_RECIPE
        }) {
            type.setEUIO(IO.IN);
        }
    }
}
