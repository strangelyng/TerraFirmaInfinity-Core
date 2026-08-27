package net.terrafirmainfinity.core.common.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.IElectricItem;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.terrafirmainfinity.core.common.data.item.InfinityToolTypes;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterialFlags;
import org.jetbrains.annotations.NotNull;

import static com.gregtechceu.gtceu.data.recipe.generated.ToolRecipeHandler.powerUnitItems;

public class InfinityToolRecipeHandler {
    public static void run(@NotNull RecipeOutput provider, @NotNull Material material) {
        ToolProperty property = material.getProperty(PropertyKey.TOOL);
        if (property == null) {
            return;
        }

        processElectricTool(provider, property, material);
    }

    /**
     * Uses flags to ensure that recipes are generated for all tiers, even if there is no LV tool for that material
     */
    public static void processElectricTool(RecipeOutput provider, ToolProperty property, Material material) {
        final int voltageMultiplier = material.getBlastTemperature() > 2800 ? GTValues.VA[GTValues.LV] :
                GTValues.VA[GTValues.ULV];
        TagPrefix toolPrefix;

        if (material.hasFlag(MaterialFlags.GENERATE_PLATE)) {
            final MaterialEntry plate = new MaterialEntry(TagPrefix.plate, material);
            final MaterialEntry steelPlate = new MaterialEntry(TagPrefix.plate, GTMaterials.Steel);
            final MaterialEntry steelRing = new MaterialEntry(TagPrefix.ring, GTMaterials.Steel);

            if (material.hasFlag(InfinityMaterialFlags.GENERATE_DRILL_HEAD)) {
                toolPrefix = TagPrefix.toolHeadDrill;
                VanillaRecipeHelper.addShapedRecipe(provider, String.format("drill_head_%s", material.getName()),
                        ChemicalHelper.get(toolPrefix, material),
                        "XSX", "XSX", "ShS",
                        'X', plate,
                        'S', steelPlate);

                addElectricToolRecipe(provider, toolPrefix,
                        new GTToolType[]{GTToolType.DRILL_MV, GTToolType.DRILL_HV, GTToolType.DRILL_EV, GTToolType.DRILL_IV}, material);
            }

            if (material.hasFlag(InfinityMaterialFlags.GENERATE_CHAINSAW_HEAD)) {
                toolPrefix = TagPrefix.toolHeadChainsaw;
                VanillaRecipeHelper.addShapedRecipe(provider, String.format("chainsaw_head_%s", material.getName()),
                        ChemicalHelper.get(toolPrefix, material),
                        "SRS", "XhX", "SRS",
                        'X', plate,
                        'S', steelPlate,
                        'R', steelRing);

                addElectricToolRecipe(provider, toolPrefix,
                        new GTToolType[]{InfinityToolTypes.CHAINSAW_MV, GTToolType.CHAINSAW_HV, InfinityToolTypes.CHAINSAW_EV, GTToolType.CHAINSAW_IV}, material);
            }

            if (material.hasFlag(InfinityMaterialFlags.GENERATE_WRENCH_HEAD)) {
                toolPrefix = TagPrefix.toolHeadWrench;

                addElectricToolRecipe(provider, toolPrefix,
                        new GTToolType[]{InfinityToolTypes.WRENCH_MV, GTToolType.WRENCH_HV, InfinityToolTypes.WRENCH_EV, GTToolType.WRENCH_IV}, material);
            }

            if (material.hasFlag(InfinityMaterialFlags.GENERATE_WIRE_CUTTER_HEAD)) {
                toolPrefix = TagPrefix.toolHeadWireCutter;

                addElectricToolRecipe(provider, toolPrefix,
                        new GTToolType[]{InfinityToolTypes.WIRE_CUTTER_MV, GTToolType.WIRE_CUTTER_HV, InfinityToolTypes.WIRE_CUTTER_EV, GTToolType.WIRE_CUTTER_IV}, material);
            }

            if (material.hasFlag(InfinityMaterialFlags.GENERATE_BUZZSAW_BLADE)) {
                toolPrefix = TagPrefix.toolHeadBuzzSaw;

                addElectricToolRecipe(provider, toolPrefix,
                        new GTToolType[]{InfinityToolTypes.BUZZSAW_MV, InfinityToolTypes.BUZZSAW_HV, InfinityToolTypes.BUZZSAW_EV, InfinityToolTypes.BUZZSAW_IV}, material);

                VanillaRecipeHelper.addShapedRecipe(provider, String.format("buzzsaw_blade_%s", material.getName()),
                        ChemicalHelper.get(toolPrefix, material),
                        "sXh", "X X", "fXx",
                        'X', plate);

                GTRecipeTypes.LATHE_RECIPES.recipeBuilder("buzzsaw_gear_" + material.getName())
                        .inputItems(TagPrefix.plateDouble, material)
                        .outputItems(toolPrefix, material)
                        .duration((int) material.getMass() * 4)
                        .EUt(8L * voltageMultiplier)
                        .save(provider);

            }

            if (material.hasFlag(InfinityMaterialFlags.GENERATE_SCREWDRIVER_HEAD)) {
                toolPrefix = TagPrefix.toolHeadScrewdriver;

                addElectricToolRecipe(provider, toolPrefix,
                        new GTToolType[]{InfinityToolTypes.SCREWDRIVER_MV, GTToolType.SCREWDRIVER_HV, InfinityToolTypes.SCREWDRIVER_EV, GTToolType.SCREWDRIVER_IV}, material);
            }
        }
    }

    private static void addElectricToolRecipe(@NotNull RecipeOutput provider, @NotNull TagPrefix toolHead,
                                              @NotNull GTToolType @NotNull [] toolItems,
                                              @NotNull Material material) {
        for (GTToolType toolType : toolItems) {
            if (!material.getProperty(PropertyKey.TOOL).hasType(toolType)) continue;

            int tier = toolType.electricTier;
            ItemStack powerUnitStack = powerUnitItems.get(tier).asStack();
            IElectricItem powerUnit = GTCapabilityHelper.getElectricItem(powerUnitStack);
            ItemStack tool = GTMaterialItems.TOOL_ITEMS.get(material, toolType).get().get(0, powerUnit.getMaxCharge());
            VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider,
                    true, true, true,
                    String.format("%s_%s", material.getName(), toolType.name),
                    Ingredient.of(powerUnitStack),
                    tool,
                    "wHd", " U ",
                    'H', new MaterialEntry(toolHead, material),
                    'U', powerUnitStack);
        }
    }
}
