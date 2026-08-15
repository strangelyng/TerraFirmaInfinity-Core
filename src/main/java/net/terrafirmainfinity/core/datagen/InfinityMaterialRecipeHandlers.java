package net.terrafirmainfinity.core.datagen;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.terrafirmainfinity.core.InfinityCore;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.include.com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.InfinityTagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static net.terrafirmainfinity.core.common.data.materials.InfinityMaterialFlags.*;

public class InfinityMaterialRecipeHandlers {
    public static void init(@NotNull RecipeOutput provider, @NotNull Material material) {
        processPowderCompacts(provider, material);
    }

    private static final Map<TagPrefix, TagPrefix> POWDER_COMPACTS_MAP = new ImmutableMap.Builder<TagPrefix, TagPrefix>()
            .put(ingot, ingotPowderCompact)
            .put(nugget, nuggetPowderCompact)
            .put(plateDense, plateDensePowderCompact)
            .put(plateDouble, plateDoublePowderCompact)
            .put(plate, platePowderCompact)
            .put(foil, foilPowderCompact)
            .put(rodLong, rodLongPowderCompact)
            .put(rod, rodPowderCompact)
            .put(bolt, boltPowderCompact)
            .put(ring, ringPowderCompact)
            .put(gearSmall, gearSmallPowderCompact)
            .put(gear, gearPowderCompact)
            .build();

    private static final Map<TagPrefix, TagPrefix> HOT_PARTS_MAP = new ImmutableMap.Builder<TagPrefix, TagPrefix>()
            .put(ingot, ingotHot)
            .put(nugget, nuggetHot)
            .put(plateDense, plateDenseHot)
            .put(plateDouble, plateDoubleHot)
            .put(plate, plateHot)
            .put(foil, foilHot)
            .put(rodLong, rodLongHot)
            .put(rod, rodHot)
            .put(bolt, boltHot)
            .put(ring, ringHot)
            .put(gearSmall, gearSmallHot)
            .put(gear, gearHot)
            .build();

    private static void processPowderCompacts(@NotNull RecipeOutput provider, @NotNull Material material) {
        if (!material.hasFlag(GENERATE_POWDER_COMPACTS)) {
            return;
        }

        if (material.shouldGenerateRecipesFor(ingot) && material.hasProperty(PropertyKey.INGOT)) {
            processPowderCompactRecipes(material, ingot, provider);
            processPowderCompactRecipes(material, nugget, provider);
        }

        if (material.shouldGenerateRecipesFor(plate) && material.hasFlag(GENERATE_PLATE)) {
            processPowderCompactRecipes(material, plate, provider);
            processPowderCompactRecipes(material, plateDouble, provider);
        }

        if (material.shouldGenerateRecipesFor(plate) && material.hasFlag(GENERATE_DENSE)) {
            processPowderCompactRecipes(material, foil, provider);
        }

        if (material.shouldGenerateRecipesFor(foil) && material.hasFlag(GENERATE_FOIL)) {
            processPowderCompactRecipes(material, foil, provider);
        }

        if (material.shouldGenerateRecipesFor(rod) && material.hasFlag(GENERATE_ROD)) {
            processPowderCompactRecipes(material, rod, provider);
        }

        if (material.shouldGenerateRecipesFor(rodLong) && material.hasFlag(GENERATE_LONG_ROD)) {
            processPowderCompactRecipes(material, rodLong, provider);
        }

        if (material.shouldGenerateRecipesFor(gear) && material.hasFlag(GENERATE_GEAR)) {
            processPowderCompactRecipes(material, gear, provider);
        }

        if (material.shouldGenerateRecipesFor(gearSmall) && material.hasFlag(GENERATE_SMALL_GEAR)) {
            processPowderCompactRecipes(material, gearSmall, provider);
        }

        if (material.shouldGenerateRecipesFor(bolt) && material.hasFlag(GENERATE_BOLT_SCREW)) {
            processPowderCompactRecipes(material, bolt, provider);
        }

        if (material.shouldGenerateRecipesFor(ring) && material.hasFlag(GENERATE_RING)) {
            processPowderCompactRecipes(material, ring, provider);
        }
    }

    private static void processPowderCompactRecipes(Material material, TagPrefix tagPrefix, RecipeOutput provider) {
        processFormingPressRecipe(material, tagPrefix, provider);

        boolean hasHotMaterial = HOT_PARTS_MAP.get(tagPrefix) != null && HOT_PARTS_MAP.get(tagPrefix).doGenerateItem(material);

        processEBFRecipe(material, tagPrefix, hasHotMaterial, provider);
    }

    private static void processFormingPressRecipe(Material material, TagPrefix tagPrefix, RecipeOutput provider) {
        TagPrefix powderCompactType = POWDER_COMPACTS_MAP.get(tagPrefix);

        GTRecipeBuilder formingPressBuilder = FORMING_PRESS_RECIPES.recipeBuilder(InfinityCore.id(material.getName() + powderCompactType.name()))
                .notConsumable(getExtruderShape(tagPrefix));

        int inputCount = 1;
        int outputCount = 1;

        if (tagPrefix.materialAmount() / M > 1) {
            inputCount = (int) (tagPrefix.materialAmount() / M);
        } else if (tagPrefix.materialAmount() / M < 1) {
            float matAmount = (float) tagPrefix.materialAmount() / M;

            if ((1 / matAmount) % 1 == 0) {
                outputCount = (int) (1 / matAmount);
            }
        }

        formingPressBuilder
                .inputItems(dust, material, inputCount)
                .outputItems(powderCompactType, material, outputCount);

        if (tagPrefix == rod || tagPrefix == plate) {
            formingPressBuilder.circuitMeta(1);
        } else if (tagPrefix == rodLong || tagPrefix == plateDouble) {
            formingPressBuilder.circuitMeta(2);
        } else if (tagPrefix == foil) {
            formingPressBuilder.circuitMeta(4);
        } else if (tagPrefix == plateDense) {
            formingPressBuilder.circuitMeta(9);
        }

        int duration = Math.max(1, (int) (material.getMass()));

        formingPressBuilder
                .duration(modifyDurationByTag(duration, tagPrefix))
                .EUt(4L * getVoltageMultiplier(material))
                .save(provider);
    }

