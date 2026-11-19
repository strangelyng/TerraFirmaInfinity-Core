package net.terrafirmainfinity.core.common.machine.part;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.feature.IMuiMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import org.jetbrains.annotations.Nullable;

public abstract class FuelPartMachine<T> extends TieredIOPartMachine implements IMuiMachine {
    @SaveField
    protected int cachedFuelDuration;

    @SaveField
    protected int cachedFuelConsumption;

    @SaveField
    protected boolean readyForRecipe;

    public FuelPartMachine(BlockEntityCreationInfo info, int tier) {
        super(info, tier, IO.IN);
    }

    @Override
    public boolean canShared(MultiblockControllerMachine controller, String substructureName) {
        return false;
    }

    public abstract boolean consumeFuel(int fuelConsumption, boolean simulate);

    public boolean isReadyForRecipe() {
        return readyForRecipe;
    }

    public int getCachedFuelConsumption() {
        return cachedFuelConsumption;
    }

    public int getCachedFuelDuration() {
        return cachedFuelDuration;
    }

    public abstract @Nullable T getCachedFuel();

    public abstract T getFuel();
}
