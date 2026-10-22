package net.terrafirmainfinity.core.common.machine.part;

import brachy.modularui.factory.PosGuiData;
import brachy.modularui.screen.UISettings;
import brachy.modularui.value.sync.BooleanSyncValue;
import brachy.modularui.value.sync.PanelSyncManager;
import brachy.modularui.widget.ParentWidget;
import brachy.modularui.widgets.slot.ItemSlot;
import brachy.modularui.widgets.slot.ModularSlot;
import brachy.modularui.widgets.slot.SlotGroup;
import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IMuiMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.notifiable.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.EnergyStack;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.api.sync_system.annotations.SyncToClient;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.part.MaintenanceHatchPartMachine;
import com.gregtechceu.gtceu.common.mui.GTGuiTextures;
import com.gregtechceu.gtceu.utils.ExtendedUseOnContext;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.gregtechceu.gtceu.utils.ISubscription;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.terrafirmainfinity.core.common.item.behavior.ElectrodeBehavior;
import net.terrafirmainfinity.core.common.ui.InfinityGuiTextures;
import org.jetbrains.annotations.Nullable;

public class ElectrodeHolderPartMachine extends TieredPartMachine implements IMuiMachine {
    @SaveField
    public final NotifiableItemStackHandler inventory;
    @SaveField
    @SyncToClient
    public boolean electrodeActivity;
    @SaveField
    @SyncToClient
    @Nullable
    public Material electrodeMaterial = null; // 0 = no electrode
    @Nullable
    protected TickableSubscription electrodeActivitySubs;
    @Nullable
    protected ISubscription electrodeInvSubs;

    public ElectrodeHolderPartMachine(BlockEntityCreationInfo info, int tier) {
        super(info, tier);
        this.inventory = attachTrait(new NotifiableItemStackHandler(1, IO.NONE, IO.NONE));
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            updateElectrodeSubscription();
            electrodeInvSubs = this.inventory.addChangedListener(this::onElectrodeInventoryChanged);
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (electrodeInvSubs != null) {
            electrodeInvSubs.unsubscribe();
        }
    }

    @Override
    public boolean canShared(MultiblockControllerMachine controller, String substructureName) {
        return false;
    }

    static int getBaseEfficiency() {
        return 100;
    }

    private void onElectrodeInventoryChanged() {
        var stack = getElectrodeStack();
        var electrodeBehavior = ElectrodeBehavior.getBehavior(stack);
        if (electrodeBehavior != null) {
            this.electrodeMaterial = electrodeBehavior.getPartMaterial(stack);
        } else {
            this.electrodeMaterial = null;
        }
        syncDataHolder.markClientSyncFieldDirty("electrodeMaterial");
    }

    public boolean hasElectrode() {
        return getElectrodeStack() != ItemStack.EMPTY;
    }

    protected void updateElectrodeSubscription() {
        if (isMachineWorking()) {
            electrodeActivitySubs = subscribeServerTick(electrodeActivitySubs, this::updateElectrodeActivity);
        } else if (electrodeActivitySubs != null) {
            electrodeActivitySubs.unsubscribe();
            electrodeActivitySubs = null;
        }
    }

    private void updateElectrodeActivity() {
        if (isMachineWorking()) {
            return;
        }
        if (!hasElectrode()) {
            setElectrodeActivity(false);
        }
        updateElectrodeSubscription();
    }

    public void setElectrodeActivity(boolean electrodeActivity) {
        this.electrodeActivity = electrodeActivity;
        syncDataHolder.markClientSyncFieldDirty("electrodeActivity");
    }

    @Override
    public boolean onWorking(WorkableMultiblockMachine controller) {
        if (getElectrodeActivity()) {
            updateElectrodeSubscription();
        }
        if (getOffsetTimer() % 20 == 0) {
            var numMaintenanceProblems = 0;
            for (var part : getControllers().first().getParts()) {
                if (part instanceof MaintenanceHatchPartMachine maintenance) {
                    numMaintenanceProblems = maintenance.getNumMaintenanceProblems();
                    break;
                }
            }
            damageElectrode(1 + numMaintenanceProblems);
        }
        return true;
    }

