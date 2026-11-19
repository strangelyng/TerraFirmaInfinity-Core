package net.terrafirmainfinity.core.common.machine.multiblock;

import brachy.modularui.api.widget.IWidget;
import brachy.modularui.value.sync.BooleanSyncValue;
import brachy.modularui.value.sync.IntSyncValue;
import brachy.modularui.value.sync.PanelSyncManager;
import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import net.terrafirmainfinity.core.common.machine.multiblock.multi.IFueledMultiblock;
import net.terrafirmainfinity.core.common.machine.part.FuelPartMachine;
import net.terrafirmainfinity.core.common.machine.trait.FuelTrait;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WorkableFueledElectricMultiblockMachine extends WorkableElectricMultiblockMachine implements IFueledMultiblock {
    @SaveField
    protected FuelTrait fuelTrait;

    public WorkableFueledElectricMultiblockMachine(BlockEntityCreationInfo info) {
        super(info);
        this.fuelTrait = attachTrait(new FuelTrait());
    }

    @Override
    public void onPartUnload() {
        super.onPartUnload();
        fuelTrait.resetBasicInfo();
    }

    public @Nullable FuelPartMachine<?> getFuelInput() {
        for (MultiblockPartMachine part : getParts()) {
            if (part instanceof FuelPartMachine<?> fuelPart) {
                return fuelPart;
            }
        }
        return null;
    }

    @Override
    public List<IWidget> getWidgetsForDisplay(PanelSyncManager syncManager) {
        BooleanSyncValue liquidFueled = new BooleanSyncValue(fuelTrait::isLiquidFueled);
        syncManager.syncValue("liquidFueled", liquidFueled);
        IntSyncValue burnTime = new IntSyncValue(fuelTrait::getBurnTime);
        syncManager.syncValue("burnTime", burnTime);
        IntSyncValue fuelDuration = new IntSyncValue(fuelTrait::getFuelDuration);
        syncManager.syncValue("fuelDuration", fuelDuration);
        IntSyncValue fuelConsumption = new IntSyncValue(fuelTrait::getFuelConsumption);
        syncManager.syncValue("fuelConsumption", fuelConsumption);
        List<IWidget> widgets = new ArrayList<>();
        widgets.add(FuelTrait.addBurnTimeLine(fuelTrait, syncManager));
        widgets.add(FuelTrait.addFuelRateLine(fuelTrait, syncManager));
        widgets.addAll(super.getWidgetsForDisplay(syncManager));
        return widgets;
    }
}
