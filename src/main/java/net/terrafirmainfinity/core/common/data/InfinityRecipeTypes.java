package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.gui.GTRecipeTypeUILayout;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.common.mui.GTGuiTextures;
import com.gregtechceu.gtceu.common.mui.GTMuiWidgets;
import net.minecraft.util.Mth;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.ui.InfinityGuiTextures;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ELECTRIC;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;

public class InfinityRecipeTypes {
    public static final GTRecipeType ROASTER_RECIPE = GTRecipeTypes.register(InfinityCore.id("roaster"), ELECTRIC)
            .setMaxIOSize(3, 2, 2, 3)
            .UI(builder -> builder.setProgressBar(GTGuiTextures.PROGRESS_ARROW)
                    .setMachineLayoutGridBuilder(ItemRecipeCapability.CAP, IO.IN, ((machine, layout) -> {
                        int slots = layout.getRecipeType().getMaxInputs(ItemRecipeCapability.CAP);
                        int width = 3;
                        if (machine instanceof SimpleSteamMachine) {
                            slots = 2;
                            width = 2;
                        }
                        return GTMuiWidgets.createGrid(slots, width, false, 's');
                    }))
                    .setMachineLayoutGridBuilder(ItemRecipeCapability.CAP, IO.OUT, ((machine, layout) -> {
                        int slots = layout.getRecipeType().getMaxOutputs(ItemRecipeCapability.CAP);
                        int width = 3;
                        if (machine instanceof SimpleSteamMachine) {
                            slots = 1;
                            width = 1;
                        }
                        return GTMuiWidgets.createGrid(slots, width, true, 's');
                    }))
                    .setMachineLayoutGridBuilder(FluidRecipeCapability.CAP, IO.IN, ((machine, layout) -> {
                        int slots = layout.getRecipeType().getMaxInputs(FluidRecipeCapability.CAP);
                        int width = 3;
                        if (machine instanceof SimpleSteamMachine) {
                            slots = 0;
                            width = 0;
                        }
                        return GTMuiWidgets.createGrid(slots, width, false, 's');
                    }))
                    .setMachineLayoutGridBuilder(FluidRecipeCapability.CAP, IO.OUT, ((machine, layout) -> {
                        int slots = layout.getRecipeType().getMaxOutputs(FluidRecipeCapability.CAP);
                        int width = 3;
                        if (machine instanceof SimpleSteamMachine) {
                            slots = 0;
                            width = 0;
                        }
                        return GTMuiWidgets.createGrid(slots, width, true, 's');
                    }))
                    .setItemSlotOverlay(IO.IN, 0, GTGuiTextures.FURNACE_OVERLAY_1)
                    .setFluidSlotOverlay(IO.OUT, 0, GTGuiTextures.VIAL_OVERLAY_2))
            .setIconSupplier(() -> InfinityMachines.ROASTER[GTValues.LV].asStack())
            .setSound(GTSoundEntries.FURNACE);

