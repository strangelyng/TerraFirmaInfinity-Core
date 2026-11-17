package net.terrafirmainfinity.core.common.machine.part;

import brachy.modularui.factory.PosGuiData;
import brachy.modularui.screen.UISettings;
import brachy.modularui.value.sync.PanelSyncManager;
import brachy.modularui.widget.ParentWidget;
import brachy.modularui.widgets.slot.ItemSlot;
import brachy.modularui.widgets.slot.ModularSlot;
import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IMuiMachine;
import com.gregtechceu.gtceu.api.machine.trait.notifiable.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.common.mui.GTGuiTextures;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gregtechceu.gtceu.utils.ISubscription;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

public class FuelBusPartMachine extends FuelPartMachine implements IMuiMachine {
    @SaveField
    public final NotifiableItemStackHandler inventory;

    @Nullable
    protected TickableSubscription fuelTimeSubs;

    @Nullable
    protected ISubscription inventorySubs;

    @Nullable
    @SaveField
    protected Item cachedFuelItem;

    // TODO: Add Larger Inventory Sizes
    public FuelBusPartMachine(BlockEntityCreationInfo info, int tier) {
        super(info, tier);
        this.inventory = attachTrait(new NotifiableItemStackHandler(1, IO.IN));
        this.cachedFuelDuration = 0;
        this.cachedFuelConsumption = 1;
        this.readyForRecipe = false;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        scheduleForNextServerTick(this::updateInventorySubscription);
        inventorySubs = inventory.addChangedListener(this::updateInventorySubscription);
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (inventorySubs != null) {
            inventorySubs.unsubscribe();
            inventorySubs = null;
        }
    }

    protected void updateInventorySubscription() {
        if (isFormed() && isWorkingEnabled() && !inventory.isEmpty()) {
            fuelTimeSubs = subscribeServerTick(fuelTimeSubs, this::findAndCacheFuelTime);
        } else if (fuelTimeSubs != null) {
            fuelTimeSubs.unsubscribe();
            fuelTimeSubs = null;
            readyForRecipe = false;
        }
    }

    protected void findAndCacheFuelTime() {
        if (getOffsetTimer() % 5 == 0) {
            if (isFormed() && isWorkingEnabled()) {
                cachedFuelDuration = getFuelTime();
                updateFuelStatus();
            }
            updateInventorySubscription();
        }
    }

    private int getFuelTime() {
        ItemStack fuelStack = getFuelItemStack();

        if (cachedFuelItem != null && fuelStack.getItem() == cachedFuelItem) {
            return cachedFuelDuration;
        }

        if (!fuelStack.isEmpty()) {
            Item fuelItem = fuelStack.getItem();

            return GTUtil.getItemBurnTime(fuelItem);
        }

        resetCachedRecipeData();
        return 0;
    }

    public ItemStack getFuelItemStack() {
        return inventory.getStackInSlot(0);
    }

    public void updateFuelStatus() {
        if (cachedFuelDuration > 0 && getFuelItemStack().getCount() > 0) {
            readyForRecipe = true;
            inventory.notifyListeners();
        } else {
            readyForRecipe = false;
            inventory.notifyListeners();
        }
    }

    @Override
    public boolean consumeFuel(int fuelConsumption, boolean simulate) {
        if (!isWorkingEnabled()) return false;
        if (getFuelItemStack().getCount() < fuelConsumption) return false;
        if (simulate) return true;
        getFuelItemStack().shrink(fuelConsumption);
        inventory.notifyListeners();
        return true;
    }

    private void resetCachedRecipeData() {
        cachedFuelItem = null;
        cachedFuelConsumption = 1;
        cachedFuelDuration = 0;
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        super.setWorkingEnabled(workingEnabled);
        updateInventorySubscription();
    }

    @Override
    public void buildMainUI(ParentWidget<?> mainWidget, PosGuiData guiData, PanelSyncManager syncManager, UISettings settings) {
        mainWidget.child(
                new ItemSlot()
                        .name("fuelItem")
                        .slot(new ModularSlot(inventory.storage, 0))
                        .background(GTGuiTextures.SLOT, GTGuiTextures.FURNACE_OVERLAY_2)
                        .center()
        );
    }

    public @Nullable ItemLike getCachedFuelItem() {
        return cachedFuelItem;
    }
}
