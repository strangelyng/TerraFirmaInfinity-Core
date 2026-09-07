package net.terrafirmainfinity.core.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.notifiable.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.recipe.RecipeLogic;
import com.gregtechceu.gtceu.api.multiblock.error.PatternStringError;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.api.sync_system.annotations.SyncToClient;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.VoidFluidHandler;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Copied near directly from DistillationTowerMachine and its accompanying RecipeLogic
 * Minor adjustments made to remove references to Distillery Recipes
 * See {@link com.gregtechceu.gtceu.common.machine.multiblock.electric.DistillationTowerMachine}
 */
public class FlashFurnaceMachine extends WorkableElectricMultiblockMachine {
    @Nullable
    private List<IFluidHandler> fluidOutputs;
    @Nullable
    private IFluidHandler firstValid = null;
    private final int yOffset;

    public FlashFurnaceMachine(BlockEntityCreationInfo info) {
        this(info, -1);
    }

    public FlashFurnaceMachine(BlockEntityCreationInfo info, int yOffset) {
        super(info, new FlashFurnaceLogic());
        this.yOffset = yOffset;
    }

    @Override
    public RecipeLogic getRecipeLogic() {
        return (FlashFurnaceLogic) super.getRecipeLogic();
    }

    @Override
    public void formStructure(String substructureName) {
        super.formStructure(substructureName);
        var pState = patternStates.get(substructureName);
        final int startY = getBlockPos().getY() + yOffset;
        List<MultiblockPartMachine> parts = getParts().stream().filter(part -> PartAbility.EXPORT_FLUIDS.isApplicable(part.getBlockState().getBlock())).filter(part -> part.getBlockPos().getY() >= startY).toList();
        if (!parts.isEmpty()) {
            // Loop from controller y + offset -> the highest output hatch
            int maxY = parts.get(parts.size() - 1).getBlockPos().getY();
            fluidOutputs = new ObjectArrayList<>(maxY - startY);
            int outputIndex = 0;
            for (int y = startY; y <= maxY; ++y) {
                if (parts.size() <= outputIndex) {
                    fluidOutputs.add(VoidFluidHandler.INSTANCE);
                    continue;
                }
                var part = parts.get(outputIndex);
                if (part.getBlockPos().getY() == y) {
                    var handler = part.getRecipeHandlers().get(0).getCapability(FluidRecipeCapability.CAP).stream().filter(IFluidHandler.class::isInstance).findFirst().map(IFluidHandler.class::cast).orElse(VoidFluidHandler.INSTANCE);
                    addOutput(handler);
                    outputIndex++;
                } else if (part.getBlockPos().getY() > y) {
                    fluidOutputs.add(VoidFluidHandler.INSTANCE);
                } else {
                    BlockPos p = part.getBlockPos();
                    pState.setError(new PatternStringError(Component.translatable("gtceu.predicate_error.distillery.unexpected_hatch", p.getX(), p.getY(), p.getZ()))); // TODO: Tweak this
                    // GTCEu.LOGGER.error(
                    // "The Distillation Tower at {} has a fluid export hatch with an unexpected Y position",
                    // getBlockPos());
                    invalidateStructure(substructureName);
                    return;
                }
            }
        } else {
            pState.setError(new PatternStringError(Component.translatable("gtceu.predicate_error.distillery.missing_outputs"))); // TODO: Tweak this
            invalidateStructure(substructureName);
        }
    }

    private void addOutput(IFluidHandler handler) {
        fluidOutputs.add(handler);
        if (firstValid == null && handler != VoidFluidHandler.INSTANCE) firstValid = handler;
    }

    @Override
    public void invalidateStructure(String name) {
        fluidOutputs = null;
        firstValid = null;
        super.invalidateStructure(name);
    }

    public static class FlashFurnaceLogic extends RecipeLogic {
        @Nullable
        @SaveField
        @SyncToClient
        GTRecipe workingRecipe = null;

        public FlashFurnaceLogic() {
            super();
        }

        @Override
        public FlashFurnaceMachine getMachine() {
            return (FlashFurnaceMachine) super.getMachine();
        }

