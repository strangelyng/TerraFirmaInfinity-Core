package net.terrafirmainfinity.core.common.machine.part;

import brachy.modularui.api.drawable.Text;
import brachy.modularui.factory.PosGuiData;
import brachy.modularui.screen.UISettings;
import brachy.modularui.utils.Alignment;
import brachy.modularui.value.sync.FluidSlotSyncHandler;
import brachy.modularui.value.sync.PanelSyncManager;
import brachy.modularui.widget.ParentWidget;
import brachy.modularui.widgets.TextWidget;
import brachy.modularui.widgets.layout.Flow;
import brachy.modularui.widgets.slot.FluidSlot;
import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.mui.MachineUIPanel;
import com.gregtechceu.gtceu.api.machine.trait.notifiable.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTTransferUtils;
import com.gregtechceu.gtceu.utils.ISubscription;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

import static net.terrafirmainfinity.core.common.data.InfinityRecipeTypes.LIQUID_FUEL_RECIPES;

public class FuelHatchPartMachine extends FuelPartMachine<Fluid> {
    public static final int INITIAL_TANK_CAPACITY = 8 * FluidType.BUCKET_VOLUME;

    @SaveField
    public final NotifiableFluidTank tank;

    @Nullable
    protected TickableSubscription autoIOSubs;

    @Nullable
    protected TickableSubscription fuelRecipeSubs;

    @Nullable
    protected ISubscription tankSubs;

    @Nullable
    @SaveField
    protected GTRecipe cachedFuelRecipe;

    @Nullable
    @SaveField
    protected Fluid cachedFuelFluid;

    public FuelHatchPartMachine(BlockEntityCreationInfo info, int tier) {
        super(info, tier);
        this.tank = attachTrait(createTank(INITIAL_TANK_CAPACITY));
        this.cachedFuelDuration = 0;
        this.readyForRecipe = false;
    }

    protected NotifiableFluidTank createTank(int initialCapacity) {
        int tankCapacity = FluidHatchPartMachine.getTankCapacity(initialCapacity, getTier());
        return new NotifiableFluidTank(1, tankCapacity, io);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        scheduleForNextServerTick(this::updateTankSubscription);
        tankSubs = tank.addChangedListener(this::updateTankSubscription);
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (tankSubs != null) {
            tankSubs.unsubscribe();
            tankSubs = null;
        }
    }

    @Override
    public void onNeighborChanged(Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        super.onNeighborChanged(neighborBlock, neighborPos, isMoving);
        updateTankSubscription();
    }

    @Override
    public void onRotated(Direction oldFacing, Direction newFacing) {
        super.onRotated(oldFacing, newFacing);
        updateTankSubscription(newFacing);
    }

    protected void updateTankSubscription() {
        updateTankSubscription(getFrontFacing());
    }

    protected void updateTankSubscription(Direction newFacing) {
        if (isWorkingEnabled() && io.support(IO.IN) && GTTransferUtils.hasAdjacentFluidHandler(getLevel(), getBlockPos(), newFacing)) {
            autoIOSubs = subscribeServerTick(autoIOSubs, this::autoIO);
        } else if (autoIOSubs != null) {
            autoIOSubs.unsubscribe();
            autoIOSubs = null;
        }
        if (isFormed() && isWorkingEnabled() && !tank.isEmpty()) {
            fuelRecipeSubs = subscribeServerTick(fuelRecipeSubs, this::findAndCacheRecipe);
        } else if (fuelRecipeSubs != null) {
            fuelRecipeSubs.unsubscribe();
            fuelRecipeSubs = null;
            readyForRecipe = false;
        }
    }

    protected void autoIO() {
        if (getOffsetTimer() % 5 == 0) {
            if (isWorkingEnabled()) {
                if (io == IO.IN) {
                    tank.importFromNearby(getFrontFacing());
                }
            }
            updateTankSubscription();
        }
    }

