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
import com.gregtechceu.gtceu.api.machine.feature.IMuiMachine;
import com.gregtechceu.gtceu.api.machine.mui.MachineUIPanel;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.notifiable.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.ISubscription;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.GAS_TURBINE_FUELS;

/**
 * TODO: FIX
 * Stops working on reload
 */
public class FuelHatchPartMachine extends TieredIOPartMachine implements IMuiMachine {
    public static final int INITIAL_TANK_CAPACITY = 8 * FluidType.BUCKET_VOLUME;

    @SaveField
    public final NotifiableFluidTank tank;

    @Nullable
    protected TickableSubscription consumeFuelSubs;

    @Nullable
    protected ISubscription tankSubs;

    @Nullable
    @SaveField
    protected GTRecipe cachedRecipe;

    @Nullable
    @SaveField
    protected Fluid cachedFluid;

    @SaveField
    protected int cachedRecipeDuration; // Default Value is 5

    @SaveField
    protected int cachedFluidConsumption;

    @SaveField
    protected long lastConsumedTick;

    @SaveField
    protected boolean isActive;

    public FuelHatchPartMachine(BlockEntityCreationInfo info, int tier, IO io, int initalCapacity) {
        super(info, tier, io);
        this.tank = attachTrait(createTank(initalCapacity));
        this.lastConsumedTick = 0L;
        this.cachedRecipeDuration = 5;
        this.isActive = false;
    }

    protected NotifiableFluidTank createTank(int initialCapacity) {
        int tankCapacity = FluidHatchPartMachine.getTankCapacity(initialCapacity, getTier());
        return new NotifiableFluidTank(1, tankCapacity, io);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        scheduleForNextServerTick(this::refreshLastConsumedTick);
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

    protected void refreshLastConsumedTick() {
        if (lastConsumedTick > getOffsetTimer()) {
            lastConsumedTick = 0L;
        }
    }

    @Override
    public boolean canShared(MultiblockControllerMachine controller, String substructureName) {
        return false;
    }

    protected void updateTankSubscription() {
        if (isFormed() && isWorkingEnabled() && !tank.isEmpty()) {
            consumeFuelSubs = subscribeServerTick(consumeFuelSubs, this::consumeFuel);
        } else if (consumeFuelSubs != null) {
            consumeFuelSubs.unsubscribe();
            consumeFuelSubs = null;
            isActive = false;
        }
    }

    protected void consumeFuel() {
        if (getOffsetTimer() % cachedRecipeDuration == 0) {
            if (isFormed() && isWorkingEnabled()) {
                cachedRecipe = getRecipe();
                if (cachedRecipe != null) {
                    FluidStack fuelStack = tank.getFluidInTank(0);
                    if (fuelStack.getAmount() > 0 && cachedRecipeDuration > 0 && addFuel(cachedRecipeDuration, true)) {
                        fuelStack.setAmount(fuelStack.getAmount() - cachedFluidConsumption);
                        addFuel(cachedRecipeDuration, false);
                    } else {
                        isActive = false;
                    }
                } else {
                    isActive = false;
                }
            }
            updateTankSubscription();
        }
    }

    protected boolean addFuel(int fuelValue, boolean simulate) {
        if (fuelValue < 0 || nextRecipeTime() < cachedRecipeDuration) return false;
        if (simulate) return true;
        lastConsumedTick = getOffsetTimer();
        isActive = true;
        tank.notifyListeners();
        return true;
    }

    protected long nextRecipeTime() {
        return getOffsetTimer() - lastConsumedTick;
    }

    @Nullable
    private GTRecipe getRecipe() {
        FluidStack fuelStack = tank.getFluidInTank(0);

        if (cachedFluid != null && fuelStack.getFluid() == cachedFluid) {
            return cachedRecipe;
        }

        if (!fuelStack.isEmpty()) {
            FluidStack copy = fuelStack.copyWithAmount(Integer.MAX_VALUE);

            cachedFluid = copy.getFluid();

            // TODO: Add Unique Recipe Category
            GTRecipe recipe = GAS_TURBINE_FUELS.get().db().find(Collections.singletonMap(FluidRecipeCapability.CAP, Collections.singletonList(copy)), r -> true);

            if (recipe != null) {
                cachedRecipeDuration = recipe.duration;

                Content recipeInputs = recipe.inputs.get(FluidRecipeCapability.CAP).getFirst();
                SizedFluidIngredient input = FluidRecipeCapability.CAP.of(recipeInputs.content());

                cachedFluidConsumption = input.amount();

                return recipe;
            }
        }

        resetCachedRecipeData();
        return null;
    }

    private void resetCachedRecipeData() {
        cachedFluid = null;
        cachedFluidConsumption = 0;
        cachedRecipeDuration = 5;
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
                                .child(new FluidSlot().name("regularFluid").syncHandler(new FluidSlotSyncHandler(tank.getStorages()[0]).canFillSlot(io.support(IO.IN)))))
        );
    }

    public boolean isActive() {
        return isActive;
    }
}
