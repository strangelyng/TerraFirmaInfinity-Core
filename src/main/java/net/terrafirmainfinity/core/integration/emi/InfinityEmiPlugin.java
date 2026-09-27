package net.terrafirmainfinity.core.integration.emi;

import com.gregtechceu.gtceu.common.data.GTItems;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.terrafirmainfinity.core.InfinityCore;

import java.util.Arrays;

@EmiEntrypoint
public class InfinityEmiPlugin implements EmiPlugin {
    public static final EmiRecipeCategory ORE_VEIN_INFO = new EmiRecipeCategory(InfinityCore.id("ore_vein_info"),
            EmiStack.of(GTItems.PROSPECTOR_LV));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(ORE_VEIN_INFO);
        registry.addWorkstation(ORE_VEIN_INFO, EmiStack.of(GTItems.PROSPECTOR_LV));
        registry.addWorkstation(ORE_VEIN_INFO, EmiStack.of(GTItems.PROSPECTOR_HV));
        registry.addWorkstation(ORE_VEIN_INFO, EmiStack.of(GTItems.PROSPECTOR_LuV));

        Arrays.stream(OreVeinDictionary.RECIPES)
                .forEach(registry::addRecipe);
    }
}
