package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.common.mui.GTGuiTextures;
import com.gregtechceu.gtceu.common.mui.GTMuiWidgets;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ELECTRIC;

public class InfinityRecipeTypes {
    public static final GTRecipeType ROASTER_RECIPE = GTRecipeTypes
            .register(InfinityCore.id("roaster"), ELECTRIC)
            .setMaxIOSize(2, 2, 2, 2)
            .prepareBuilder(recipeBuilder -> recipeBuilder.duration(200).EUt(GTValues.VH[GTValues.LV]))
            .UI(builder -> builder.setProgressBar(GTGuiTextures.PROGRESS_ARROW)
                    .setMachineLayoutGridBuilder(FluidRecipeCapability.CAP, IO.OUT, (machine, layout) -> {
                        int slots = layout.getRecipeType().getMaxOutputs(FluidRecipeCapability.CAP);
                        int width = 1;
                        if (machine instanceof ITieredMachine tieredMachine) {
                            if (tieredMachine.getTier() < GTValues.ULV) {
                                slots = 0;
                                width = 0;
                            }
                        }

                        return GTMuiWidgets.createGrid(slots, width, true, 's');
                    }))
            .setIconSupplier(() -> InfinityMachines.ROASTER[GTValues.LV].asStack())
            .setSound(GTSoundEntries.FURNACE);

    public static void init() {
        for (GTRecipeType type : new GTRecipeType[] {
                ROASTER_RECIPE
        }) {
            type.setEUIO(IO.IN);
        }
    }
}