    protected void findAndCacheRecipe() {
        if (getOffsetTimer() % 5 == 0) {
            if (isFormed() && isWorkingEnabled()) {
                cachedFuelRecipe = getRecipe();
                updateFuelStatus(cachedFuelRecipe);
            }
            updateTankSubscription();
        }
    }

    @Nullable
    private GTRecipe getRecipe() {
        FluidStack fuelStack = getFuelFluidStack();

        if (cachedFuelFluid != null && fuelStack.getFluid() == cachedFuelFluid) {
            return cachedFuelRecipe;
        }

        if (!fuelStack.isEmpty()) {
            FluidStack copy = fuelStack.copyWithAmount(Integer.MAX_VALUE);

            cachedFuelFluid = copy.getFluid();

            GTRecipe recipe = LIQUID_FUEL_RECIPES.get().db().find(Collections.singletonMap(FluidRecipeCapability.CAP, Collections.singletonList(copy)), r -> true);

            if (recipe != null) {
                cachedFuelDuration = recipe.duration;

                Content recipeInputs = recipe.inputs.get(FluidRecipeCapability.CAP).getFirst();
                SizedFluidIngredient input = FluidRecipeCapability.CAP.of(recipeInputs.content());

                cachedFuelConsumption = input.amount();

                return recipe;
            }
        }

        resetCachedRecipeData();
        return null;
    }

    public FluidStack getFuelFluidStack() {
        return tank.getFluidInTank(0);
    }

    public void updateFuelStatus(@Nullable GTRecipe fuelRecipe) {
        if (fuelRecipe != null && cachedFuelDuration > 0 && getFuelFluidStack().getAmount() > cachedFuelConsumption) {
            readyForRecipe = true;
            tank.notifyListeners();
        } else {
            readyForRecipe = false;
            tank.notifyListeners();
        }
    }

    @Override
    public boolean consumeFuel(int fuelConsumption, boolean simulate) {
        if (!isWorkingEnabled()) return false;
        if (getFuelFluidStack().getAmount() < fuelConsumption) return false;
        if (simulate) return true;
        getFuelFluidStack().setAmount(getFuelFluidStack().getAmount() - fuelConsumption);
        tank.notifyListeners();
        return true;
    }

    private void resetCachedRecipeData() {
        cachedFuelFluid = null;
        cachedFuelConsumption = 0;
        cachedFuelDuration = 5;
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        super.setWorkingEnabled(workingEnabled);
        updateTankSubscription();
    }

    private Component getFluidNameText() {
        return this.tank.getFluidInTank(0).isEmpty() ? Component.translatable("gtceu.fluid.empty") : this.tank.getFluidInTank(0).getHoverName();
    }

    private Component getFluidAmountText() {
        return Component.literal(FormattingUtil.formatBuckets(this.tank.getFluidInTank(0).getAmount()));
    }

    @Override
    public void buildMainUI(ParentWidget<?> mainWidget, PosGuiData guiData, PanelSyncManager syncManager, UISettings settings) {
        mainWidget.child(
                Flow.col().width(MachineUIPanel.DEFAULT_CONTENT_WIDTH).height(MachineUIPanel.DEFAULT_CONTENT_HEIGHT).mainAxisAlignment(Alignment.MainAxis.CENTER).childPadding(4)
                        .child(new TextWidget<>(Text.dynamic(this::getFluidNameText)).horizontalCenter())
                        .child(new TextWidget<>(Text.dynamic(this::getFluidAmountText)).horizontalCenter())
                        .child(Flow.row().childPadding(2).coverChildren()
                                .child(new FluidSlot().name("fuelFluid").syncHandler(new FluidSlotSyncHandler(tank.getStorages()[0]).canFillSlot(io.support(IO.IN)))))
        );
    }

    @Override
    public @Nullable Fluid getCachedFuel() {
        return cachedFuelFluid;
    }

    @Override
    public Fluid getFuel() {
        return getFuelFluidStack().getFluid();
    }
}