    private static void processEBFRecipe(Material material, TagPrefix tagPrefix, boolean hasHotMaterial, RecipeOutput provider) {
        BlastProperty blastProp = material.getProperty(PropertyKey.BLAST);

        if (blastProp == null) {
            return;
        }

        int blastTemp = blastProp.getBlastTemperature();
        BlastProperty.GasTier gasTier = blastProp.getGasTier();

        int duration = blastProp.getDurationOverride() != -1 ? blastProp.getDurationOverride() :
               Math.max(1, (int) ((material.getMass()) * blastTemp / 50L));

        duration = modifyDurationByTag(duration, tagPrefix);

        int EUt = blastProp.getEUtOverride();
        if (EUt <= 0) EUt = VA[MV];

        TagPrefix powderCompactType = POWDER_COMPACTS_MAP.get(tagPrefix);
        TagPrefix hotType = HOT_PARTS_MAP.get(tagPrefix);

        ItemStack outputStack = hasHotMaterial ? ChemicalHelper.get(hotType, material) : ChemicalHelper.get(tagPrefix, material);

        GTRecipeBuilder blastBuilder = BLAST_RECIPES.recipeBuilder(InfinityCore.id("blast_" + material.getName() + "_" + powderCompactType.name()))
                .inputItems(powderCompactType, material)
                .outputItems(outputStack)
                .blastFurnaceTemp(blastTemp - 200)
                .EUt(EUt);

        if (gasTier != null) {
            SizedFluidIngredient gas = gasTier.getFluid();

            blastBuilder.copy(InfinityCore.id("blast_" + material.getName() + "_" + powderCompactType.name()))
                    .circuitMeta(1)
                    .duration(duration)
                    .save(provider);

            blastBuilder.copy(InfinityCore.id("blast_" + material.getName() + "_" + powderCompactType.name() + "_gas"))
                    .circuitMeta(2)
                    .inputFluids(gas)
                    .duration((int) (duration * 0.67))
                    .save(provider);
        } else {
            blastBuilder.duration(duration);
            blastBuilder.save(provider);
        }

        // Generate Vacuum Freezer Recipes for non-Ingots
        if (hasHotMaterial && tagPrefix != ingot) {
            int vacuumEUt = blastProp.getVacuumEUtOverride() != -1 ? blastProp.getVacuumEUtOverride() : VA[MV];

            int vacuumDuration = blastProp.getVacuumDurationOverride() != -1 ? blastProp.getVacuumDurationOverride() :
                    (int) material.getMass() * 3;

            vacuumDuration = modifyDurationByTag(vacuumDuration, tagPrefix);

            if (blastTemp < 5000) {
                VACUUM_RECIPES.recipeBuilder(InfinityCore.id("cool_hot_" + material.getName() + "_" + tagPrefix.name()))
                        .inputItems(outputStack)
                        .outputItems(tagPrefix, material)
                        .duration(vacuumDuration)
                        .EUt(vacuumEUt)
                        .save(provider);
            } else {
                VACUUM_RECIPES.recipeBuilder("cool_hot_" + material.getName() + "_" + tagPrefix.name())
                        .inputItems(outputStack)
                        .inputFluids(Helium.getFluid(FluidStorageKeys.LIQUID, 500))
                        .outputItems(tagPrefix, material)
                        .outputFluids(Helium.getFluid(250))
                        .duration(vacuumDuration)
                        .EUt(vacuumEUt)
                        .save(provider);
            }
        }
    }

    private static Item getExtruderShape(TagPrefix tagPrefix) {
        Item extruderShape = null;

        if (tagPrefix == ingot) {
            extruderShape = GTItems.SHAPE_EXTRUDER_INGOT.get();
        } else if (tagPrefix == nugget) {
            extruderShape = GTItems.SHAPE_MOLD_NUGGET.get();
        } else if (tagPrefix == plateDense || tagPrefix == plateDouble || tagPrefix == plate || tagPrefix == foil) {
            extruderShape = GTItems.SHAPE_EXTRUDER_PLATE.get();
        } else if (tagPrefix == rodLong || tagPrefix == rod) {
            extruderShape = GTItems.SHAPE_EXTRUDER_ROD.get();
        } else if (tagPrefix == bolt) {
            extruderShape = GTItems.SHAPE_EXTRUDER_BOLT.get();
        } else if (tagPrefix == ring) {
            extruderShape = GTItems.SHAPE_EXTRUDER_RING.get();
        } else if (tagPrefix == gearSmall) {
            extruderShape = GTItems.SHAPE_EXTRUDER_GEAR_SMALL.get();
        }else if (tagPrefix == gear) {
            extruderShape = GTItems.SHAPE_EXTRUDER_GEAR.get();
        }

        if (extruderShape != null) {
            return extruderShape;
        } else throw new IllegalArgumentException("Missing extruder shape for TagPrefix: " + tagPrefix.name());
    }

    private static int modifyDurationByTag(int duration, TagPrefix tagPrefix) {
        float materialRatio = (float) tagPrefix.materialAmount() / M;
        return Math.min(duration * 2, (int) (duration * materialRatio));
    }

    private static int getVoltageMultiplier(Material material) {
        return material.getBlastTemperature() >= 2800 ? VA[LV] : VA[ULV];
    }
}