        // Copy of lastRecipe with fluid outputs trimmed, for output displays like Jade or GUI text
        @Override
        @Nullable
        public GTRecipe getLastRecipe() {
            return workingRecipe;
        }

        @Override
        protected ActionResult matchRecipe(GTRecipe recipe) {
            var match = matchFFRecipe(recipe);
            if (!match.isSuccess()) return match;
            return RecipeHelper.matchTickRecipe(getMachine(), recipe);
        }

        @Override
        protected void handleSearchingRecipes(Iterator<GTRecipe> matches) {
            workingRecipe = null;
            super.handleSearchingRecipes(matches);
        }

        private ActionResult matchFFRecipe(GTRecipe recipe) {
            var result = RecipeHelper.handleRecipe(getMachine(), recipe, IO.IN, recipe.inputs, Collections.emptyMap(), false, true);
            if (!result.isSuccess()) return result;
            if (!applyFluidOutputs(recipe, IFluidHandler.FluidAction.SIMULATE, getMachine().getVoidingMode())) {
                return ActionResult.fail(Component.translatable("gtceu.recipe_logic.insufficient_out").append(": ").append(FluidRecipeCapability.CAP.getName()), FluidRecipeCapability.CAP, IO.OUT);
            }
            return ActionResult.SUCCESS;
        }

        private void updateWorkingRecipe(GTRecipe recipe) {
            this.workingRecipe = recipe.copy();
            var contents = recipe.getOutputContents(FluidRecipeCapability.CAP);
            var outputs = getMachine().getFluidOutputs();
            List<Content> trimmed = new ArrayList<>(12);
            for (int i = 0; i < Math.min(contents.size(), outputs.size()); ++i) {
                if (!(outputs.get(i) instanceof VoidFluidHandler)) trimmed.add(contents.get(i));
            }
            this.workingRecipe.outputs.put(FluidRecipeCapability.CAP, trimmed);
            syncDataHolder.markClientSyncFieldDirty("workingRecipe");
        }

        @Override
        protected ActionResult handleRecipeIO(GTRecipe recipe, IO io) {
            if (io != IO.OUT) {
                var handleIO = super.handleRecipeIO(recipe, io);
                if (handleIO.isSuccess()) {
                    updateWorkingRecipe(recipe);
                } else {
                    this.workingRecipe = null;
                }
                return handleIO;
            }
            var items = recipe.getOutputContents(ItemRecipeCapability.CAP);
            if (!items.isEmpty()) {
                Map<RecipeCapability<?>, List<Content>> out = Map.of(ItemRecipeCapability.CAP, items);
                RecipeHelper.handleRecipe(getMachine(), recipe, io, out, chanceCaches, false, false);
            }
            if (applyFluidOutputs(recipe, IFluidHandler.FluidAction.EXECUTE, getMachine().getVoidingMode())) {
                workingRecipe = null;
                return ActionResult.SUCCESS;
            }
            return ActionResult.fail(Component.translatable("gtceu.recipe_logic.insufficient_out").append(": ").append(FluidRecipeCapability.CAP.getName()), FluidRecipeCapability.CAP, IO.OUT);
        }

        private boolean applyFluidOutputs(GTRecipe recipe, IFluidHandler.FluidAction action, VoidingMode voidMode) {
            var fluids = recipe.getOutputContents(FluidRecipeCapability.CAP).stream().map(Content::content).map(FluidRecipeCapability.CAP::of).toList();
            // Distillery recipes should output to the first non-void handler
            boolean valid = true;
            var outputs = getMachine().getFluidOutputs();
            for (int i = 0; i < Math.min(fluids.size(), outputs.size()); ++i) {
                var handler = outputs.get(i);
                var fluid = fluids.get(i).getFluids()[0];
                int filled = (handler instanceof NotifiableFluidTank nft) ? nft.fillInternal(fluid, action) : handler.fill(fluid, action);
                if (filled != fluid.getAmount() && !voidMode.canVoid(FluidRecipeCapability.CAP)) valid = false;
                if (action.simulate() && !valid) break;
            }
            return valid;
        }
    }

    @Nullable
    public List<IFluidHandler> getFluidOutputs() {
        return this.fluidOutputs;
    }
}
