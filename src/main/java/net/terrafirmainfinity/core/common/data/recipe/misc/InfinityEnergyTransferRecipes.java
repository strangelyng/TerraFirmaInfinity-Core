package net.terrafirmainfinity.core.common.data.recipe.misc;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gregtechceu.gtceu.utils.ToolItemHelper;
import com.tterrag.registrate.util.entry.ItemEntry;
import it.unimi.dsi.fastutil.ints.Int2ReferenceArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ReferenceMap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.terrafirmainfinity.core.common.InfinityItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.data.recipe.generated.ToolRecipeHandler.powerUnitItems;

/**
 * Adapted from CustomToolRecipes {@link com.gregtechceu.gtceu.data.recipe.misc.CustomToolRecipes}
 */
public class InfinityEnergyTransferRecipes {
    private static final Int2ReferenceMap<ItemEntry<? extends Item>> motorItems = new Int2ReferenceArrayMap<>();
    private static final Int2ReferenceMap<Holder<Material>> baseMaterials = new Int2ReferenceArrayMap<>();
    private static final Int2ReferenceMap<List<ItemEntry<? extends Item>>> batteryItems = new Int2ReferenceArrayMap<>();

    public static void init(@NotNull RecipeOutput provider) {
        initializeInfinityItems();
        registerPowerUnitRecipes(provider);
        registerCustomToolRecipes(provider);
    }

    private static void initializeInfinityItems() {
        motorItems.put(GTValues.LV, GTItems.ELECTRIC_MOTOR_LV);
        motorItems.put(GTValues.MV, GTItems.ELECTRIC_MOTOR_MV);
        motorItems.put(GTValues.HV, GTItems.ELECTRIC_MOTOR_HV);
        motorItems.put(GTValues.EV, GTItems.ELECTRIC_MOTOR_EV);
        motorItems.put(GTValues.IV, GTItems.ELECTRIC_MOTOR_IV);

        baseMaterials.put(GTValues.LV, GTMaterials.Steel);
        baseMaterials.put(GTValues.MV, GTMaterials.Aluminium);
        baseMaterials.put(GTValues.HV, GTMaterials.StainlessSteel);
        baseMaterials.put(GTValues.EV, GTMaterials.Titanium);
        baseMaterials.put(GTValues.IV, GTMaterials.TungstenSteel);

        batteryItems.put(GTValues.LV,
                List.of(InfinityItems.BATTERY_LV_LEAD_ACID, InfinityItems.BATTERY_LV_NICKEL_IRON, InfinityItems.BATTERY_LV_NICKEL_CADMIUM));
    }

    private static void registerPowerUnitRecipes(@NotNull RecipeOutput provider) {
        for (int tier : powerUnitItems.keySet()) {
            List<ItemEntry<? extends Item>> tieredBatteryItems = batteryItems.get(tier);
            if (tieredBatteryItems != null) {
                for (ItemEntry<? extends Item> batteryItem : tieredBatteryItems) {
                    if (powerUnitItems.get(tier) != null) {
                        ItemStack batteryStack = batteryItem.asStack();
                        long maxCharge = GTCapabilityHelper.getElectricItem(batteryStack).getMaxCharge();
                        ItemStack powerUnitStack = ToolItemHelper.getMaxChargeOverrideStack(powerUnitItems.get(tier).get(),
                                maxCharge);
                        String recipeName = String.format("%s_%s",
                                BuiltInRegistries.ITEM.getKey(powerUnitItems.get(tier).get()).getPath(),
                                BuiltInRegistries.ITEM.getKey(batteryItem.get()).getPath());

                        VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider, true, false, true, recipeName,
                                Ingredient.of(batteryStack), powerUnitStack,
                                "S d", "GMG", "PBP",
                                'M', motorItems.get(tier).asStack(),
                                'S', new MaterialEntry(screw, baseMaterials.get(tier)),
                                'P', new MaterialEntry(plate, baseMaterials.get(tier)),
                                'G', new MaterialEntry(gearSmall, baseMaterials.get(tier)),
                                'B', batteryStack);
                    }
                }
            }
        }
    }

    private static void registerCustomToolRecipes(@NotNull RecipeOutput provider) {
        registerElectricRecipes(provider);
    }

    private static void registerElectricRecipes(@NotNull RecipeOutput provider) {
        for (ItemEntry<? extends Item> batteryItem : batteryItems.get(GTValues.LV)) {
            VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider, true, false, true,
                    "prospector_lv_" + batteryItem.getId().getPath(),
                    Ingredient.of(batteryItem), GTItems.PROSPECTOR_LV.asStack(),
                    "EPS", "CDC", "PBP",
                    'E', GTItems.EMITTER_LV.asStack(),
                    'P', new MaterialEntry(plate, GTMaterials.Steel),
                    'S', GTItems.SENSOR_LV.asStack(),
                    'D', new MaterialEntry(plate, GTMaterials.Glass),
                    'C', CustomTags.LV_CIRCUITS,
                    'B', batteryItem.asStack());

            VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider, true, false, true,
                    "lv_magnet_" + batteryItem.getId().getPath(),
                    Ingredient.of(batteryItem), GTItems.ITEM_MAGNET_LV.asStack(),
                    "MwM", "MBM", "CPC",
                    'M', new MaterialEntry(rod, GTMaterials.SteelMagnetic),
                    'P', new MaterialEntry(plate, GTMaterials.Steel),
                    'C', new MaterialEntry(cableGtSingle, GTMaterials.Tin),
                    'B', batteryItem.asStack());
        }
    }
}
