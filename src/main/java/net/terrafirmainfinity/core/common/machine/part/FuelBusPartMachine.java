package net.terrafirmainfinity.core.common.machine.part;

import brachy.modularui.factory.PosGuiData;
import brachy.modularui.screen.UISettings;
import brachy.modularui.value.sync.PanelSyncManager;
import brachy.modularui.value.sync.SyncHandlers;
import brachy.modularui.widget.ParentWidget;
import brachy.modularui.widgets.layout.Grid;
import brachy.modularui.widgets.slot.ItemSlot;
import brachy.modularui.widgets.slot.SlotGroup;
import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IMuiMachine;
import com.gregtechceu.gtceu.api.machine.trait.notifiable.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.utils.GTTransferUtils;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gregtechceu.gtceu.utils.ISubscription;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.terrafirmainfinity.core.common.data.tag.InfinityItemTags;
import org.jetbrains.annotations.Nullable;

public class FuelBusPartMachine extends FuelPartMachine<Item> implements IMuiMachine {
    @SaveField
    public final NotifiableItemStackHandler inventory;

    @Nullable
    protected TickableSubscription autoIOSubs;

    @Nullable
    protected TickableSubscription fuelTimeSubs;

    @Nullable
    protected ISubscription inventorySubs;

    @Nullable
    @SaveField
    protected Item cachedFuelItem;

    public FuelBusPartMachine(BlockEntityCreationInfo info, int tier) {
        super(info, tier);
        this.inventory = attachTrait(createInventory(getInventorySize(tier)));
        this.cachedFuelDuration = 0;
        this.cachedFuelConsumption = 1;
        this.readyForRecipe = false;
    }

    protected static int getInventorySize(int tier) {
        int sizeRoot = 1 + Math.min(9, tier);
        return sizeRoot * sizeRoot;
    }

    private static NotifiableItemStackHandler createInventory(int slots) {
        NotifiableItemStackHandler itemHandler = new NotifiableItemStackHandler(slots, IO.IN);
        itemHandler.setFilter(stack -> stack.is(InfinityItemTags.CARBON_FUELS));
        return itemHandler;
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

    @Override
    public void onNeighborChanged(Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        super.onNeighborChanged(neighborBlock, neighborPos, isMoving);
        updateInventorySubscription();
    }

    @Override
    public void onRotated(Direction oldFacing, Direction newFacing) {
        super.onRotated(oldFacing, newFacing);
        updateInventorySubscription(newFacing);
    }

    protected void updateInventorySubscription() {
        updateInventorySubscription(getFrontFacing());
    }

    protected void updateInventorySubscription(Direction newFacing) {
        if (isWorkingEnabled() && io.support(IO.IN) && GTTransferUtils.hasAdjacentItemHandler(getLevel(), getBlockPos(), newFacing)) {
            autoIOSubs = subscribeServerTick(autoIOSubs, this::autoIO);
        } else if (autoIOSubs != null) {
            autoIOSubs.unsubscribe();
            autoIOSubs = null;
        }
        if (isFormed() && isWorkingEnabled() && !inventory.isEmpty()) {
            fuelTimeSubs = subscribeServerTick(fuelTimeSubs, this::findAndCacheFuelTime);
        } else if (fuelTimeSubs != null) {
            fuelTimeSubs.unsubscribe();
            fuelTimeSubs = null;
            readyForRecipe = false;
        }
    }

    protected void autoIO() {
        if (getOffsetTimer() % 5 == 0) {
            if (isWorkingEnabled()) {
                if (io == IO.IN) {
                    getInventory().importFromNearby(getFrontFacing());
                }
            }
            updateInventorySubscription();
        }
    }

    protected void findAndCacheFuelTime() {
        if (getOffsetTimer() % 5 == 0) {
            if (isFormed() && isWorkingEnabled()) {
                ItemStack fuelStack = getFuelItemStack();

                if (!fuelStack.isEmpty()) {
                    cachedFuelDuration = getFuelTime(fuelStack);
                    if (updateFuelStatus(fuelStack)) {
                        cachedFuelItem = fuelStack.getItem();
                    }
                }
            }
            updateInventorySubscription();
        }
    }

    private int getFuelTime(ItemStack fuelStack) {
        if (cachedFuelItem != null && fuelStack.getItem() == cachedFuelItem) {
            return cachedFuelDuration;
        }

        if (!fuelStack.isEmpty()) {
            Item fuelItem = fuelStack.getItem();

            cachedFuelItem = fuelItem;

            return (GTUtil.getItemBurnTime(fuelItem) / 4);
        }

        resetCachedRecipeData();
        return 0;
    }

    public ItemStack getFuelItemStack() {
        for (int i = 0; i < inventory.getSlots() - 1; i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (!itemStack.isEmpty()) {
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean updateFuelStatus(ItemStack fuelStack) {
        readyForRecipe = cachedFuelDuration > 0 && fuelStack.getCount() > 0;
        inventory.notifyListeners();
        return readyForRecipe;
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
        int rowSize = (int) Math.sqrt(getInventorySize(tier));
        SlotGroup group = new SlotGroup("fuel_inv", rowSize, 0, true);
        Grid grid = new Grid().coverChildren().margin(7, 5).gridOfSizeHeight(rowSize * rowSize, rowSize, (x, y, index) -> new ItemSlot().slot(
                SyncHandlers.itemSlot(inventory.storage, index).slotGroup(group).changeListener((oldStack, newStack, client, init) -> {
                }).accessibility(inventory.handlerIO.support(IO.IN), true)));
        mainWidget.child(grid.center());
    }

    public NotifiableItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public @Nullable Item getCachedFuel() {
        return cachedFuelItem;
    }

    @Override
    public Item getFuel() {
        return getFuelItemStack().getItem();
    }
}