    public static final GTRecipeType ELECTROLYTIC_CELL_RECIPE = GTRecipeTypes.register(InfinityCore.id("electrolytic_cell"), ELECTRIC)
            .setMaxIOSize(4, 3, 3, 4)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.LV]))
            .UI(builder -> builder.setProgressBar(GTGuiTextures.PROGRESS_EXTRACT)
                    .setRecipeViewerLayoutGridBuilder(ItemRecipeCapability.CAP, IO.IN, (layout) -> {
                        int slots = layout.getRecipeType().getMaxInputs(ItemRecipeCapability.CAP);
                        return GTMuiWidgets.createGrid(slots, (int) Mth.sqrt(slots), false, 's');
                    })
                    .setItemSlotsOverlay(IO.IN, 0, 1, InfinityGuiTextures.ELECTRODE_OVERLAY_1)
                    .setItemSlotsOverlay(IO.IN, 2, 3, GTGuiTextures.CANISTER_OVERLAY)
                    .setFluidSlotOverlay(IO.IN, 0, GTGuiTextures.LIGHTNING_OVERLAY_2)
                    .setItemSlotOverlay(IO.OUT, 0, GTGuiTextures.VIAL_OVERLAY_1)
                    .setFluidSlotOverlay(IO.OUT, 0, GTGuiTextures.VIAL_OVERLAY_2))
            .setSound(GTSoundEntries.ELECTROLYZER);

    public static final GTRecipeType SPIRAL_SEPARATOR_RECIPE = GTRecipeTypes.register(InfinityCore.id("spiral_separator"), MULTIBLOCK)
            .setMaxIOSize(1, 6, 1, 3)
            .UI(builder -> builder.setProgressBar(GTGuiTextures.PROGRESS_ARROW))
            .setSound(GTSoundEntries.BATH);

    public static final GTRecipeType METALLURGICAL_CONVERTER_RECIPE = GTRecipeTypes.register(InfinityCore.id("metallurgical_converter"), MULTIBLOCK)
            .setMaxIOSize(3, 2, 3, 2)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.LV]))
            .UI(builder -> builder.setProgressBar(GTGuiTextures.PROGRESS_ARROW))
            .setSound(GTSoundEntries.FURNACE);

    public static final GTRecipeType FLASH_SMELTING_RECIPE = GTRecipeTypes.register(InfinityCore.id("flash_smelting"), MULTIBLOCK)
            .setMaxIOSize(2, 0, 2, 3)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.MV]))
            .UI(builder -> builder.setProgressBar(GTGuiTextures.PROGRESS_ARROW_MULTIPLE))
            .setSound(GTSoundEntries.FURNACE);

    public static void init() {
        for (GTRecipeType type : new GTRecipeType[] {
                ROASTER_RECIPE, ELECTROLYTIC_CELL_RECIPE, SPIRAL_SEPARATOR_RECIPE, METALLURGICAL_CONVERTER_RECIPE, FLASH_SMELTING_RECIPE
        }) {
            type.setEUIO(IO.IN);
        }

        // TODO: Arc Furnace Overhaul
        GTRecipeTypes.ARC_FURNACE_RECIPES.setMaxIOSize(4, 9, 1, 1);
        var type = GTRecipeTypes.ARC_FURNACE_RECIPES;
        var builder = new GTRecipeTypeUILayout.Builder(type);
        builder.setProgressBar(GTGuiTextures.PROGRESS_ARROW)
                .setMachineLayoutGridBuilder(ItemRecipeCapability.CAP, IO.OUT,
                        (machine, layout) -> {
                            int slots = layout.getRecipeType().getMaxOutputs(ItemRecipeCapability.CAP);
                            if (machine instanceof ITieredMachine tieredMachine) {
                                if (tieredMachine.getTier() < GTValues.EV) {
                                    slots = 4;
                                }
                            }
                            return GTMuiWidgets.createGrid(slots, (int) Mth.sqrt(slots), true, 's');
                        })
                .setMachineLayoutGridBuilder(ItemRecipeCapability.CAP, IO.IN, ((machine, layout) -> {
                            int slots = layout.getRecipeType().getMaxInputs(ItemRecipeCapability.CAP);
                            return GTMuiWidgets.createGrid(slots, (int) Mth.sqrt(slots), false, 's');
                        }))
                .setRecipeViewerLayoutGridBuilder(ItemRecipeCapability.CAP, IO.IN, ((layout) -> {
                            int slots = layout.getRecipeType().getMaxInputs(ItemRecipeCapability.CAP);
                            return GTMuiWidgets.createGrid(slots, (int) Mth.sqrt(slots), false, 's');
                        }));
        GTRecipeTypes.ARC_FURNACE_RECIPES.setUiLayout(builder.build());

        // TODO: Electrolyzer Overhaul
        GTRecipeTypes.ELECTROLYZER_RECIPES.setMaxIOSize(4, 3, 1, 3);
        type = GTRecipeTypes.ELECTROLYZER_RECIPES;
        builder = new GTRecipeTypeUILayout.Builder(type);
        builder.setProgressBar(GTGuiTextures.PROGRESS_EXTRACT)
                .setMachineLayoutGridBuilder(ItemRecipeCapability.CAP, IO.IN, ((machine, layout) -> {
                    int slots = layout.getRecipeType().getMaxInputs(ItemRecipeCapability.CAP);
                    return GTMuiWidgets.createGrid(slots, (int) Mth.sqrt(slots), false, 's');
                }))
                .setRecipeViewerLayoutGridBuilder(ItemRecipeCapability.CAP, IO.IN, ((layout) -> {
                    int slots = layout.getRecipeType().getMaxInputs(ItemRecipeCapability.CAP);
                    return GTMuiWidgets.createGrid(slots, (int) Mth.sqrt(slots), false, 's');
                }))
                .setItemSlotsOverlay(IO.IN, 0, 1, InfinityGuiTextures.ELECTRODE_OVERLAY_1)
                .setItemSlotsOverlay(IO.IN, 2, 3, GTGuiTextures.CANISTER_OVERLAY)
                .setFluidSlotOverlay(IO.IN, 0, GTGuiTextures.LIGHTNING_OVERLAY_2);
        GTRecipeTypes.ELECTROLYZER_RECIPES.setUiLayout(builder.build());
    }
}