    public int getTierDifference() {
        if (isFormed() && getControllers().first() instanceof ITieredMachine tieredMachine) {
            return getTier() - tieredMachine.getTier();
        }
        return -1;
    }

    public ItemStack getElectrodeStack() {
        return inventory.getStackInSlot(0);
    }

    public void setElectrodeStack(ItemStack electrodeStack) {
        inventory.setStackInSlot(0, electrodeStack);
        inventory.onContentsChanged();
    }

    public boolean isMachineWorking() {
        if (isFormed() && getControllers().first() instanceof WorkableMultiblockMachine machineController) {
            return machineController.getRecipeLogic().isWorking();
        } else return false;
    }

    public @Nullable EnergyStack getRecipeInputEUt() {
        if (isMachineWorking() && getControllers().first() instanceof WorkableElectricMultiblockMachine electricMachine) {
            @Nullable GTRecipe recipe = electricMachine.getRecipeLogic().getLastRecipe();
            if (recipe != null) {
                return recipe.getInputEUt();
            }
        }
        return null;
    }

    @Override
    public InteractionResult onUse(ExtendedUseOnContext context) {
        var superResult = super.onUse(context);
        if (superResult != InteractionResult.PASS) return superResult;
        if (!isRemote() && getElectrodeActivity() && !context.getPlayer().isCreative()) {
            EnergyStack energyStack = getRecipeInputEUt();
            if (energyStack != null) {
                float damageAmount = (float) ((GTUtil.getTierByVoltage(energyStack.voltage()) + 1) * energyStack.amperage() * 4);
                context.getPlayer().hurt(level.damageSources().source(GTDamageTypes.ELECTRIC), damageAmount);
            }
            return InteractionResult.FAIL;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void buildMainUI(ParentWidget<?> mainWidget, PosGuiData guiData, PanelSyncManager syncManager, UISettings settings) {
        var slot = new ItemSlot().slot(new ModularSlot(inventory.storage, 0)
                        .slotGroup(new SlotGroup("electrode", 1)))
                .background(GTGuiTextures.SLOT, InfinityGuiTextures.ELECTRODE_OVERLAY_1)
                .center();

        var electrodeSync = new BooleanSyncValue(this::getElectrodeActivity);
        electrodeSync.setChangeListener(() -> {
            boolean canEdit = !electrodeSync.getBoolValue();
            slot.getSlot().accessibility(canEdit, canEdit);
        });
        syncManager.syncValue("electrode_activity", electrodeSync);
        mainWidget.child(slot);
    }

    @Override
    public @Nullable GTRecipe modifyRecipe(GTRecipe recipe) {
        if (!hasElectrode()) {
            return null;
        }
        return super.modifyRecipe(recipe);
    }

    public int getTotalEfficiency() {
        int electrodeEfficiency = getElectrodeEfficiency();
        if (electrodeEfficiency == -1) return -1;
        int holderEfficiency = getHolderEfficiency();
        if (holderEfficiency == -1) return -1;
        return Math.max(getBaseEfficiency(), electrodeEfficiency * holderEfficiency / 100);
    }

    public int getHolderEfficiency() {
        int tierDifference = getTierDifference();
        if (tierDifference == -1) return -1;
        return 100 + 10 * tierDifference;
    }

    public int getElectrodeEfficiency() {
        var stack = getElectrodeStack();
        var behavior = ElectrodeBehavior.getBehavior(stack);
        if (behavior != null) {
            return behavior.getElectrodeEfficiency(stack);
        }
        return -1;
    }

    public int getElectrodeDurabilityPercent() {
        var stack = getElectrodeStack();
        var behavior = ElectrodeBehavior.getBehavior(stack);
        if (behavior != null) {
            return behavior.getElectrodeDurabilityPercent(stack);
        }
        return -1;
    }

    public void damageElectrode(int damageAmount) {
        var stack = getElectrodeStack();
        var behavior = ElectrodeBehavior.getBehavior(stack);
        if (behavior != null) {
            behavior.applyElectrodeDamage(stack, damageAmount);
            setElectrodeStack(stack);
        }
    }

    public boolean getElectrodeActivity() {
        return this.electrodeActivity;
    }
}
